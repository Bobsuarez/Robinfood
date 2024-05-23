package com.robinfood.app.strategies.querystatements.helper;

import com.robinfood.core.dtos.filterstoconsult.SearchToCriteriaDTO;
import com.robinfood.core.entities.OrderPaymentEntity;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class JoinOrderIntegrationPredicateStrategy<T> implements IQueryStatementStrategy<T> {

    @Override
    public Predicate buildPredicate(CriteriaBuilder criteriaBuilder, Root<T> root, SearchToCriteriaDTO searchToCriteriaDTO) {

        Join<OrderPaymentEntity, T> authorsBook = root.join("orderIntegrationEntityList");
        return criteriaBuilder.like(authorsBook.get(searchToCriteriaDTO.getKey()), "%" + searchToCriteriaDTO.getValue() + "%");
    }
}
