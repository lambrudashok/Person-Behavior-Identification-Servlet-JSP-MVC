package org.repository;

import java.util.ArrayList;
import java.util.List;

import org.model.UserInfoModel;

public class UserSearchRepository extends DBHelper{
	
	
	/*fetch all user details for searching*/
	public List<UserInfoModel> fetchAllUserDetails(){
		List<UserInfoModel> list = new ArrayList<UserInfoModel>(); // store all user  details
		try {
			ps = con.prepareStatement("select registerid,username,customername,profileimg from registrationmaster");
			rs=ps.executeQuery();
			while(rs.next()) {
				UserInfoModel user = new UserInfoModel();
				user.setRegisterid(rs.getInt("registerid"));
				user.setUsername(rs.getString("username"));
				user.setName(rs.getString("customername"));
				user.setProfileimage(rs.getString("profileimg"));
				list.add(user);
			}	
						
			return (list.size()>0) ? list : null;
		}catch(Exception e) {
			System.out.println("following repo error :"+e);
			return null;
		}
	}
	
	
	
	/*fetch all user details for searching using id*/
	public List<UserInfoModel> fetchAllUserDetails(int registerid){
		List<UserInfoModel> list = new ArrayList<UserInfoModel>(); // store all user  details
		try {
			// store not following user
			ps = con.prepareStatement("select registerid,username,customername,profileimg from registrationmaster "
					+ "where registerid NOT IN (select fm.followingregisterid  from registrationmaster rm "
					+ "inner join userfollowingfollowerjoin uffj on uffj.registerid=rm.registerid "
					+ "inner join followingmaster fm on fm.followingid=uffj.followingid "
					+ "where rm.registerid=?)");
			ps.setInt(1, registerid);
			rs=ps.executeQuery();
			while(rs.next()) {
				UserInfoModel user = new UserInfoModel();
				user.setRegisterid(rs.getInt("registerid"));
				user.setUsername(rs.getString("username"));
				user.setName(rs.getString("customername"));
				user.setProfileimage(rs.getString("profileimg"));
				user.setStatus(0);
				list.add(user);
			}
			// store following user
			ps = con.prepareStatement("select registerid,username,customername,profileimg from registrationmaster "
					+ "where registerid IN (select fm.followingregisterid  from registrationmaster rm "
					+ "inner join userfollowingfollowerjoin uffj on uffj.registerid=rm.registerid "
					+ "inner join followingmaster fm on fm.followingid=uffj.followingid "
					+ "where rm.registerid=?)");
			ps.setInt(1, registerid);
			rs=ps.executeQuery();
			while(rs.next()) {
				UserInfoModel user = new UserInfoModel();
				user.setRegisterid(rs.getInt("registerid"));
				user.setUsername(rs.getString("username"));
				user.setName(rs.getString("customername"));
				user.setProfileimage(rs.getString("profileimg"));
				user.setStatus(1);
				list.add(user);
			}
					
			return (list.size()>0) ? list : null;
		}catch(Exception e) {
			System.out.println("following repo error :"+e);
			return null;
		}
	}
	
	
	
	
	/*fetch all user details for searching using name*/
	public List<UserInfoModel> fetchAllUserDetails(String name ,int registerid){
		List<UserInfoModel> list = new ArrayList<UserInfoModel>(); // store all user  details
		try {
						
			
			// store not following user
			ps = con.prepareStatement("select registerid,username,customername,profileimg from registrationmaster "
					+ "where username like (?) and registerid NOT IN (select fm.followingregisterid  from registrationmaster rm "
					+ "inner join userfollowingfollowerjoin uffj on uffj.registerid=rm.registerid "
					+ "inner join followingmaster fm on fm.followingid=uffj.followingid "
					+ "where rm.registerid=?)");
			ps.setString(1, name+"%");
			ps.setInt(2, registerid);
			rs=ps.executeQuery();
			while(rs.next()) {
				UserInfoModel user = new UserInfoModel();
				user.setRegisterid(rs.getInt("registerid"));
				user.setUsername(rs.getString("username"));
				user.setName(rs.getString("customername"));
				user.setProfileimage(rs.getString("profileimg"));
				user.setStatus(0);
				list.add(user);
			}
			
			// store following user	
			ps = con.prepareStatement("select registerid,username,customername,profileimg from registrationmaster "
					+ "where username like (?) and registerid IN (select fm.followingregisterid  from registrationmaster rm "
					+ "inner join userfollowingfollowerjoin uffj on uffj.registerid=rm.registerid "
					+ "inner join followingmaster fm on fm.followingid=uffj.followingid "
					+ "where rm.registerid=?)");
			ps.setString(1, name+"%");
			ps.setInt(2, registerid);
			rs=ps.executeQuery();
			while(rs.next()) {
				UserInfoModel user = new UserInfoModel();
				user.setRegisterid(rs.getInt("registerid"));
				user.setUsername(rs.getString("username"));
				user.setName(rs.getString("customername"));
				user.setProfileimage(rs.getString("profileimg"));
				user.setStatus(1);
				list.add(user);
			}
			
			return (list.size()>0) ? list : null;
		}catch(Exception e) {
			System.out.println("following repo error :"+e);
			return null;
		}
	}
	
	
	/*fetch all user details for searching using name*/
	public List<UserInfoModel> fetchAllUserDetails(String name){
		List<UserInfoModel> list = new ArrayList<UserInfoModel>(); // store all user  details
		try {
			
			ps = con.prepareStatement("select registerid,username,customername,profileimg from registrationmaster where username like (?) ");
			ps.setString(1, name+"%");
			rs=ps.executeQuery();
			while(rs.next()) {
				UserInfoModel user = new UserInfoModel();
				user.setRegisterid(rs.getInt("registerid"));
				user.setUsername(rs.getString("username"));
				user.setName(rs.getString("customername"));
				user.setProfileimage(rs.getString("profileimg"));
				list.add(user);
			}	
						
			return (list.size()>0) ? list : null;
		}catch(Exception e) {
			System.out.println("following repo error :"+e);
			return null;
		}
	}

}
