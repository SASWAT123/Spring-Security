package com.example.demo.securityConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private PasswordEncoderConfig passwordEncoderConfig;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/api/**").hasRole(ApplicationUserRole.STUDENT.name())	//Protects the api only for STUDENT roles
			.anyRequest()
			.authenticated()
			.and()
			.httpBasic();
	}

	@Override
	@Bean
	protected UserDetailsService userDetailsService() {
		UserDetails saswatUser = User.builder()
				.username("saswat")
				.password(passwordEncoderConfig.passwordEncoder().encode("password1")) //Encrypting password using BCrypt encoder
				.roles(ApplicationUserRole.STUDENT.name()) //ROLE_STUDENT
				.build();
		
		UserDetails adminUser = User.builder()
				.username("admin")
				.password(passwordEncoderConfig.passwordEncoder().encode("password123")) //Encrypting password using BCrypt encoder
				.roles(ApplicationUserRole.ADMIN.name()) //ROLE_STUDENT
				.build();
		
		return new InMemoryUserDetailsManager(saswatUser, adminUser);
				
	}
	
	

}
