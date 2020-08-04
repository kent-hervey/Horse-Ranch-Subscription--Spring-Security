package com.hervey.app.controllers;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hervey.app.models.HorseRanch;
import com.hervey.app.models.User;
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
	
	//For Owners to view
	//Show Owners page that lists property and other non personal information about owner who is logged in
	@GetMapping("/owners-properties")
	public String showOwnerProperties(Principal principal, Model model) {
		
		String email = principal.getName();
		System.out.println("and the logged in email is:  " + email);
		User user = userService.fetchByEmail(email);
		System.out.println("so the user is:  " + user);
		model.addAttribute("loggedInUser", user);
		
//		List<HorseRanch> horseRanches = ranchService.fetchAllRanches();
//		model.addAttribute("horseRanches", horseRanches);
		
		List<HorseRanch> horseRanchesForOwner = ranchService.fetchRanchesByOwner(user);
		model.addAttribute("horseRanchesThisOwner", horseRanchesForOwner);
		
		
		return "ranch/ownersPage.jsp";
	}
	
	
	
	
	//Show Create Horse Ranch Property Page
	@GetMapping("/owners-add-property")
	public String showAddProperty(@ModelAttribute("horseRanch") HorseRanch horseRanch, Principal principal, Model model) {
		
		String email = principal.getName();
		System.out.println("and the logged in email is:  " + email);
		User user = userService.fetchByEmail(email);
		System.out.println("so the user is:  " + user);
		model.addAttribute("loggedInUser", user);
		
		
		return "ranch/create-property.jsp";
		//return   "ranch/ownersPage.jsp";
		
	}
	
	//does action of creating Horse Ranch
	@PostMapping("/owners-add-property")
	public String createHorseRanch(@Valid @ModelAttribute("horseRanch") HorseRanch horseRanch, BindingResult result, Principal principal) {
		System.out.println("at top of createHorseRanch with horseRandh of " + horseRanch);

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		//User userDetails = (User) auth.getPrincipal();
		
		String email = principal.getName();
		
		User user = userService.fetchByEmail(email);
		
		System.out.println("and the principal's name is:  " + email);
		
		System.out.println("the logged in user is:  " + user);
		
		//horseRanch.setRanchOwner(userDetails);
		
		horseRanch.setRanchOwner((User) user);
		
		//now to add the User to this HorseRanch
		//>>>>
		
		ranchService.saveRanch(horseRanch);
		
		return "redirect:/ranches/owners-properties";   //take app user back to the owner ranch listing
	}
	
	
	
	
	
	//>>>Needs id added to make specific property
	//Show Ranch details for each owner for owner's view
	@GetMapping({"/owners-property-details", "/property-details-owner"})
	public String showPropertyDetailsOwner() {
		
		return "ranch/property-details-owner.jsp";
	}
	
	
	
	
	
	//End Owners
	
	
	//for Guests
	//Show Ranch Listing page that provides a table listing of ranches with link to details page so guests can subscribe
	@GetMapping("/property-list")
	public String showAllProperties(Principal principal, Model model) {
		String email = principal.getName();
		System.out.println("and the logged in email is:  " + email);
		User user = userService.fetchByEmail(email);
		System.out.println("so the user is:  " + user);
		model.addAttribute("loggedInUser", user);
		
		List<HorseRanch> horseRanches = ranchService.fetchAllRanches();
		model.addAttribute("horseRanches", horseRanches);
		
		
		
		return "ranch/property-list.jsp";
	}
	
	
	
	//>>>Needs id added to make specific property
	//Show Ranch details page after GUEST clicked to request so GUEST can decide to subscribe
	@GetMapping("/property-details-guest/{ranchId}")
	public String showPropertyDetails(@PathVariable("ranchId") Long ranchId, Model model) {
		
		return "ranch/property-details-guest.jsp";
	}
	
	//End Guests

	
	
	
	
	
	
	
}
