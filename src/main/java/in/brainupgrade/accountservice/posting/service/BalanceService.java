package in.brainupgrade.accountservice.posting.service;

import in.brainupgrade.accountservice.posting.domain.Direction;
import in.brainupgrade.accountservice.posting.domain.LedgerEntry;
import in.brainupgrade.accountservice.posting.repository.LedgerAccountRepository;
import in.brainupgrade.accountservice.posting.repository.LedgerEntryRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BalanceService {

    private final LedgerEntryRepository ledgerEntryRepository;
    private final LedgerAccountRepository ledgerAccountRepository;

    public BalanceService(LedgerEntryRepository ledgerEntryRepository,
                          LedgerAccountRepository ledgerAccountRepository) {
        this.ledgerEntryRepository = ledgerEntryRepository;
        this.ledgerAccountRepository = ledgerAccountRepository;
    }

    /**
     * Balance is derived from the entries, never stored. Credits increase, debits decrease.
     */
    @Transactional(readOnly = true)
    public long balanceMinorFor(String accountId) {
        ledgerAccountRepository.findById(accountId)
                .orElseThrow(() -> new UnknownAccountException(accountId));

        List<LedgerEntry> entries = ledgerEntryRepository.findByAccountId(accountId);
        long balance = 0L;
        for (LedgerEntry entry : entries) {
            if (entry.getDirection() == Direction.CREDIT) {
                balance += entry.getAmountMinor();
            } else {
                balance -= entry.getAmountMinor();
            }
        }
        return balance;
    }

    @Transactional(readOnly = true)
    public String currencyFor(String accountId) {
        return ledgerAccountRepository.findById(accountId)
                .orElseThrow(() -> new UnknownAccountException(accountId))
                .getCurrency();
    }
}
