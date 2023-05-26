package com.banking.service;

import java.util.List;
import java.util.Optional;

import com.banking.exception.ValidationException;
import com.banking.model.UserModel;
import com.banking.repository.UserDao;

public class UserService {

	private UserDao userDao = new UserDao();
	
	public UserModel addNewUserDetails(UserModel user) throws ValidationException {
		if(user.getAge() == 0) {
			throw new ValidationException("Invalid Age");
		}
		if(user.getFirstName() == null || user.getFirstName().length() == 0) {
			throw new ValidationException("Invalid First Name");
		}
		if(user.getLastName() == null || user.getLastName().length() == 0) {
			throw new ValidationException("Invalid Last Name");
		}
		if(user.getUsername() == null || user.getUsername().length() == 0) {
			throw new ValidationException("Invalid Username");
		}
		if(user.getPassword() == null || user.getPassword().length() == 0) {
			throw new ValidationException("Invalid Password");
		}
		return userDao.addNewUserDetails(user);
		
	}
	
	public UserModel updateUserDetails(UserModel user) throws ValidationException {
		if(user.getAge() == 0) {
			throw new ValidationException("Invalid Age");
		}
		if(user.getFirstName() == null || user.getFirstName().length() == 0) {
			throw new ValidationException("Invalid First Name");
		}
		if(user.getLastName() == null || user.getLastName().length() == 0) {
			throw new ValidationException("Invalid Last Name");
		}
		if(user.getUsername() == null || user.getUsername().length() == 0) {
			throw new ValidationException("Invalid Username");
		}
		if(user.getPassword() == null || user.getPassword().length() == 0) {
			throw new ValidationException("Invalid Password");
		}
		return userDao.updateUserDetails(user);
		
	}	
	
	public Optional<UserModel> findUserByUsername(String username) {
		List<UserModel> userList = userDao.populateAllUsers();
		for(int i=0; i < userList.size(); i++ ) {
			UserModel user = userList.get(i);
			if(user.getUsername().equalsIgnoreCase(username)) {
				return Optional.ofNullable(user);
			}
		}
		return Optional.empty();
	}
	
	public Optional<UserModel> findUserByFirstname(String firstname) {
		List<UserModel> userList = userDao.populateAllUsers();
		for(int i=0; i < userList.size(); i++ ) {
			UserModel user = userList.get(i);
			if(user.getFirstName().equalsIgnoreCase(firstname)) {
				return Optional.ofNullable(user);
			}
		}
		return Optional.empty();
		
	}
	
	public Optional<UserModel> findUserByLastname(String lastname) {
		List<UserModel> userList = userDao.populateAllUsers();
		for(int i=0; i < userList.size(); i++ ) {
			UserModel user = userList.get(i);
			if(user.getLastName().equalsIgnoreCase(lastname)) {
				return Optional.ofNullable(user);
			}
		}
		return Optional.empty();
		
	}
	
	public Optional<UserModel> findUserByAge(int age) {
		List<UserModel> userList = userDao.populateAllUsers();
		for(int i=0; i < userList.size(); i++ ) {
			UserModel user = userList.get(i);
			if(user.getAge() == age) {
				return Optional.ofNullable(user);
			}
		}
		return Optional.empty();
		
	}
	
	public Optional<UserModel> findUserById(int userId) {
		List<UserModel> userList = userDao.populateAllUsers();
		for(int i=0; i < userList.size(); i++ ) {
			UserModel user = userList.get(i);
			if(user.getUserId() == userId) {
				return Optional.ofNullable(user);
			}
		}
		return Optional.empty();
		
	}
	
	public void deleteUserByUserId(Integer userId) {
		Optional<UserModel> user = findUserById(userId);
		if(user.isPresent()) {
			userDao.deleteUser(user.get());
		}
		
	}
	
	public void deleteUser(UserModel user) {
		userDao.deleteUser(user);
		
	}
}
