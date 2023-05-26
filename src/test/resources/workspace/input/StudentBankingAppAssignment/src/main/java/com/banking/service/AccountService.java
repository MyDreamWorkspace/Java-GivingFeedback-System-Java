package com.banking.service;

import java.util.Optional;

import com.banking.model.AccountModel;
import com.banking.repository.AccountDao;

public class AccountService {
	
	private AccountDao accountDao = new AccountDao();

	public Optional<AccountModel> getAccountByAccountNumber(Integer accountNumber) {
		return accountDao.getAccountByAccountNumber(accountNumber);
	}

	public AccountModel addNewAccount(AccountModel account) {
		return accountDao.addNewAccount(account);
	}

	public AccountModel updateAccount(AccountModel account) {
		return accountDao.updateAccount(account);
	}
}
