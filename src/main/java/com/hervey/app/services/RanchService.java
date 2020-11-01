package com.hervey.app.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.hervey.app.models.HorseRanch;
import com.hervey.app.models.User;
import com.hervey.app.models.UserHorseRanch;
import com.hervey.app.repositories.HorseRanchRepository;
import com.hervey.app.repositories.RoleRepository;
import com.hervey.app.repositories.UserHorseRanchRepository;
import com.hervey.app.repositories.UserRepository;
import com.hervey.app.repositories.UserRoleRepository;

@Service
public class RanchService {
	
	private static final int MIN_ACRES =5;
	private static final int MAX_ACRES = 100_000;
	
	private static final int MIN_PRICE=100;
	private static final int MAX_PRICE=100_000;
	
	private UserRepository userRepository;
	private RoleRepository roleRepository;
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	private UserRoleRepository userRoleRepository;
	private HorseRanchRepository horseRanchRepository;
	private UserHorseRanchRepository userHorseRanchRepository;


	public RanchService(UserRepository userRepository, RoleRepository roleRepository,
			BCryptPasswordEncoder bCryptPasswordEncoder, UserRoleRepository userRoleRepository, HorseRanchRepository horseRanchRepository, UserHorseRanchRepository userHorseRanchRepository) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.userRoleRepository = userRoleRepository;
		this.horseRanchRepository = horseRanchRepository;
		this.userHorseRanchRepository = userHorseRanchRepository;

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
			System.out.println("looks like optionalHorseRanch is present [inside service deleteRanch...]");
			HorseRanch horseRanch = optionalHorseRanch.get();
			System.out.println("horseRanch is [in service deleteRanch..id]  " + horseRanch);
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
			System.out.println("looks like optionalHorseRanch is present [in fetch ranch by id]");
			HorseRanch horseRanch = optionalHorseRanch.get();
			System.out.println("horseRanch is [in service fetchRanch...id ]  " + horseRanch);
			return horseRanch;
		}
		else {
			return null; //return statement for clarity only
		}
	}

	public void subscribeThisUserToThisRanchIdO(User user, Long ranchId) {
		HorseRanch horseRanch = this.fetchRanchByRanchId(ranchId);
		horseRanch.getSubscribers().add(user);
		horseRanchRepository.save(horseRanch);
	}
	
	public void subscribeThisUserToThisRanchId(User user, Long ranchId) {
		HorseRanch horseRanch = this.fetchRanchByRanchId(ranchId);
		UserHorseRanch userHorseRanch = new UserHorseRanch();		
		System.out.println("In subscribe method, user is:  " + user);
		System.out.println("In subscribe method, horseRanch is:  " + horseRanch);
		System.out.println("is userhorseRanch a real thing?  " + userHorseRanch);

		
		userHorseRanch.setHorseRanchSubscriber(horseRanch);
		userHorseRanch.setUserSubscriber(user);
		System.out.println(">>>>>>>>>>>>>>>userHorseRanch before saving is:  " + userHorseRanch);
		

		userHorseRanchRepository.save(userHorseRanch);
		System.out.println(">>>>>>>>>>>>>>>userHorseRanch after saving is:  " + userHorseRanch);
		
	}
	
	public void unSubscribeThisUserToThisRanchId(User user, Long ranchId) {
		HorseRanch horseRanch = this.fetchRanchByRanchId(ranchId);
		UserHorseRanch userHorseRanch = userHorseRanchRepository.findByUserSubscriberAndHorseRanchSubscriber(user, horseRanch);
		userHorseRanchRepository.delete(userHorseRanch);
	}

	public List<UserHorseRanch> fetchUserHorseRanchesByThisRanch(HorseRanch horseRanch) {
		List<UserHorseRanch> userHorseRanch = userHorseRanchRepository.findByHorseRanchSubscriber(horseRanch);
		return userHorseRanch;
	}

	public static boolean numberAcresMalFormed(String numberOfAcres) {
		
		int parsedNumAcres;
		try {
			parsedNumAcres = Integer.parseInt(numberOfAcres);
		}
		
		catch (NumberFormatException e) {
			System.out.println("number of acres entered was not a number");
			return true;
		}
		if(parsedNumAcres< MIN_ACRES || parsedNumAcres > MAX_ACRES ) {
			return true;
		}
		
		
		
		
		return false;
	}

	public static boolean annualSubscriptionPriceMalFormed(String annualSubscriptionPrice) {
		// TODO Auto-generated method stub
		
		System.out.println("price malformed in String:  " + annualSubscriptionPrice);
		
		int parsedPrice;
		annualSubscriptionPrice = annualSubscriptionPrice.replace("$", "").replace(",", "");
		try {
			parsedPrice = Integer.parseInt(annualSubscriptionPrice);
		}
		
		catch (NumberFormatException e) {
			System.out.println("number of acres entered was not a number");
			return true;
		}
		if(parsedPrice< MIN_PRICE || parsedPrice > MAX_PRICE) {
			return true;
		}
		
		
		return false;
	}








}
