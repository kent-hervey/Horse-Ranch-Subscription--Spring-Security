package com.hervey.app.services;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.hervey.app.models.HorseRanch;
import com.hervey.app.models.User;
import com.hervey.app.repositories.HorseRanchRepository;
import com.hervey.app.repositories.RoleRepository;
import com.hervey.app.repositories.UserRepository;
import com.hervey.app.repositories.UserRoleRepository;

@Service
public class RanchService {
	private UserRepository userRepository;
	private RoleRepository roleRepository;
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	private UserRoleRepository userRoleRepository;
	private HorseRanchRepository horseRanchRepository;

	public RanchService(UserRepository userRepository, RoleRepository roleRepository,
			BCryptPasswordEncoder bCryptPasswordEncoder, UserRoleRepository userRoleRepository, HorseRanchRepository horseRanchRepository) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.userRoleRepository = userRoleRepository;
		this.horseRanchRepository = horseRanchRepository;
	}

	public void saveRanch(HorseRanch horseRanch) {
		horseRanchRepository.save(horseRanch);
		
	}

	public List<HorseRanch> fetchAllRanches() {
		return horseRanchRepository.findAll();
	}

	public List<HorseRanch> fetchRanchesByOwner(User user) {
		return horseRanchRepository.findByRanchOwner(user);

	}










}
