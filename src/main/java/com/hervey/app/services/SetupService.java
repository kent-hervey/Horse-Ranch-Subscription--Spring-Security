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
			System.out.println("Looks like we made it.  adminRoleExists value is:  " + adminRoleExists);
		}
		
		if(!adminRoleExists) {
			System.out.println("admin role does not exist in Roles Table");
		}
		else {
			System.out.println("Admin role does actually exist");
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
