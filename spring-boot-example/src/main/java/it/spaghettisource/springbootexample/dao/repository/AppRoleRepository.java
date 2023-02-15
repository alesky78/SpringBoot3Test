package it.spaghettisource.springbootexample.dao.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import it.spaghettisource.springbootexample.dao.model.AppRole;

public interface AppRoleRepository  extends JpaRepository<AppRole,Long> {

	Optional<AppRole> findByName(String roleName);
	
}
