---
applyTo: "src/main/java/in/brainupgrade/accountservice/posting/domain/**"
---

# Domain layer

- Entities are JPA entities with a `protected` no-arg constructor for Hibernate and a public
  constructor that takes every required field. Objects are valid on construction.
- No setters for fields that must not change after creation. `Posting` exposes exactly one mutator,
  `markReversed()`, because reversal is the only legal state transition.
- The domain has no Spring annotations beyond JPA and knows nothing about HTTP, DTOs or repositories.
- `amountMinor` is always a `long`. If you find yourself wanting a decimal type here, read ADR-003
  before writing anything.
