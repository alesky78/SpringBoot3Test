package it.spaghettisource.springbootexample.security;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import it.spaghettisource.springbootexample.dao.model.AppUser;

public class AppUserDetails implements UserDetails {

	
    private String username;
    private String password;
    private Collection<GrantedAuthority> authorization;
	
	public AppUserDetails(AppUser user) {
		super();
		username = user.getUsername();
		password = user.getPassword();
		authorization = user.getRole().getAuthorizations().stream().map(a -> new SimpleGrantedAuthority(a.getPermission())).collect(Collectors.toList()); 
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorization;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
