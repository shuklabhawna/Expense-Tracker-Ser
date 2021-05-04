
package com.example.sample.expenseTracker.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sample.expenseTracker.exception.ApplicationException;
import com.example.sample.expenseTracker.model.ExpenseDetails;
import com.example.sample.expenseTracker.model.ExpenseType;
import com.example.sample.expenseTracker.repository.ExpenseDetailsRepository;
import com.example.sample.expenseTracker.repository.ExpenseTypeRepository;
import com.example.sample.expenseTracker.service.ExpenseTypeService;

@Service
public class ExpenseTypeServiceImpl implements ExpenseTypeService{

	@Autowired
	public ExpenseTypeRepository expenseTypeRepository;
	
	@Autowired
	private ExpenseDetailsRepository expenseDetailsRepository;
	
	
	@Override
	public List<ExpenseType> findAll() {
		return expenseTypeRepository.findAll();
	}

	@Override
	public ExpenseType findById(Long id) {
		return expenseTypeRepository.findById(id).orElseThrow(
				() -> new ApplicationException("Couldn't find ExpenseType with ID <" + id + ">")
				);
	}

	@Override
	public ExpenseType save(ExpenseType entity) {
		return expenseTypeRepository.save(entity);
	}

	@Override
	public void delete(Long expenseTypeId) {
		Map<String, Object> queryParameter = new HashMap<String, Object>();
		queryParameter.put("expenseType", expenseTypeId);
		List<ExpenseDetails> linkedExpenseDetails =
				expenseDetailsRepository.findByParameter(queryParameter);
		if(null != linkedExpenseDetails && !linkedExpenseDetails.isEmpty()) {
			throw new ApplicationException("Couldn't delete ExpenseType with ID <" + expenseTypeId + ">");
		}
		expenseTypeRepository.deleteById(expenseTypeId);
		
	}

	@Override
	public ExpenseType findOneByCode(String code) {
		return expenseTypeRepository.findByCode(code);
	}

}