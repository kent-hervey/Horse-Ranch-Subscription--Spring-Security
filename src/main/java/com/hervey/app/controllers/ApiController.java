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

@RestController
@RequestMapping("/api")
public class ApiController {
	
	private final RanchService apiService;
	
	public ApiController(RanchService apiService) {
		this.apiService = apiService;
	}
	
	//Fetch All Horse Ranch Properties
//	@GetMapping("/horse-ranch-properties")
//	public List<HorseRanch> showAllHorseRanches(){
//	List<HorseRanch> horseRanches  = apiService.fetchAllRanches();
//	System.out.println(horseRanches.toString());
//		return horseRanches;
//	}
//
//	//Fetch All Horse Ranch Properties alternate
//	@GetMapping("/horse-ranch-properties-alt")
//	public ResponseEntity<List<HorseRanch>> showAllHorseRanchesAlt(){
//		List<HorseRanch> horseRanches  = apiService.fetchAllRanches();
//		ResponseEntity<List<HorseRanch>> lists = ResponseEntity.ok(horseRanches);
//
//	return lists;
//	}
//	
//	//Fetch all roles 
//	@GetMapping("/roles")
//	public ResponseEntity<List<Role>> showAllRoles() {
//		List<Role> roles = apiService.fetchAllRoles();
//		ResponseEntity<List<Role>> lists = ResponseEntity.ok(roles);
//		
//		return lists;
//	}
//	
//	@GetMapping("/admin")
//	public Principal adminPage(Principal principal, Model model) {
//		System.out.println(principal.toString());
//		return principal;
//	}
//	

	
	
}
