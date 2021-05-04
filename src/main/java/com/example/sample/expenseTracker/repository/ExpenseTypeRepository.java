package com.example.sample.expenseTracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sample.expenseTracker.model.ExpenseType;

public interface ExpenseTypeRepository extends JpaRepository<ExpenseType, Long> {
       ExpenseType findByCode(String code);
}
