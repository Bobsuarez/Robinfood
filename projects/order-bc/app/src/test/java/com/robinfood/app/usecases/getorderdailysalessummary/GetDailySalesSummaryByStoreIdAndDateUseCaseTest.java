package com.robinfood.app.usecases.getorderdailysalessummary;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import com.robinfood.app.usecases.getdailysalessummary.GetDailySalesSummaryByStoreIdAndDateUseCase;
import com.robinfood.core.dtos.OrderDailySaleSummaryDTO;
import com.robinfood.core.entities.OrderEntity;
import com.robinfood.repository.orders.IOrdersRepository;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class GetDailySalesSummaryByStoreIdAndDateUseCaseTest {

    @Mock
    private IOrdersRepository mockOrdersRepository;

    @InjectMocks
    private GetDailySalesSummaryByStoreIdAndDateUseCase mockUseCase;

    private final List<OrderEntity> orderEntities = new ArrayList<>(Arrays.asList(
        new OrderEntity(
            1L,
            1L,
            "Brand Name",
            1L,
            LocalDateTime.of(2021, 10, 11, 10, 10, 10),
            "CO",
            1L,
            0.0,
            1,
            1L,
            LocalDate.of(2021, 10, 11),
            LocalTime.of(10, 10, 10),
            1,
            LocalDate.of(2021, 10, 11),
            "476383",
            "234234",
            1L,
            "Origin Name",
            Boolean.TRUE,
            1L,
            "Pickup Time",
            1L,
                Boolean.TRUE,
                1L,
                1L,
                "Store Name",
                0.0,
                0.0,
                1L,
                BigDecimal.valueOf(0.0),
                BigDecimal.TEN.doubleValue(),
                "uid",
                UUID.randomUUID().toString(),
                LocalDateTime.now(),
                1L,
                1L
        )

    ));

    private final OrderDailySaleSummaryDTO orderDailySaleSummaryDTO = OrderDailySaleSummaryDTO.builder()
        .salesSummary(BigDecimal.TEN.doubleValue())
        .ordersNumber(BigDecimal.ONE.intValue())
        .build();


    @Test
    void test_Get_order_daily_sales_summary_ok() {
        when(mockOrdersRepository
            .findAllByStoreIdAndOperationDateAndPaidAndStatusIdNotIn(anyLong(), any(), any(), anyList()))
            .thenReturn(orderEntities);

        OrderDailySaleSummaryDTO result = mockUseCase.invoke(20L, LocalDate.of(2022, 3, 17));
        Assertions.assertEquals(orderDailySaleSummaryDTO, result);
    }
}
