package com.example.sample.expenseTracker.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.example.sample.expenseTracker.model.User;

@Component 
public interface UserService {

	List<User> findAll();
	User findByName(String name);
	Optional<User> findById(Long id);
	User save(User entity);
	Boolean deleteById(Long id);
	User findUserByToken(String token);
}
