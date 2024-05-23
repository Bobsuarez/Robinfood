package com.robinfood.app.strategies.querystatements.context;

import com.robinfood.app.datamocks.dto.input.SearchToCriteriaDTOMock;
import com.robinfood.app.strategies.querystatements.helper.LikePredicateStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class SearchQueryStrategyContextTest<T> {

    @Mock
    private CriteriaBuilder criteriaBuilder;

    @Mock
    private CriteriaQuery<T> criteriaQuery;

    @Mock
    private Root<T> root;

    @InjectMocks
    private SearchQueryStrategyContext<T> searchQueryStrategyContext;

    @BeforeEach
    public void setup() {
        searchQueryStrategyContext = new SearchQueryStrategyContext<>();
    }

    @Test
    void testToPredicate() {
        searchQueryStrategyContext.add(SearchToCriteriaDTOMock.getDefault(), "", new LikePredicateStrategy<>());
        Predicate predicateResult = searchQueryStrategyContext.toPredicate(root, criteriaQuery, criteriaBuilder);
        assertNull(predicateResult);
    }
}
