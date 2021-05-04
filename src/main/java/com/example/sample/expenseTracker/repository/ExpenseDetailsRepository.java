
package com.example.sample.expenseTracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.sample.expenseTracker.model.ExpenseDetails;

@Repository
public interface ExpenseDetailsRepository extends JpaRepository<ExpenseDetails, Long>,ExpenseDetailsRepositoryCustom{
	
}