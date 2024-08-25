package org.repository;

public class ChangePasswordRepository extends DBHelper{

	// check for change password user
	public int checkPassword(String pass ,int registerid) {
		try {
			ps=con.prepareStatement("select registerid,password from registrationmaster where registerid=? and password=?");
			ps.setInt(1, registerid);
			ps.setString(2, pass);
			rs=ps.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);
			}else {
				return 0;
			}
			
		}catch(Exception e) {
			System.out.println("error :"+e);
			return 0;
		}
	}
	
	
	// change password user
		public int changeUserPassword(String pass ,int registerid) {
			try {
				ps=con.prepareStatement("update registrationmaster set password=? where registerid=?");
				ps.setString(1, pass);
				ps.setInt(2, registerid);
				int v=ps.executeUpdate();
				return (v>0) ? 1: 0;
				
			}catch(Exception e) {
				System.out.println("error :"+e);
				return 0;
			}
		}
		
	// forgot password check username
	public int checkUsername(String username) {
		try {
			ps=con.prepareStatement("select registerid from registrationmaster where username=?");
			ps.setString(1, username);
			rs=ps.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);
			}else {
				return 0;
			}
		}catch(Exception e) {
			System.out.println("error: "+e);
			return 0;
		}
	}
	
	// check email and username particular user
	public int checkEmail(String email, String username) {
		try {
			ps=con.prepareStatement("select registerid from registrationmaster where email=? and username=?");
			ps.setString(1, email);
			ps.setString(2, username);
			rs=ps.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);
			}else {
				return 0;
			}
		}catch(Exception e) {
			System.out.println("error: "+e);
			return 0;
		}
	}
	
}
