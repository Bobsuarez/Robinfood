package com.robinfood.repository.orderdailysalessummary;

import com.robinfood.core.dtos.OrderDailySaleSummaryDTO;
import com.robinfood.core.entities.APIResponseEntity;
import com.robinfood.core.entities.OrderDailySaleSummaryEntity;
import com.robinfood.core.enums.Result;
import com.robinfood.core.extensions.ObjectExtensions;
import com.robinfood.network.api.OrderBcQueriesAPI;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import retrofit2.Call;
import retrofit2.Response;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OrderDailySalesSummaryRepositoryTest {

    @Mock
    private OrderBcQueriesAPI orderBcQueriesAPI;

    @Mock
    private Call<APIResponseEntity<OrderDailySaleSummaryEntity>> mockOrderDailySaleSummary;

    @InjectMocks
    private OrderDailySalesSummaryRepository orderDailySalesSummaryRepository;

    private final String token = "token";

    @Test
    void test_GetOrderDailySalesSummary_OK() throws Exception {

        OrderDailySaleSummaryEntity orderDailySaleSummaryEntity = new OrderDailySaleSummaryEntity();

        APIResponseEntity<OrderDailySaleSummaryEntity> apiResponseEntity = new APIResponseEntity<>(
            200,
            orderDailySaleSummaryEntity,
            "CO",
            "",
            "Order history returned"
        );

        when(orderBcQueriesAPI.getOrderDailySalesSummary(token,1L, LocalDate.parse("2022-05-05")))
            .thenReturn(mockOrderDailySaleSummary);

        when(mockOrderDailySaleSummary.execute()).thenReturn(Response.success(apiResponseEntity));

        final Result<OrderDailySaleSummaryDTO> result = orderDailySalesSummaryRepository
            .getOrderDailySalesSummary(token,1L, LocalDate.parse("2022-05-05"));

        assertTrue(result instanceof Result.Success);

    }

    @Test
    void test_GetOrderTotalDailySales_Error() throws Exception {

        OrderDailySaleSummaryEntity orderDailySaleSummaryEntity = new OrderDailySaleSummaryEntity();

        APIResponseEntity<OrderDailySaleSummaryEntity> apiResponseEntity = new APIResponseEntity<>(
            200,
            orderDailySaleSummaryEntity,
            "CO",
            "",
            "Order history returned"
        );

        when(orderBcQueriesAPI.getOrderDailySalesSummary(token,1L, LocalDate.parse("2022-05-05")))
            .thenReturn(mockOrderDailySaleSummary);

        when(mockOrderDailySaleSummary.execute()).thenReturn(Response.error(500, ResponseBody.create(
            MediaType.parse("application/json"),
            ObjectExtensions.toJson(apiResponseEntity)
        )));

        final Result<OrderDailySaleSummaryDTO> result = orderDailySalesSummaryRepository
            .getOrderDailySalesSummary(token,1L, LocalDate.parse("2022-05-05"));
        assertTrue(result instanceof Result.Error);
    }
}
