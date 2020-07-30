package com.hervey.app.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hervey.app.models.UserRole;

@Repository
public interface UserRoleRepository extends CrudRepository<UserRole, Long> {
	// UserRoleRepository is injected into UserService, but only mentioned in an
	// unused method

}
