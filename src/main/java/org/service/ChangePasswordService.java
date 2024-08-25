package org.service;

import org.repository.ChangePasswordRepository;

public class ChangePasswordService {

	ChangePasswordRepository changeRepo = new ChangePasswordRepository();
	
	// check for change password user
	public int checkPassword(String pass ,int registerid) {
		return changeRepo.checkPassword(pass, registerid);
	}
	
	// change password user
	public int changeUserPassword(String pass ,int registerid) {
		return changeRepo.changeUserPassword(pass, registerid);
	}
	
	// forgot password check username
	public int checkUsername(String username) {
		return changeRepo.checkUsername(username);
	}
	
	// check email and username particular user
	public int checkEmail(String email, String username) {
		return changeRepo.checkEmail(email, username);
	}
}
