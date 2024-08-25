package org.service;

import java.sql.Date;
import java.util.List;

import org.model.LoginModel;
import org.model.PostLayoutModel;
import org.model.RegistrationModel;
import org.repository.ValidateAdminRepository;

public class ValidateAdminService {

	ValidateAdminRepository adminRepo = new ValidateAdminRepository();
	
	/*check admin login*/
	public int checkAdminLogin(LoginModel login) {
		return adminRepo.checkAdminLogin(login);
	}
	
	// fetch all user details
	public List<RegistrationModel> fetchAllUserDetails(){
		return adminRepo.fetchAllUserDetails();
	}
	
	//delete user
	public int deleteUser(int registerid) {
		return adminRepo.deleteUser(registerid);
	}
	
	/*view All posts application users*/
	public List<PostLayoutModel> ViewAllUserPosts(){
		return adminRepo.ViewAllUserPosts();
	}
	
	// fetch delete account  requests  users
	public List<RegistrationModel> fetchDeleteUserAccountReuests(){
		return adminRepo.fetchDeleteUserAccountReuests();
	}
	
	// delete user from delete request
	public int deleteUserRequestAccount(int registerid) {
		return adminRepo.deleteUserRequestAccount(registerid);
	}
	
	// fetch user login details date time 
	public List<LoginModel> viewUserLoginDetails(){
		return adminRepo.viewUserLoginDetails();
	}
	
	
}
