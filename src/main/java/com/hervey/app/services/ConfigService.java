package com.hervey.app.services;
//Creates roles table as needed on application startup

import org.springframework.stereotype.Service;

import com.hervey.app.models.Role;
import com.hervey.app.repositories.RoleRepository;

@Service
public class ConfigService {
	
	private final RoleRepository roleRepository;
	
	public ConfigService(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}

	public Long fetchCountRoles() {

		Long rolesCount = roleRepository.count();
		return rolesCount;
	}
	
	
	public void createRole(Role role) {
		roleRepository.save(role);
	}
	//Sample
	


}
