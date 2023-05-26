package com.banking.model;

import java.util.Date;

import com.banking.account.AccountType;

public class AccountModel {

	private int accountNumber;
	private AccountType accountType;
	private UserModel user;
	private Date dateOpened;

	public AccountModel() {
		super();
	}
	
	public AccountModel(AccountType accountType, UserModel user, Date dateOpened) {
		super();
		this.accountType = accountType;
		this.user = user;
		this.dateOpened = dateOpened;
	}

	public AccountModel(int accountNumber, AccountType accountType, UserModel user, Date dateOpened) {
		super();
		this.accountNumber = accountNumber;
		this.accountType = accountType;
		this.user = user;
		this.dateOpened = dateOpened;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public AccountType getAccountType() {
		return accountType;
	}

	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}

	public UserModel getUser() {
		return user;
	}

	public void setUser(UserModel user) {
		this.user = user;
	}

	public Date getDateOpened() {
		return dateOpened;
	}

}
