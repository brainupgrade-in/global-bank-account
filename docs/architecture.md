# Architecture

The posting API is the `posting` package inside the account service: Spring Boot over an H2 database
(in memory in every environment we run today).

The rest of this repository (`controller/`, `service/`, `model/`) is the older account code. It
stores `currentBalance` as a `double` and predates ADR-003. It is out of scope for posting work:
do not extend it, and do not copy its patterns into `posting`.

## Module map

All under `in.brainupgrade.accountservice.posting`.

| Package | Holds | May depend on |
|---|---|---|
| `api` | Controllers, request/response types, the exception advice | `service`, `domain` |
| `service` | Business rules, the exception hierarchy, transaction boundaries | `domain`, `repository` |
| `domain` | JPA entities and enums. No framework beyond JPA | nothing |
| `repository` | Spring Data interfaces only. No hand-written queries today | `domain` |
| `support` | Small stateless helpers (`Money`) | nothing |

**The layering rule:** dependencies point inward only. `domain` depends on nothing in this codebase.
A controller never touches a repository.

## Request path for a posting

`PostingController.create` → `PostingService.post` → validate amount and limit → load both accounts →
check currencies match → save `Posting` → save two `LedgerEntry` rows → return `PostingResponse`.

The transaction boundary is `PostingService`. It is the only place `@Transactional` appears.

## What is deliberately absent

- No idempotency or duplicate suppression is implemented yet. See **ADR-007** for the agreed design.
- No authentication. The service sits behind the platform gateway.
- No outbound calls from the posting API. (The older account code calls customer, auth and
  transaction; the posting package does not.)
