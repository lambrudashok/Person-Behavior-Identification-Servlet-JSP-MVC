package org.repository;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.model.ProfileInformationModel;
import org.model.RegistrationModel;
import org.model.UserInfoModel;

public class UserRegistrationRepository extends DBHelper{

	
	
	
	/* new user insert data in registration master table*/
	public boolean isAddNewUserRegistration(RegistrationModel register) {
		
		try {
			ps = con.prepareStatement("insert into registrationmaster (customername,email,username,password) values (?,?,?,?)");
			ps.setString(1, register.getCustomername());
			ps.setString(2, register.getEmail());
			ps.setString(3, register.getUsername());
			ps.setString(4, register.getPassword());
			int v =ps.executeUpdate();
			return (v>0) ?true:false;
		}catch(Exception e) {
			System.out.println("user register repo :"+e);
			return false;
		}
	}
	
	/*check email duplicate or not */
	public boolean searchEmail(String email) {
		try {
			ps = con.prepareStatement("select registerid from registrationmaster where email=?");
			ps.setString(1, email);
			rs =ps.executeQuery();
			if(rs.next()) {
				return true;
			}else {
				return false;
			}
		}catch(Exception e) {
			System.out.println("User Registration Repository error :"+e);
			return false;
		}
	}
	
	/*check username duplicate or not */
	public boolean searchUsername(String username) {
		try {
			ps = con.prepareStatement("select registerid from registrationmaster where username=?");
			ps.setString(1, username);
			rs =ps.executeQuery();
			if(rs.next()) {
				return true;
			}else {
				return false;
			}
		}catch(Exception e) {
			System.out.println("User Registration Repository error :"+e);
			return false;
		}
	}
	
	/*profile information show*/
	public List <ProfileInformationModel>profileInformation(int registerid) {
		List<ProfileInformationModel> list = new LinkedList<ProfileInformationModel>();
		try {
			ProfileInformationModel accountInfo = new ProfileInformationModel();
			// store customer name and username in LinkedList
			ps = con.prepareStatement("select customername,username,profileimg from registrationmaster where registerid=?");
			ps.setInt(1, registerid);
			rs=ps.executeQuery();
			if(rs.next()) {
				accountInfo.setCustomername(rs.getString("customername"));
				accountInfo.setUsername(rs.getString("username"));
				accountInfo.setProfilephoto(rs.getString("profileimg"));
			}
			
			// we store following count
			ps = con.prepareStatement("select count(fm.followingid) as 'following count' from followingmaster fm "
					+ "inner join userfollowingfollowerjoin uffj on uffj.followingid=fm.followingid "
					+ "inner join registrationmaster rm on rm.registerid=uffj.registerid "
					+ "where rm.registerid=?");
			ps.setInt(1, registerid);
			rs=ps.executeQuery();
			if(rs.next()) {
				accountInfo.setFollowingCount(rs.getInt("following count"));
			}
			
			// we store follower count
			ps = con.prepareStatement("select count(fm.followerid) as 'follower count' from followermaster fm "
					+ "inner join userfollowingfollowerjoin uffj on uffj.followerid=fm.followerid "
					+ "inner join registrationmaster rm on rm.registerid=uffj.registerid "
					+ "where rm.registerid=?");
			ps.setInt(1, registerid);
			rs=ps.executeQuery();
			if(rs.next()) {
				accountInfo.setFollowerCount(rs.getInt("follower count"));
			}
			
			// store post count
			ps = con.prepareStatement("select count(pm.postid) as 'post count' from postmaster pm "
					+ "inner join postregistrationjoin prj on prj.postid=pm.postid "
					+ "inner join registrationmaster rm on rm.registerid=prj.registerid "
					+ "where rm.registerid=?");
			ps.setInt(1, registerid);
			rs=ps.executeQuery();
			if(rs.next()) {
				accountInfo.setPostCount(rs.getInt("post count"));
			}
			
			// store bio
			ps=con.prepareStatement("select bm.bio from biomaster bm "
					+ "inner join bioregistrationjoin brj on brj.bioid=bm.bioid "
					+ "inner join registrationmaster rm on rm.registerid=brj.registerid "
					+ "where brj.registerid=?");
			ps.setInt(1, registerid);
			rs=ps.executeQuery();
			if(rs.next()) {
				accountInfo.setBio(rs.getString(1));
			}
			list.add(accountInfo);
			return list.size()>0 ?list:null;
			
		}catch(Exception e) {
			System.out.println("register repo error :"+e);
			return null;
		}
	}
	
	
	// fetch username and name particular user
	public UserInfoModel getUserInfo(int registerid){
		UserInfoModel model = new UserInfoModel();
		try {
			ps=con.prepareStatement("select username,customername,profileimg from registrationmaster where registerid=?");
			ps.setInt(1, registerid);
			rs=ps.executeQuery();
			if(rs.next()) {
				model.setUsername(rs.getString("username"));
				model.setName(rs.getString("customername"));
				model.setProfileimage(rs.getString("profileimg"));
				return model;
			}else {
				return null;
			}
		}catch(Exception e) {
			System.out.println("error :"+e);
			return null;
		}
	}
	
	
	//update or add profile image
	public boolean isAddProfilePhoto(RegistrationModel model) {
		try {
			ps=con.prepareStatement("update registrationmaster set profileimg=? where registerid=?");
			ps.setString(1, model.getProfileimgname());
			ps.setInt(2, model.getRegisterid());
			int v=ps.executeUpdate();
			return (v>0)?true:false;
		}catch(Exception e) {
			System.out.println("error :"+e);
			return false;
		}
	}
	
	//update username
	public int isUpdateUsername(String username,int registerid) {
		try {
			ps=con.prepareStatement("update registrationmaster set username=? where registerid=?");
			ps.setString(1, username);
			ps.setInt(2, registerid);
			int v=ps.executeUpdate();
			return (v>0)?1:0;
		}catch(Exception e) {
			System.out.println("error :"+e);
			return 0;
		}
	}
	
	
	//update email
		public int isUpdateEmail(String email,int registerid) {
			try {
				ps=con.prepareStatement("update registrationmaster set email=? where registerid=?");
				ps.setString(1, email);
				ps.setInt(2, registerid);
				int v=ps.executeUpdate();
				return (v>0)?1:0;
			}catch(Exception e) {
				System.out.println("error :"+e);
				return 0;
			}
		}
		
	//update customer name
		public int isUpdateCustomerName(String name,int registerid) {
			try {
				ps=con.prepareStatement("update registrationmaster set customername=? where registerid=?");
				ps.setString(1, name);
				ps.setInt(2, registerid);
				int v=ps.executeUpdate();
				return (v>0)?1:0;
			}catch(Exception e) {
				System.out.println("error :"+e);
				return 0;
			}
		}
	
		
		/*generate bio id end user*/
		public int bioIDGenerate() {
			try {
				ps=con.prepareStatement("select max(bioid) from biomaster");
				rs=ps.executeQuery();
				if(rs.next()) {
					return rs.getInt(1);
				}else {
					return 0;
				}
			}catch(Exception e) {
				System.out.println("bio reop error :"+e);
				return -1;
			}
		}
		
		/*Add Bio*/
		public boolean isaddBio(String bio,int registerid) {
			try {
				ps=con.prepareStatement("insert into biomaster values('0',?)");
				ps.setString(1, bio);
				int v=ps.executeUpdate();
				
				// get bioid
				int bioid=this.bioIDGenerate();
				ps=con.prepareStatement("insert into bioregistrationjoin values (?,?)");
				ps.setInt(1, bioid);
				ps.setInt(2, registerid);
				v=ps.executeUpdate();
				return (v>0)?true:false;
			}catch(Exception e) {
				System.out.println("bio repo error :"+e);
				return false;
			}
		}
		
		/*search bio in database*/
		public int searchBio(int registerId) {
			try {
				ps=con.prepareStatement("select bm.bioid from biomaster bm "
						+ "inner join bioregistrationjoin brj on brj.bioid=bm.bioid "
						+ "inner join registrationmaster rm on rm.registerid=brj.registerid "
						+ "where rm.registerid=?");
				ps.setInt(1, registerId);
				rs=ps.executeQuery();
				if(rs.next()) {
					return rs.getInt(1);
				}else {
					return 0;
				}
			}catch(Exception e) {
				System.out.println("bio repo error :"+e);
				return -1;
			}
		}
		
		
		//update bio
		public int isUpdateBio(String bio,int registerid) {
			try {
				ps=con.prepareStatement("update biomaster set bio=? where bioid=?");
				ps.setString(1, bio);
				ps.setInt(2, registerid);
				int v=ps.executeUpdate();
	
				return (v>0)?1:0;
			}catch(Exception e) {
				System.out.println("error :"+e);
				return 0;
			}
		}
		
		
		/*check request account delete*/
		public int checkRequestDelete(int registerId) {
			try {
				ps=con.prepareStatement("select registerid from deleterequest where registerid=?");
				ps.setInt(1, registerId);
				rs=ps.executeQuery();
				if(rs.next()) {
					return rs.getInt(1);
				}else {
					return 0;
				}
			}catch(Exception e) {
				System.out.println("register repo :"+e);
				return -1;
			}
		}
		
		/*recover delete requested account*/
		public int recoverAccount(int register) {
			try {
				ps=con.prepareStatement("delete from deleterequest where registerid=?");
				ps.setInt(1, register);
				int v=ps.executeUpdate();
				return (v>0)?1:0;
			}catch(Exception e) {
				System.out.println("register repo :"+e);
				return -1;
			}
		}
		
		
		/*delete account user*/
		public int deleteUserAccount(int registerId) {
			try {
				ps=con.prepareStatement("insert into deleterequest values(?,(select curdate()))");
				ps.setInt(1, registerId);
				int v=ps.executeUpdate();
				return (v>0)?1:0;
			}catch(Exception e) {
				System.out.println("register repo error :"+e);
				return -1;
			}
		}
		
}
