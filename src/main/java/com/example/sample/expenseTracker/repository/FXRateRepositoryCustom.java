package com.example.sample.expenseTracker.repository;

import java.util.Map;

import com.example.sample.expenseTracker.model.FXRates;

public interface FXRateRepositoryCustom {

	 public FXRates findOneByCriteria(Map<String, Object> queryParameters);
}