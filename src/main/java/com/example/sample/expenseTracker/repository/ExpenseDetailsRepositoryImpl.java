
package com.example.sample.expenseTracker.repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.example.sample.expenseTracker.model.ExpenseDetails;
import com.example.sample.expenseTracker.model.ExpenseType;
import com.example.sample.expenseTracker.model.User;

@Repository
public class ExpenseDetailsRepositoryImpl implements ExpenseDetailsRepositoryCustom {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<ExpenseDetails> findByParameter(Map<String, Object> queryParameter) {
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<ExpenseDetails> criteriaQuery = createCriteriaQuery(criteriaBuilder, queryParameter);
		List<ExpenseDetails> expenseDetails = entityManager.createQuery(criteriaQuery).getResultList();
		
		return expenseDetails;
	}

	private CriteriaQuery<ExpenseDetails> createCriteriaQuery(CriteriaBuilder criteriaBuilder,
			Map<String, Object> criteriaMap) {

		CriteriaQuery<ExpenseDetails> criteriaQuery = criteriaBuilder.createQuery(ExpenseDetails.class);
		Root<ExpenseDetails> baseCriteria = criteriaQuery.from(ExpenseDetails.class);
		Predicate searchPredicate = criteriaBuilder.isNotNull(baseCriteria.get("id"));

		if (criteriaMap.containsKey("expenseType") ) {
			Path<ExpenseType> expenseType = baseCriteria.get("expenseType");
			
			Predicate expenseTypePredicate = criteriaBuilder.equal(expenseType.get("id"), criteriaMap.get("expenseType"));
			searchPredicate = criteriaBuilder
					.and(expenseTypePredicate);
		}

		if (criteriaMap.containsKey("expenseDate")) {
			Predicate expenseDatePredicate = criteriaBuilder.equal(baseCriteria.get("date"), (Date) criteriaMap.get("expenseDate"));
			searchPredicate = criteriaBuilder
					.and(expenseDatePredicate);
		}
		if (criteriaMap.containsKey("userId")) {
			Path<User> user = baseCriteria.get("user");
			Predicate userIdPredicate = criteriaBuilder.equal(user.get("id"), criteriaMap.get("userId"));
			searchPredicate = criteriaBuilder
					.and(userIdPredicate);
		}
		if (criteriaMap.containsKey("status")) {
			Predicate userIdPredicate = criteriaBuilder.equal(baseCriteria.get("status"), criteriaMap.get("status"));
			searchPredicate = criteriaBuilder
					.and(userIdPredicate);
		}
		criteriaQuery.where(searchPredicate);
		return criteriaQuery;

	}

	@Override
	public ExpenseDetails findOneByParameter(Map<String, Object> queryParameter) {

		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<ExpenseDetails> criteriaQuery = createCriteriaQuery(criteriaBuilder, queryParameter);
		List<ExpenseDetails> expenseDetails = entityManager.createQuery(criteriaQuery).getResultList();
		
		return null != expenseDetails? expenseDetails.get(0):null;
	}
}