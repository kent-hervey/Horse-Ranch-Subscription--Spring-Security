package com.hervey.app.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

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

	public void deleteRanchWithThisRanchId(Long ranchId) {
		System.out.println("at top of delete Ranch by Id method.  incoming ranchId:  " +  ranchId);
		Optional<HorseRanch> optionalHorseRanch = horseRanchRepository.findById(ranchId);
		System.out.println("created optionalHorseRanch:  " + optionalHorseRanch);
		if (optionalHorseRanch.isPresent()) {
			System.out.println("looks like optionalHorseRanch is presentat");
			HorseRanch horseRanch = optionalHorseRanch.get();
			System.out.println("horseRanch is;  " + horseRanch);
			horseRanchRepository.delete(horseRanch);
		}
		else {
			return; //return statement for clarity only
		}
	}

	public void updateRanch(@Valid HorseRanch horseRanch) {
		horseRanchRepository.save(horseRanch);
	}

	public HorseRanch fetchRanchByRanchId(Long ranchId) {
		Optional<HorseRanch> optionalHorseRanch = horseRanchRepository.findById(ranchId);
		System.out.println("created optionalHorseRanch:  " + optionalHorseRanch);
		if (optionalHorseRanch.isPresent()) {
			System.out.println("looks like optionalHorseRanch is presentat");
			HorseRanch horseRanch = optionalHorseRanch.get();
			System.out.println("horseRanch is;  " + horseRanch);
			return horseRanch;
		}
		else {
			return null; //return statement for clarity only
		}
	}








}
