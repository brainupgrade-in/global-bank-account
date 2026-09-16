package in.brainupgrade.accountservice.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;

public class TransactionInput {
	/**
	 * Class used for inputing 2 account info during transfer amount
	 */
	private AccountInput sourceAccount;
	private AccountInput targetAccount;
	@Positive(message = "Transfer amount must be positive")
	@Min(value = 1, message = "Amount must be larger than 1")
	private double amount;
	private String reference;

	/**
	 * Class used for inputing 2 account info during transfer amount
	 */
	public AccountInput getSourceAccount() {
		return this.sourceAccount;
	}

	public AccountInput getTargetAccount() {
		return this.targetAccount;
	}

	public double getAmount() {
		return this.amount;
	}

	public String getReference() {
		return this.reference;
	}

	/**
	 * Class used for inputing 2 account info during transfer amount
	 */
	public void setSourceAccount(final AccountInput sourceAccount) {
		this.sourceAccount = sourceAccount;
	}

	public void setTargetAccount(final AccountInput targetAccount) {
		this.targetAccount = targetAccount;
	}

	public void setAmount(final double amount) {
		this.amount = amount;
	}

	public void setReference(final String reference) {
		this.reference = reference;
	}

	/**
	 * Creates a new {@code TransactionInput} instance.
	 *
	 * @param sourceAccount Class used for inputing 2 account info during transfer amount
	 * @param targetAccount
	 * @param amount
	 * @param reference
	 */
	public TransactionInput(final AccountInput sourceAccount, final AccountInput targetAccount, final double amount, final String reference) {
		this.sourceAccount = sourceAccount;
		this.targetAccount = targetAccount;
		this.amount = amount;
		this.reference = reference;
	}

	public TransactionInput() {
	}

	@java.lang.Override
	public java.lang.String toString() {
		return "TransactionInput(sourceAccount=" + this.getSourceAccount() + ", targetAccount=" + this.getTargetAccount() + ", amount=" + this.getAmount() + ", reference=" + this.getReference() + ")";
	}
}
