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
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class JoinOrderIntegrationPredicateStrategyTest<T> {

    @Mock
    private Root<T> root;

    @Mock
    private CriteriaBuilder criteriaBuilder;

    @InjectMocks
    private JoinOrderIntegrationPredicateStrategy<T> tJoinOrderIntegrationPredicateStrategy;

    @Test
    void TestBuildPredicate() {

        when(root.join(anyString())).thenReturn(mock(Join.class));

        tJoinOrderIntegrationPredicateStrategy
                .buildPredicate(criteriaBuilder, root, SearchToCriteriaDTOMock.getDefault());
    }
}
