package com.hervey.app.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hervey.app.models.CustomUserDetails;
import com.hervey.app.models.Role;
import com.hervey.app.models.User;
import com.hervey.app.repositories.UserRepository;

@Service
public class UserDetailsServiceImplementation implements UserDetailsService {

	private UserRepository userRepository;

	public UserDetailsServiceImplementation(UserRepository userRepository) {
		this.userRepository = userRepository;

	}

	@Override // important to override this method because we have to and also to set my own
				// "username" for SS
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// User user = userRepository.findByUsername(username); //the standard username
		User user = userRepository.findByEmail(email);

		if (user == null) {
			throw new UsernameNotFoundException("User not found");
		}

		CustomUserDetails userDetails = new CustomUserDetails(user.getId(), user.getFirstName(), user.getLastName(),
				user.getEmail(), user.getPassword(), user.isUserAdmin(), user.getLastSignIn(), user.getCurrentSignIn(),
				user.getCreatedAt(), user.getUpdateAt(), true, true, true, true, getAuthorities(user), user.getRoles(), user.getNoteToAdmin());

		return userDetails;
	}

	// getAuthorities(User user): returns a list of authorities/permissions for a
	// specific user.
	// For example, our clients can be 'user', 'admin', or both. For Spring Security
	// to implement authorization,
	// we must get the name of the possibles roles for current user from our
	// database and create a new `SimpleGrantedAuthority' object with those roles.
	private List<GrantedAuthority> getAuthorities(User user) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		System.out.println("authorities on creation" + Arrays.toString(authorities.toArray()));
		for (Role role : user.getRoles()) {
			System.out.println("this role is:  " + role.toString());
			GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.getName());
			authorities.add(grantedAuthority);
			System.out.println("grantedAuthority at end of for loop " + grantedAuthority.toString());
		}
		System.out.println("authorities after for loop " + Arrays.toString(authorities.toArray()));
		return authorities;
	}

}
