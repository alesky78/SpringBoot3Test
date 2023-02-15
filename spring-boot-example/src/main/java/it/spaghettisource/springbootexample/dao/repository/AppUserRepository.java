package it.spaghettisource.springbootexample.dao.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import it.spaghettisource.springbootexample.dao.model.AppUser;


public interface AppUserRepository  extends JpaRepository<AppUser,Long> {

	
	Optional<AppUser> findByUsername(String username);

}
