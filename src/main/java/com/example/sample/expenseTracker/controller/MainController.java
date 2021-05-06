package com.example.sample.expenseTracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.sample.expenseTracker.model.User;
import com.example.sample.expenseTracker.service.UserService;
import com.example.sample.expenseTracker.web.model.UserModel;

@RestController
public class MainController {

	@Autowired
	UserService UserService;
	
	@RequestMapping("/")
	String welcomeMethod() {
		return "Welcome to Expense Tracking Application";
	}

	@CrossOrigin
	@PostMapping("/verifyToken/{token}")
	ResponseEntity<?> verifyToken(@RequestBody  String token) {
		User userDetails = UserService.findUserByToken(token);
		if(null != userDetails) {
			UserModel model = new UserModel();
			model.setToken(userDetails.getPasswordHash());
			model.setUser(userDetails.getName());
			return 	ResponseEntity.status(HttpStatus.OK).body(model);
		}else {
			return 	ResponseEntity.notFound().build();
		}
	}
	@CrossOrigin
	@RequestMapping("/login")
	String login() {
		return ("login.html");
		/*
		 * UserDetails userDetail = new UserDetails();
		 * System.out.println("Returning "+userDetail); return
		 * ResponseEntity.ok(userDetail);
		 */
	}

	/*
	 * @PostMapping("/login") String authenticate(@RequestBody Model model) {
	 * System.out.println("In Authenticate");
	 * 
	 * String user =(String) model.getAttribute("username"); String pwd=
	 * (String)model.getAttribute("password"); System.out.println(user);
	 * System.out.println(pwd);
	 * 
	 * return "{token: 'test123'}"; }
	 */
	@CrossOrigin
	@RequestMapping("/logout")
	String logout() {
		return "logout.html";
	}

	@CrossOrigin
	@RequestMapping("/login-error")
	String error() {
		System.out.println("IN Error");
		return "login";
	}

}