package com.banking.service;

import com.banking.exception.ValidationException;
import com.banking.model.UserModel;

public class RegistrationService {

	private UserService userService = new UserService();
	
	public UserModel save(UserModel user) throws ValidationException {
		
		if(user == null) {
			return null;
		}
		
		userService.addNewUserDetails(user);
		
		return user;
		
	}
}
