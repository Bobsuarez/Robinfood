package com.robinfood.app.strategies.querystatements.helper;

import com.robinfood.core.dtos.filterstoconsult.SearchToCriteriaDTO;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class NotEqualPredicateStrategy<T> implements IQueryStatementStrategy<T> {

    @Override
    public Predicate buildPredicate(CriteriaBuilder criteriaBuilder, Root<T> root, SearchToCriteriaDTO searchToCriteriaDTO) {
        return criteriaBuilder.notEqual(root.get(searchToCriteriaDTO.getKey()), searchToCriteriaDTO.getValue());
    }
}
