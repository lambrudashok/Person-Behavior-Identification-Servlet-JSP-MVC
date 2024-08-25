package org.service;

import org.model.LoginModel;
import org.repository.ValidateUserRepository;

public class ValidateUserService {

	ValidateUserRepository userLoginRepo = new ValidateUserRepository();
	
	/*check user login in database */
	public int checkUserLogin(LoginModel login) {
		return userLoginRepo.checkUserLogin(login); 
	}
	
	/* when user login then login data added in database table*/
	public boolean isAddUserLogin(LoginModel login) {
		return userLoginRepo.isAddUserLogin(login);
	}
}
