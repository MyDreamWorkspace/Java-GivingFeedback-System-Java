package com.banking.model;

import java.math.BigDecimal;
import java.util.Date;

import com.banking.transaction.TransactionType;

public class TransactionModel {

	private int transactionId;
	private int accountNumber;
	private Date date;
	private BigDecimal transactionAmount;
	private TransactionType transactionType;

	public TransactionModel() {
		super();
	}

	public TransactionModel(int accountNumber, Date date, BigDecimal transactionAmount,
			TransactionType transactionType) {
		super();
		this.accountNumber = accountNumber;
		this.date = date;
		this.transactionAmount = transactionAmount;
		this.transactionType = transactionType;
	}
	
	public TransactionModel(int transactionId, int accountNumber, Date date, BigDecimal transactionAmount,
			TransactionType transactionType) {
		super();
		this.transactionId = transactionId;
		this.accountNumber = accountNumber;
		this.date = date;
		this.transactionAmount = transactionAmount;
		this.transactionType = transactionType;
	}

	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public BigDecimal getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(BigDecimal transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	public TransactionType getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + accountNumber;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TransactionModel other = (TransactionModel) obj;
		if (accountNumber != other.accountNumber)
			return false;
		return true;
	}

}
