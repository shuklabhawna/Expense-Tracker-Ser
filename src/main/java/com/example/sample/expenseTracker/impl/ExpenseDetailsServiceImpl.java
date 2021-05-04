package com.example.sample.expenseTracker.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sample.expenseTracker.exception.ApplicationException;
import com.example.sample.expenseTracker.model.Currency;
import com.example.sample.expenseTracker.model.ExpenseDetails;
import com.example.sample.expenseTracker.model.ExpenseType;
import com.example.sample.expenseTracker.model.User;
import com.example.sample.expenseTracker.repository.CurrencyRepository;
import com.example.sample.expenseTracker.repository.ExpenseDetailsRepository;
import com.example.sample.expenseTracker.repository.ExpenseTypeRepository;
import com.example.sample.expenseTracker.repository.UserRepository;
import com.example.sample.expenseTracker.service.ExpenseDetailsService;
import com.example.sample.expenseTracker.service.SettlementService;
import com.example.sample.expenseTracker.utilities.AppUtilities;

@Service
public class ExpenseDetailsServiceImpl implements ExpenseDetailsService {

	@Autowired
	ExpenseDetailsRepository expenseDetailsRepository;

	@Autowired
	SettlementService settlementService;
	
	@Autowired
	CurrencyRepository currencyRepository;

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ExpenseTypeRepository expenseTypeRepository;

	@Override
	public List<ExpenseDetails> findAll() {
		return expenseDetailsRepository.findAll();
	}

	@Override
	public ExpenseDetails findById(Long id) {
		return expenseDetailsRepository.findById(id)
				.orElseThrow(() -> new ApplicationException("Couldn't find ExpenseDetail with ID <" + id + ">"));
	}

	@Override
	public ExpenseDetails save(ExpenseDetails entity) {
		if(null != entity.getCurrency() && null != entity.getCurrency().getCurrencyCode()) {
			Optional<Currency> optCurrency = currencyRepository.findById(entity.getCurrency().getCurrencyCode());
			if(optCurrency.isPresent()) {
				entity.setCurrency(optCurrency.get());
			}
		}else {
			throw new ApplicationException("Currency is mandatory");
		}
		if(null != entity.getUser() &&  (0 <= entity.getUser().getId()) ) {
			Optional<User> optUser = userRepository.findById(entity.getUser().getId());
			if(optUser.isPresent()) {
				entity.setUser(optUser.get());
			}
		}else {
			throw new ApplicationException("User is mandatory");
		}
		if(null != entity.getExpenseType() &&  (0 <= entity.getExpenseType().getId()) ) {
			Optional<ExpenseType> optExpenseType = expenseTypeRepository.findById(entity.getExpenseType().getId());
			if(optExpenseType.isPresent()) {
				entity.setExpenseType(optExpenseType.get());
			}
		}else {
			throw new ApplicationException("ExpenseType is mandatory");
		}
		ExpenseDetails savedEntity = expenseDetailsRepository.save(entity);
		settlementService.debit(savedEntity);
		return savedEntity;
	}

	@Override
	public void delete(Long id) {
		Map<String, Object> queryParameter = new HashMap<String, Object>();
		queryParameter.put("id", id);
		ExpenseDetails existing = expenseDetailsRepository.findOneByParameter(queryParameter);
		if (null != existing) {
			if (AppUtilities.CLAIM_STATUS_NEW.equals(existing.getStatus())) {
				settlementService.credit(existing);
			}
			expenseDetailsRepository.delete(existing);
		}

	}

	@Override
	public ExpenseDetails findOneByParameter(Map<String, Object> queryParameter) {
		return expenseDetailsRepository.findOneByParameter(queryParameter);
	}

	@Override
	public List<ExpenseDetails> findByParameter(Map<String, Object> queryParameter) {
		return expenseDetailsRepository.findByParameter(queryParameter);
	}

	@Override
	public ExpenseDetails update(Long id, ExpenseDetails entity) {
		Map<String, Object> queryParameter = new HashMap<String, Object>();
		queryParameter.put("id",id);
		queryParameter.put("status",AppUtilities.CLAIM_STATUS_NEW);
		
		ExpenseDetails existing = expenseDetailsRepository.findOneByParameter(queryParameter);
		
		if(null != existing) {
			if((existing.getAmount().compareTo(entity.getAmount()) != 0)
					||(! existing.getCurrency().equals(entity.getCurrency()))
				){
				throw new ApplicationException("Change of amount or Currency is not allowed. Please delete and make new claim.");
			}
			existing.setExpenseType(entity.getExpenseType());
			existing.setDate(entity.getDate());
			existing.setDescription(entity.getDescription());
			expenseDetailsRepository.save(existing);
			return existing;
		}else {
			throw new ApplicationException("No Pending claim found by ID <\" + id + \">");
		}
		
	}

}
