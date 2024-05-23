package com.robinfood.app.usecases.getordersquerybysegmentstatement;

import com.robinfood.app.datamocks.dto.input.DataIdsToFindTheSegmentDTOMock;
import com.robinfood.app.datamocks.entity.OrderEntityMock;
import com.robinfood.app.strategies.querystatements.context.SearchQueryStrategyContext;
import com.robinfood.core.entities.OrderEntity;
import com.robinfood.repository.orders.IOrdersRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class GetOrdersQueryBySegmentStatementUseCaseTest {

    @Mock
    private IOrdersRepository ordersRepository;

    @InjectMocks
    private GetOrdersQueryBySegmentStatementUseCase getOrdersQueryBySegmentStatementUseCase;

    @Test
    void testInvokeQueryBySegmentStatement() {

        when(ordersRepository.findAll(any(SearchQueryStrategyContext.class), any(Pageable.class)))
                .thenReturn(new PageImpl<>(List.of(OrderEntityMock.getDataDefaultList())));

        List<OrderEntity> orderEntityPage = getOrdersQueryBySegmentStatementUseCase
                .invoke(LocalDateTime.now(), DataIdsToFindTheSegmentDTOMock.getDefault());

        assertNotNull(orderEntityPage);
    }
}
