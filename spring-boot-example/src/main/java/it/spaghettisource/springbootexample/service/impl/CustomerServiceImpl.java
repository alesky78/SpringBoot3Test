package it.spaghettisource.springbootexample.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.spaghettisource.springbootexample.dao.model.Customer;
import it.spaghettisource.springbootexample.dao.repository.CustomerRepository;
import it.spaghettisource.springbootexample.exception.ExceptionFactory;
import it.spaghettisource.springbootexample.service.CustomerService;
import it.spaghettisource.springbootexample.service.dto.CustomerRequest;
import it.spaghettisource.springbootexample.service.dto.CustomerResponse;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

	private static final Logger log = LoggerFactory.getLogger(CustomerServiceImpl.class);
	
	private final CustomerRepository customerRepository;
	
	private final ExceptionFactory exceptionFactory;

	
	public CustomerServiceImpl(CustomerRepository customerRepository,ExceptionFactory exceptionFactory) {
		super();
		this.customerRepository = customerRepository;
		this.exceptionFactory = exceptionFactory;
	}
	
	@Override
	@Transactional(readOnly = true)	
	public List<CustomerResponse> findAllCustomer(){
		return customerRepository.findAll().stream().map(customer -> mapToCustomerCustomerResponse(customer)).toList();
	}

	@Override
	@Transactional(readOnly = true)	
	public CustomerResponse findCustomerById(Long id){
		return customerRepository.findById(id).map(customer -> mapToCustomerCustomerResponse(customer)).orElseThrow(() -> exceptionFactory.getEntityNotFound(id));
	}
	
	@Override
	public CustomerResponse addNewCustomer(CustomerRequest newCustomer) {
		Customer customer = new Customer();
		customer.setAge(newCustomer.getAge());
		customer.setEmail(newCustomer.getEmail());
		customer.setName(newCustomer.getName());
		
		customerRepository.save(customer);
		
		return mapToCustomerCustomerResponse(customer);
	}
	
	@Override
	public void updateCustomer(Long id,CustomerRequest newCustomer){
		Customer entity = customerRepository.findById(id).orElseThrow(() -> exceptionFactory.getEntityNotFound(id));
		
		entity.setAge(newCustomer.getAge());
		entity.setEmail(newCustomer.getEmail());
		entity.setName(newCustomer.getName());
		
		customerRepository.save(entity);
	}
	
	@Override
	public void deleteCustomer(Long id) {
		customerRepository.findById(id).orElseThrow(() -> exceptionFactory.getEntityNotFound(id));
		customerRepository.deleteById(id);			
	}
	
	
	
	private CustomerResponse mapToCustomerCustomerResponse(Customer customer) {

		CustomerResponse response = new CustomerResponse();
		response.setId(customer.getId());
		response.setAge(customer.getAge());
		response.setEmail(customer.getEmail());
		response.setName(customer.getName());		
		
		return response;
	}
	
}
