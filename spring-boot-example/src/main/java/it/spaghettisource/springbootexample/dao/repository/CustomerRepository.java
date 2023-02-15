package it.spaghettisource.springbootexample.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.spaghettisource.springbootexample.dao.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer,Long> {

	
}
