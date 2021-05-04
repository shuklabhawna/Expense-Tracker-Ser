package com.example.sample.expenseTracker.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ACCOUNT")
public class Accounts {

	@Id
	@Column(name = "ID")
	private Long id;
	
	@OneToOne
	@JoinColumn(name="USERID", referencedColumnName = "ID", unique=true, nullable=false)
	private User user;
	
	@Column(name = "ACCOUNTCODE",nullable = false,unique = true)
	private String accountCode;
	
	@ManyToOne
	@JoinColumn(name = "CURRENCY_CODE",nullable = false)
	private Currency currency;
	
	@Column(name = "BALANCE",nullable = false)
	private BigDecimal balance;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getAccountCode() {
		return accountCode;
	}

	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode;
	}

	public Currency getCurrency() {
		return currency;
	}
	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	
	public String getBalanceWithCurrency() {
		return currency+" "+balance;
	}
}
