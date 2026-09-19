---
applyTo: "src/main/java/in/brainupgrade/accountservice/posting/api/**"
---

# API layer

- Controllers are thin: validate, delegate to a service, map to a response type. No business rules,
  no repository access, no `@Transactional` on a controller method.
- Requests are validated with Jakarta Bean Validation annotations on the request type.
- Errors are never returned by hand. Throw a subclass of `LedgerException` with the right status and
  reason; `ApiExceptionHandler` turns it into an RFC 9457 `ProblemDetail`. If a new failure mode needs
  a new type, add one next to the others in `service/`.
- Response types are constructed from a domain object and expose getters only.
- The base path is `/api/v1`. A breaking change to a payload needs a new version, not a mutated one —
  and the consumer repository has to be sequenced with it. See ADR-009.
