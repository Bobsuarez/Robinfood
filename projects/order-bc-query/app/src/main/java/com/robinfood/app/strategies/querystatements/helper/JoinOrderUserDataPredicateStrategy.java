package com.robinfood.app.strategies.querystatements.helper;

import com.robinfood.core.dtos.filterstoconsult.SearchToCriteriaDTO;
import com.robinfood.core.entities.OrderUserDataEntity;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class JoinOrderUserDataPredicateStrategy<T> implements IQueryStatementStrategy<T> {

    @Override
    public Predicate buildPredicate(CriteriaBuilder criteriaBuilder, Root<T> root, SearchToCriteriaDTO searchToCriteriaDTO) {

        Join<OrderUserDataEntity, T> authorsBook = root.join("userDataEntity");
        return criteriaBuilder.like(authorsBook.get(searchToCriteriaDTO.getKey()), "%" + searchToCriteriaDTO.getValue() + "%");
    }
}
