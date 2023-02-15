package it.spaghettisource.springbootexample.service;

import java.util.List;

import it.spaghettisource.springbootexample.service.dto.UserRequest;
import it.spaghettisource.springbootexample.service.dto.UserResponse;

public interface UserService {

	List<UserResponse> findAllUser();

	UserResponse findUserById(Long id);
	
	UserResponse addNewUser(UserRequest request);

	void updateUser(Long id, UserRequest request);

	void deleteUser(Long id);

}