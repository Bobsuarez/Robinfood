package com.robinfood.repository.getordersbytransaction;

import com.robinfood.core.entities.ApiResponseEntity;
import com.robinfood.core.entities.getordersbytransaction.OrdersByTransactionEntity;
import com.robinfood.core.extensions.ObjectExtensions;
import com.robinfood.network.api.OrderBCApi;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetOrdersByTransactionRemoteDataSourceTest {

    @Mock
    private OrderBCApi mockOrderBCApi;

    @InjectMocks
    private GetOrdersByTransactionRemoteDataSource getOrdersByTransactionRemoteDataSource;

    @Mock
    private Call<ApiResponseEntity<List<OrdersByTransactionEntity>>> mockOrdersBytransaction;

    private final String token = "token";

    private final Long transactionId = 3L;

    final ApiResponseEntity<List<OrdersByTransactionEntity>> apiOrdersEntity = ApiResponseEntity.<List<OrdersByTransactionEntity>>builder()
            .code(200)
            .data(
                    List.of(OrdersByTransactionEntity.builder()
                            .billingResolutionId(1L)
                            .brandId(4L)
                            .brandName("Pixi")
                            .companyId(1L)
                            .currency("COP")
                            .deliveryTypeId(1L)
                            .discounts(2500D)
                            .id(3328802L)
                            .originId(10L)
                            .statusId(2L)
                            .transactionId(3L)
                            .userId(55318L)
                            .build())
            )
            .locale("CO")
            .message("OK")
            .build();

    final ApiResponseEntity<Boolean> apiErrorResponseOrders = ApiResponseEntity.<Boolean>builder()
            .code(400)
            .error("Error")
            .message("Error obtain orders")
            .build();

    @Test
    void test_GetOrdersByTransactionRemoteDataSource_Is_Successfully() throws Exception {
        OrdersByTransactionEntity order =  OrdersByTransactionEntity.builder()
            .billingResolutionId(1L)
            .brandId(4L)
            .brandName("Pixi")
            .companyId(1L)
            .currency("COP")
            .deliveryTypeId(1L)
            .discounts(2500D)
            .id(3328802L)
            .originId(10L)
            .statusId(2L)
            .transactionId(3L)
            .userId(55318L)
            .build();

        when(mockOrderBCApi.getOrdersByTransactionId(any(), any())).thenReturn(mockOrdersBytransaction);
        when(mockOrdersBytransaction.execute()).thenReturn(Response.success(apiOrdersEntity));

        List<OrdersByTransactionEntity> result = getOrdersByTransactionRemoteDataSource.invoke(transactionId, token);

        assertEquals(order.getTransactionId(), result.get(0).getTransactionId());
    }

    @Test
    void test_GetOrdersByTransactionRemoteDataSource_Error() throws Exception {

        String responseJSON = ObjectExtensions.toJson(apiErrorResponseOrders);

        when(mockOrderBCApi.getOrdersByTransactionId(any(), any())).thenReturn(mockOrdersBytransaction);
        when(mockOrdersBytransaction.execute()).thenReturn(Response.error(500, ResponseBody.create(
            MediaType.parse("application/json"),
            responseJSON
        )));

        try {
            getOrdersByTransactionRemoteDataSource.invoke(transactionId, token);
        } catch (Exception exception) {
            assertTrue(exception.getLocalizedMessage().contains(apiErrorResponseOrders.getMessage()));
        }
    }
}
