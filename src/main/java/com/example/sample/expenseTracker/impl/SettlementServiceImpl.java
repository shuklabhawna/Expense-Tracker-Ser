
package com.example.sample.expenseTracker.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sample.expenseTracker.model.Accounts;
import com.example.sample.expenseTracker.model.ExpenseDetails;
import com.example.sample.expenseTracker.model.FXRates;
import com.example.sample.expenseTracker.model.User;
import com.example.sample.expenseTracker.repository.AccountsRepository;
import com.example.sample.expenseTracker.repository.ExpenseDetailsRepository;
import com.example.sample.expenseTracker.repository.FXRatesRepository;
import com.example.sample.expenseTracker.service.SettlementService;
import com.example.sample.expenseTracker.utilities.AppUtilities;

@Service
public class SettlementServiceImpl implements SettlementService{
	private final Logger logger = LoggerFactory
            .getLogger(SettlementServiceImpl.class);


	@Autowired
	private AccountsRepository accountsRepository;
	
	@Autowired
	private ExpenseDetailsRepository expenseDetailsRepository;
	
	@Autowired
	private FXRatesRepository fxRateRepository;
	
	private Optional<ExpenseDetails> getExpenseDetails(Long expenseDetailId){
		return expenseDetailsRepository.findById(expenseDetailId);
	}
	
	@Override 
	public String getBalance(Long accountId) {
		Optional<Accounts> optAccount = accountsRepository.findById(accountId);
		if(optAccount.isPresent()) {
			return optAccount.get().getBalanceWithCurrency();
		}else {
			return "";
		}
	}
	@Override
	public ExpenseDetails credit(ExpenseDetails expenseDetails) {
		logger.info("Settlement for expense detail with id = "+expenseDetails.getId());
		String ccyCode = expenseDetails.getCurrency().getCurrencyCode();
		User user = expenseDetails.getUser();
		Date expenseDate = expenseDetails.getDate();
		Accounts userAccount = accountsRepository.findByUser(user);
		BigDecimal creditAmount = new BigDecimal(0);
		if(null != userAccount) {
			if(ccyCode.equals(userAccount.getCurrency().getCurrencyCode())) {
				creditAmount = expenseDetails.getAmount();
			}else {
				Map<String, Object> queryParameters = new HashMap<>();
		        queryParameters.put("userAccountCcyCode", userAccount.getCurrency());
		        queryParameters.put("settlementCcyCode", ccyCode);
		        queryParameters.put("expenseDate", expenseDate);
		        
		        FXRates rate = fxRateRepository.findOneByCriteria(queryParameters);
		        
		        if(null == rate) {
					rate=FXRates.getDummyFXRates();
					rate.setPrimaryCcy(ccyCode);
					rate.setSecondaryCcy(userAccount.getCurrency().getCurrencyCode());
				}
				
					logger.info("Due to currency diff the amount %d is converted at rate %d ",expenseDetails.getAmount(),rate.getExchaneRate());
					if(ccyCode.equals(rate.getPrimaryCcy())) {
						creditAmount =  expenseDetails.getAmount().multiply(rate.getExchaneRate());
					}else {
						creditAmount =  expenseDetails.getAmount().divide(rate.getExchaneRate());
					}
				
			}
			BigDecimal currentBalance = userAccount.getBalance();
			BigDecimal updateBalance = currentBalance.add(creditAmount);
			
			logger.info("Crediting %d amount to account number % . Old Balance was %d and new Balance is %d",creditAmount,userAccount.getAccountCode(),currentBalance,updateBalance);
			userAccount.setBalance(updateBalance);
			accountsRepository.save(userAccount);
			expenseDetails.setStatus(AppUtilities.CLAIM_STATUS_SETTLED);
			expenseDetailsRepository.save(expenseDetails);
		
		}else {
			logger.info("No account for user "+expenseDetails.getUser().getName());
		}
		return expenseDetails;
	}
	
	@Override
	public boolean debit(ExpenseDetails expenseDetails) {
		logger.info("Debit for expense detail with id = "+expenseDetails.getId());
		String ccyCode = expenseDetails.getCurrency().getCurrencyCode();
		User user = expenseDetails.getUser();
		Date expenseDate = expenseDetails.getDate();
		Accounts userAccount = accountsRepository.findByUser(user);
		BigDecimal debitAmount = new BigDecimal(0);
		
		if(null != userAccount) {
			
		
		if(ccyCode.equals(userAccount.getCurrency().getCurrencyCode())) {
			debitAmount = expenseDetails.getAmount();
		}else {
			Map<String, Object> queryParameters = new HashMap<>();
	        queryParameters.put("userAccountCcyCode", userAccount.getCurrency());
	        queryParameters.put("settlementCcyCode", ccyCode);
	        queryParameters.put("expenseDate", expenseDate);
	        
	        FXRates rate = fxRateRepository.findOneByCriteria(queryParameters);
			if(null == rate) {
				rate=FXRates.getDummyFXRates();
				rate.setPrimaryCcy(ccyCode);
				rate.setSecondaryCcy(userAccount.getCurrency().getCurrencyCode());
			}
				logger.info("Due to currency diff the amount %d is converted at rate %d for Debit",expenseDetails.getAmount(),rate.getExchaneRate());
				if(ccyCode.equals(rate.getPrimaryCcy())) {
					debitAmount =  expenseDetails.getAmount().multiply(rate.getExchaneRate());
				}else {
					debitAmount =  expenseDetails.getAmount().divide(rate.getExchaneRate());
				}
			
		}
		
		BigDecimal currentBalance = userAccount.getBalance();
		BigDecimal updateBalance = currentBalance.subtract(debitAmount);
		
		logger.info("Debit %d amount from account number % . Old Balance was %d and new Balance is %d",updateBalance,userAccount.getAccountCode(),currentBalance,updateBalance);
		userAccount.setBalance(updateBalance);
		accountsRepository.save(userAccount);
		}else {
			logger.info("No account for user "+expenseDetails.getUser().getName());
		}
		return true;
	}

}