
package com.example.sample.expenseTracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.sample.expenseTracker.model.ExpenseDetails;
import com.example.sample.expenseTracker.service.ExpenseDetailsService;

@RestController
@RequestMapping("/expenseDetails")
public class ExpenseDetailsController {
	
	@Autowired
	ExpenseDetailsService expenseDetailsService;
	
	@PostMapping(value = "/new" )
	ResponseEntity<?> submitClaim(@RequestBody ExpenseDetails expenseDetail){
		return ResponseEntity.ok(expenseDetailsService.save(expenseDetail));
	}
	
	@GetMapping("/list")
	List<ExpenseDetails> find(){
		return expenseDetailsService.findAll();
	}
	
	@DeleteMapping("/delete/{id}")
	ResponseEntity<?> delete(@PathVariable Long id){
		 expenseDetailsService.delete(id);
		 return ResponseEntity.ok().build();
	}
	
	@PutMapping("/update/{id}")
	ResponseEntity<?> update(@PathVariable Long id, @RequestBody ExpenseDetails expenseDetails){
		ExpenseDetails updated = expenseDetailsService.update(id, expenseDetails);
		return ResponseEntity.ok(updated);
	}

}