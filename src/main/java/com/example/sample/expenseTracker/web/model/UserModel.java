package com.example.sample.expenseTracker.web.model;

import org.springframework.boot.jackson.JsonComponent;

@JsonComponent
public class UserModel {

	private String username;
	private String password;
	private String token;
	private String user;
	
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
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
}
