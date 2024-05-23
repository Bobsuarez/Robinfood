package com.robinfood.app.usecases.orderdetailfilter;

import com.robinfood.app.datamocks.entity.OrderEntityMock;
import com.robinfood.core.entities.OrderEntity;
import com.robinfood.repository.orders.IOrdersRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderDetailFilterUseCaseTest {

    @Mock
    private IOrdersRepository mockOrdersRepository;

    @InjectMocks
    private OrderDetailFilterUseCase orderDetailFilterUseCase;

    private final OrderEntityMock orderEntityMock = new OrderEntityMock();


    @Test
    void Test_invoke_Should_RespondToOrdersAccordingToFilters_When_LetsInvokeTheUseCase() {

        OrderEntity orderEntity = orderEntityMock.getDataDefault();
        Pageable pageable = PageRequest.of(0, 10);

        when(
                mockOrdersRepository
                        .findByStoreIdAndOrderNumberAndOrderInvoiceNumberContainingCreatedAtBetweenOrderByCreatedAtDesc(
                                any(LocalDateTime.class),
                                any(LocalDateTime.class),
                                any(Pageable.class),
                                anyString(),
                                anyString(),
                                anyLong()
                        ))
                .thenReturn(new PageImpl<>(List.of(orderEntity), pageable, 1));

        orderDetailFilterUseCase.invoke(
                1,
                "filter",
                LocalDate.now(),
                LocalDate.now(),
                10,
                1L,
                "America/Bogota"
        );

        Mockito.verify(mockOrdersRepository)
                .findByStoreIdAndOrderNumberAndOrderInvoiceNumberContainingCreatedAtBetweenOrderByCreatedAtDesc(
                        any(LocalDateTime.class),
                        any(LocalDateTime.class),
                        any(Pageable.class),
                        anyString(),
                        anyString(),
                        anyLong()
                );
    }

}