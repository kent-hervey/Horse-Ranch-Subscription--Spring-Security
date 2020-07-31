package com.hervey.app.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hervey.app.models.HorseRanch;

@Repository
public interface HorseRanchRepository extends CrudRepository<HorseRanch, Long> {

	List<HorseRanch> findAll(); 
	
}
