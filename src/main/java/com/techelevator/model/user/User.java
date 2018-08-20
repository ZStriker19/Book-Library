package com.techelevator.model.user;

import javax.validation.constraints.Pattern;

public class User {
	
	private String userName;
	private long userId;
	
	@Pattern.List({
		@Pattern(regexp=".*[a-z].*", message="Must have a lower case"),
		@Pattern(regexp=".*[A-Z].*", message="Must have a capital")
	})
	private String password;
	private boolean role;
	
	private String confirmPassword;
	public String getUserName() {
		return userName;
	}
	/**
	 * @return the role
	 */
	public boolean getRole() {
		return role;
	}
	/**
	 * @param role the role to set
	 */
	public void setRole(boolean role) {
		this.role = role;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	
	public void setId(long userId) {
		this.userId = userId;
	}
	
	public long getId() {
		return userId;
	}
}
