package org.service;

import java.util.List;

import org.model.UserInfoModel;
import org.repository.UserSearchRepository;

public class UserSearchService {

	UserSearchRepository serRepo = new UserSearchRepository();
	
	
	/*fetch all user details for searching*/
	public List<UserInfoModel> fetchAllUserDetails(){
		return serRepo.fetchAllUserDetails();
	}
	
	/*fetch all user details for searching using id*/
	public List<UserInfoModel> fetchAllUserDetails(int registerid){
		return serRepo.fetchAllUserDetails(registerid);
	}
	
	/*fetch all user details for searching using name*/
	public List<UserInfoModel> fetchAllUserDetails(String name, int registerid){
		return serRepo.fetchAllUserDetails(name,registerid);
	}
	
	/*fetch all user details for searching using name*/
	public List<UserInfoModel> fetchAllUserDetails(String name){
		return serRepo.fetchAllUserDetails(name);
	}
}
