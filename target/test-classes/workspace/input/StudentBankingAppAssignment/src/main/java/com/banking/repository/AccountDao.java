package com.banking.repository;

import java.util.Optional;

import com.banking.model.AccountModel;

public class AccountDao {

	public Optional<AccountModel> getAccountByAccountNumber(Integer accountNumber) {
		return InMemoryDB.getAccountByAccountNumber(accountNumber);
	}

	public AccountModel addNewAccount(AccountModel account) {
		return InMemoryDB.addNewAccount(account);
	}

	public AccountModel updateAccount(AccountModel account) {
		return InMemoryDB.updateAccount(account);
	}

	public void deleteAccount(AccountModel account) {
		InMemoryDB.deleteAccount(account);
	}
}
