# ADR-007 — Duplicate suppression keys on client reference + value date

**Status:** accepted · 2025-06-30 · **not yet implemented**

## Context

Upstream systems retry. A retried payroll or settlement instruction that posts twice is a client
money break, so we need to suppress duplicates. Two designs were considered.

**Rejected — an `Idempotency-Key` request header with a key store.** This is the conventional answer
and it does not work for us. The two systems that generate most of our volume are a mainframe batch
feed and a vendor payments gateway; **neither propagates a client-generated header** through their
retry path, and the gateway re-frames the request on retry so any header we asked for would be lost.
A header-based scheme would silently suppress nothing for our two largest callers while looking
correct in tests.

**Accepted — the natural key already on the instruction.** Every caller already sends a
`clientReference` that is stable across their own retries, because it is their instruction id.

## Decision

A posting is a duplicate of an existing posting when **both** the `clientReference` and the
`valueDate` match. Same reference on a different value date is a legitimate repeat instruction
(standing orders do this every month) and must be allowed through.

- Look it up with a repository finder on both fields. `PostingRepository` has a finder on
  `clientReference` alone, which is **not** sufficient on its own: add one that takes the value
  date too, rather than filtering in the service.
- On a detected duplicate, return the **original** posting with `200 OK` rather than creating a second
  one. Do not return an error: the caller retried in good faith and wants the posting id.
- Suppression is scoped to that pair only. Amount, narrative and the account ids are **not** part
  of the key, so none of them can make two instructions distinct.
- A match on the key is then handled one of two ways, and this is the part that is easy to get wrong:
  - **The rest of the instruction agrees** (both accounts, amount, currency). This is a true retry.
    Return the original posting with `200 OK`.
  - **The amount, the currency or either account differs.** The caller has reused one instruction id
    for a different payment, which is a caller defect. Reject with `409 Conflict`, naming the
    original posting id. Do not return the original, and do not post the new one: quietly merging
    two different payments under one reference is the client money break this ADR exists to stop.
- Narrative never makes that difference. It is free text, so a reworded resend is a retry.

## Consequences

- Requires a unique index on `(client_reference, value_date)` to close the concurrent-retry window.
  The check-then-insert alone is not sufficient under concurrency.
- `clientReference` becomes contractually significant. This must be stated in the API documentation
  before the change ships.
