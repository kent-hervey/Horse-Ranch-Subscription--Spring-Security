package com.hervey.app.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hervey.app.models.HorseRanch;
import com.hervey.app.models.User;
import com.hervey.app.models.UserHorseRanch;

@Repository
public interface UserHorseRanchRepository extends CrudRepository<UserHorseRanch, Long> {
	
	UserHorseRanch findByUserSubscriberAndHorseRanchSubscriber(User user, HorseRanch horseRanch);

	List<UserHorseRanch> findByHorseRanchSubscriber(HorseRanch horseRanch); 
	

}
