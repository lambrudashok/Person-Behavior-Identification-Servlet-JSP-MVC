package org.repository;

import org.model.LoginModel;

public class ValidateUserRepository extends DBHelper{
	
	/*check user login in database */
	public int checkUserLogin(LoginModel login) {
		try {
			ps = con.prepareStatement("select registerid from registrationmaster where username=? and password=?");
			ps.setString(1, login.getUsername());
			ps.setString(2, login.getPassword());
			rs = ps.executeQuery();
			if(rs.next()) {
				return rs.getInt("registerid");
			}else {
				return -1;
			}
		}catch(Exception e) {
			System.out.println("User Registration Repository error :"+e);
			return -1;
		}
	}
	
	/* when user login then login data added in database table*/
	public boolean isAddUserLogin(LoginModel login) {
		try {
			ps = con.prepareStatement("insert into loginmaster values('0',?,?,(select curdate()),(select curtime()),?)");
			ps.setString(1, login.getUsername());
			ps.setString(2, login.getPassword());
			ps.setInt(3, login.getLoginid());
			int v=ps.executeUpdate();
			return (v>0) ?true:false;
		}catch(Exception e) {
			System.out.println("user login repository error :"+e);
			return false;
		}
	}

}
