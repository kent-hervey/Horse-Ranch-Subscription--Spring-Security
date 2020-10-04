package com.hervey.app.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.hervey.app.models.HorseRanch;

@Component
public class RanchValidator implements Validator {
	
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

		HorseRanch ranch = (HorseRanch) object;
		
		System.out.println("\n\n\n >>>>>>>>>>>>   ranch is this ranch:  " + ranch + "\n And name is:  " + ranch.getRanchName() );

//		if (!user.getPasswordConfirmation().equals(user.getPassword())) {
//			// .rejectValue(String, String): the first argument is the member variable of
//			// our Domain model that we are validating. The second argument is a code for us
//			// to use to set an error message.
//			errors.rejectValue("passwordConfirmation", "Match");
//		}
//		
//		else if (userService.isEmailAlreadyRegistered(user)) {
//			//System.out.println(">>>>>>>>>>>\nelse if that will be true when duplicate email");
//			errors.rejectValue("email", "Match");
//		}
//		
//		
//		else {
//			System.out.println("Inside UserValidator; validate method: pass and passConfirm do match");
//		}

	}

}
