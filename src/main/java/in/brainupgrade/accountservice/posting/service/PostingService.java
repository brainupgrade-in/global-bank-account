package in.brainupgrade.accountservice.posting.service;

import in.brainupgrade.accountservice.posting.domain.LedgerAccount;
import in.brainupgrade.accountservice.posting.domain.Direction;
import in.brainupgrade.accountservice.posting.domain.LedgerEntry;
import in.brainupgrade.accountservice.posting.domain.Posting;
import in.brainupgrade.accountservice.posting.repository.LedgerAccountRepository;
import in.brainupgrade.accountservice.posting.repository.LedgerEntryRepository;
import in.brainupgrade.accountservice.posting.repository.PostingRepository;
import in.brainupgrade.accountservice.posting.support.Money;
import java.time.LocalDate;
import java.util.UUID;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PostingService {

    private final PostingRepository postingRepository;
    private final LedgerEntryRepository ledgerEntryRepository;
    private final LedgerAccountRepository ledgerAccountRepository;

    public PostingService(PostingRepository postingRepository,
                          LedgerEntryRepository ledgerEntryRepository,
                          LedgerAccountRepository ledgerAccountRepository) {
        this.postingRepository = postingRepository;
        this.ledgerEntryRepository = ledgerEntryRepository;
        this.ledgerAccountRepository = ledgerAccountRepository;
    }

    @Transactional
    public Posting post(String clientReference, String debitAccountId, String creditAccountId,
                        long amountMinor, LocalDate valueDate, String narrative) {

        if (!Money.isPositive(amountMinor)) {
            throw new InvalidAmountException("Amount must be greater than zero");
        }
        if (!Money.isWithinPostingLimit(amountMinor)) {
            throw new InvalidAmountException(
                    "Amount exceeds the single-posting limit of "
                            + Money.format(Money.MAX_POSTING_MINOR, "minor units"));
        }
        if (debitAccountId.equals(creditAccountId)) {
            throw new InvalidAmountException("Debit and credit accounts must differ");
        }

        LedgerAccount debit = ledgerAccountRepository.findById(debitAccountId)
                .orElseThrow(() -> new UnknownAccountException(debitAccountId));
        LedgerAccount credit = ledgerAccountRepository.findById(creditAccountId)
                .orElseThrow(() -> new UnknownAccountException(creditAccountId));

        if (!debit.getCurrency().equals(credit.getCurrency())) {
            throw new CurrencyMismatchException(debit.getCurrency(), credit.getCurrency());
        }

        String currency = debit.getCurrency();
        LocalDate effectiveValueDate = valueDate != null ? valueDate : LocalDate.now();

        Posting posting = new Posting(UUID.randomUUID().toString(), clientReference,
                debitAccountId, creditAccountId, amountMinor, currency,
                effectiveValueDate, narrative);
        postingRepository.save(posting);

        ledgerEntryRepository.save(new LedgerEntry(posting.getId(), debitAccountId,
                Direction.DEBIT, amountMinor, currency, effectiveValueDate));
        ledgerEntryRepository.save(new LedgerEntry(posting.getId(), creditAccountId,
                Direction.CREDIT, amountMinor, currency, effectiveValueDate));

        return posting;
    }

    @Transactional(readOnly = true)
    public Posting findById(String postingId) {
        return postingRepository.findById(postingId)
                .orElseThrow(() -> new PostingNotFoundException(postingId));
    }
}
