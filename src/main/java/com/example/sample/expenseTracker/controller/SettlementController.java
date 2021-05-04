
package com.example.sample.expenseTracker.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.sample.expenseTracker.model.ExpenseDetails;
import com.example.sample.expenseTracker.repository.ExpenseDetailsRepository;
import com.example.sample.expenseTracker.service.SettlementService;

@RestController
@RequestMapping("/settlements")
public class SettlementController {
	@Autowired
	private SettlementService SettlementService;
	
	@Autowired
	private ExpenseDetailsRepository expenseDetailsRepository;

	@PutMapping("/process/{id}")
	ResponseEntity<?> processSettlement(@PathVariable Long id) {
		Optional<ExpenseDetails> optExpense = expenseDetailsRepository.findById(id);
		if(optExpense.isPresent()) {
			return ResponseEntity.ok(SettlementService.credit(optExpense.get()));
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
}