package com.example.sample.expenseTracker.repository;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.example.sample.expenseTracker.model.ExpenseDetails;

public interface ExpenseDetailsRepositoryCustom {
	List<ExpenseDetails> findByParameter(Map<String, Object> queryParameter);
	ExpenseDetails findOneByParameter(Map<String, Object> queryParameter);
}
