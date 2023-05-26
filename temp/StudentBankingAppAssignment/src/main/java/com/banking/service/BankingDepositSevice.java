package com.banking.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.banking.exception.InvalidDepositAmount;
import com.banking.model.AccountModel;
import com.banking.model.TransactionModel;
import com.banking.repository.TransactionDao;
import com.banking.transaction.TransactionType;

public class BankingDepositSevice {

	private TransactionDao transactionDao = new TransactionDao();
	
	public TransactionModel deposit(AccountModel account, BigDecimal transactionAmount) throws InvalidDepositAmount {
		if(transactionAmount == null) {
			return null;
		}
		if(transactionAmount.compareTo(BigDecimal.ZERO) <= 0) {
			throw new InvalidDepositAmount("Amount should be greater than 0");
		}
		TransactionModel transaction = new TransactionModel(account.getAccountNumber(), new Date(), transactionAmount,
				TransactionType.DEPOSIT);
		transaction = transactionDao.addNewTransaction(transaction);
		return transaction;
		
	}	
	
	public BigDecimal balance(AccountModel account) {
		
		if(account == null) {
			return null;
		}
		
		List<TransactionModel> transactions = transactionDao.getTransactionsByAccountNumber(account.getAccountNumber());
		BigDecimal totalDeposit = BigDecimal.ZERO;
		BigDecimal totalWithdraw = BigDecimal.ZERO;
		for(TransactionModel transaction : transactions) {
			if(transaction.getTransactionType() == TransactionType.DEPOSIT) {
				totalDeposit = totalDeposit.add(transaction.getTransactionAmount());
			}
			
			if(transaction.getTransactionType() == TransactionType.WITHDRAW) {
				totalWithdraw = totalWithdraw.add(transaction.getTransactionAmount());
			}
		}
		return totalDeposit.subtract(totalWithdraw);		
	}
}
