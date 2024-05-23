package com.robinfood.repository.ordertotaldailysales;

import com.robinfood.core.dtos.OrderTotalDailySalesDTO;
import com.robinfood.core.dtos.OrderTotalDailySalesResponseDTO;
import com.robinfood.core.entities.APIResponseEntity;
import com.robinfood.core.enums.Result;
import com.robinfood.core.extensions.ObjectExtensions;
import com.robinfood.network.api.OrderBcAPI;
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
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OrderTotalDailySalesTest {

    @Mock
    private OrderBcQueriesAPI orderBcQueriesAPI;

    @Mock
    private Call<APIResponseEntity<List<OrderTotalDailySalesResponseDTO>>> mockOrderTotalDailySales;

    @InjectMocks
    private OrderTotalDailySalesRepository orderTotalDailySalesRepository;

    private final String token = "token";

    @Test
    void test_GetOrderTotalDailySales_OK() throws Exception {
        OrderTotalDailySalesResponseDTO orderTotalDailySales =
            new OrderTotalDailySalesResponseDTO(10.0, 1, "Prueba", 10);
        List<OrderTotalDailySalesResponseDTO> orderTotalDailySalesList = new ArrayList<>();
        orderTotalDailySalesList.add(orderTotalDailySales);

        APIResponseEntity<List<OrderTotalDailySalesResponseDTO>> apiResponseEntity = new APIResponseEntity<>(
            200,
            orderTotalDailySalesList,
            "CO",
            "",
            "Order history returned"
        );
        when(orderBcQueriesAPI.getOrderTotalDailySales(token,"1", LocalDate.parse("2022-05-05")))
            .thenReturn(mockOrderTotalDailySales);
        when(mockOrderTotalDailySales.execute()).thenReturn(Response.success(apiResponseEntity));
        final Result<List<OrderTotalDailySalesDTO>> result =
            orderTotalDailySalesRepository.getOrderTotalDailySales(token, 1, LocalDate.parse("2022-05-05"));
        assertTrue(result instanceof Result.Success);
    }

    @Test
    void test_GetOrderTotalDailySales_Error() throws Exception {
        OrderTotalDailySalesDTO orderTotalDailySales =
            new OrderTotalDailySalesDTO("10.0", 1, "Prueba", 10);
        List<OrderTotalDailySalesDTO> orderTotalDailySalesList = new ArrayList<>();
        orderTotalDailySalesList.add(orderTotalDailySales);

        when(orderBcQueriesAPI.getOrderTotalDailySales(token,"1",LocalDate.parse("2022-05-05")))
            .thenReturn(mockOrderTotalDailySales);
        when(mockOrderTotalDailySales.execute()).thenReturn(Response.error(500, ResponseBody.create(
            MediaType.parse("application/json"),
            ObjectExtensions.toJson(orderTotalDailySalesList)
        )));
        final Result<List<OrderTotalDailySalesDTO>> result =
            orderTotalDailySalesRepository.getOrderTotalDailySales(token, 1, LocalDate.parse("2022-05-05"));
        assertTrue(result instanceof Result.Error);
    }
}
