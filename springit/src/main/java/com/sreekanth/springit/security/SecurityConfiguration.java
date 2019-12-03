package com.sreekanth.springit.security;

import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	private UserDetailsServiceImpl userDetailsService;
	

	public SecurityConfiguration(UserDetailsServiceImpl userDetailService) {
		this.userDetailsService = userDetailService;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService(userDetailsService);
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		//super.configure(http);
		http
		 	.authorizeRequests()
			 	.requestMatchers(EndpointRequest.to("info")).permitAll()
	            .requestMatchers(EndpointRequest.toAnyEndpoint()).hasRole("ADMIN")
	            .antMatchers("/actuator/").hasRole("ACTUATOR")
		 		.antMatchers("/").permitAll()
		 		.antMatchers("/h2-console/**").permitAll()
		 		.antMatchers("/link/submit").hasRole("USER")
		 	.and()
		 	.formLogin()
		 		.loginPage("/login")
		 		.permitAll()
		 		.usernameParameter("email")
		 	.and()
		 		.logout()
		 	.and()
		 		.rememberMe()
		 	.and()
		 		.csrf().disable()
		 		.headers().frameOptions().disable()
		 		;
		 	/*.and()
		 	.csrf().disable()
	        .headers().frameOptions().disable();*/
	}
	
	
	
}
