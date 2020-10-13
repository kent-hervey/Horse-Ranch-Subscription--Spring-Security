package com.hervey.app.controllers;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hervey.app.models.HorseRanch;
import com.hervey.app.models.User;
import com.hervey.app.models.UserHorseRanch;
import com.hervey.app.services.RanchService;
import com.hervey.app.services.UserService;
import com.hervey.app.validator.HorseRanchValidator;
import com.hervey.app.validator.UserValidator;

@Controller
@RequestMapping("/ranches") //remove this and do owners and guests separately
public class RanchController {

	private UserService userService;
	private HorseRanchValidator horseRanchValidator;
	public RanchService ranchService;

	@Autowired //Required because there are two constuctors
	public RanchController(UserService userService, HorseRanchValidator horseRanchValidator, RanchService ranchService) {
		this.userService = userService;
		this.horseRanchValidator = horseRanchValidator;
		this.ranchService = ranchService;
	}
	
	public RanchController() {  //including this no arg second constrctor requires use of @Autowired
	}

	
	//For Browsers to view
	@GetMapping("/entry")
	public String showEntryPage() {
		System.out.println("top of showEntryPage method");
		
		return "ranch/entry.jsp";
	}
	
	
	//For Owners to view
	//Show Owners page that lists all his ranches and potentially other non-personal info about logged in owner
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
		
		horseRanchValidator.validate(horseRanch, result);
		
		if(result.hasErrors()) {
			System.out.println("\n>>>>>we had this error:  " + result.toString());
			return "ranch/create-property.jsp";
		}

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		//User userDetails = (User) auth.getPrincipal();
		String email = principal.getName();
		User user = userService.fetchByEmail(email);
		
		System.out.println("and the principal's name is:  " + email);
		System.out.println("the logged in user is:  " + user);
		
		horseRanch.setNumberAcres(horseRanch.getNumberAcres().replaceFirst("^0+(?!$)", "")); //deletes leading zeros
		
		horseRanch.setRanchOwner((User) user);
		
		Integer horseCapacityInput = horseRanch.getHorseCapacity();
		
		if(horseCapacityInput < 10){
			System.out.println("horse cpacity was entered as:  " + horseCapacityInput + "which is less than 2, so is bing changed to 11");
			horseRanch.setHorseCapacity(11);
		}
		else {
			System.out.println("horse capacity entered was not less than 2; it was:  " +  horseCapacityInput + "so it is not changed");
		}
		
		
		ranchService.saveRanch(horseRanch);
		
		return "redirect:/ranches/owners-properties";   //take app user back to the owner ranch listing
	}
	
	//Delete Horse Ranch
	@DeleteMapping("/{ranchId}")
	public String deleteRanch(@PathVariable("ranchId") Long ranchId) {
		System.out.println("at tope of delete Ranch.  incoming ranchId:  " + ranchId);
		ranchService.deleteRanchWithThisRanchId(ranchId);
		
		
		return "redirect:/ranches/owners-properties";
	}
	
	
	
	//Show Ranch details for each owner for owner's view
	@GetMapping({"/owners-property-details/{ranchId}"})
	public String showPropertyDetailsOwner(@PathVariable("ranchId") Long ranchId, Model model, Principal principal) {
		
		String email = principal.getName();
		System.out.println("and the logged in email is:  " + email);
		User user = userService.fetchByEmail(email);
		System.out.println("so the user is:  " + user);
		model.addAttribute("loggedInUser", user);
		
		HorseRanch horseRanch = ranchService.fetchRanchByRanchId(ranchId);
		model.addAttribute("horseRanch", horseRanch);
		
		List<User> subscribers = horseRanch.getSubscribers();
		System.out.println("subscribers from subscribers:  " + subscribers);
		model.addAttribute("subscribersThisRanch", subscribers);
		
		List<UserHorseRanch> userHorseRanchesThisRanch = ranchService.fetchUserHorseRanchesByThisRanch(horseRanch);
		System.out.println("number of subscribers for this ranch is:  " + userHorseRanchesThisRanch.size());
		
		
		model.addAttribute("userHorseRanchesThisRanch", userHorseRanchesThisRanch);
		
		
		
		return "ranch/property-details-owner.jsp";
	}
	
	//Show Edit Ranch page
	@GetMapping("/{id}/edit") //Spring seems to inject the proper horseRanch based on the id in the PathVariable...would it work if the pathvariable were named something else....
	public String showEditRanch(@PathVariable("id") HorseRanch horseRanch, Model model) {
		
		System.out.println("horseRanch at top of showEditRanch is:  " + horseRanch);
		
		model.addAttribute("horseRanch", horseRanch);
		
		model.addAttribute("ranchOnwerFullName", horseRanch.getRanchOwner().getFirstName() + " " + horseRanch.getRanchOwner().getLastName());
		
		return "ranch/property-update.jsp";
	}
	
	//Does action of editing Horse Ranch
	@PutMapping("/{id}")
	public String editRanch(@Valid @ModelAttribute("horseRanch") HorseRanch horseRanch, BindingResult result, Principal principal) {
		System.out.println("at top of editRanch with horseRandh of " + horseRanch);
		
		horseRanchValidator.validate(horseRanch, result);
		
	
		if(result.hasErrors()) {
			System.out.println("\n>>>>>we had this error in update ranch:  " + result.toString());
			return "ranch/property-update.jsp";
		}
		
		
		String email = principal.getName();
		System.out.println("and the logged in email is:  " + email);
		User user = userService.fetchByEmail(email);
		
		horseRanch.setRanchOwner((User) user);
		
		horseRanch.setNumberAcres(horseRanch.getNumberAcres().replaceFirst("^0+(?!$)", ""));
		horseRanch.setAnnualSubscriptionPrice(horseRanch.getAnnualSubscriptionPrice().replace("$","").replace(",", ""));
		
		ranchService.updateRanch(horseRanch);
		return "redirect:/ranches/owners-properties";
		
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
	
	
	
	//Show Ranch details page after GUEST clicked to request so GUEST can decide to subscribe
	@GetMapping("/property-details-guest/{ranchId}")
	public String showPropertyDetails(@PathVariable("ranchId") Long ranchId, Model model, Principal principal) {
		String email = principal.getName();
		System.out.println("and the logged in email is:  " + email);
		User user = userService.fetchByEmail(email);
		System.out.println("so the user is:  " + user);
		model.addAttribute("loggedInUser", user);
		
		HorseRanch horseRanch = ranchService.fetchRanchByRanchId(ranchId);
		model.addAttribute("horseRanch", horseRanch);		
		
		
		
		
		
		return "ranch/property-details-guest.jsp";
	}
	
	//Does action of adding guest to a Horse Ranch or subscribing to Ranch
	@PostMapping("/{ranchId}/users")
	public String subscribeThisUserToRanchById(@PathVariable("ranchId") Long ranchId, Principal principal) {
		String email = principal.getName();
		User user = userService.fetchByEmail(email);//the logged in user
		
		ranchService.subscribeThisUserToThisRanchId(user, ranchId);
		
		return "redirect:/ranches/property-details-guest/"+ranchId;
	}
	

	//Does action of removing a Horse Ranch or Unsubscribing to Ranch
	@DeleteMapping("/{ranchId}/users")
	public String UnsubscribeThisUserFromRanchById(@PathVariable("ranchId") Long ranchId, Principal principal) {
	
		String email = principal.getName();
		User user = userService.fetchByEmail(email);//the logged in user
		
		ranchService.unSubscribeThisUserToThisRanchId(user, ranchId);
	
	return "redirect:/ranches/property-details-guest/"+ranchId;
}
	
	//End Guests

	
	
	
	
	
	
	
}
