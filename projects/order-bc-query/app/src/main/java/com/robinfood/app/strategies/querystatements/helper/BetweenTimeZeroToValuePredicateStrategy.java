package com.robinfood.app.strategies.querystatements.helper;

import com.robinfood.core.dtos.filterstoconsult.SearchToCriteriaDTO;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalTime;

import static com.robinfood.core.constants.DateTimeConstants.START_HOUR;
import static com.robinfood.core.constants.DateTimeConstants.START_MINUTE;

public class BetweenTimeZeroToValuePredicateStrategy<T> implements IQueryStatementStrategy<T> {

    @Override
    public Predicate buildPredicate(CriteriaBuilder criteriaBuilder, Root<T> root, SearchToCriteriaDTO searchToCriteriaDTO) {

        return criteriaBuilder.between(root.get(searchToCriteriaDTO.getKey()),
                LocalTime.of(START_HOUR, START_MINUTE),
                LocalTime.parse(searchToCriteriaDTO.getValue().toString()));
    }
}
