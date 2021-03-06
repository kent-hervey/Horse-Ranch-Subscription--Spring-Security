package com.hervey.app.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hervey.app.models.HorseRanch;
import com.hervey.app.models.User;
import com.hervey.app.models.UserHorseRanch;

@Repository
public interface HorseRanchRepository extends CrudRepository<HorseRanch, Long> {

	List<HorseRanch> findAll();

	List<HorseRanch> findByRanchOwner(User ranchOwner);

	//UserHorseRanch findByUserAndHorseRanch(User user, HorseRanch horseRanch); 
	
}
