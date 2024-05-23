package com.robinfood.app.strategies.querystatements.context;

import com.robinfood.app.strategies.querystatements.helper.IQueryStatementStrategy;
import com.robinfood.core.constants.GlobalConstants;
import com.robinfood.core.dtos.filterstoconsult.SearchToCriteriaDTO;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
public class SearchQueryStrategyContext<T> implements Specification<T> {

    private Map<String, IQueryStatementStrategy<T>> predicateBuilders = new HashMap<>();

    private List<SearchToCriteriaDTO> searchCriteriaList = new ArrayList<>();

    private void setPredicate(String column, IQueryStatementStrategy<T> operation) {
        predicateBuilders.put(column, operation);
    }

    public void add(SearchToCriteriaDTO criteria, Object value, IQueryStatementStrategy<T> operation) {

        if (Objects.nonNull(value)) {
            searchCriteriaList.add(criteria);
            setPredicate(criteria.getKey(), operation);
        }
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

        List<Predicate> predicates = new ArrayList<>();

        for (SearchToCriteriaDTO searchToCriteriaDTO : searchCriteriaList) {
            IQueryStatementStrategy<T> predicateBuilder = predicateBuilders.get(searchToCriteriaDTO.getKey());
            Predicate predicate = predicateBuilder.buildPredicate(criteriaBuilder, root, searchToCriteriaDTO);
            predicates.add(predicate);
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[GlobalConstants.DEFAULT_INTEGER_VALUE]));
    }
}
