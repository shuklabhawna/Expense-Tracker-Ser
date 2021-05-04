package com.example.sample.expenseTracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sample.expenseTracker.model.Accounts;
import com.example.sample.expenseTracker.model.User;

public interface AccountsRepository extends JpaRepository<Accounts, Long>{

	Accounts findByUser(User user);
}
