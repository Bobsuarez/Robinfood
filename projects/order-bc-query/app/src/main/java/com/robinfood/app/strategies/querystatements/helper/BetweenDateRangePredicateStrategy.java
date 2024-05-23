package com.robinfood.app.strategies.querystatements.helper;

import com.robinfood.core.dtos.filterstoconsult.SearchToCriteriaDTO;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.List;

import static com.robinfood.core.constants.GlobalConstants.DEFAULT_BOOLEAN_FALSE_VALUE_AS_INT;
import static com.robinfood.core.constants.GlobalConstants.DEFAULT_BOOLEAN_TRUE_VALUE_AS_INT;

public class BetweenDateRangePredicateStrategy<T> implements IQueryStatementStrategy<T> {

    @Override
    public Predicate buildPredicate(CriteriaBuilder criteriaBuilder, Root<T> root, SearchToCriteriaDTO searchToCriteriaDTO) {

        final List<LocalDate> localDateList = (List<LocalDate>) searchToCriteriaDTO.getValue();

        return criteriaBuilder
                .between(root.get(searchToCriteriaDTO.getKey()),
                        localDateList.get(DEFAULT_BOOLEAN_FALSE_VALUE_AS_INT),
                        localDateList.get(DEFAULT_BOOLEAN_TRUE_VALUE_AS_INT));
    }
}
