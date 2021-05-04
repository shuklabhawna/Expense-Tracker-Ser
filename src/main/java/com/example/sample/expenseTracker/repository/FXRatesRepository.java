package com.example.sample.expenseTracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sample.expenseTracker.model.FXRates;

public interface FXRatesRepository extends JpaRepository<FXRates, Long>,FXRateRepositoryCustom{

}