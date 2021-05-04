package com.example.sample.expenseTracker.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

@Entity
@Table(name = "USER")
public class User {
	@Id
	@Column(name = "ID")
	private long id;
	
	@Column(name = "USER_CODE" ,nullable = false, unique = true)
	private String userCode;
	
	@Column(name = "NAME" ,nullable = false)
	private String name;
	
	@Column(name = "EMAIL")
	private String email;
	
	@JsonIgnore
	@Column(name = "SALT")
	private String salt;
	
	@JsonIgnore
	@Column(name = "PASSWORDHASH")
	private String passwordHash;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	public String getPasswordHash() {
		return passwordHash;
	}
	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}
}
