package com.hervey.app.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hervey.app.models.CustomUserDetails;
import com.hervey.app.models.Role;
import com.hervey.app.models.User;
import com.hervey.app.models.UserRole;
import com.hervey.app.services.UserService;
import com.hervey.app.validator.UserValidator;

@Controller
public class UserController {
	// if @Autowired is here...then Spring will say what is the reference type of
	// the field variable
	// in this case it finds UserService....and it will look for where the bean
	// userService is created...then it makes the variable userService point to that
	// object

	private UserService userService;
	private UserValidator userValidator;

	public UserController(UserService userService, UserValidator userValidator) {
		this.userService = userService;
		this.userValidator = userValidator;
	}

	@GetMapping("/shouldfail")
	public String shouldFail() {
		return "shouldfail.jsp";
	}

	// Note: this method as with other get requests does not and should not use
	// @Valid
	@GetMapping({ "/login", "/loginreg", "/registration" })
	public String login(@ModelAttribute("user") User user,
			@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout, Model model) { // k old T below
		// public String login(@RequestParam(value="error", required=false) String
		// error, @RequestParam(value="logout", required=false) String logout, Model
		// model){ //T's

		if (error != null) {
			model.addAttribute("errorMessage", "Invalid Credentials, Please try again.");
		}
		if (logout != null) {
			model.addAttribute("logoutMessage", "Logout Successful!");
		}
		return "loginReg.jsp";
	}

	@PostMapping("/registration")
	public String registration(@Valid @ModelAttribute("user") User user, BindingResult result, Model model,
			HttpSession session) {
		userValidator.validate(user, result);
		if (result.hasErrors()) {
			return "redirect:/loginreg";
		}

		Boolean haveAnAdmin = userService.doesAdminExist();
		if (haveAnAdmin) {
			userService.saveWithUserRole(user);
			System.out.println("plain user was just registered");
			return "redirect:/";
		} else {
			userService.saveWithAdminRole(user); // use this when person registering should be an Admin
			System.out.println("admin was just registered");
			return "redirect:/admin";
			// return "redirect:/"; //this is T's version, above I have admins being sent to
			// admin dashboard
		}

	}

	@GetMapping("/admin")
	public String showAdmin(Model model) {
		System.out.println("Very top of show Admin method");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();

		model.addAttribute("currentUser", userDetails);
		// System.out.println("is this an admin232? " +
		// userService.isUserAdmin(username));
		//model.addAttribute("isAdmin", userDetails.getIsUserAdmin());
		//model.addAttribute("isGuest" )

		List<User> users = userService.fetchAllUsers();
		System.out.println("number of total users:  " + users.size());
		model.addAttribute("users", users);

//		System.out.println("using user function is user admin?: " + user.isUserAdmin());

		return "adminPage.jsp";
	}

	// POST("login")
	// One would assume that the next logical step would be to create a method in
	// our controller that handles our
	// POST request. However, because we are using Spring Security that process is
	// done for us.

	// On a login attempt, Spring Security will automatically call the
	// loadUserByUsername(String) in
	// UserDetailsServiceImplementation class. From there, Spring Security will have
	// two options:

	// Successful Login: The user is authenticated, saves them in a context, and
	// redirects to "/" (root route).
	// Unsuccessful Login: The client is redirected to "/login?error".
	// It is important to note that the form must have a name field with the
	// username
	// value for Spring Security to correctly grab the information in the
	// loadUserByUsername(String) method.

	// successful login redirects here by Spring
	@GetMapping({ "/" })
	public String home(Principal principal, Model model) {
		System.out.println("very top of home/successful login page");
		// After a successful authentication, we are able to get the name of our
		// principal (current user) via the .getName() method.
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();

		model.addAttribute("currentUser", userDetails);
//		System.out.println("is this an admin?  " + userService.isUserAdmin(username));
		model.addAttribute("isAdmin", userDetails.getIsUserAdmin());
		if (userDetails.getIsUserAdmin()) {
			return "redirect:/admin";
		}

//		User user = userService.findByUsername(username);
		System.out.println("using user function is user admin?: " + userDetails.getIsUserAdmin());

		return "redirect:/user-details";
	}

	
	@GetMapping({"/user-details"})
	public String showUserPage(Principal principal, Model model) {
		System.out.println("very top of showUserPage method");
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
		
		model.addAttribute("currentUser", userDetails);
		
		
		return "showUser.jsp";
	}

	
	
	
	
	
	
	
	
	
	
	
	// Delete User
	@DeleteMapping("/admins/{userId}")
	public String destroysAdmin(@PathVariable("userId") Long userId) { //
		System.out.println("time to delete admin number:  " + userId);
		User user = userService.fetchById(userId);
		System.out.println("just used userService.fetchById");
		userService.deleteThisUser(user);

		return "redirect:/admin";
	}

	// Add Admin role to user
	@PostMapping("/admins/{userId}/roles/adminRole") // roles/adminRole not used in code, but kept for RESTful clarity
	public String addsAdminRole(@PathVariable("userId") Long userId) {
		System.out.println("let's promote user with id of " + userId + " to admin level/role");

		User user = userService.fetchById(userId);

		// Role role = userService.fetchRoleById((long) 2);//removing this to have the
		// service pick the role based on knowing it should be admin

		userService.promoteUserToAdmin(user);

		return "redirect:/admin";

	}

}
