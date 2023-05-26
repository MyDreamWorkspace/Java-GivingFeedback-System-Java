package com.banking.account;

public enum AccountType {

	SAVINGS(100),
	CURRENT(200),
	SALARY(300),
	LOAN(400);
	
	private int accountTypeCode;

	AccountType(int accountTypeCode) {
		this.accountTypeCode = accountTypeCode;
	}

	public int getAccountTypeCode() {
		return accountTypeCode;
	}

}
