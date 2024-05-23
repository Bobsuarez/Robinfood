package com.robinfood.app.strategies.querystatements.helper;

import com.robinfood.app.datamocks.dto.input.SearchToCriteriaDTOMock;
import com.robinfood.core.dtos.filterstoconsult.SearchToCriteriaDTO;
import com.robinfood.core.entities.OrderEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalTime;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class EqualPredicateStrategyTest {

    @Mock
    private CriteriaBuilder criteriaBuilder;

    @Mock
    private Root<OrderEntity> root;

    @Mock
    private Path<Object> path;

    @InjectMocks
    private EqualPredicateStrategy<OrderEntity> equalPredicateStrategy;

    @Test
    void testBuildPredicate() {

        SearchToCriteriaDTO searchToCriteriaDTO = SearchToCriteriaDTOMock.getDefault();
        searchToCriteriaDTO.setValue(LocalTime.now());

        when(root.get(SearchToCriteriaDTOMock.getDefault().getKey())).thenReturn(path);

        Predicate expectedPredicate = criteriaBuilder.equal(path, searchToCriteriaDTO.getValue());

        Predicate actualPredicate = equalPredicateStrategy
                .buildPredicate(criteriaBuilder, root, searchToCriteriaDTO);

        Assertions.assertEquals(expectedPredicate, actualPredicate);
    }
}
