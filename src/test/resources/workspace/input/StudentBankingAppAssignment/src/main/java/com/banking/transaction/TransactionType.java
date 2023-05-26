package com.banking.transaction;

public enum TransactionType {

	DEPOSIT(100),
	WITHDRAW(200);
	
	private int transactionTypeCode;

	TransactionType(int transactionTypeCode) {
		this.transactionTypeCode = transactionTypeCode;
	}

	public int getTransactionTypeCode() {
		return transactionTypeCode;
	}

}
