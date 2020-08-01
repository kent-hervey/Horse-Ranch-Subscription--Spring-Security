package com.hervey.app.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.hervey.app.models.CustomUserDetails;
import com.hervey.app.models.Role;
import com.hervey.app.models.User;
import com.hervey.app.models.UserRole;
import com.hervey.app.repositories.RoleRepository;
import com.hervey.app.repositories.UserRepository;
import com.hervey.app.repositories.UserRoleRepository;

@Service
public class RanchService {
	private UserRepository userRepository;
	private RoleRepository roleRepository;
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	private UserRoleRepository userRoleRepository;

	public RanchService(UserRepository userRepository, RoleRepository roleRepository,
			BCryptPasswordEncoder bCryptPasswordEncoder, UserRoleRepository userRoleRepository) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.userRoleRepository = userRoleRepository;
	}










}
