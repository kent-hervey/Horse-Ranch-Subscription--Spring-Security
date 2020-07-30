package com.hervey.app.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.hervey.app.models.User;

@Component
public class UserValidator implements Validator {

	@Override
	// Specifies that a instance of the User Domain Model can be validated with this
	// custom validator
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}

	@Override
	// Creating our custom validation. We can add errors via .rejectValue(String,
	// String).
	public void validate(Object object, Errors errors) {
		User user = (User) object;

		if (!user.getPasswordConfirmation().equals(user.getPassword())) {
			// .rejectValue(String, String): the first argument is the member variable of
			// our Domain model that we are validating. The second argument is a code for us
			// to use to set an error message.
			errors.rejectValue("passwordConfirmation", "Match");
		}

	}

}
