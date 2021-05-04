
package com.example.sample.expenseTracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sample.expenseTracker.model.Currency;

public interface CurrencyRepository extends JpaRepository<Currency, String>{

}