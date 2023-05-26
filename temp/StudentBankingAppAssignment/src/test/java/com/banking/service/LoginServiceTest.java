package com.banking.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.banking.exception.ValidationException;
import com.banking.model.UserModel;

class LoginServiceTest {

	@BeforeEach
	void setUp(){
		RegistrationService registrationService = new RegistrationService();
		UserModel user = new UserModel("testuser", "1234", "John", "Joy", 23);		
		try {
			user = registrationService.save(user);
		} catch (ValidationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	void loginTest1() {
		LoginService loginService = new LoginService();
		boolean isLoggedIn = loginService.login(new UserModel("testuser", "1234", null, null, 0));
		assertEquals(true, isLoggedIn);
	}
	
	@Test
	void loginNotEqualTest1() {
		LoginService loginService = new LoginService();
		boolean isLoggedIn = loginService.login(new UserModel("testuser", "122", null, null, 0));
		assertNotEquals(true, isLoggedIn);
	}
	
	@Test
	void loginNullTest1() {
		LoginService loginService = new LoginService();
		boolean isLoggedIn = loginService.login(null);
		assertEquals(false, isLoggedIn);
	}
	
	@Test
	void loginNullNotEqualTest1() {
		LoginService loginService = new LoginService();
		boolean isLoggedIn = loginService.login(null);
		assertNotEquals(true, isLoggedIn);
	}
	
	@Test
	void loginTest2() {
		LoginService loginService = new LoginService();
		boolean isLoggedIn = loginService.login("testuser", "1234");
		assertEquals(true, isLoggedIn);
	}
	
	@Test
	void loginNotEqualTest2() {
		LoginService loginService = new LoginService();
		boolean isLoggedIn = loginService.login("testuser", "122");
		assertNotEquals(true, isLoggedIn);
	}
	
	@Test
	void loginNullTest2() {
		LoginService loginService = new LoginService();
		boolean isLoggedIn = loginService.login(null, null);
		assertEquals(false, isLoggedIn);
	}
	
	@Test
	void loginNullNotEqualTest2() {
		LoginService loginService = new LoginService();
		boolean isLoggedIn = loginService.login(null, null);
		assertNotEquals(true, isLoggedIn);
	}
	
	@Test
	void loginInvalidUsernameTest() {
		LoginService loginService = new LoginService();
		boolean isLoggedIn = loginService.login("", "1234");
		assertEquals(false, isLoggedIn);
	}
	
	@Test
	void loginInvalidPasswordTest() {
		LoginService loginService = new LoginService();
		boolean isLoggedIn = loginService.login("testuser", "");
		assertEquals(false, isLoggedIn);
	}
}
