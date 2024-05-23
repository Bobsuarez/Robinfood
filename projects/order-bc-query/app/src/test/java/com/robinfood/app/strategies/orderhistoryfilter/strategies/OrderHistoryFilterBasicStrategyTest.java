package com.robinfood.app.strategies.orderhistoryfilter.strategies;

import com.robinfood.core.dtos.request.orderhistory.OrderHistoryRequestDTO;
import com.robinfood.repository.orders.IOrdersRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static com.robinfood.core.constants.GlobalConstants.DEFAULT_BOOLEAN_TRUE_VALUE_AS_INT;
import static com.robinfood.core.constants.GlobalConstants.DEFAULT_CURRENT_PAGE;
import static com.robinfood.core.constants.GlobalConstants.DEFAULT_PER_PAGE;
import static com.robinfood.core.constants.GlobalConstants.LOCAL_DATE_TIME_END;
import static com.robinfood.core.constants.GlobalConstants.LOCAL_DATE_TIME_START;
import static com.robinfood.core.constants.GlobalConstants.ORDER_PAID;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderHistoryFilterBasicStrategyTest {

    @InjectMocks
    private OrderHistoryFilterBasicStrategy orderHistoryFilterBasicStrategy;

    @Mock
    private IOrdersRepository mockOrdersRepository;

    @Test
    void test_execute_should_Orders_when_ExecuteStrategy() {

        final LocalDateTime localDateTimeEnd = LocalDateTime.now();
        final LocalDateTime LocalDateTimeStart = LocalDateTime.now();

        when(mockOrdersRepository.findAllByStoreIdAndDeliveryTypeIdInAndPaidAndCreatedAtBetweenOrderByCreatedAtDesc(
                1L,
                List.of(1L),
                ORDER_PAID,
                LocalDateTimeStart,
                localDateTimeEnd,
                PageRequest.of(
                        Integer.parseInt(DEFAULT_CURRENT_PAGE) - DEFAULT_BOOLEAN_TRUE_VALUE_AS_INT,
                        Integer.parseInt(DEFAULT_PER_PAGE)
                )
        )).thenReturn(
                Page.empty()
        );

        orderHistoryFilterBasicStrategy.execute(
                List.of(1L),
                Map.of(LOCAL_DATE_TIME_START, LocalDateTimeStart, LOCAL_DATE_TIME_END, localDateTimeEnd),
                OrderHistoryRequestDTO.builder()
                        .storeId(1L)
                        .currentPage(Integer.parseInt(DEFAULT_CURRENT_PAGE))
                        .perPage(Integer.parseInt(DEFAULT_PER_PAGE))
                        .build()
        );

        verify(mockOrdersRepository)
                .findAllByStoreIdAndDeliveryTypeIdInAndPaidAndCreatedAtBetweenOrderByCreatedAtDesc(
                        anyLong(),
                        anyList(),
                        anyBoolean(),
                        any(LocalDateTime.class),
                        any(LocalDateTime.class),
                        any(PageRequest.class)
                );

    }

}