# ADR-005 — Lombok removed, and not to be reintroduced

**Status:** accepted · 2025-02-04

## Context

The codebase used Lombok for entity and DTO boilerplate. Three recurring costs: `@Data` on JPA
entities generated `equals`/`hashCode` over mutable and lazily-loaded fields, which produced
surprising behaviour in collections; the generated all-args constructors made parameter-order
mistakes invisible at the call site, and two amount/account transpositions reached review that way;
and the annotation processor broke twice on toolchain upgrades, blocking the build for a day each time.

We removed it over a sprint in early 2025. The removal is complete.

## Decision

Lombok is not a dependency of this project and will not be reintroduced. Getters, constructors and
`equals`/`hashCode` are written out where they are needed and omitted where they are not.

## Consequences

- Entity and DTO classes are longer. This is accepted and is not a problem to be solved.
- **Do not add `org.projectlombok` to the POM.** A change that reintroduces it will be rejected in
  review regardless of how much boilerplate it removes.
- If generated accessors are genuinely wanted, the conversation to have is about Java records for
  DTOs — not about bringing Lombok back.
