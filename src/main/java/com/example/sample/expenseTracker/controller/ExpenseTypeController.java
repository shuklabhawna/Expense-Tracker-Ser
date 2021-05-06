
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

import com.example.sample.expenseTracker.exception.ApplicationException;
import com.example.sample.expenseTracker.model.ExpenseType;
import com.example.sample.expenseTracker.service.ExpenseTypeService;

@RestController
@RequestMapping("/expenseTypes")
public class ExpenseTypeController {

	@Autowired
	public ExpenseTypeService expenseTypeService;

	@GetMapping("/list")
	List<ExpenseType> getExpenseTypes() {
		return expenseTypeService.findAll();
	}

	@GetMapping("/find/{id}")
	ResponseEntity<?> findExpenseType(@PathVariable Long id) {
		return ResponseEntity.ok(expenseTypeService.findById(id));
	}

	@GetMapping("/search/{code}")
	ExpenseType searchExpenseType(@PathVariable String code) {
		return expenseTypeService.findOneByCode(code);
	}

	@PostMapping("/save")
	ExpenseType saveExpenseType(@RequestBody ExpenseType expenseType) {
		return expenseTypeService.save(expenseType);
	}

	@PutMapping("/update/{id}")
	ResponseEntity<?> updateExpenseType(@PathVariable Long id, @RequestBody ExpenseType expenseType) {
		ExpenseType existing = expenseTypeService.findById(id);
		if (null != existing) {
			existing.setCode(expenseType.getCode());
			existing.setDescription(expenseType.getDescription());
			expenseTypeService.save(existing);
			return ResponseEntity.ok(existing);
		} else {
			return ResponseEntity.notFound().build();
		}

	}

	@DeleteMapping("/delete/{id}")
	ResponseEntity<?> deleteExpenseType(@PathVariable Long id){
		try{	
			expenseTypeService.delete(id);
			return ResponseEntity.ok().build();
		}catch (Exception e) {
			throw new ApplicationException(e.getMessage());
		}

	}
}