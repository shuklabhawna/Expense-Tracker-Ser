package com.example.sample.expenseTracker.controller;

import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.sample.expenseTracker.model.Accounts;
import com.example.sample.expenseTracker.model.Currency;
import com.example.sample.expenseTracker.model.User;
import com.example.sample.expenseTracker.repository.AccountsRepository;
import com.example.sample.expenseTracker.repository.UserRepository;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AccountsRepository accountsRepository;

	@GetMapping("/list")
	List<User> getUsers() {
		return userRepository.findAll();
	}

	@GetMapping("/find/{id}")
	ResponseEntity<?> getUser(@PathVariable Long id) {
		Optional<User> optUser = userRepository.findById(id);
		return optUser.map(response -> ResponseEntity.ok(response))
		.orElse(new ResponseEntity<User>(HttpStatus.NOT_FOUND));
		
	}
	
	@GetMapping("/search/{name}")
	User getUserByName(@PathVariable String name) {
		User user = userRepository.findByName(name);
		return user;
	}
	
	@PostMapping("/save")
	ResponseEntity<User> saveUser(@Validated @RequestBody User user) throws URISyntaxException{
		
		boolean isNewUser = false;
		User createdUser = userRepository.save(user);
		Accounts existingUserAccount = accountsRepository.findByUser(createdUser);
		if(null == existingUserAccount) {
			
			Accounts newUserAccount = new Accounts();
			newUserAccount.setBalance(new BigDecimal(35000));
			newUserAccount.setCurrency(new Currency());
			newUserAccount.getCurrency().setCurrencyCode("SGD");
			newUserAccount.setUser(createdUser);
			newUserAccount.setAccountCode(createdUser.getId()+"-"+createdUser.getUserCode());
			newUserAccount.setId(createdUser.getId());
			accountsRepository.save(newUserAccount);
		}
		return ResponseEntity.created(new URI("/users/save"+createdUser.getId())).body(createdUser);
	}
	
	@PutMapping("/update/{id}")
	ResponseEntity<User> updateUser(@PathVariable Long id, @Validated @RequestBody User user){
		Optional<User> optUser = userRepository.findById(id);
		if(optUser.isPresent()) {
			User updatedUser = userRepository.save(user);
			return ResponseEntity.ok(updatedUser);
		}else return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/delete/{id}")
	ResponseEntity<?> deteleUser(@PathVariable Long id) {
		if(userRepository.existsById(id)) {
			userRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}else return ResponseEntity.notFound().build();
		
	}

}
