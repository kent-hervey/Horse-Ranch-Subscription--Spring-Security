package com.hervey.app.services;

import javax.validation.constraints.AssertFalse;

import org.springframework.stereotype.Service;

import com.hervey.app.models.Role;
import com.hervey.app.repositories.RoleRepository;

@Service
public class SetupService {

	
	private static final String EXPLANATION_ADMIN_ROLE_1 = "Admin has full capability";
	private static final String EXPLANATION_BROWSER_ROLE_2 = "Browser is assigned to any user who registers.  It allows little more than viewing his own page";
	private static final String EXPLANATION_GUEST_ROLE_3 = "Guests are abel to/are looking at a Horse Ranch to subscribe";
	private static final String EXPLANATION_OWNER_ROLE_4 = "Owners have ranches they have or will list for subscribers";
	
	private final RoleRepository roleRepository;
	private final ConfigService configService;
	
	
	public SetupService(RoleRepository roleRepository, ConfigService configService) {
		this.roleRepository = roleRepository;
		this.configService = configService;
	}

	public Role updateRole(Role role) {
		return roleRepository.save(role);
		
	}

	public void addAdminIfNotExtant() {

		
		Boolean adminRoleExists = false;
		try {
		adminRoleExists = roleRepository.existsByName("ROLE_ADMIN");
		}
		
		catch (Exception e) {
			System.out.println("error is:  " + e);
			System.out.println(adminRoleExists);
		}
		
		finally {
			System.out.println("in Class SetupService adminRoleExists value is:  " + adminRoleExists);
		}
		
		if(!adminRoleExists) {
			System.out.println("admin role does not exist in Roles Table, do add");

			Role role = new Role();
			role.setId((long) 1);
			role.setName("ROLE_ADMIN");
			role.setRoleExplanation(EXPLANATION_ADMIN_ROLE_1);
			configService.createRole(role);
		}
		else {
			System.out.println("Admin role already exists");
		}
	}
	
	

	public void addBrowserIfNotExtant() {
		Boolean browserRoleExists = false;
		try {
			browserRoleExists = roleRepository.existsByName("ROLE_BROWSER");
		}
		
		catch (Exception errorBrowser) {
			System.out.println("error is: " + errorBrowser + " because browserRoleExists is:  " + browserRoleExists);
		}
		
		if(!browserRoleExists) {
			System.out.println("browser role did not exist, so adding it");
			Role role = new Role();
			role.setId((long) 2);
			role.setName("ROLE_BROWSER");
			role.setRoleExplanation(EXPLANATION_BROWSER_ROLE_2);
			configService.createRole(role);
			
		}
		else {
			//browser role already existed
		}
		
	}


		public void addGuestIfNotExtant() {
			Boolean guestRoleExists = false;
			try {
				guestRoleExists = roleRepository.existsByName("ROLE_GUEST");
			}
		catch (Exception errorGuest) {
			System.out.println("error is " + errorGuest + " because guestRoleExists is:  " + guestRoleExists);
		}
			
		if(!guestRoleExists) {
			System.out.println("guest role did not exist, so addingt it");
 
			Role role = new Role();
			role.setId((long) 3);
			role.setName("ROLE_GUEST");
			role.setRoleExplanation(EXPLANATION_GUEST_ROLE_3);
			configService.createRole(role);
			
		}
		else {
			// role already existed
		}
		
	}
	

	public void addOwnerIfNotExtant() {
		Boolean ownerRoleExists = false;
		try {
			ownerRoleExists = roleRepository.existsByName("ROLE_OWNER");
		}
		
		catch (Exception errorOwner) {
			System.out.println("error is " + errorOwner + " because ownerRoleExists is:  " + ownerRoleExists);
		}
		
		if(!ownerRoleExists) {
			System.out.println("owner role did not exist, so adding it");
 
			Role role = new Role();
			role.setId((long) 4);
			role.setName("ROLE_OWNER");
			role.setRoleExplanation(EXPLANATION_OWNER_ROLE_4);
			configService.createRole(role);
			
		}
		else {
			// role already existed
		}
		
	}


	public void addTestRoleIfNotExtant() {

		@AssertFalse
		Boolean testRoleExists = false;
		try {
			testRoleExists = roleRepository.existsByName("ROLE_TEMP");

		}
		
		catch (Exception e) {
			System.out.println("error is:  " + e);
			System.out.println(testRoleExists);
		}
		
		finally {
			System.out.println("testRole Value is:  " + testRoleExists);
		}
		
		if(!testRoleExists) {
			System.out.println("test role does not exist in Roles Table");
			Role tempRole = new Role();
			tempRole.setId((long) 5);
			tempRole.setName("ROLE_TEMP");
			tempRole.setRoleExplanation("This role is good and temp");
			configService.createRole(tempRole);
	
		}
		else {
			System.out.println("test role does actually exist--bad");

			
			
			
			
		}
	}
	

}
