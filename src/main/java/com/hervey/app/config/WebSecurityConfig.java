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
		
		
			.antMatchers("/css/**", "/images/**", "/js/**", "/registration", "/loginreg", "/entry").permitAll() 
			
			//below turns off all security for trouble shooting
			//.antMatchers("/**").permitAll()

			// line below temp commented on 9/21/20
 				.antMatchers("/admin/**", "/admins/**").access("hasRole('ADMIN')")
				//routes not specified get access to all
				
				//GUEST should become just /guests-auth/**
				.antMatchers("/guests-auth/**").hasAnyRole("ADMIN","GUEST")  //use /ranches/guests/**
				
				 //OWNER should become just /ranches/** and /owners/**
				.antMatchers("/owners/**" /* , "/ranches/**",  "/ranches/owners**" */).hasAnyRole("ADMIN","OWNER") // 9/29/20 added the third parameter to prevent GUEST from accessing that route, changed the endpoint URL to have the xx temporarily....that solved, but then removing the xx
				
				.antMatchers("/browser/**").hasAnyRole("ADMIN","OWNER","BROWSER", "GUEST")
				
				//.antMatchers("/ranches/property-list", "/ranches/property-details-guest").hasRole("GUEST")
				//.antMatchers("/ranches/owners-properties", "/ranches/property-details-owner").hasRole("OWNER")
				
				
				//two lines below temp commented on 9/21/20
				.anyRequest().authenticated().and()
				.formLogin().loginPage("/login").permitAll().and().logout().permitAll()
				
				.and().httpBasic();
		http.csrf().disable();
		
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
