package com.hervey.app.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.hervey.app.models.HorseRanch;
import com.hervey.app.services.RanchService;

@Component
public class HorseRanchValidator implements Validator {
	
//	private UserService userService;
//
//	public RanchValidator(UserService userService) {
//		this.userService = userService;
//
//	}

	@Override
	// Specifies that a instance of the User Domain Model can be validated with this
	// custom validator
	public boolean supports(Class<?> clazz) {
		return HorseRanch.class.equals(clazz);
	}

	@Override
	// Creating our custom validation. We can add errors via .rejectValue(String,
	// String).
	public void validate(Object object, Errors errors) {

		HorseRanch horseRanch = (HorseRanch) object;
		System.out.println("\n\n\n >>>>>>>>>>>> from validate method...   ranch is this ranch:  " + horseRanch + "\n And name is:  " + horseRanch.getRanchName() );
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ranchName",  "ranchName.required");
		System.out.println("\n horse capacity as presented in validate method:  " + horseRanch.getHorseCapacity());
		
		if (RanchService.numberAcresMalFormed(horseRanch.getNumberAcres())) {
			errors.rejectValue("numberAcres", "Match");
		}
		
		if (RanchService.annualSubscriptionPriceMalFormed(horseRanch.getAnnualSubscriptionPrice())) {
			errors.rejectValue("annualSubscriptionPrice", "Match");
		}
		

	}

}
