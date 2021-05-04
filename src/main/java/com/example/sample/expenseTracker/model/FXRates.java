package com.example.sample.expenseTracker.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Random;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="FX_RATES")
public class FXRates {
	
	@Id
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "PRIMARY_CCY")
	private String primaryCcy;
	
	@Column(name = "SECONDARY_CCY")
	private String secondaryCcy;
	
	@Column(name = "EXCHANGE_RATE")
	private BigDecimal exchaneRate;
	//how much of the quote currency is needed to buy one unit of the provided base currency.
	
	@Column(name = "T_DATE")
	private Date date;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPrimaryCcy() {
		return primaryCcy;
	}

	public void setPrimaryCcy(String primaryCcy) {
		this.primaryCcy = primaryCcy;
	}

	public String getSecondaryCcy() {
		return secondaryCcy;
	}

	public void setSecondaryCcy(String secondaryCcy) {
		this.secondaryCcy = secondaryCcy;
	}

	public BigDecimal getExchaneRate() {
		return exchaneRate;
	}

	public void setExchaneRate(BigDecimal exchaneRate) {
		if(exchaneRate.signum() == 0 || exchaneRate.signum() == -1){
			throw new RuntimeException("Negative value not accepted for exchangeRate");
		}
		this.exchaneRate = exchaneRate;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	public static FXRates getDummyFXRates(){
		FXRates dummyRate = new FXRates();
		dummyRate.setExchaneRate(new BigDecimal(Math.random()));
		return dummyRate;
	}
	

}
