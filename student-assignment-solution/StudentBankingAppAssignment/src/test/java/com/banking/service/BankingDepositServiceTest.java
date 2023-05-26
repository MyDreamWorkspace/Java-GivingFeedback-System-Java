package com.banking.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.banking.account.AccountType;
import com.banking.exception.InvalidDepositAmount;
import com.banking.exception.ValidationException;
import com.banking.model.AccountModel;
import com.banking.model.TransactionModel;
import com.banking.model.UserModel;

class BankingDepositServiceTest{

	private BankingDepositSevice bankingService;
	private AccountService accountService;
	private AccountModel account;
	private UserModel user;
	
	@BeforeEach
	void setUp(){
		bankingService = new BankingDepositSevice();
		UserService userService = new UserService();
		user = new UserModel("testuser", "1234", "John", "Joy", 23);		
		try {
			user = userService.addNewUserDetails(user);
		} catch (ValidationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		accountService = new AccountService();
		account = new AccountModel(AccountType.SAVINGS, user, new Date());
		account = accountService.addNewAccount(account);
		
	}
	
	@Test
	void depositTest() {
		TransactionModel transaction = null;
		try {
			transaction = bankingService.deposit(account, new BigDecimal(100));
		} catch (InvalidDepositAmount e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(new BigDecimal(100), transaction.getTransactionAmount());
		assertNotEquals(0, transaction.getTransactionId());
	}
	
	@Test
	void depositNotEqualTest() {
		TransactionModel transaction = null;
		try {
			transaction = bankingService.deposit(account, new BigDecimal(100));
		} catch (InvalidDepositAmount e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertNotEquals(new BigDecimal(110), transaction.getTransactionAmount());
		assertNotEquals(0, transaction.getTransactionId());
	}
	
	@Test
	void depositZeroValueTest() {
		Exception exception = assertThrows(InvalidDepositAmount.class, () -> {
			bankingService.deposit(account, BigDecimal.ZERO);
		});

		String expectedMessage = "Amount should be greater than 0";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}
	
	@Test
	void depositNegativeValueTest() {
		Exception exception = assertThrows(InvalidDepositAmount.class, () -> {
			bankingService.deposit(account, new BigDecimal(-100));
		});

		String expectedMessage = "Amount should be greater than 0";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}
	
	@Test
	void depositNullTest() {
		TransactionModel transaction = null;
		try {
			transaction = bankingService.deposit(account, null);
		} catch (InvalidDepositAmount e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(null, transaction);
	}
	
	@Test
	void depositNullNotEqualTest() {
		TransactionModel transaction = null;
		try {
			transaction = bankingService.deposit(account, new BigDecimal(100));
		} catch (InvalidDepositAmount e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertNotEquals(new BigDecimal(110), transaction.getTransactionAmount());
	}	
	
	@Test
	void balanceTest() {
		try {
			bankingService.deposit(account, new BigDecimal(100));
		} catch (InvalidDepositAmount e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BigDecimal balance = bankingService.balance(account);
		assertEquals(new BigDecimal(100), balance);
		assertNotEquals(BigDecimal.ZERO, balance);
	}
	
	@Test
	void balanceNotEqualTest() {
		try {
			bankingService.deposit(account, new BigDecimal(100));
		} catch (InvalidDepositAmount e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BigDecimal balance = bankingService.balance(account);
		assertNotEquals(new BigDecimal(110), balance);
		assertNotEquals(BigDecimal.ZERO, balance);
	}
	
	@Test
	void balanceNullTest() {
		BigDecimal balance = bankingService.balance(null);
		assertEquals(null, balance);
	}
	
	@Test
	void balanceNullNotEqualTest() {
		BigDecimal balance = bankingService.balance(null);
		assertNotEquals(new BigDecimal(100), balance);
	}
}
