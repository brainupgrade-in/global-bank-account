---
applyTo: "src/test/**"
---

# Tests

- **Slice tests, not `@SpringBootTest`.** Persistence-facing service tests use `@DataJpaTest` with
  `@Import` of the services under test. Controller tests use `@WebMvcTest` with the service mocked.
  Booting the whole context for a unit of logic is the thing we are avoiding.
- Assertions use AssertJ (`assertThat`, `assertThatThrownBy`). Do not mix in JUnit's `Assertions`.
- Test names are sentences describing the behaviour: `rejectsACrossCurrencyPosting`, not `testPost2`.
- Amounts in tests are written with underscores at the minor-unit boundary — `12_34L` is ₹12.34 —
  so a misplaced factor of 100 is visible on the page.
- A test that asserts only "no exception was thrown" is not a test. Assert the ledger effect.
