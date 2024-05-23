package com.robinfood.app.strategies.querystatements.helper;

import com.robinfood.app.datamocks.dto.input.SearchToCriteriaDTOMock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Root;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class InPredicateStrategyTest<T> {

    @Mock
    private Root<T> root;

    @Mock
    private CriteriaBuilder criteriaBuilder;

    @InjectMocks
    private InPredicateStrategy<T> inPredicateStrategy;

    @Test
    void testBuildPredicate() {

       assertThrows(NullPointerException.class, () -> {
            inPredicateStrategy.buildPredicate(criteriaBuilder, root, SearchToCriteriaDTOMock.getDefault());
        });
    }
}
