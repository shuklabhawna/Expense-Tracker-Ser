package com.example.sample.expenseTracker.controller;

import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.sample.expenseTracker.model.UserDetails;

@RestController
public class MainController {
	
	@RequestMapping("/")
	String welcomeMethod() {
		return "Welcome to Expense Tracking Application";
	}
	
	@RequestMapping("/login")
	ResponseEntity<?>  login() {
		System.out.println("In Login");
		UserDetails userDetail = new UserDetails();
		System.out.println("Returning "+userDetail);
		return ResponseEntity.ok(userDetail);
	}
	
	@PostMapping("/login")
	String  authenticate(@RequestBody Model model) {
		System.out.println("In Authenticate");
		/*
		 * String user =(String) model.getAttribute("username"); String pwd=
		 * (String)model.getAttribute("password"); System.out.println(user);
		 * System.out.println(pwd);
		 */
		return "{token: 'test123'}";
	}
	@RequestMapping("/logout")
	String logout() {
		return "logout.html";
	}
	
	@RequestMapping("/login-error")
	String error() {
		System.out.println("IN Error");
		return "login.html";
	}

}