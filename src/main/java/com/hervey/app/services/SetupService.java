package com.hervey.app.services;

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
	
	
	
	

}
