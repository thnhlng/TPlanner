package com.example.TPlanner;


import java.util.ArrayList;
import java.util.List;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;


import com.example.TPlanner.web.UserDetailServiceImpl;



@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled= true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	  
	@Autowired
	    private UserDetailServiceImpl userDetailsService;
	
	
	@Override
	protected void configure(HttpSecurity http)throws Exception {
	http
	.authorizeRequests()
		//.antMatchers("/").permitAll()
		.antMatchers("/signup", "/login", "/saveuser").permitAll()
		//.antMatchers("/team/**").hasRole("ADMIN")
		.anyRequest().authenticated()
		.and()
	.formLogin()
		.loginPage("/login")
		 .defaultSuccessUrl("/projects", true)
		.permitAll()
		.and()
	.logout()
		.permitAll();
	}
	
	
	 @Bean
	  @Override
	  public UserDetailsService userDetailsService() {
		  List<UserDetails> users = new ArrayList();
		  UserDetails user = User.withDefaultPasswordEncoder()
				  .username("user")
				  .password("user")
				  .roles("USER")
				  .build();
		  
		  users.add(user);
		  
		  user = User.withDefaultPasswordEncoder()
				  .username("admin")
				  .password("admin")
				  .roles("ADMIN", "USER")
				  .build();
		  
		  users.add(user);
		  
		return new InMemoryUserDetailsManager(users);
		  
	  
		  
	  }
	
	@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }
}
