package com.example.sample.expenseTracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.sample.expenseTracker.model.Currency;
import com.example.sample.expenseTracker.repository.CurrencyRepository;

@RestController
@RequestMapping("/currencies")
public class CurrencyController {

	
	@Autowired
	private CurrencyRepository currencyRepository;

	@GetMapping("/list")
	List<Currency> getCurrencies() {
		return currencyRepository.findAll();
	}
}
