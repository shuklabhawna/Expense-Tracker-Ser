
package com.example.sample.expenseTracker.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.sun.istack.NotNull;

@Entity
@Table(name = "EXPENSE_TYPE")
public class ExpenseType {

	@Id
	@Column(name = "ID")
	private long id;
	
	@Column(name = "CODE" , nullable = false,unique = true)
	private String code;
	
	@Column(name = "DESCRIPTION")
	private String description;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}