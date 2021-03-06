package com.hervey.app.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hervey.app.models.Role;
import com.hervey.app.models.User;
import com.hervey.app.models.UserRole;

@Repository
public interface UserRoleRepository extends CrudRepository<UserRole, Long> {
	UserRole findByUserAndRole(User user, Role role);

}
