package com.robinfood.repository.useractiveorder;

import com.robinfood.core.dtos.orderbyuser.ResponseOrderDTO;
import com.robinfood.core.entities.APIResponseEntity;
import com.robinfood.core.enums.Result;
import com.robinfood.core.extensions.ObjectExtensions;
import com.robinfood.network.api.OrderBcAPI;
import com.robinfood.repository.mocks.ResponseActiveOrderMock;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import retrofit2.Call;
import retrofit2.Response;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

/**
 * Test of UserOrderDetailRepository
 */
@ExtendWith(MockitoExtension.class)
public class UserActiveOrderRepositoryTest {

    @Mock
    private OrderBcAPI orderBcAPI;

    @Mock
    private Call<APIResponseEntity<List<ResponseOrderDTO>>> mockValidateGetActiveOrdersCall;

    @InjectMocks
    private UserActiveOrderRepository userActiveOrderRepository;

    private final String token = "token";

    @Test
    void test_GetActiveOrders_Returns_With_Failure() throws Exception {
        when(orderBcAPI.getActiveOrdersByUser(
            token,
            1L
        )).thenReturn(mockValidateGetActiveOrdersCall);

        when(mockValidateGetActiveOrdersCall.execute()).thenReturn(Response.error(500, ResponseBody.create(
            MediaType.parse("application/json"),
            ObjectExtensions.toJson(apiResponseErrorEntity)
        )));

        final Result<List<ResponseOrderDTO>> result = userActiveOrderRepository.getActiveOrders(
            token,
            1L
        );
        assertTrue(result instanceof Result.Error);
    }

    @Test
    void test_GetActiveOrders_Returns_Successfully() throws Exception {
        when(orderBcAPI.getActiveOrdersByUser(
            token,
            1L
        )).thenReturn(mockValidateGetActiveOrdersCall);

        when(mockValidateGetActiveOrdersCall.execute()).thenReturn(Response.success(apiResponseEntity));

        final Result<List<ResponseOrderDTO>> result = userActiveOrderRepository.getActiveOrders(
            token,
            1L
        );
        assertTrue(result instanceof Result.Success);
        assertEquals(
            ResponseActiveOrderMock.getResponseActiveOrderMock(),
            ((Result.Success<List<ResponseOrderDTO>>) result).getData()
        );
    }

    private final APIResponseEntity<List<ResponseOrderDTO>> apiResponseErrorEntity = new APIResponseEntity<>(
        500,
        null,
        "CO",
        "Error",
        "Active orders could not be returned"
    );

    private final APIResponseEntity<List<ResponseOrderDTO>> apiResponseEntity = new APIResponseEntity<>(
        200,
        ResponseActiveOrderMock.getResponseActiveOrderMock(),
        "CO",
        "",
        "Active orders returned"
    );
}
