package in.brainupgrade.accountservice.posting.api;

import in.brainupgrade.accountservice.posting.domain.Posting;
import java.time.Instant;
import java.time.LocalDate;

public class PostingResponse {

    private final String postingId;
    private final String clientReference;
    private final String debitAccountId;
    private final String creditAccountId;
    private final long amountMinor;
    private final String currency;
    private final LocalDate valueDate;
    private final LocalDate settlementDate;
    private final String narrative;
    private final String status;
    private final Instant createdAt;

    public PostingResponse(Posting posting) {
        this.postingId = posting.getId();
        this.clientReference = posting.getClientReference();
        this.debitAccountId = posting.getDebitAccountId();
        this.creditAccountId = posting.getCreditAccountId();
        this.amountMinor = posting.getAmountMinor();
        this.currency = posting.getCurrency();
        this.valueDate = posting.getValueDate();
        this.settlementDate = posting.getSettlementDate();
        this.narrative = posting.getNarrative();
        this.status = posting.getStatus().name();
        this.createdAt = posting.getCreatedAt();
    }

    public String getPostingId() {
        return postingId;
    }

    public String getClientReference() {
        return clientReference;
    }

    public String getDebitAccountId() {
        return debitAccountId;
    }

    public String getCreditAccountId() {
        return creditAccountId;
    }

    public long getAmountMinor() {
        return amountMinor;
    }

    public String getCurrency() {
        return currency;
    }

    public LocalDate getValueDate() {
        return valueDate;
    }

    public LocalDate getSettlementDate() {
        return settlementDate;
    }

    public String getNarrative() {
        return narrative;
    }

    public String getStatus() {
        return status;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}
