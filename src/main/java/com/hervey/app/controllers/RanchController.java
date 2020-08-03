package com.hervey.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hervey.app.services.RanchService;
import com.hervey.app.services.UserService;
import com.hervey.app.validator.UserValidator;

@Controller
@RequestMapping("/ranches")
public class RanchController {

	private UserService userService;
	private UserValidator userValidator;
	public RanchService ranchService;

	public RanchController(UserService userService, UserValidator userValidator, RanchService ranchService) {
		this.userService = userService;
		this.userValidator = userValidator;
		this.ranchService = ranchService;
	}

	@GetMapping("/shouldfail")
	public String shouldFail() {
		return "shouldfail.jsp";
	}
	
	
	//Show Owners page that lists property and other non personal information about owner who is logged in
	@GetMapping("/owners-properties")
	public String showOwnerProperties() {
		
		return "ranch/ownersPage.jsp";
	}
	
	//Show Ranch Listing page that provides a table listing of ranches with link to details page so guests can subscribe
	@GetMapping("/property-list")
	public String showAllProperties() {
		
		return "ranch/property-list.jsp";
	}
	
	//>>>Needs id added to make specific property
	//Show Ranch details page after GUEST clicked to request so GUEST can decide to subscribe
	@GetMapping("/property-details-guest")
	public String showPropertyDetails() {
		
		return "ranch/property-details-guest.jsp";
	}
	
	//>>>Needs id added to make specific property
	//Show Ranch details for each owner for owner's view
	@GetMapping("/property-details-owner")
	public String showPropertyDetailsOwner() {
		
		return "ranch/property-details-owner.jsp";
	}
	
	
	
	
	
	
	
}
