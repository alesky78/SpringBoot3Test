package it.spaghettisource.springbootexample.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import it.spaghettisource.springbootexample.dao.repository.AppUserRepository;
import it.spaghettisource.springbootexample.service.impl.UserServiceImpl;



@Component
public class AppUserDetailServiceImpl implements UserDetailsService {

	private static final Logger log = LoggerFactory.getLogger(AppUserDetailServiceImpl.class);
	
	private final AppUserRepository appUserRepository;
	
	
	public AppUserDetailServiceImpl(AppUserRepository appUserRepository) {
		super();
		this.appUserRepository = appUserRepository;
	}

	@Override
	@Transactional(readOnly = true)	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return appUserRepository.findByUsername(username).map(AppUserDetails::new).orElseThrow(() -> new UsernameNotFoundException("user not found: "+ username));
	}
	
	

}
