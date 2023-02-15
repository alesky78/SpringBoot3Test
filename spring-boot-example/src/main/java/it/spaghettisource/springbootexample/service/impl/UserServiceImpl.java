package it.spaghettisource.springbootexample.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.spaghettisource.springbootexample.dao.model.AppRole;
import it.spaghettisource.springbootexample.dao.model.AppUser;
import it.spaghettisource.springbootexample.dao.repository.AppRoleRepository;
import it.spaghettisource.springbootexample.dao.repository.AppUserRepository;
import it.spaghettisource.springbootexample.exception.ExceptionFactory;
import it.spaghettisource.springbootexample.service.UserService;
import it.spaghettisource.springbootexample.service.dto.UserRequest;
import it.spaghettisource.springbootexample.service.dto.UserResponse;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
	
	private final AppUserRepository userRepository;
	private final AppRoleRepository roleRepository;
	
	private final PasswordEncoder passwordEncoder;
	private final ExceptionFactory exceptionFactory;

	
	public UserServiceImpl(AppUserRepository userRepository,AppRoleRepository roleRepository,PasswordEncoder passwordEncoder,ExceptionFactory exceptionFactory) {
		super();
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.passwordEncoder = passwordEncoder;
		this.exceptionFactory = exceptionFactory;
	}
	
	@Override
	@Transactional(readOnly = true)	
	public List<UserResponse> findAllUser(){
		return userRepository.findAll().stream().map(user -> mapToCustomerCustomerResponse(user)).toList();
	}
	
	@Override
	@Transactional(readOnly = true)	
	public UserResponse findUserById(Long id){
		return userRepository.findById(id).map(customer -> mapToCustomerCustomerResponse(customer)).orElseThrow(() -> exceptionFactory.getEntityNotFound(id));
	}
	
	@Override
	public UserResponse addNewUser(UserRequest newUser) {
		
		AppRole role = roleRepository.findByName(newUser.getRole()).orElseThrow(() -> exceptionFactory.getEntityNotFound("AppRole", newUser.getRole()));
		
		AppUser user = new AppUser();
		user.setUsername(newUser.getUsername());
		user.setPassword(passwordEncoder.encode(newUser.getPassword()));
		user.setRole(role);
		
		userRepository.save(user);
		
		UserResponse response = new UserResponse();
		response.setId(user.getId());		
		response.setUsername(user.getUsername());
		response.setPassword(user.getPassword());
		response.setRole(role.getName());
	
		return response;
	}
	
	@Override
	public void updateUser(Long id,UserRequest newUser){
		AppUser entity = userRepository.findById(id).orElseThrow(() -> exceptionFactory.getEntityNotFound(id));
		
		AppRole role = roleRepository.findByName(newUser.getRole()).orElseThrow(() -> exceptionFactory.getEntityNotFound("AppRole", newUser.getRole()));
		
		entity.setUsername(newUser.getUsername());
		entity.setPassword(newUser.getPassword());
		entity.setRole(role);
		
		userRepository.save(entity);
	}
	
	@Override
	public void deleteUser(Long id) {
		userRepository.findById(id).orElseThrow(() -> exceptionFactory.getEntityNotFound(id));
		userRepository.deleteById(id);			
	}
	
	
	
	private UserResponse mapToCustomerCustomerResponse(AppUser user) {

		UserResponse response = new UserResponse();
		response.setId(user.getId());
		response.setUsername(user.getUsername());
		response.setPassword(user.getPassword());
		response.setRole(user.getRole().getName());
		
		return response;
	}
	
}
