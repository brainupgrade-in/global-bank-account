---
name: account-change
description: Implement a change to posting or balance behaviour in global-bank-account
metadata:
  version: "3"
  owner: payments-platform
---

# Before you write anything

1. Read `docs/adr/README.md` and open any ADR whose subject the ticket touches.
2. Quote the constraint you are relying on, naming the file it came from.
3. Show the current contents of any method you are about to change, before proposing the edit.

# Constraints that always apply

- Amounts are `long` minor units. Never `double`, `float` or `BigDecimal` — ADR-003.
- Every posting writes exactly two `LedgerEntry` rows, one DEBIT and one CREDIT, equal in amount.
- Balances are derived from entries. Do not add a stored balance column.
- Constructor injection only. No Lombok — ADR-005.
- Slice tests only: `@DataJpaTest` with `@Import`, or `@WebMvcTest`. Never `@SpringBootTest`.

# Output contract

- A service method, a controller method if the ticket needs one, and slice tests.
- Errors as `ProblemDetail` via the existing advice, with a stable kebab-case reason.
- No new dependencies without an ADR.
- Touch only the files listed in INPUTS. If you need another, stop and list it first.

# Done criteria

- `mvn test` is green, and the new test fails against the pre-change code.
- Every acceptance criterion on the ticket is met, or named as not met.

# Stop and ask — do not choose

- Two defensible readings of the acceptance criteria.
- An architectural choice with no ADR covering it.
- Any change to an existing ledger entry, or anything that removes data.
- An identifier, status value, currency code or limit you cannot find in the repository.
