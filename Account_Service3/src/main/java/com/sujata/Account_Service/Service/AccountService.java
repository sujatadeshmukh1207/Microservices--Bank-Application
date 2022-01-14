package com.sujata.Account_Service.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sujata.Account_Service.Model.Account;
import com.sujata.Account_Service.Repository.AccountRepository;

@Service
public class AccountService {
	@Autowired
	private AccountRepository accountRepository;

	// save Details
	public Account saveAccount(Account account) {
		return accountRepository.save(account);
	}

	// Get AccountDetails
	public Account findAccountById(int accountId) {
		return accountRepository.findByAccountId(accountId);
	}

	// Get Balance
	public int getBalance(int accountId) {
		return accountRepository.findBalanceByaccountId(accountId);
	}

	// Deposit amount
	public void depositAmount(int accountId, int amount) {
		accountRepository.saveBalanceByaccountId(accountId, amount);
	}

	// Withdraw amount
	public void withdrawAmount(int accountId, int amount) {
		accountRepository.withdrawAmountByaccountId(accountId, amount);
	}

	// Delete Account
	public void deleteAccount(int accountId) {
		accountRepository.deleteById(accountId);
	}
}
