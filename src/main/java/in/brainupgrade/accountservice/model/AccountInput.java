package in.brainupgrade.accountservice.model;

import jakarta.validation.constraints.NotNull;

public class AccountInput {
	/**
	 * Class used for inputing account info during transaction
	 */
	@NotNull(message = "Account number is mandatory")
	private long accountId;
	@NotNull(message = "Amount is mandatory")
	private double amount;

	/**
	 * Class used for inputing account info during transaction
	 */
	public long getAccountId() {
		return this.accountId;
	}

	public double getAmount() {
		return this.amount;
	}

	/**
	 * Class used for inputing account info during transaction
	 */
	public void setAccountId(final long accountId) {
		this.accountId = accountId;
	}

	public void setAmount(final double amount) {
		this.amount = amount;
	}

	public AccountInput() {
	}

	/**
	 * Creates a new {@code AccountInput} instance.
	 *
	 * @param accountId Class used for inputing account info during transaction
	 * @param amount
	 */
	public AccountInput(final long accountId, final double amount) {
		this.accountId = accountId;
		this.amount = amount;
	}
}
