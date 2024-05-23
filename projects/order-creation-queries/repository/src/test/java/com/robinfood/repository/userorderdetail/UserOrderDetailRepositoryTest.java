package com.robinfood.repository.userorderdetail;

import com.robinfood.core.dtos.orderbyuser.ResponseOrderDetailDTO;
import com.robinfood.core.entities.APIResponseEntity;
import com.robinfood.core.enums.Result;
import com.robinfood.core.extensions.ObjectExtensions;
import com.robinfood.network.api.OrderBcAPI;
import com.robinfood.repository.mocks.ResponseOrderDetailMock;
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
 * Test of UserOrderDetailRepository
 */
@ExtendWith(MockitoExtension.class)
public class UserOrderDetailRepositoryTest {

    @Mock
    private OrderBcAPI orderBcAPI;

    @Mock
    private Call<APIResponseEntity<ResponseOrderDetailDTO>> mockValidateGetOrderDetailCall;

    @InjectMocks
    private UserOrderDetailRepository userOrderDetailRepository;

    private final String token = "token";

    @Test
    void test_GetOrderDetail_Returns_With_Failure() throws Exception {
        when(orderBcAPI.getOrderDetailByUser(
            token,
            1L,
            "12345abcde"
        )).thenReturn(mockValidateGetOrderDetailCall);

        when(mockValidateGetOrderDetailCall.execute()).thenReturn(Response.error(500, ResponseBody.create(
            MediaType.parse("application/json"),
            ObjectExtensions.toJson(apiResponseErrorEntity)
        )));

        final Result<ResponseOrderDetailDTO> result = userOrderDetailRepository.getOrderDetail(
            "12345abcde",
            token,
            1L
        );
        assertTrue(result instanceof Result.Error);
    }

    @Test
    void test_GetOrderDetail_Returns_Successfully() throws Exception {
        when(orderBcAPI.getOrderDetailByUser(
            token,
            1L,
            "12345abcde"
        )).thenReturn(mockValidateGetOrderDetailCall);

        when(mockValidateGetOrderDetailCall.execute()).thenReturn(Response.success(apiResponseEntity));

        final Result<ResponseOrderDetailDTO> result = userOrderDetailRepository.getOrderDetail(
            "12345abcde",
            token,
            1L
        );
        assertTrue(result instanceof Result.Success);
        assertEquals(
            ResponseOrderDetailMock.getResponseOrderDetail(),
            ((Result.Success<ResponseOrderDetailDTO>) result).getData()
        );
    }

    private final APIResponseEntity<ResponseOrderDetailDTO> apiResponseErrorEntity = new APIResponseEntity<>(
        500,
        null,
        "CO",
        "Error",
        "Order detail could not be returned"
    );

    private final APIResponseEntity<ResponseOrderDetailDTO> apiResponseEntity = new APIResponseEntity<>(
        200,
        ResponseOrderDetailMock.getResponseOrderDetail(),
        "CO",
        "",
        "Order detail returned"
    );
}
