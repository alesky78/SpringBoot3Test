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

import it.spaghettisource.springbootexample.rest.infrastructure.RestServiceExceptionHandler;
import it.spaghettisource.springbootexample.service.CustomerService;
import it.spaghettisource.springbootexample.service.dto.CustomerRequest;
import it.spaghettisource.springbootexample.service.dto.CustomerResponse;


@RestController
@RequestMapping("api/v1/customer")
public class CustomerController {

	private static final Logger log = LoggerFactory.getLogger(CustomerController.class);
	
	private final CustomerService customerService;
	
	public CustomerController(CustomerService customerService) {
		super();
		this.customerService = customerService;
	}

	
	@GetMapping()
	@PreAuthorize("hasAnyAuthority('VIEWER','EDITOR')")	
	public List<CustomerResponse> getAllCustomers(){
		return customerService.findAllCustomer();
	}
	
	
	@GetMapping("{customerID}")
	@PreAuthorize("hasAnyAuthority('VIEWER','EDITOR')")	
	public ResponseEntity<CustomerResponse> getCustomerById(@PathVariable("customerID") Long id){
		CustomerResponse response = customerService.findCustomerById(id);
		return ResponseEntity.ok(response);
		//return ResponseEntity.notFound().build();
	}
	
	
	@PostMapping
	@PreAuthorize("hasAuthority('EDITOR')")
	public ResponseEntity<CustomerResponse> createCustomer(@RequestBody CustomerRequest newCustomer) {
		CustomerResponse response = customerService.addNewCustomer(newCustomer);	
		return ResponseEntity.ok(response);
		
	}
	
	
	@PutMapping("{customerID}")
	@PreAuthorize("hasAuthority('EDITOR')")	
	public void updateCustomer(@PathVariable("customerID") Long id,@RequestBody CustomerRequest newCustomer) {
		customerService.updateCustomer(id, newCustomer);
	}
	
	
	@DeleteMapping("{customerID}")
	@PreAuthorize("hasAuthority('EDITOR')")
	public void deleteCustomer(@PathVariable("customerID") Long id) {
		customerService.deleteCustomer(id);
	}
	
}
