# Global Bank Account Service — posting API — working agreement

Double-entry payment posting for client money, in the `posting` package. The older account code
beside it (`controller/`, `service/`, `model/`) uses `double` and a stored balance and predates these
rules — do not copy its patterns.

Every request either produces a balanced pair of ledger entries or fails. There is no partial posting.

## Read these before proposing a change

- `docs/architecture.md` — what lives where, and the layering rule
- `docs/conventions.md` — how we write code here
- `docs/glossary.md` — what our nouns mean (they are not the obvious meanings)
- `docs/adr/` — decisions already made, and why. **Check for an existing ADR before designing anything.**

## Non-negotiables

- **Money is a `long` of minor units.** Never `double`, never `float`, never `BigDecimal`. See ADR-003.
- **Every posting writes exactly two `LedgerEntry` rows**, one DEBIT and one CREDIT, equal in amount
  and currency. A change that can produce one entry is a defect, not a feature.
- **Balances are derived from entries, never stored.** Do not add a balance column.
- **Constructor injection only.** No field injection, no `@Autowired` on fields, no setter injection.
- **No Lombok.** See ADR-005 — this is a deliberate removal, not an oversight.
- Cross-currency postings are rejected. This service does not convert.

## When you are unsure

Say so and ask. Do not invent an account id, a currency code, a status value or a limit —
the real ones are in `docs/glossary.md` and `src/main/resources/data.sql`.
Quote the file you are relying on when you propose a change.
