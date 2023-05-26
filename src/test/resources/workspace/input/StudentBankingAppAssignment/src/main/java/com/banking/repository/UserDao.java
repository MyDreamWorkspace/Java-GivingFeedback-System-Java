package com.banking.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.banking.model.UserModel;

public class UserDao {

	public UserModel addNewUserDetails(UserModel user) {
		return InMemoryDB.addNewUser(user);
		
	}
	
	public UserModel updateUserDetails(UserModel user) {
		return InMemoryDB.updateUser(user);
		
	}
	
	public List<UserModel> populateAllUsers() {
		List<UserModel> userListRes = new ArrayList<>();

		Optional<List<UserModel>> userListOpt = InMemoryDB.getAllUsers();

		if (userListOpt.isEmpty()) {
			return userListRes;
		}

		List<UserModel> userList = userListOpt.get();

		for (int i = 0; i < userList.size(); i++) {
			userListRes.add(userList.get(i));
		}

		return userListRes;

	}
	
	public Optional<UserModel> populateUserDetailsByUserId(Integer userId) {
		return InMemoryDB.getUserById(userId);
		
	}
	
	public Optional<UserModel> populateUserDetailsByUsername(String username) {
		return InMemoryDB.getUserByUsername(username);
		
	}
	
	public void deleteUser(UserModel user) {
		InMemoryDB.deleteUser(user);;
		
	}
}
