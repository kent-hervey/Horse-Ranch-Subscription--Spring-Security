package com.hervey.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
//Spring Doc:  The WebSecurityConfig class is annotated with @EnableWebSecurity to enable Spring Security’s web security support and provide the Spring MVC integration.
@EnableWebSecurity(debug=true)
//Spring Doc on WebSecurityConfigureAdapter:  Provides a convenient base class for creating a WebSecurityConfigurer instance. The implementation allows customization by overriding methods.
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	private UserDetailsService userDetailsService;

	@Autowired
	public WebSecurityConfig(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	// .authorizeRequests(): Allows restricting access based upon the
	// HttpServletRequest
	// .antMatchers("/css/**", "/js/**, "/registration"): PathMatcher implementation
	// for Ant-style path patterns.
	@Override
	// Spring Doc: defines which URL paths should be secured and which should not.
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		
		
			.antMatchers("/css/**", "/js/**", "/registration", "/loginreg", "/ranches/entry").permitAll() 
			
				

				.antMatchers("/admin/**", "/admins/**").access("hasRole('ADMIN')")
				//routes not specified get access to all
				.antMatchers("/ranches/property-list", "/ranches/property-details-guest").hasAnyRole("ADMIN","GUEST")  //use /ranches/guests/**
				.antMatchers("/ranches/owners*").hasAnyRole("ADMIN","OWNER")
				//.antMatchers("/ranches/entry").hasAnyRole("ADMIN","OWNER","BROWSER", "GUEST")
				
				//.antMatchers("/ranches/property-list", "/ranches/property-details-guest").hasRole("GUEST")
				//.antMatchers("/ranches/owners-properties", "/ranches/property-details-owner").hasRole("OWNER")
				
				.anyRequest().authenticated().and()
				.formLogin().loginPage("/login").permitAll().and().logout().permitAll()
				
				.and().httpBasic();
		
//			/admin/whatever
//			/owners/whatever
//			/guests/whatever
//			/api/admin/whatever
//			/api/owners/whatever
//			/api/guests/whatever
	}

	//desired:
		//ranches/property-details-guest only shows for guests and maybe admins
		//ranches/property-list only shows for guest and maybe admins
		//ranches/owners-properties only shows for owners and maybe admins
		//ranches/property-details-owner only shows for owners and maybe admins
	
	
	
	// added //, "/ranches/owners-properties"
																												// "/loginreg"
																											// so SS
																											// allows
																											// that
																											// route to
																											// work
	
	
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder app) throws Exception {
		app.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
	}

}
