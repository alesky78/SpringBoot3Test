package it.spaghettisource.springbootexample.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.spaghettisource.springbootexample.dao.model.AppAuthorization;

public interface AppAuthorizationRepository  extends JpaRepository<AppAuthorization,Long> {

}
