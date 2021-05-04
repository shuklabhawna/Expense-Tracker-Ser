package com.example.sample.expenseTracker.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class MainController {
	
	@RequestMapping("/")
	String welcomeMethod() {
		return "Welcome to Expense Tracking Application";
	}

}
