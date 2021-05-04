
package com.example.sample.expenseTracker.service;

import org.springframework.stereotype.Component;

import com.example.sample.expenseTracker.model.ExpenseDetails;

@Component 
public interface SettlementService {
	
	public String getBalance(Long accountId);
	public ExpenseDetails credit(ExpenseDetails expenseDetails);
	public boolean debit(ExpenseDetails expenseDetails);
	
}