package com.robinfood.repository.userorderhistory;

import com.robinfood.core.dtos.EntityDTO;
import com.robinfood.core.dtos.orderbyuser.ResponseOrderDTO;
import com.robinfood.core.entities.APIResponseEntity;
import com.robinfood.core.enums.Result;
import com.robinfood.core.extensions.ObjectExtensions;
import com.robinfood.network.api.OrderBcAPI;
import com.robinfood.repository.mocks.ResponseOrderHistoryMock;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import retrofit2.Call;
import retrofit2.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

/**
 * Test of UserOrderHistoryRepository
 */
@ExtendWith(MockitoExtension.class)
public class UserOrderHistoryRepositoryTest {

    @Mock
    private OrderBcAPI orderBcAPI;

    @Mock
    private Call<APIResponseEntity<EntityDTO<ResponseOrderDTO>>> mockValidateGetOrderHistoryCall;

    @InjectMocks
    private UserOrderHistoryRepository userOrderHistoryRepository;

    private final String token = "token";

    @Test
    void test_GetOrderHistory_Returns_With_Failure() throws Exception {
        when(orderBcAPI.getOrderHistoryByUser(
            token,
            1L,
            1,
            10
        )).thenReturn(mockValidateGetOrderHistoryCall);

        when(mockValidateGetOrderHistoryCall.execute()).thenReturn(Response.error(500, ResponseBody.create(
            MediaType.parse("application/json"),
            ObjectExtensions.toJson(apiResponseErrorEntity)
        )));

        final Result<EntityDTO<ResponseOrderDTO>> result = userOrderHistoryRepository.getOrderHistory(
            1,
            10,
            token,
            1L
        );
        assertTrue(result instanceof Result.Error);
    }

    @Test
    void test_GetOrderHistory_Returns_Successfully() throws Exception {
        when(orderBcAPI.getOrderHistoryByUser(
            token,
            1L,
            1,
            10
        )).thenReturn(mockValidateGetOrderHistoryCall);

        when(mockValidateGetOrderHistoryCall.execute()).thenReturn(Response.success(apiResponseEntity));

        final Result<EntityDTO<ResponseOrderDTO>> result = userOrderHistoryRepository.getOrderHistory(
            1,
            10,
            token,
            1L
        );
        assertTrue(result instanceof Result.Success);
        assertEquals(
            ResponseOrderHistoryMock.getEntityResponseOrderHistoryMock(),
            ((Result.Success<EntityDTO<ResponseOrderDTO>>) result).getData()
        );
    }

    private final APIResponseEntity<EntityDTO<ResponseOrderDTO>> apiResponseErrorEntity = new APIResponseEntity<>(
        500,
        null,
        "CO",
        "Error",
        "Order history could not be returned"
    );

    private final APIResponseEntity<EntityDTO<ResponseOrderDTO>> apiResponseEntity = new APIResponseEntity<>(
        200,
        ResponseOrderHistoryMock.getEntityResponseOrderHistoryMock(),
        "CO",
        "",
        "Order history returned"
    );
}
