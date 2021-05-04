
package com.example.sample.expenseTracker.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.sun.istack.NotNull;

@Entity
@Table(name="EXPENSE_DETAILS")
public class ExpenseDetails {

	@Id
	@Column(name = "ID")
	private long id;

	@Column(name = "DATE",nullable = false)
	private Date date;
	
	@Column(name = "DESCRIPTION")
	private String description;
	
	@ManyToOne
	@JoinColumn(name = "USER_ID",nullable = false)
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "EXPENSE_CODE", nullable = false )
	private ExpenseType expenseType;

	@Column(name = "AMOUNT",nullable = false)
	private BigDecimal amount;
	
	@ManyToOne
	@JoinColumn(name = "CURRENCY_CODE",nullable = false)
	private Currency currency;
	
	@Column(name = "STATUS")
	private String status;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public ExpenseType getExpenseType() {
		return expenseType;
	}

	public void setExpenseType(ExpenseType expenseType) {
		this.expenseType = expenseType;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		if(amount.signum() == 0 || amount.signum() == -1){
			throw new RuntimeException("Negative value not accepted for expense Amount");
		}
		this.amount = amount;
	}

	public Currency getCurrency() {
		return currency;
	}
	public void setCurrency(Currency currency) {
		this.currency = currency;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}