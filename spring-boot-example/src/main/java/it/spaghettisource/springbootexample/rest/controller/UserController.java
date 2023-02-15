package it.spaghettisource.springbootexample.rest.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.spaghettisource.springbootexample.service.UserService;
import it.spaghettisource.springbootexample.service.dto.UserRequest;
import it.spaghettisource.springbootexample.service.dto.UserResponse;


@RestController
@RequestMapping("api/v1/user")
public class UserController {

	private static final Logger log = LoggerFactory.getLogger(UserController.class);
	
	private final UserService userService;
	
	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}

	
	@GetMapping()
	@PreAuthorize("hasAuthority('USER_MANAGER')")	
	public List<UserResponse> getAllUsers(){
		return userService.findAllUser();
	}
	
	
	@GetMapping("{userID}")
	@PreAuthorize("hasAuthority('USER_MANAGER')")	
	public ResponseEntity<UserResponse> getUserById(@PathVariable("userID") Long id){
		UserResponse response = userService.findUserById(id);
		return ResponseEntity.ok(response);
	}
	
	
	@PostMapping
	@PreAuthorize("hasAuthority('USER_MANAGER')")
	public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest request) {
		UserResponse response = userService.addNewUser(request);	
		return ResponseEntity.ok(response);
		
	}
	
	
	@PutMapping("{userID}")
	@PreAuthorize("hasAuthority('USER_MANAGER')")	
	public void updateUser(@PathVariable("userID") Long id,@RequestBody UserRequest request) {
		userService.updateUser(id, request);
	}
	
	
	@DeleteMapping("{userID}")
	@PreAuthorize("hasAuthority('USER_MANAGER')")
	public void deleteUser(@PathVariable("userID") Long id) {
		userService.deleteUser(id);
	}
	
}
