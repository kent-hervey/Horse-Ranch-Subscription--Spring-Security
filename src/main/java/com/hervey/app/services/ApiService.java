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
public class ApiService {
	private UserRepository userRepository;
	private RoleRepository roleRepository;
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	private UserRoleRepository userRoleRepository;

	public ApiService(UserRepository userRepository, RoleRepository roleRepository,
			BCryptPasswordEncoder bCryptPasswordEncoder, UserRoleRepository userRoleRepository) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.userRoleRepository = userRoleRepository;
	}

	public void saveWithUserRole(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setRoles(roleRepository.findByName("ROLE_USER"));
		userRepository.save(user);
	}

	public void saveWithAdminRole(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setRoles(roleRepository.findByName("ROLE_ADMIN"));
		userRepository.save(user);
	}

//	public User findByUsername(String username) {
//		return userRepository.findByUsername(username);
//		}

//	public Boolean isUserAdmin(User user) {
//		List<Role> roles =user.getRoles();
//		for(Role role : roles) {
//			if(role.getName().contains("ADMIN")) {
//				return true;
//			}
//			//System.out.println(role.getName());
//		}
//		return false;
//	}

	public List<User> fetchAllUsers() {
		return userRepository.findAll();
	}

	public User fetchById(Long userId) {
		// return userRepository.findById(adminId).orElse(null); //Way#1, seems less
		// verbose, but use of orElse is controversial

		Optional<User> optionalUser = userRepository.findById(userId); // Way#2
		System.out.println("fetchById way#2");
		if (optionalUser.isPresent()) {
			return optionalUser.get();
		} else {
			return null;
		}

	}

	public void deleteThisUser(User user) {
		userRepository.delete(user);

	}

	// unused
//	public Role fetchRoleById(Long roleId) {
//		//return roleRepository.findById(roleId).orElse(null); //Way#1, seems less verbose, but use of orElse is controversial
//		
//		Optional<Role> optionalRole = roleRepository.findById(roleId);
//		System.out.println("fetchbyRoleId way#2");
//		if(optionalRole.isPresent()) {
//			return optionalRole.get();
//		}
//		else {
//			return null;
//		}
//		
//	}

	public Boolean doesAdminExist() {
		List<User> users = userRepository.findAll();
		for (User user : users) {
			if (user.isUserAdmin()) {
				return true;
			}
		}
		return false;
	}

	// Below is my traditional way of adding a row to a middle/join table, but I
	// want to try the T way
	public void promoteUserToAdminOld(User user) {
		Role role = roleRepository.findFirstByNameContaining("ADMIN");
		UserRole userRole = new UserRole();
		userRole.setRole(role);
		userRole.setUser(user);
		userRoleRepository.save(userRole);
	}

	public void promoteUserToAdmin(User user) {
		Role role = roleRepository.findFirstByNameContaining("ADMIN");
		user.getRoles().add(role);
		userRepository.save(user);
	}

	// It's best to use User to store them and CustomUserDetails to retrieve them.
	// for this user, copy currentSignIn to lastSignIn, then put current time/date
	// into currentSignIn
	public void queInCurrentDate(CustomUserDetails userDetails) {

		String email = userDetails.getEmail();
		User user = userRepository.findByEmail(email);

		user.setLastSignIn(user.getCurrentSignIn());

		Date nowDate = new Date();
		System.out.println("now date is " + nowDate);

		user.setCurrentSignIn(nowDate);

		userRepository.save(user);

		// user.setLastSignIn(LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);

	}

}
