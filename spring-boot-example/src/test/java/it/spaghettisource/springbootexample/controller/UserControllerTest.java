package it.spaghettisource.springbootexample.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import it.spaghettisource.springbootexample.rest.controller.UserController;
import it.spaghettisource.springbootexample.service.dto.CustomerRequest;
import it.spaghettisource.springbootexample.service.dto.UserRequest;
import it.spaghettisource.springbootexample.service.dto.UserResponse;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) //This will start our Spring Boot application and make it available for our test to perform requests to it.
public class UserControllerTest {

	
	private static final String ENDPOINT_URL = "/api/v1/user";
	
	@Value("${test.integration.rest.user}")
	private String AUTH_USER;
	@Value("${test.integration.rest.user}")	
	private String AUTH_PWD;	
	
    @Autowired
    UserController userController;
	
    @Autowired
    TestRestTemplate restTemplate;	//inject a test helper that’ll allow us to make HTTP requests to the locally running application

    @Test
	public void getAllUser_OK_Test() {
    	
    	ResponseEntity<UserResponse[]> response = restTemplate.withBasicAuth(AUTH_USER,AUTH_PWD).getForEntity(ENDPOINT_URL, UserResponse[].class);
    	UserResponse[] employees = response.getBody();
    	
		//asserts
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertTrue(employees.length>0);
				
	}
    
    @Test
	public void getUserById_OK_Test() {
	
    	//get by ID 1
    	Integer id = 1;
    	ResponseEntity<UserResponse> response = restTemplate.withBasicAuth(AUTH_USER,AUTH_PWD).getForEntity(ENDPOINT_URL+"/"+id, UserResponse.class);
    	UserResponse employees = response.getBody();
    	
		//asserts
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertTrue(employees.getId()!=null);
				
	}    
    
    @Test
	public void addUser_OK_Test() {
	
    	UserRequest request = new UserRequest("new-1", "new-1", "ADMIN");
		ResponseEntity<UserResponse> response = restTemplate.withBasicAuth(AUTH_USER,AUTH_PWD).postForEntity(ENDPOINT_URL, request, UserResponse.class);
		
		//asserts
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		
	}
    
    @Test
	public void updateUser_OK_Test() {
	
    	HttpHeaders headers = new HttpHeaders();
    	
    	Integer id = 2;
    	UserRequest request = new UserRequest("new-2", "new-2", "ADMIN");
    	String resourceUrl = ENDPOINT_URL+"/"+id;
    	
    	HttpEntity<UserRequest> requestUpdate = new HttpEntity<>(request, headers);
    	ResponseEntity<Void> response = restTemplate.withBasicAuth(AUTH_USER,AUTH_PWD).exchange(resourceUrl, HttpMethod.PUT, requestUpdate, Void.class);
		
		//asserts
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		
	}
    
    @Test
	public void deleteUser_OK_Test() {
	
    	HttpHeaders headers = new HttpHeaders();
    	
    	Integer id = 3;
    	String resourceUrl = ENDPOINT_URL+"/"+id;
    	HttpEntity<CustomerRequest> requestUpdate = new HttpEntity<>(null, headers);
    	ResponseEntity<Void> response = restTemplate.withBasicAuth(AUTH_USER,AUTH_PWD).exchange(resourceUrl, HttpMethod.DELETE, requestUpdate, Void.class);
		
		//asserts
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		
	}
	
    
    
}
