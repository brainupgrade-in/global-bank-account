package in.brainupgrade.accountservice.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class Account {

	/**
	 *  Account Entity used in Repository
	 */
	@Id
//	@NotNull(message = "Enter Account number")
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

}