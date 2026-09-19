# ADR-003 — Amounts are held as `long` minor units

**Status:** accepted · 2024-11-18

## Context

An early version used `BigDecimal` for amounts. Two problems followed. Scale was inconsistent across
the wire, the database and the code, so equality comparisons behaved differently in each. And a
handful of call sites had quietly been written against `double` before `BigDecimal` arrived, which
produced a reconciliation break of a few paise across a month-end run — small enough that it was not
noticed for two cycles.

## Decision

All amounts are `long`, in the currency's minor units. ₹12.34 is `1234`.

- The wire contract uses `amountMinor`, so the unit is unambiguous to callers.
- `Money` in `support` holds the limit and formatting helpers. It is deliberately not a value object
  wrapping the amount — we tried that and the JPA mapping cost was not worth it.
- Currency is a separate three-letter field. There is no multi-currency amount type.

## Consequences

- No decimal type appears anywhere in this codebase. If you are reaching for one, you are about to
  reintroduce this bug.
- Division needs an explicit rounding decision at the call site. We have not needed one yet; when we
  do it gets its own ADR.
