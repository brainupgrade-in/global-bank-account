package in.brainupgrade.accountservice.model;

import jakarta.persistence.Id;

public class AccountCreationStatus {
	/**
	 * AccountCreationStatus for returning response
	 */
	@Id
	private long accountId;
	private String message;

	public AccountCreationStatus() {
	}

	/**
	 * Creates a new {@code AccountCreationStatus} instance.
	 *
	 * @param accountId AccountCreationStatus for returning response
	 * @param message
	 */
	public AccountCreationStatus(final long accountId, final String message) {
		this.accountId = accountId;
		this.message = message;
	}

	/**
	 * AccountCreationStatus for returning response
	 */
	public long getAccountId() {
		return this.accountId;
	}

	public String getMessage() {
		return this.message;
	}

	/**
	 * AccountCreationStatus for returning response
	 */
	public void setAccountId(final long accountId) {
		this.accountId = accountId;
	}

	public void setMessage(final String message) {
		this.message = message;
	}
}
