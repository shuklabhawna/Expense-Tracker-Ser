package com.example.sample.expenseTracker.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.sample.expenseTracker.model.ExpenseType;

@Component
public interface ExpenseTypeService {
	List<ExpenseType> findAll();
	ExpenseType findById(Long id);
	ExpenseType save(ExpenseType entity);
	void delete(Long id);
	ExpenseType findOneByCode(String code);
}
