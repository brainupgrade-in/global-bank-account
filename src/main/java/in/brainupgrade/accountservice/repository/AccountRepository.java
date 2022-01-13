package in.brainupgrade.accountservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import in.brainupgrade.accountservice.model.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

	/**
	 * Class used Account Repository
	 */

	/*
	 * method to get account from database
	 */
	@Query(nativeQuery = false, value = "SELECT a from Account a WHERE a.accountId = :accountId")
	Account findByAccountId(@Param(value = "accountId") long accountId);

	/*
	 * method to find customer from database
	 */
	List<Account> findByCustomerId(String customerId);

}
