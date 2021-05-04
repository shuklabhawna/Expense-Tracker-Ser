
package com.example.sample.expenseTracker.repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.example.sample.expenseTracker.model.FXRates;


public class FXRatesRepositoryImpl implements FXRateRepositoryCustom{

	@PersistenceContext
    private EntityManager entityManager;
	
	@Override
	public FXRates findOneByCriteria(Map<String, Object> criteriaMap) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<FXRates> criteriaQuery = createCriteriaQuery(criteriaBuilder, criteriaMap);
		List<FXRates> fxRates = entityManager.createQuery(criteriaQuery).getResultList();
		return (null != fxRates && !fxRates.isEmpty()) ? fxRates.get(0): null;
	}

	private CriteriaQuery<FXRates> createCriteriaQuery (CriteriaBuilder criteriaBuilder ,Map<String, Object> criteriaMap) {
		
		CriteriaQuery<FXRates> criteriaQuery = criteriaBuilder.createQuery(FXRates.class);
		Root<FXRates> baseCriteria = criteriaQuery.from(FXRates.class);
		
		Predicate searchPredicate = criteriaBuilder.isNotNull(baseCriteria.get("id"));
		
		if(criteriaMap.containsKey("userAccountCcyCode")
				&& criteriaMap.containsKey("settlementCcyCode")) {
		Predicate primaryCcyIsUserAccCcy
		  = criteriaBuilder.equal(baseCriteria.get("primaryCcy"), criteriaMap.get("userAccountCcyCode"));
		Predicate primaryCcyIsSettlementCcy
		  = criteriaBuilder.equal(baseCriteria.get("primaryCcy"), criteriaMap.get("settlementCcyCode"));
		
		Predicate secCcyIsUserAccCcy
		  = criteriaBuilder.equal(baseCriteria.get("secondaryCcy"), criteriaMap.get("userAccountCcyCode"));
		Predicate secCcyIsSettlementCcy
		  = criteriaBuilder.equal(baseCriteria.get("secondaryCcy"), criteriaMap.get("settlementCcyCode"));
		
		searchPredicate = criteriaBuilder.and(
				   criteriaBuilder
				  .or(
						(criteriaBuilder.and(primaryCcyIsSettlementCcy , secCcyIsUserAccCcy)),
						(criteriaBuilder.and(primaryCcyIsUserAccCcy , secCcyIsSettlementCcy))
						)
		  );
		}
		
		if(criteriaMap.containsKey("expenseDate")) {
			searchPredicate = criteriaBuilder.and(criteriaBuilder.equal(baseCriteria.get("date"), (Date)criteriaMap.get("expenseDate")));
		}
		
		criteriaQuery.where(searchPredicate);
		return criteriaQuery;
		
	}
}