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

import it.spaghettisource.springbootexample.rest.controller.CustomerController;
import it.spaghettisource.springbootexample.service.dto.CustomerRequest;
import it.spaghettisource.springbootexample.service.dto.CustomerResponse;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) //This will start our Spring Boot application and make it available for our test to perform requests to it.
public class CustomerControllerTest {

	
	private static final String ENDPOINT_URL = "/api/v1/customer";
	
	@Value("${test.integration.rest.user}")
	private String AUTH_USER;
	@Value("${test.integration.rest.user}")	
	private String AUTH_PWD;	
	
    @Autowired
    CustomerController customerController;
	
    @Autowired
    TestRestTemplate restTemplate;	//inject a test helper that’ll allow us to make HTTP requests to the locally running application

    @Test
	public void getAllCustomer_OK_Test() {
    	
    	ResponseEntity<CustomerResponse[]> response = restTemplate.withBasicAuth(AUTH_USER,AUTH_PWD).getForEntity(ENDPOINT_URL, CustomerResponse[].class);
    	CustomerResponse[] employees = response.getBody();
    	
		//asserts
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertTrue(employees.length>0);
				
	}
    
    @Test
	public void getCustomerById_OK_Test() {
	
    	//get by ID 1
    	Integer id = 1;
    	ResponseEntity<CustomerResponse> response = restTemplate.withBasicAuth(AUTH_USER,AUTH_PWD).getForEntity(ENDPOINT_URL+"/"+id, CustomerResponse.class);
    	CustomerResponse employees = response.getBody();
    	
		//asserts
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertTrue(employees.getId()!=null);
				
	}    
    
    @Test
	public void addCustomer_OK_Test() {
	
    	CustomerRequest request = new CustomerRequest("name", "email", 18);
		ResponseEntity<CustomerResponse> response = restTemplate.withBasicAuth(AUTH_USER,AUTH_PWD).postForEntity(ENDPOINT_URL, request, CustomerResponse.class);
		
		//asserts
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		
	}
    
    @Test
	public void updateCustomer_OK_Test() {
	
    	HttpHeaders headers = new HttpHeaders();
    	
    	//update by ID 1
    	Integer id = 1;
    	CustomerRequest request = new CustomerRequest("update", "update", 0);
    	String resourceUrl = ENDPOINT_URL+"/"+id;
    	
    	HttpEntity<CustomerRequest> requestUpdate = new HttpEntity<>(request, headers);
    	ResponseEntity<Void> response = restTemplate.withBasicAuth(AUTH_USER,AUTH_PWD).exchange(resourceUrl, HttpMethod.PUT, requestUpdate, Void.class);
		
		//asserts
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		
	}
    
    @Test
	public void deleteCustomer_OK_Test() {
	
    	HttpHeaders headers = new HttpHeaders();
    	
    	//delete by ID 2
    	Integer id = 2;
    	String resourceUrl = ENDPOINT_URL+"/"+id;
    	HttpEntity<CustomerRequest> requestUpdate = new HttpEntity<>(null, headers);
    	ResponseEntity<Void> response = restTemplate.withBasicAuth(AUTH_USER,AUTH_PWD).exchange(resourceUrl, HttpMethod.DELETE, requestUpdate, Void.class);
		
		//asserts
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		
	}
	
    
    
}
