package it.spaghettisource.springbootexample.service;

import java.util.List;

import it.spaghettisource.springbootexample.service.dto.CustomerRequest;
import it.spaghettisource.springbootexample.service.dto.CustomerResponse;

public interface CustomerService {

	List<CustomerResponse> findAllCustomer();

	CustomerResponse findCustomerById(Long id);

	CustomerResponse addNewCustomer(CustomerRequest request);

	void updateCustomer(Long id, CustomerRequest request);

	void deleteCustomer(Long id);

}