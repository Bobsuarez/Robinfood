package com.robinfood.app.usecases.ordersquerystatement;

import com.robinfood.app.datamocks.dto.input.DataIdsToFindOrderCancellationDTOMock;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class OrdersQueryStatementUseCaseTest {

    @Mock
    private IOrdersRepository ordersRepository;

    @InjectMocks
    private OrdersQueryStatementUseCase ordersQueryStatementUseCase;

    @Test
    void testInvokeQueryStatement() {

        when(ordersRepository.findAll(any(SearchQueryStrategyContext.class), any(Pageable.class)))
                .thenReturn(new PageImpl<>(List.of(OrderEntityMock.getDataDefaultList())));

        Page<OrderEntity> orderEntityPage = ordersQueryStatementUseCase.invoke(DataIdsToFindOrderCancellationDTOMock.getDefault());

        assertNotNull(orderEntityPage);
    }

}