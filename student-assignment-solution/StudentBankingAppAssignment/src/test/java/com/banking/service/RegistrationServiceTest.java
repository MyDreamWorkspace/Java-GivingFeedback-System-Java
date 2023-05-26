package com.banking.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.banking.exception.ValidationException;
import com.banking.model.UserModel;

class RegistrationServiceTest {
	
	private RegistrationService registrationService;

	@BeforeEach
	void setUp(){
		registrationService = new RegistrationService();		
	}
	
	@Test
	void saveTest() {
		UserModel user = new UserModel("testuser", "1234", "John", "Joy", 23);		
		UserModel userRes = null;
		try {
			userRes = registrationService.save(user);
		} catch (ValidationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(user, userRes);
	}
	
	@Test
	void saveInvalidUserNameTest() {
		UserModel user = new UserModel("", "1234", "John", "Joy", 23);		
		Exception exception = assertThrows(ValidationException.class, () -> {
			registrationService.save(user);
		});

		String expectedMessage = "Invalid Username";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}
	
	@Test
	void saveInvalidPasswordTest() {
		UserModel user = new UserModel("testuser", "", "John", "Joy", 23);		
		Exception exception = assertThrows(ValidationException.class, () -> {
			registrationService.save(user);
		});

		String expectedMessage = "Invalid Password";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}
	
	@Test
	void saveInvalidFirstNameTest() {
		UserModel user = new UserModel("testuser", "1234", "", "Joy", 23);		
		Exception exception = assertThrows(ValidationException.class, () -> {
			registrationService.save(user);
		});

		String expectedMessage = "Invalid First Name";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}
	
	@Test
	void saveInvalidLastNameTest() {
		UserModel user = new UserModel("testuser", "1234", "John", "", 23);		
		Exception exception = assertThrows(ValidationException.class, () -> {
			registrationService.save(user);
		});

		String expectedMessage = "Invalid Last Name";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}
	
	@Test
	void saveInvalidAgeTest() {
		UserModel user = new UserModel("testuser", "1234", "John", "Joy", 0);		
		Exception exception = assertThrows(ValidationException.class, () -> {
			registrationService.save(user);
		});

		String expectedMessage = "Invalid Age";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}
	
	@Test
	void saveNotEqualTest() {
		UserModel user = new UserModel("testuser", "1234", "John", "Joy", 23);		
		UserModel userRes = null;
		try {
			userRes = registrationService.save(user);
		} catch (ValidationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertNotEquals(new UserModel("testuser", "133", "John", "Joy", 23), userRes);
	}
	
	@Test
	void saveNullTest() {	
		UserModel userRes = null;
		try {
			userRes = registrationService.save(null);
		} catch (ValidationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(null, userRes);
	}
	
	@Test
	void saveNullNotEqualTest() {	
		UserModel userRes = null;
		try {
			userRes = registrationService.save(null);
		} catch (ValidationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertNotEquals(new UserModel("testuser", "1234", "John", "Joy", 23), userRes);
	}
}
