package com.hervey.app.services;

import javax.validation.constraints.AssertFalse;

import org.springframework.stereotype.Service;

import com.hervey.app.models.Role;
import com.hervey.app.repositories.RoleRepository;

@Service
public class SetupService {

	
	
	private final RoleRepository roleRepository;
	
	
	public SetupService(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
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
			testRoleExists = roleRepository.existsByName("TestRoleNoGood");

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
		}
		else {
			System.out.println("test role does actually exist--bad");
		}
	}
	
	

}
