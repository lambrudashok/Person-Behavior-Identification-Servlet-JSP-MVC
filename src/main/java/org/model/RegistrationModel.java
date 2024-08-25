package org.model;

import java.sql.Date;

public class RegistrationModel{

	private int registerid;
	private String customername;
	private String email;
	private String username;
	private String password;
	private Date date;
	private int remain;
	private String profileimgname;
	
	
	public int getRemain() {
		return remain;
	}
	public void setRemain(int remain) {
		this.remain = remain;
	}
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getProfileimgname() {
		return profileimgname;
	}
	public void setProfileimgname(String profileimgname) {
		this.profileimgname = profileimgname;
	}
	
	public int getRegisterid() {
		return registerid;
	}
	public void setRegisterid(int registerid) {
		this.registerid = registerid;
	}
	
	public String getCustomername() {
		return customername;
	}
	public void setCustomername(String customername) {
		this.customername = customername;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
}
