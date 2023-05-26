package com.banking.repository;

import java.util.List;
import java.util.Optional;

import com.banking.model.TransactionModel;

public class TransactionDao {

	public Optional<TransactionModel> getTransactionByTransactionId(Integer accountNumber) {
		return InMemoryDB.getTransactionByTransactionId(accountNumber);
	}
	
	public List<TransactionModel> getTransactionsByAccountNumber(int accountNumber) {
		return InMemoryDB.getTransactionsByAccountNumber(accountNumber);
	}

	public TransactionModel addNewTransaction(TransactionModel transaction) {
		return InMemoryDB.addNewTransaction(transaction);
	}
}
