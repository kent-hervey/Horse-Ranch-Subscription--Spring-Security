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

public class RanchController {

	private UserService userService;
	private HorseRanchValidator horseRanchValidator;
	public RanchService ranchService;

	@Autowired // Required because there are two constuctors
	public RanchController(UserService userService, HorseRanchValidator horseRanchValidator, RanchService ranchService) {
		this.userService = userService;
		this.horseRanchValidator = horseRanchValidator;
		this.ranchService = ranchService;
	}

	public RanchController() { // including this no arg second constructor requires use of @Autowired
	}

	// show Owner's information as owner, including list of all his own ranches
	// role: OWNER
	@GetMapping({"/owners/auth"})
	public String showOwnerWithRanches(Principal principal, Model model) {

		String email = principal.getName();
		System.out.println("and the logged in email is:  " + email);
		User user = userService.fetchByEmail(email);
		System.out.println("so the user is:  " + user);
		model.addAttribute("loggedInUser", user);

		List<HorseRanch> RanchesForOwner = ranchService.fetchRanchesByOwner(user);
		model.addAttribute("horseRanchesThisOwner", RanchesForOwner);

		return "ranch/ownersPage.jsp";
	}

	// Show page form to create Ranch using POST
	// role: OWNER
	@GetMapping({ "/ranches/new"})
	public String showAddRanch(@ModelAttribute("horseRanch") HorseRanch horseRanch, Principal principal, Model model) {

		String email = principal.getName();
		System.out.println("and the logged in email is:  " + email);
		User user = userService.fetchByEmail(email);
		System.out.println("so the user is:  " + user);
		model.addAttribute("loggedInUser", user);

		return "ranch/create-property.jsp";

	}

	// does action of creating Ranch
	// role: OWNER
	@PostMapping({ "/ranches"})
	public String createRanch(@Valid @ModelAttribute("horseRanch") HorseRanch horseRanch, BindingResult result, Principal principal) {
		System.out.println("xxat top of createHorseRanch with horseRanch of " + horseRanch);

		// Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		// User userDetails = (User) auth.getPrincipal();
		String email = principal.getName();
		System.out.println("and the principal's name is:  " + email);
		User user = userService.fetchByEmail(email);

		System.out.println("the logged in user is:  " + user);

		horseRanchValidator.validate(horseRanch, result);

		if (result.hasErrors()) {
			System.out.println("\n>>>>>we had this error:  " + result.toString());
			return "ranch/create-property.jsp";
		}

		horseRanch.setNumberAcres(horseRanch.getNumberAcres().replaceFirst("^0+(?!$)", "")); // deletes leading zeros
		horseRanch.setAnnualSubscriptionPrice(horseRanch.getAnnualSubscriptionPrice().replace("$", "").replace(",", ""));

		horseRanch.setRanchOwner((User) user);

		Integer horseCapacityInput = horseRanch.getHorseCapacity();

		if (horseCapacityInput < 10) {
			System.out.println("horse cpacity was entered as:  " + horseCapacityInput + "which is less than 2, so is bing changed to 11");
			horseRanch.setHorseCapacity(11);
		} else {
			System.out.println("horse capacity entered was not less than 2; it was:  " + horseCapacityInput + "so it is not changed");
		}

		ranchService.saveRanch(horseRanch);

		return "redirect:/owners/auth"; // take app user back to the owner ranch listing
	}

	// Delete Ranch
	// role: OWNER
	@DeleteMapping({"/ranches/{ranchId}"})
	public String deleteRanch(@PathVariable("ranchId") Long ranchId, Principal principal) {
		System.out.println("at top of delete Ranch.  incoming ranchId:  " + ranchId);

		User userThisHorseRanch = ranchService.fetchRanchByRanchId(ranchId).getRanchOwner();
		String Principalemail = principal.getName();
		if (!userThisHorseRanch.getEmail().equals(Principalemail)) {
			System.out.println("showEditRanch; you don't own this ranch\n");
			return "redirect:/owners/auth";
		} else {
			System.out.println("showEditRanch; you do own this ranch\n");
		}

		ranchService.deleteRanchWithThisRanchId(ranchId);

		return "redirect:/owners/auth";
	}

	// Show Ranch details for each owner for owner's view
	// role: OWNER
	@GetMapping({"/owners/auth/ranches/{ranchId}"})
	public String showRanchOfOwner(@PathVariable("ranchId") Long ranchId, Model model, Principal principal) {
		HorseRanch horseRanch = ranchService.fetchRanchByRanchId(ranchId);

		User userThisHorseRanch = horseRanch.getRanchOwner();
		System.out.println("-----------show prop details; userThisHorseRanch:  " + userThisHorseRanch);
		System.out.println("email for this ranch is:  " + userThisHorseRanch.getEmail());

		String Principalemail = principal.getName();
		System.out.println("and the logged in email is:  " + Principalemail);
		User userPrincipal = userService.fetchByEmail(Principalemail);
		System.out.println("show prop details; so the user is:  " + userPrincipal + "\n");
		model.addAttribute("loggedInUser", userPrincipal);

//		System.out.println("showPropDetails checking for user==user");
//		if (userThisHorseRanch.getEmail().equals(Principalemail)) {
//			System.out.println("\name user\n");
//		}

		if (!userThisHorseRanch.getEmail().equals(Principalemail)) {
			System.out.println("you don't own this ranch\n");
			return "redirect:/owners/auth";
		} else {
			System.out.println("you do own this ranch\n");
		}

		model.addAttribute("horseRanch", horseRanch);

		List<User> subscribers = horseRanch.getSubscribers();
		System.out.println("subscribers from subscribers:  " + subscribers);
		model.addAttribute("subscribersThisRanch", subscribers);

		List<UserHorseRanch> userHorseRanchesThisRanch = ranchService.fetchUserHorseRanchesByThisRanch(horseRanch);
		System.out.println("number of subscribers for this ranch is:  " + userHorseRanchesThisRanch.size());

		model.addAttribute("userHorseRanchesThisRanch", userHorseRanchesThisRanch);

		return "ranch/property-details-owner.jsp";
	}

	// Show Edit Ranch page
	// role: OWNER
	@GetMapping("/ranches/{id}/edit")
	public String showEditRanch(@PathVariable("id") HorseRanch horseRanch, Model model, Principal principal) {
		User userThisHorseRanch = horseRanch.getRanchOwner();
		String Principalemail = principal.getName();
		if (!userThisHorseRanch.getEmail().equals(Principalemail)) {
			System.out.println("showEditRanch; you don't own this ranch\n");
			return "redirect:/owners/auth";
		} else {
			System.out.println("showEditRanch; you do own this ranch\n");
		}

		System.out.println("horseRanch at top of showEditRanch is:  " + horseRanch);

		model.addAttribute("horseRanch", horseRanch);

		model.addAttribute("ranchOnwerFullName",
				horseRanch.getRanchOwner().getFirstName() + " " + horseRanch.getRanchOwner().getLastName());

		return "ranch/property-update.jsp";
	}

	// Does action of editing Ranch
	// role: OWNER
	@PutMapping("/ranches/{id}")
	public String editRanch(@Valid @ModelAttribute("horseRanch") HorseRanch horseRanch, BindingResult result, Principal principal) {
		System.out.println("\nat top of editRanch with horseRanch From Form of " + horseRanch);

		HorseRanch horseRanchFromDB = ranchService.fetchRanchByRanchId(horseRanch.getId());
		System.out.println("\nat top of editRanch with horseRanch From Database of " + horseRanchFromDB);

		User userThisHorseRanch = horseRanchFromDB.getRanchOwner();

		System.out.println("user/owner of this horseRanch from databse is:  " + userThisHorseRanch);

		String email = principal.getName();
		System.out.println("and the logged in email is:  " + email);
		User user = userService.fetchByEmail(email);

		if (!userThisHorseRanch.getEmail().equals(email)) {
			System.out.println("showEditRanch; you don't own this ranch\n");
			return "redirect:/owners/auth";
		} else {
			System.out.println("editRanch; you do own this ranch\n");
		}

		horseRanchValidator.validate(horseRanch, result);

		if (result.hasErrors()) {
			System.out.println("\n>>>>>we had this error in update ranch:  " + result.toString());
			return "ranch/property-update.jsp";
		}

		horseRanch.setRanchOwner((User) user);

		horseRanch.setNumberAcres(horseRanch.getNumberAcres().replaceFirst("^0+(?!$)", ""));
		horseRanch.setAnnualSubscriptionPrice(horseRanch.getAnnualSubscriptionPrice().replace("$", "").replace(",", ""));

		ranchService.updateRanch(horseRanch);
		return "redirect:/owners/auth";

	}

	// End Owners

	// for Guests
	// Show Ranch Listing page that provides a table listing of ranches with link to details page so guests can subscribe
	//role:  GUEST
	@GetMapping({ "/guest-auth/ranches"})
	public String showAllRanches(Principal principal, Model model) {
		String email = principal.getName();
		System.out.println("and the logged in email is:  " + email);
		User user = userService.fetchByEmail(email);
		System.out.println("so the user is:  " + user);
		model.addAttribute("loggedInUser", user);

		List<HorseRanch> horseRanches = ranchService.fetchAllRanches();
		model.addAttribute("horseRanches", horseRanches);

		return "ranch/property-list.jsp";
	}

	// Show Ranch details page after GUEST clicked to request so GUEST can decide to subscribe
	// role: GUEST
	@GetMapping({"/guest-auth/ranches/{ranchId}" })
	public String showRanch(@PathVariable("ranchId") Long ranchId, Model model, Principal principal) {
		String email = principal.getName();
		System.out.println("and the logged in email is:  " + email);
		User user = userService.fetchByEmail(email);
		System.out.println("so the user is:  " + user);
		model.addAttribute("loggedInUser", user);

		HorseRanch horseRanch = ranchService.fetchRanchByRanchId(ranchId);
		model.addAttribute("horseRanch", horseRanch);

		return "ranch/property-details-guest.jsp";
	}

	// Does action of adding guest to a Horse Ranch or subscribing to Ranch
	// role: GUEST
	@PostMapping({ "/guest-auth/ranches/{ranchId}/users/auth"})
	public String subscribeThisUserToRanchById(@PathVariable("ranchId") Long ranchId, Principal principal) {
		String email = principal.getName();
		User user = userService.fetchByEmail(email);// the logged in user

		ranchService.subscribeThisUserToThisRanchId(user, ranchId);

		return "redirect:/guest-auth/ranches/" + ranchId;
	}

	// Does action of removing a Horse Ranch or Unsubscribing to Ranch
	//role: GUEST
	@DeleteMapping({ "/guest-auth/ranches/{ranchId}/users/auth"})
	public String unsubscribeThisUserFromRanchById(@PathVariable("ranchId") Long ranchId, Principal principal) {

		String email = principal.getName();
		User user = userService.fetchByEmail(email);// the logged in user

		ranchService.unSubscribeThisUserToThisRanchId(user, ranchId);

		return "redirect:/guest-auth/ranches/" + ranchId;
	}

	// End Guests

}
