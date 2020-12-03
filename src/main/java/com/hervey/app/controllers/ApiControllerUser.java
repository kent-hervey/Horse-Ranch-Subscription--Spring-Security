package com.hervey.app.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hervey.app.models.HorseRanch;
import com.hervey.app.models.Role;
import com.hervey.app.models.User;
import com.hervey.app.services.RanchService;
import com.hervey.app.services.UserService;
import com.hervey.app.validator.UserValidator;

@RestController
@RequestMapping("/api")
public class ApiControllerUser {
	
	private UserService userService;
	private UserValidator userValidator;
	
	public ApiControllerUser(UserService userService, UserValidator userValidator) {
		this.userService = userService;
		this.userValidator = userValidator;
	}
	

	// Fetch all roles
	@GetMapping("/roles")
	public ResponseEntity<List<Role>> showAllRoles() {
		System.out.println("top of showAllRoles");
		List<Role> roles = userService.fetchAllRoles();
		ResponseEntity<List<Role>> lists = ResponseEntity.ok(roles);

		return lists;
	}


	
	
}
