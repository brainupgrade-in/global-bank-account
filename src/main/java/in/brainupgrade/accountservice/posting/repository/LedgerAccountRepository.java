package in.brainupgrade.accountservice.posting.repository;

import in.brainupgrade.accountservice.posting.domain.LedgerAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LedgerAccountRepository extends JpaRepository<LedgerAccount, String> {
}
