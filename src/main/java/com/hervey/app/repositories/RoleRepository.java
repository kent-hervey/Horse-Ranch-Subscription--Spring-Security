package com.hervey.app.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hervey.app.models.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
	List<Role> findByName(String name);

	// List<Role> findAll(); //Role...findAll not used

	Role findFirstByNameContaining(String string);

}
