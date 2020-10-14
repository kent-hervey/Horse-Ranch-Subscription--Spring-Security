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
		System.out.println("\n>>>>>>>Prints whenever application is started/restarted\n\n");
		
		//For each role make String of the role_explanation and pass it to the method that confirms the role exists
		
		//test only
		setupService.addTestRoleIfNotExtant();
		
		setupService.addAdminIfNotExtant();
		
//		Role tempRole = new Role();
//		
//		Role role = tempRole;
//		
//		
//		setupService.updateRole(role);
		
		
		
		
		System.out.println("\n>>>>>>>End onApplicationEvent\n\n");
	}

}
