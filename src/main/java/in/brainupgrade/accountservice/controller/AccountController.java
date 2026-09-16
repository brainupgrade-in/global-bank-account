package in.brainupgrade.accountservice.controller;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import in.brainupgrade.accountservice.exceptionhandling.MinimumBalanceException;
import in.brainupgrade.accountservice.feignclient.TransactionFeign;
import in.brainupgrade.accountservice.model.Account;
import in.brainupgrade.accountservice.model.AccountCreationStatus;
import in.brainupgrade.accountservice.model.AccountInput;
import in.brainupgrade.accountservice.model.Transaction;
import in.brainupgrade.accountservice.model.TransactionInput;
import in.brainupgrade.accountservice.service.AccountServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.extern.slf4j.Slf4j;

/**
 * Account Controller
 * 
 * Every Method needs JWT Token to validate login user
 */
@RestController
@Slf4j
@CrossOrigin(origins = "*")
public class AccountController {

	@Autowired
	private AccountServiceImpl accountServiceImpl;

	@Autowired
	private TransactionFeign transactionFeign;

	/**
	 * To get the account Details
	 * 
	 * Pass the AccountInput Entity along with token
	 * 
	 * @param token
	 * @param accountId
	 * @return
	 */

	@GetMapping("/getAccount/{accountId}")
	@Operation(summary = "Find account by id", description = "Provide account id to get the specific account")
	public ResponseEntity<Account> getAccount(
			@Parameter(description = "Token for authentication", required = true) @RequestHeader("Authorization") String token,
			@Parameter(description = "Id of the account whose detail is to be retrieved", required = true) @PathVariable long accountId) {
		accountServiceImpl.hasPermission(token);
		Account accountReturnObject = accountServiceImpl.getAccount(accountId);
		log.info("Account Details Returned Sucessfully");
		return new ResponseEntity<>(accountReturnObject, HttpStatus.OK);
	}

	/**
	 * To create the account Details
	 * 
	 * This controller is called by Customer-MS for creating Account
	 * 
	 * @param token
	 * @param customerId
	 * @param account
	 * @return
	 */
	@PostMapping("/createAccount/{customerId}")
	@Operation(summary = "Create Account", description = "Create account by providing the customer id  and token in the header")
	public ResponseEntity<?> createAccount(
			@Parameter(description = "Token for authentication", required = true) @RequestHeader("Authorization") String token,
			@Parameter(description = "Id of customer", required = true) @PathVariable String customerId,
			@Parameter(description = "Account details for validation and creating account", required = true) @RequestBody Account account) {

		log.info(account.toString());
		log.info(customerId);
		accountServiceImpl.hasEmployeePermission(token);

		AccountCreationStatus returnObjAccountCreationStatus = accountServiceImpl.createAccount(customerId, account);

		if (returnObjAccountCreationStatus == null)
			return new ResponseEntity<>("Customer Creation Unsucessful", HttpStatus.NOT_ACCEPTABLE);

		log.info("Account Created Sucessfully");

		return new ResponseEntity<>(returnObjAccountCreationStatus, HttpStatus.CREATED);
	}

	/**
	 * To get the account Details
	 * 
	 * Pass the CustomerID along with token
	 * 
	 * @param token
	 * @param customerId
	 * @return
	 */
	@GetMapping("/getAccounts/{customerId}")
	@Operation(summary = "Get Account Details ", description = "Get all the account details of a particular customer")
	public ResponseEntity<List<Account>> getCustomerAccount(
			@Parameter(description = "token for authentication passed in header", required = true) @RequestHeader("Authorization") String token,
			@Parameter(description = "id of customer whose account details is to be retrieved", required = true) @PathVariable String customerId) {
		accountServiceImpl.hasPermission(token);
		log.info("Account List Returned");
		return new ResponseEntity<>(accountServiceImpl.getCustomerAccount(token, customerId), HttpStatus.OK);
	}

	/**
	 * To Deposit cash in the account
	 * 
	 * This controller is called by Account-Ms to Transaction-MS for Depositing in
	 * the Account
	 * 
	 * @param token
	 * @param accInput
	 * @return
	 */
	@PostMapping("/deposit")
	@Operation(summary = "Deposit Amount ", description = "To deposit cash in the account")
	public ResponseEntity<Account> deposit(
			@Parameter(description = "token for authentication passed in header", required = true) @RequestHeader("Authorization") String token,
			@Parameter(description = "Account details", required = true) @RequestBody AccountInput accInput) {
		accountServiceImpl.hasPermission(token);
		transactionFeign.makeDeposit(token, accInput);
		Account newUpdateAccBal = accountServiceImpl.updateDepositBalance(accInput);
		List<Transaction> list = transactionFeign.getTransactionsByAccId(token, accInput.getAccountId());
		newUpdateAccBal.setTransactions(list);
		log.info("amount deposited");
		return new ResponseEntity<>(newUpdateAccBal, HttpStatus.OK);
	}

	/**
	 * * To Withdraw cash in the account
	 * 
	 * This controller is called by Account-Ms to Transaction-MS for Withdrawing in
	 * the Account
	 * 
	 * @param token
	 * @param accInput
	 * @return
	 */
	@PostMapping("/withdraw")
	@Operation(summary = "Withdraw Amount ", description = "To withdraw cash from the account")
	public ResponseEntity<Account> withdraw(
			@Parameter(description = "token for authentication passed in header", required = true) @RequestHeader("Authorization") String token,
			@Parameter(description = "Account Details", required = true) @RequestBody AccountInput accInput) {
		accountServiceImpl.hasPermission(token);
		try {
			transactionFeign.makeWithdraw(token, accInput);

		} catch (Exception e) {
			throw new MinimumBalanceException("Minimum Balance 1000 should be maintaind");
		}
		Account newUpdateAccBal = accountServiceImpl.updateBalance(accInput);
		List<Transaction> list = transactionFeign.getTransactionsByAccId(token, accInput.getAccountId());
		newUpdateAccBal.setTransactions(list);
		log.info("amount withdraw sucessful");
		return new ResponseEntity<>(newUpdateAccBal, HttpStatus.OK);
	}

	/**
	 * To imply service charge on not maintaining minimum balance account
	 * 
	 * @param token
	 * @param accInput
	 * @return
	 */

	@PostMapping("/servicecharge")
	@Operation(summary = "Service Charge ", description = "Service Charge to be cut for not maintaining the minimum balance ")
	public ResponseEntity<Account> servicecharge(
			@Parameter(description = "token for authentication passed in header", required = true) @RequestHeader("Authorization") String token,
			@Parameter(description = "Account Details", required = true) @RequestBody AccountInput accInput) {
		accountServiceImpl.hasPermission(token);
		try {
			transactionFeign.makeServiceCharges(token, accInput);

		} catch (Exception e) {
			throw new MinimumBalanceException("Minimum Balance 1000 should be maintained");
		}
		Account newUpdateAccBal = accountServiceImpl.updateBalance(accInput);
		List<Transaction> list = transactionFeign.getTransactionsByAccId(token, accInput.getAccountId());
		newUpdateAccBal.setTransactions(list);
		log.info("amount service charged sucessful");
		return new ResponseEntity<>(newUpdateAccBal, HttpStatus.OK);
	}

	/**
	 * To Transaction of cash in the other account
	 * 
	 * This controller is called by Account-Ms to Transaction-MS for Transaction in
	 * the another Account
	 * 
	 * @param token
	 * @param transInput
	 * @return
	 */
	@PostMapping("/transaction")
	@Operation(summary = "Transfer amount ", description = "Transfers amount from source acc to target acc ")
	public ResponseEntity<String> transaction(
			@Parameter(description = "token for authentication passed in header", required = true) @RequestHeader("Authorization") String token,
			@Parameter(description = "details required for transfer", required = true) @RequestBody TransactionInput transInput) {
		
		log.info(transInput.toString());
		accountServiceImpl.hasPermission(token);
		boolean status = true;
		try {
			status = transactionFeign.makeTransfer(token, transInput);

		} catch (Exception e) {
			throw new MinimumBalanceException("Minimum Balance 1000 should be maintained");
		}
		if (status == false) {
			return new ResponseEntity<>("Transaction Failed", HttpStatus.FORBIDDEN);
		}
		Account updatedSourceAccBal = accountServiceImpl.updateBalance(transInput.getSourceAccount());
		List<Transaction> sourcelist = transactionFeign.getTransactionsByAccId(token,
				transInput.getSourceAccount().getAccountId());
		updatedSourceAccBal.setTransactions(sourcelist);

		Account updatedTargetAccBal = accountServiceImpl.updateDepositBalance(transInput.getTargetAccount());
		List<Transaction> targetlist = transactionFeign.getTransactionsByAccId(token,
				transInput.getTargetAccount().getAccountId());
		updatedTargetAccBal.setTransactions(targetlist);
		return new ResponseEntity<>(
				"Transaction Made Successfully From Source AccId" + transInput.getSourceAccount().getAccountId()
						+ " TO Target AccId " + transInput.getTargetAccount().getAccountId() + " ",
				HttpStatus.OK);
	}

	/*
	 * To Checking balance cash in the account
	 * 
	 * This controller is called by Customer-Ms for Checking Balance in the Account
	 */
	@PostMapping("/checkBalance")
	@Operation(summary = "Checks Balance ", description = "Checks balance in the account ")
	public ResponseEntity<Account> checkAccountBalance(
			@Parameter(description = "token for authentication passed in header", required = true) @RequestHeader("Authorization") String token,
			@Parameter(description = "Account Details", required = true) @Valid @RequestBody AccountInput accountInput) {
		accountServiceImpl.hasPermission(token);
		Account account = accountServiceImpl.getAccount(accountInput.getAccountId());
		return new ResponseEntity<>(account, HttpStatus.OK);
	}

	/*
	 * Get list of all accounts 
	 * 
	 */
	@GetMapping("/find")
	@Operation(summary = "Get All Accounts ", description = "Provides a list of all the accounts ")
	public ResponseEntity<List<Account>> getAllAccount(
			@Parameter(description = "token for authentication passed in header", required = true) @RequestHeader("Authorization") String token) {
		accountServiceImpl.hasPermission(token);
		List<Account> account = accountServiceImpl.getAllAccounts();
		return new ResponseEntity<>(account, HttpStatus.OK);
	}

}
