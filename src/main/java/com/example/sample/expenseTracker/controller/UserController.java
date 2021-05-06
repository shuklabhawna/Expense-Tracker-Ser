
package com.example.sample.expenseTracker.controller;

import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
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
import com.example.sample.expenseTracker.model.CustomUserDetails;
import com.example.sample.expenseTracker.model.User;
import com.example.sample.expenseTracker.repository.AccountsRepository;
import com.example.sample.expenseTracker.repository.UserRepository;
import com.example.sample.expenseTracker.service.UserService;
import com.example.sample.expenseTracker.web.model.UserModel;


@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private AccountsRepository accountsRepository;

	@GetMapping("/list")
	List<User> getUsers() {
		return userService.findAll();
	}

	@PostMapping("/signin")
	ResponseEntity<?> userLogin( @RequestBody UserModel model ) {
		
		String username = model.getUsername();
		
		String password = model.getPassword();
		
		User userDetails = userService.findByName(username);
		if(null != userDetails) {
			model.setToken(userDetails.getPasswordHash());
			model.setUser(userDetails.getName());
			return 	ResponseEntity.status(HttpStatus.OK).body(model);
		}else {
			return 	ResponseEntity.notFound().build();
		}
	
	}
	
	@GetMapping("/find/{id}")
	ResponseEntity<?> getUser(@PathVariable Long id) {
		Optional<User> optUser = userService.findById(id);
		return optUser.map(response -> ResponseEntity.ok(response))
		.orElse(new ResponseEntity<User>(HttpStatus.NOT_FOUND));
		
	}
	
	@GetMapping("/search/{name}")
	User getUserByName(@PathVariable String name) {
		User user = userService.findByName(name);
		return user;
	}
	
	@PostMapping("/save")
	ResponseEntity<User> saveUser( @RequestBody User user) throws URISyntaxException{
		
		System.out.println("In user save "+ user.getName());
		User createdUser = userService.save(user);
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
	ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user){
		Optional<User> optUser = userService.findById(id);
		if(optUser.isPresent()) {
			User updatedUser = userService.save(user);
			return ResponseEntity.ok(updatedUser);
		}else return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/delete/{id}")
	ResponseEntity<?> deteleUser(@PathVariable Long id) {
		if(userService.deleteById(id)) {
			return ResponseEntity.ok().build();
		}else return ResponseEntity.notFound().build();
		
	}

}