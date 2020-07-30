package com.hervey.app.security;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.hervey.app.models.CustomUserDetails;
import com.hervey.app.models.User;
import com.hervey.app.services.UserService;

@Component
//If authentication is successful, then Success.
//ApplicationEventPublisher publishes an InteractiveAuthenticationSuccessEvent.
public class LoginListener implements ApplicationListener<AuthenticationSuccessEvent> {

	private Logger logger = LoggerFactory.getLogger(LoginListener.class);
	private UserService userService;

	public LoginListener(UserService userService) {
		this.userService = userService;
	}

	@Override
	public void onApplicationEvent(AuthenticationSuccessEvent event) {
		// This fires whenever Authentication was successful
		CustomUserDetails userDetails = (CustomUserDetails) event.getAuthentication().getPrincipal();

		String email = userDetails.getEmail();
		String loginTime = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);

		System.out.println("*** here is user's email" + email + " and he logged in at:  " + loginTime);

		// copy current to previous and store above as current

		userService.queInCurrentDate(userDetails);

	}

}
