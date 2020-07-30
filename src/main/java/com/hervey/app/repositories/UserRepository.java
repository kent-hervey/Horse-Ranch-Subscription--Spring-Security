package com.hervey.app.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hervey.app.models.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
	// User findByUsername(String username);
	List<User> findAll(); // findAll ...User is used in UserService for fetchAllUsers; required..not
							// default

	User findByEmail(String email);
	// Optional<User>findById(Long id); //findbyId for User in UserService, but JPA
	// seems to automatically include, so this line not needed

}
