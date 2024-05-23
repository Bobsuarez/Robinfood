package com.robinfood.app.usecases.getordercategory;

import com.robinfood.app.datamocks.entity.OrderEntityMock;
import com.robinfood.app.datamocks.entity.OrderFinalProductEntityMock;
import com.robinfood.app.datamocks.entity.OrderServicesEntityMock;
import com.robinfood.core.entities.OrderServicesEntity;
import com.robinfood.core.entities.StatusEntity;
import com.robinfood.repository.orderfinalproducts.IOrderFinalProductRepository;
import com.robinfood.repository.orders.IOrdersRepository;
import com.robinfood.repository.orderservices.IOrderServiceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetOrderCategoryUseCaseTest {

    @Mock
    private IOrdersRepository mockOrdersRepository;

    @Mock
    private IOrderFinalProductRepository mockOrderFinalProductRepository;

    @Mock
    private IOrderServiceRepository mockOrderServiceRepository;

    @InjectMocks
    private GetOrderCategoryUseCase getOrderCategoryUseCase;

    final private OrderEntityMock orderEntityMock = new OrderEntityMock();

    final private OrderFinalProductEntityMock orderFinalProductEntityMock = new OrderFinalProductEntityMock();

    @Test
    void test_invoke_Should_ReturnOrderCategories_WhenInvokeUseCase() {

        when(mockOrdersRepository.findByCreatedAtBetweenAndPaidAndPosIdAndStatusIdNot(
                any(LocalDateTime.class),
                any(LocalDateTime.class),
                anyBoolean(),
                anyLong(),
                anyLong()
        )).thenReturn(Optional.of(List.of(orderEntityMock.getDataDefault())));

        when(mockOrderFinalProductRepository.findAllByOrderIdIn(
                anyList()
        )).thenReturn(orderFinalProductEntityMock.getDataDefaultList());

        when(mockOrderServiceRepository.findAllByOrderIdIsIn(anyList())).thenReturn(
                List.of(OrderServicesEntityMock.getDataDefault())
        );

        getOrderCategoryUseCase.invoke(LocalDate.now(), LocalDate.now(), 1L, "America/Bogota");

        verify(mockOrderFinalProductRepository)
                .findAllByOrderIdIn(anyList());

    }

}
