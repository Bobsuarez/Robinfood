package com.robinfood.app.usecases.getstoreorders;

import com.robinfood.app.datamocks.entity.OrderEntityMock;
import com.robinfood.app.datamocks.entity.StatusEntityMock;
import com.robinfood.core.entities.StatusEntity;
import com.robinfood.repository.orders.IOrdersRepository;
import com.robinfood.repository.status.IStatusRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetStoreOrdersUseCaseTest {

    @Mock
    private IOrdersRepository mockOrdersRepository;

    @Mock
    private IStatusRepository mockStatusRepository;

    @InjectMocks
    private GetStoreOrdersUseCase getStoreOrdersUseCase;

    final private OrderEntityMock orderEntityMock = new OrderEntityMock();
    @Test
    void test_invoke_Should_ReturnStoreOrders_WhenInvokeUseCase() {

        when(mockOrdersRepository.findByCreatedAtBetweenAndPaidAndStoreIdOrderByCreatedAtAsc(
                any(LocalDateTime.class),
                any(LocalDateTime.class),
                anyBoolean(),
                anyLong()
        )).thenReturn(Optional.of(List.of(OrderEntityMock.getDataDefault())));

        when(mockStatusRepository.findAll()).thenReturn(List.of(StatusEntityMock.getDataDefault()));

        getStoreOrdersUseCase.invoke(LocalDate.now(), LocalDate.now(), 1L, "America/Bogota");

        verify(mockOrdersRepository)
                .findByCreatedAtBetweenAndPaidAndStoreIdOrderByCreatedAtAsc(
                        any(LocalDateTime.class),
                        any(LocalDateTime.class),
                        anyBoolean(),
                        anyLong()
                );
    }
}