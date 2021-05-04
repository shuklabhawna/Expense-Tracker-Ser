
package com.example.sample.expenseTracker.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.sample.expenseTracker.model.Accounts;
import com.example.sample.expenseTracker.model.User;
import com.example.sample.expenseTracker.repository.AccountsRepository;
import com.example.sample.expenseTracker.repository.UserRepository;

@RestController
@RequestMapping("/accounts")
public class AccountsController {
	
	@Autowired
	private AccountsRepository accountsRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/list")
	List<Accounts> getAccounts() {
		return accountsRepository.findAll();
	}

	@GetMapping("/search/{user}")
	Accounts getForUser(@PathVariable Long userId) {
		
		Optional<User> optUser = userRepository.findById(userId);
		if(optUser.isPresent()) {
			return accountsRepository.findByUser(optUser.get());
		}
		return new Accounts();
	}
}