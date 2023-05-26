package com.banking.service;

import java.util.Optional;

import com.banking.model.UserModel;

public class LoginService {

	private UserService userService = new UserService();
	
	public boolean login(UserModel user) {
		if(user == null) {
			return false;
		}
		Optional<UserModel> userOpt = userService.findUserByUsername(user.getUsername());
		if(userOpt.isPresent()) {
			UserModel userFromDB = userOpt.get();
			if(userFromDB.getUsername().equalsIgnoreCase(user.getUsername())
					&& userFromDB.getPassword().equals(user.getPassword())) {
				return true;
			}
		}
		return false;
	}
	
	public boolean login(String username, String password) {
		Optional<UserModel> userOpt = userService.findUserByUsername(username);
		if(userOpt.isPresent()) {
			UserModel userFromDB = userOpt.get();
			if(userFromDB.getUsername().equalsIgnoreCase(username)
					&& userFromDB.getPassword().equals(password)) {
				return true;
			}
		}
		return false;
	}
}
