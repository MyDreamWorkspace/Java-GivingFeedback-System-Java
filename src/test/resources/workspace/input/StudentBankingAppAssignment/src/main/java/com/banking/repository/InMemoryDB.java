package com.banking.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

import com.banking.model.AccountModel;
import com.banking.model.TransactionModel;
import com.banking.model.UserModel;

public class InMemoryDB {

	private static Map<Integer, UserModel> userMap = new TreeMap<>();
	private static Map<Integer, TransactionModel> transactionMap = new TreeMap<>();
	private static Map<Integer, AccountModel> accountMap = new TreeMap<>();

	private InMemoryDB() {
		
	}
	
	public static Optional<UserModel> getUserById(Integer userId) {
		if(userMap.containsKey(userId)) {
			return Optional.of(userMap.get(userId));
		}
		else {
			return Optional.empty();
		}
	}
	
	public static Optional<List<UserModel>> getAllUsers() {
		return Optional.of(new ArrayList<>(userMap.values()));
	}
	
	public static Optional<UserModel> getUserByUsername(String username) {

		for (UserModel user : userMap.values()) {
			if (user.getUsername().equalsIgnoreCase(username)) {
				return Optional.of(user);
			}
		}

		return Optional.empty();
	}

	public static UserModel addNewUser(UserModel user) {
		if(user.getUserId() == null || user.getUserId() == 0) {
			user.setUserId(1);
		}
		userMap.put(user.getUserId(), user);
		return user;
	}
	
	public static UserModel updateUser(UserModel user) {
		
		userMap.put(user.getUserId(), user);
		return user;
	}
	
	public static void deleteUser(UserModel user) {
		
		userMap.remove(user.getUserId());
	}
	
	// Transaction 
	

	public static Optional<TransactionModel> getTransactionByTransactionId(Integer transactionId) {
		if(transactionMap.containsKey(transactionId)) {
			return Optional.of(transactionMap.get(transactionId));
		}
		else {
			return Optional.empty();
		}
	}
	
	public static List<TransactionModel> getTransactionsByAccountNumber(int accountNumber) {

		List<TransactionModel> transactions = new ArrayList<TransactionModel>();
		for (TransactionModel transaction : transactionMap.values()) {
			if (transaction.getAccountNumber() == accountNumber) {
				transactions.add(transaction);
			}
		}

		return transactions;
	}

	public static TransactionModel addNewTransaction(TransactionModel transaction) {
		if(transaction.getTransactionId() == 0) {
			transaction.setTransactionId(transactionMap.size() + 1);
		}
		transactionMap.put(transaction.getTransactionId(), transaction);
		return transaction;
	}
	
	// Account
	
	public static Optional<AccountModel> getAccountByAccountNumber(Integer accountNumber) {
		if(accountMap.containsKey(accountNumber)) {
			return Optional.of(accountMap.get(accountNumber));
		}
		else {
			return Optional.empty();
		}
	}

	public static AccountModel addNewAccount(AccountModel account) {
		if(account.getAccountNumber() == 0) {
			account.setAccountNumber(accountMap.size() + 1);
		}
		accountMap.put(account.getAccountNumber(), account);
		return account;
	}
	
	public static AccountModel updateAccount(AccountModel account) {
		
		accountMap.put(account.getAccountNumber(), account);
		return account;
	}
	
	public static void deleteAccount(AccountModel account) {
		
		accountMap.remove(account.getAccountNumber());
	}
}
