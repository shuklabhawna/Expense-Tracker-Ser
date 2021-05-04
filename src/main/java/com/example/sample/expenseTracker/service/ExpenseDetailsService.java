
package com.example.sample.expenseTracker.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.example.sample.expenseTracker.model.ExpenseDetails;

@Component
public interface ExpenseDetailsService {

	List<ExpenseDetails> findAll();
	ExpenseDetails findById(Long id);
	ExpenseDetails save(ExpenseDetails entity);
	ExpenseDetails update(Long id,ExpenseDetails entity);
	void delete(Long id);
	ExpenseDetails findOneByParameter(Map<String, Object> queryParameter);
	List<ExpenseDetails> findByParameter(Map<String, Object> queryParameter);
}