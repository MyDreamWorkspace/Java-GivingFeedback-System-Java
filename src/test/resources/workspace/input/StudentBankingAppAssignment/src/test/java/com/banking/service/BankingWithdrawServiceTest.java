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
import com.banking.exception.InvalidWithdrawAmount;
import com.banking.exception.ValidationException;
import com.banking.model.AccountModel;
import com.banking.model.TransactionModel;
import com.banking.model.UserModel;

class BankingWithdrawServiceTest{

	private BankingWithdrawSevice bankingService;
	private BankingDepositSevice bankingDepositService;
	private AccountService accountService;
	private AccountModel account;
	private UserModel user;
	
	@BeforeEach
	void setUp(){
		bankingService = new BankingWithdrawSevice();
		bankingDepositService = new BankingDepositSevice();
		accountService = new AccountService();
		account = new AccountModel(AccountType.SAVINGS, user, new Date());
		account = accountService.addNewAccount(account);
		UserService userService = new UserService();
		user = new UserModel("testuser", "1234", "John", "Joy", 23);		
		try {
			user = userService.addNewUserDetails(user);
			bankingDepositService.deposit(account, new BigDecimal(100));
		} catch (ValidationException | InvalidDepositAmount e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	@Test
	void withdrawTest() {
		TransactionModel transaction = null;
		try {
			transaction = bankingService.withdraw(account, new BigDecimal(100));
		} catch (InvalidWithdrawAmount e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(new BigDecimal(100), transaction.getTransactionAmount());
		assertNotEquals(0, transaction.getTransactionId());
	}
	
	@Test
	void withdrawNotEqualTest() {
		TransactionModel transaction = null;
		try {
			transaction = bankingService.withdraw(account, new BigDecimal(100));
		} catch (InvalidWithdrawAmount e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertNotEquals(new BigDecimal(110), transaction.getTransactionAmount());
		assertNotEquals(0, transaction.getTransactionId());
	}
	
	@Test
	void withdrawWithZeroBalanceTest() {
		Exception exception = assertThrows(InvalidWithdrawAmount.class, () -> {
			bankingService.withdraw(account, new BigDecimal(100));
			bankingService.withdraw(account, new BigDecimal(100));
		});

		String expectedMessage = "Insufficient balance available";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));		
	}
	
	@Test
	void withdrawZeroValueTest() {
		Exception exception = assertThrows(InvalidWithdrawAmount.class, () -> {
			bankingService.withdraw(account, BigDecimal.ZERO);
		});

		String expectedMessage = "Amount should be greater than 0";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}
	
	@Test
	void withdrawNegativeValueTest() {
		Exception exception = assertThrows(InvalidWithdrawAmount.class, () -> {
			bankingService.withdraw(account, new BigDecimal(-100));
		});

		String expectedMessage = "Amount should be greater than 0";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}
	
	@Test
	void withdrawNullTest() {
		TransactionModel transaction = null;
		try {
			transaction = bankingService.withdraw(account, null);
		} catch (InvalidWithdrawAmount e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(null, transaction);
	}
	
	@Test
	void withdrawNullNotEqualTest() {
		TransactionModel transaction = null;
		try {
			transaction = bankingService.withdraw(account, null);
		} catch (InvalidWithdrawAmount e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertNotEquals(new BigDecimal(100), transaction);
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
