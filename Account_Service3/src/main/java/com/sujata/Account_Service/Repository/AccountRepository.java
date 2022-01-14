package com.sujata.Account_Service.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sujata.Account_Service.Model.Account;

@Repository
public interface AccountRepository extends CrudRepository<Account, Integer>, JpaRepository<Account, Integer> {

	//Get Account Details
	Account findByAccountId(int accountId);

	//Check Balance
	@Query("select balance from Account where accountId = ?1")
	public int findBalanceByaccountId(int accountId);

	//Deposit Amount
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("update Account set balance = balance+?2 where accountId=?1")
	public void saveBalanceByaccountId(int accountId, int balance);

	// Withdraw amount
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("update Account set balance = balance-?2 where accountId=?1")
	public void withdrawAmountByaccountId(int accountId, int balance);

}
