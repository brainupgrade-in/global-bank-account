# ADR-009 — Posting contract changes are versioned and sequenced

**Status:** accepted · 2025-09-15

## Context

`global-bank-transaction` hand-maintains a mirror of our request type. A field added to the request in a
single release once broke the consumer's build after our deploy, because the two repositories were
merged in the wrong order.

## Decision

- Additive, optional fields may go into `/api/v1`. Anything a caller must now send, or any field that
  changes meaning or type, requires `/api/v2` alongside the existing version.
- Producer merges **first**, consumer second. The producer must accept both shapes for a compatibility
  window of at least one release before the old shape is withdrawn.
- The consumer's pull request links to the producer's. Neither merges without the other existing.

## Consequences

- There is always a release in which both shapes are accepted. That is intentional, not technical debt.
- Removing a field is a three-step change across two repositories, never a single edit.
