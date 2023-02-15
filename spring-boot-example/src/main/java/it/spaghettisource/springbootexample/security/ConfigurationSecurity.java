package it.spaghettisource.springbootexample.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration 
@EnableWebSecurity
@EnableMethodSecurity
public class ConfigurationSecurity {
	

	/**
	 * in memory implementation, no more used, i have the implementation,see {@link#AppUserDetailServiceImpl} 
	 */
//	@Bean
//	public UserDetailsService userDetailsService(PasswordEncoder encoder) {
//		UserDetails admin = User.withUsername("admin").password(encoder.encode("admin")).authorities("VIEWER","EDITOR","USER_MANAGER").build();
//		UserDetails user = User.withUsername("user").password(encoder.encode("user")).authorities("VIEWER").build();
//		return new InMemoryUserDetailsManager(admin,user);
//	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		//auth on url based on rules
//		return http.csrf().disable()
//			   .authorizeHttpRequests().requestMatchers("/api/v1/customer").authenticated().and()
//			   .authorizeHttpRequests().requestMatchers("/api/v1/customer").authenticated().and().formLogin().and()				
//			   .authorizeHttpRequests().requestMatchers("/api/v1/customer/**").authenticated().and().formLogin().and()
//			   .authorizeHttpRequests().requestMatchers("/api/v1/gino/**").permitAll().and().formLogin().and()
//			   .build();
		
		//basic authentication
		return http.csrf().disable()
					.cors().and()
					//auth basic
					.authorizeHttpRequests().anyRequest().authenticated().and()
					.httpBasic(Customizer.withDefaults()).build();
					//no auth basic		
					//.authorizeHttpRequests().anyRequest().permitAll().and().build();
	}
	
	@Bean	
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationProvider authenticationProvider(AppUserDetailServiceImpl appUserDetailServiceImpl,PasswordEncoder passwordEncoder) {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(appUserDetailServiceImpl);
		authenticationProvider.setPasswordEncoder(passwordEncoder);
		
		return authenticationProvider; 
	}


}
