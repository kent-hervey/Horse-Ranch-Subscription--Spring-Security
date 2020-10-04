package com.hervey.app.config;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.hervey.app.models.Role;
import com.hervey.app.services.SetupService;

@Component
public class SetupDatabaseData implements ApplicationListener<ContextRefreshedEvent> {
	
	private SetupService setupService;
	
	public SetupDatabaseData(SetupService setupService) {
		this.setupService = setupService;
	}
	

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		// TODO Auto-generated method stub
		
		Role tempRole = new Role();
		
		Role role = tempRole;
		
		
		setupService.updateRole(role);
		
	}

}
