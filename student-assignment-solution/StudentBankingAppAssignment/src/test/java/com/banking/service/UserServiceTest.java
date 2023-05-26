package com.banking.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.banking.exception.ValidationException;
import com.banking.model.UserModel;

class UserServiceTest {
	
	private UserService userService;
	
	@BeforeEach
	void setUp(){
		userService = new UserService();
		
	}
	
	@Test
	void addNewUserDetailsTest() {
		UserModel user = new UserModel("testuser", "1234", "John", "Joy", 23);		
		UserModel userRes = null;
		try {
			userRes = userService.addNewUserDetails(user);
		} catch (ValidationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(user, userRes);
		assertNotEquals(0, userRes.getUserId());
	}
	
	@Test
	void addNewUserDetailsInvalidUsernameTest() {
		UserModel user = new UserModel("", "1234", "John", "Joy", 23);
		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.addNewUserDetails(user);
		});

		String expectedMessage = "Invalid Username";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}
	
	@Test
	void addNewUserDetailsInvalidPasswordTest() {
		UserModel user = new UserModel("testuser", "", "John", "Joy", 23);
		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.addNewUserDetails(user);
		});

		String expectedMessage = "Invalid Password";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}
	
	@Test
	void addNewUserDetailsInvalidFirstNameTest() {
		UserModel user = new UserModel("testuser", "1234", "", "Joy", 23);
		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.addNewUserDetails(user);
		});

		String expectedMessage = "Invalid First Name";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}
	
	@Test
	void addNewUserDetailsInvalidLastNameTest() {
		UserModel user = new UserModel("testuser", "1234", "John", "", 23);
		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.addNewUserDetails(user);
		});

		String expectedMessage = "Invalid Last Name";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}
	
	@Test
	void addNewUserDetailsInvalidAgeTest() {
		UserModel user = new UserModel("testuser", "1234", "John", "", 0);
		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.addNewUserDetails(user);
		});

		String expectedMessage = "Invalid Age";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	void updateUserDetailsTest() {
		UserModel user = new UserModel(1, "testuser", "1234", "John", "Joy", 23);		
		UserModel userRes = null;
		try {
			userRes = userService.updateUserDetails(user);
		} catch (ValidationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(user, userRes);
		assertNotEquals(0, userRes.getUserId());
	}
	
	@Test
	void updateUserDetailsUsernameTest() {
		UserModel user = new UserModel("", "1234", "John", "Joy", 23);				
		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.updateUserDetails(user);
		});

		String expectedMessage = "Invalid Username";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}
	
	@Test
	void updateUserDetailsPasswordTest() {
		UserModel user = new UserModel("testuser", "", "John", "Joy", 23);				
		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.updateUserDetails(user);
		});

		String expectedMessage = "Invalid Password";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}
	
	@Test
	void updateUserDetailsFirstnameTest() {
		UserModel user = new UserModel("testuser", "1234", "", "Joy", 23);				
		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.updateUserDetails(user);
		});

		String expectedMessage = "Invalid First Name";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}
	
	@Test
	void updateUserDetailsLastNameTest() {
		UserModel user = new UserModel("testuser", "1234", "John", "", 23);				
		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.updateUserDetails(user);
		});

		String expectedMessage = "Invalid Last Name";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}
	
	@Test
	void updateUserDetailsAgeTest() {
		UserModel user = new UserModel("testuser", "1234", "John", "Joy", 0);				
		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.updateUserDetails(user);
		});

		String expectedMessage = "Invalid Age";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}
	
	@Test
	void findUserByUsernameTest() {
		UserModel user = new UserModel(1, "testuser", "1234", "John", "Joy", 23);		
		try {
			userService.addNewUserDetails(user);
		} catch (ValidationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String username = "testuser";
		UserModel userRes = userService.findUserByUsername(username).get();
		assertEquals(user, userRes);
		assertNotEquals(0, userRes.getUserId());
	}
	
	@Test
	void findUserByFirstnameTest() {
		UserModel user1 = new UserModel(1, "testuser", "1234", "Alex", "Rony", 29);
		UserModel user2 = new UserModel(2, "testuser", "1234", "John", "Joy", 23);	
		
		try {
			userService.addNewUserDetails(user1);
			userService.addNewUserDetails(user2);
		} catch (ValidationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String firstname = "John";
		Optional<UserModel> userModelOpt = userService.findUserByFirstname(firstname);
		assertEquals(true, userModelOpt.isPresent());
		if(userModelOpt.isPresent()) {
			UserModel userRes = userModelOpt.get();
			assertEquals(user2, userRes);
			assertNotEquals(0, userRes.getUserId());
		}
	}
/*	
	@Test
	void findUserByFirstnameNotFoundTest() {
		UserModel user1 = new UserModel(1, "testuser", "1234", "Alex", "Rony", 29);
		
		try {
			userService.addNewUserDetails(user1);
		//	userService.addNewUserDetails(user2);
		} catch (ValidationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String firstname = "John";
		Optional<UserModel> userModelOpt = userService.findUserByFirstname(firstname);
		assertEquals(true, userModelOpt.isEmpty());
		
	}
	*/
	
	@Test
	void findUserByLastnameTest() {
		UserModel user = new UserModel(1, "testuser", "1234", "John", "Joy", 23);		
		try {
			userService.addNewUserDetails(user);
		} catch (ValidationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String lastname = "Joy";
		UserModel userRes = userService.findUserByLastname(lastname).get();
		assertEquals(user, userRes);
		assertNotEquals(0, userRes.getUserId());
	}	
	
	@Test
	void findUserByAgeTest() {
		UserModel user = new UserModel(1, "testuser", "1234", "John", "Joy", 23);		
		try {
			userService.addNewUserDetails(user);
		} catch (ValidationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int age = 23;
		UserModel userRes = userService.findUserByAge(age).get();
		assertEquals(user, userRes);
		assertNotEquals(0, userRes.getUserId());
	}
	
	@Test
	void findUserByIdTest() {
		UserModel user = new UserModel(1, "testuser", "1234", "John", "Joy", 23);		
		try {
			userService.addNewUserDetails(user);
		} catch (ValidationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int userId = 1;
		UserModel userRes = userService.findUserById(userId).get();
		assertEquals(user, userRes);
		assertEquals(userId, userRes.getUserId());
	}
	
	
	
}
