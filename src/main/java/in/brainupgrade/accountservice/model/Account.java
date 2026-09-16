package in.brainupgrade.accountservice.model;

import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table
public class Account {
//	@NotNull(message = "Enter Account number")
	/**
	 * Account Entity used in Repository
	 */
	@Id
	private long accountId;
//	@NotBlank(message = "Enter customerId")
	private String customerId;
//	@NotNull(message = "Enter currentBalance")
	private double currentBalance;
//	@NotBlank(message = "Enter accountType")
	private String accountType;
//	@Column(length = 20)
//	@NotBlank(message = "Enter ownerName")
	private String ownerName;
	@Transient
	private List<Transaction> transactions;

	public Account() {
	}

	/**
	 * Creates a new {@code Account} instance.
	 *
	 * @param accountId Account Entity used in Repository
	 * @param customerId
	 * @param currentBalance
	 * @param accountType
	 * @param ownerName
	 * @param transactions
	 */
	public Account(final long accountId, final String customerId, final double currentBalance, final String accountType, final String ownerName, final List<Transaction> transactions) {
		this.accountId = accountId;
		this.customerId = customerId;
		this.currentBalance = currentBalance;
		this.accountType = accountType;
		this.ownerName = ownerName;
		this.transactions = transactions;
	}

	@java.lang.Override
	public java.lang.String toString() {
		return "Account(accountId=" + this.getAccountId() + ", customerId=" + this.getCustomerId() + ", currentBalance=" + this.getCurrentBalance() + ", accountType=" + this.getAccountType() + ", ownerName=" + this.getOwnerName() + ", transactions=" + this.getTransactions() + ")";
	}

	/**
	 * Account Entity used in Repository
	 */
	public long getAccountId() {
		return this.accountId;
	}

	public String getCustomerId() {
		return this.customerId;
	}

	public double getCurrentBalance() {
		return this.currentBalance;
	}

	public String getAccountType() {
		return this.accountType;
	}

	public String getOwnerName() {
		return this.ownerName;
	}

	public List<Transaction> getTransactions() {
		return this.transactions;
	}

	/**
	 * Account Entity used in Repository
	 */
	public void setAccountId(final long accountId) {
		this.accountId = accountId;
	}

	public void setCustomerId(final String customerId) {
		this.customerId = customerId;
	}

	public void setCurrentBalance(final double currentBalance) {
		this.currentBalance = currentBalance;
	}

	public void setAccountType(final String accountType) {
		this.accountType = accountType;
	}

	public void setOwnerName(final String ownerName) {
		this.ownerName = ownerName;
	}

	public void setTransactions(final List<Transaction> transactions) {
		this.transactions = transactions;
	}
}
