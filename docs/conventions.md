# Conventions

## Wiring

- Constructor injection, always. Fields are `private final`. No `@Autowired` anywhere — with a single
  constructor Spring does not need it.
- `@Service` on business classes, `@Component` on infrastructure, `@RestController` on controllers.

## Errors

- Every caller-visible failure is a subclass of `LedgerException`, carrying its own `HttpStatus` and a
  short kebab-case `reason`. The advice maps it; nothing else builds an error response.
- Reasons are stable strings — callers match on them. Renaming one is a breaking change.

## Naming

- Amount fields end in `Minor` so the unit is visible at the call site: `amountMinor`, `balanceMinor`.
- Account identifiers are strings shaped `ACC-<POOL>-<NNN>`, never numeric.
- `clientReference` is the caller's reference. `postingId` is ours. They are never interchanged.

## Style

- No Lombok (ADR-005). Getters are written out.
- No wildcard imports.
- Javadoc only where it says something the signature does not.
