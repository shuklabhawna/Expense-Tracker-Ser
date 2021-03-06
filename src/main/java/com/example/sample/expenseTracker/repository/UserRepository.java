
package com.example.sample.expenseTracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sample.expenseTracker.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

	User findByName(String name);
	User findByPasswordHash(String passwordHash);
}