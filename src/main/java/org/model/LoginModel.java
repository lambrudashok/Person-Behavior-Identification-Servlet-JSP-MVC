package org.model;

import java.sql.Date;
import java.sql.Time;

public class LoginModel {

	private int loginid;
	private String username;
	private String password;
	private Date date;
	private Time time;
	
	public int getLoginid() {
		return loginid;
	}
	public void setLoginid(int loginid) {
		this.loginid = loginid;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Time getTime() {
		return time;
	}
	public void setTime(Time time) {
		this.time = time;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public int hashCode() {
		return username.length()*1000;
	}
	public boolean equals(Object obj) {
		LoginModel m= (LoginModel)obj;
		if(m.username.equals(this.username) && (m.password.equals(this.password))) {
			return true;
		}else {
			return false;
		}
	}
}
