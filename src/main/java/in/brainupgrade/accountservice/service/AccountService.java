package in.brainupgrade.accountservice.service;

import java.util.List;

import in.brainupgrade.accountservice.model.Account;
import in.brainupgrade.accountservice.model.AccountCreationStatus;
import in.brainupgrade.accountservice.model.AccountInput;
import in.brainupgrade.accountservice.model.AuthenticationResponse;

public interface AccountService {
	
	/**
	 *  Interface used for Service offered in Account service
	 */
	

	public AccountCreationStatus createAccount(String customerId, Account account);

	public List<Account> getCustomerAccount(String token, String customerId);

	public Account getAccount(long accountId);

	public AuthenticationResponse hasPermission(String token);

	public AuthenticationResponse hasEmployeePermission(String token);

	public AuthenticationResponse hasCustomerPermission(String token);

	public Account updateDepositBalance(AccountInput accountInput);

	public Account updateBalance(AccountInput accountInput);
	
	public List<Account> getAllAccounts();

}
