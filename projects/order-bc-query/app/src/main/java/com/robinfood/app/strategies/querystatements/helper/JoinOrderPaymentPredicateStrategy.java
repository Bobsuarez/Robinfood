package com.robinfood.app.strategies.querystatements.helper;

import com.robinfood.core.dtos.filterstoconsult.SearchToCriteriaDTO;
import com.robinfood.core.entities.OrderPaymentEntity;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class JoinOrderPaymentPredicateStrategy<T> implements IQueryStatementStrategy<T> {

    @Override
    public Predicate buildPredicate(CriteriaBuilder criteriaBuilder, Root<T> root, SearchToCriteriaDTO searchToCriteriaDTO) {

        Join<OrderPaymentEntity, T> authorsBook = root.join("paymentEntityList");
        return criteriaBuilder.in(authorsBook.get(searchToCriteriaDTO.getKey())).value(searchToCriteriaDTO.getValue());
    }
}
