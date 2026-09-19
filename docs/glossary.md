# Glossary

Our nouns do not all mean what they mean elsewhere. These are the local meanings.

| Term | Here it means |
|---|---|
| **Posting** | One instruction that moves value between two accounts. Produces exactly two ledger entries. |
| **Ledger entry** | One side of a posting — a DEBIT or a CREDIT against a single account. |
| **Client reference** | The *originating system's* identifier for an instruction. Not unique in our data today. |
| **Posting id** | Our identifier, a UUID we mint. Returned as `postingId`. |
| **Value date** | The date the posting takes economic effect. Not the date we received it. |
| **Minor units** | The smallest denomination of the currency. ₹12.34 is `1234`. |
| **Reversal** | A status change on the original posting. We do **not** delete postings. |
| **Duplicate** | Same `clientReference` **and** same `valueDate`. See ADR-007 — this is narrower than it sounds. |
