package com.robinfood.repository.orderdaily;

import com.robinfood.core.entities.APIResponseEntity;
import com.robinfood.core.entities.OrderDailyEntity;
import com.robinfood.core.extensions.ObjectExtensions;
import com.robinfood.network.api.OrderBcAPI;
import com.robinfood.repository.mocks.OrderDailyEntityMock;
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

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderDailyRepositoryTest {

    @Mock
    private OrderBcAPI orderBcAPI;

    @Mock
    private Call<APIResponseEntity<List<OrderDailyEntity>>> mockOrderDailyEntityCall;

    @InjectMocks
    private OrderDailyRepository orderDailyRepository;

    @Test
    void test_GetOrderDetail_Returns_Successfully() throws Exception {

        when(orderBcAPI.getOrderDaily(
                anyLong(),
                anyString(),
                anyString()
        )).thenReturn(mockOrderDailyEntityCall);

        when(mockOrderDailyEntityCall.execute()).thenReturn(Response.success(new APIResponseEntity<>(
                200,
                List.of(OrderDailyEntityMock.getDataDefault()),
                "CO",
                "",
                "Order daily returned"
        )));

        orderDailyRepository.getOrderDaily(anyLong(), anyString(), anyString());

        verify(orderBcAPI).getOrderDaily(anyLong(), anyString(), anyString());
    }

    @Test
    void test_GetOrderDetail_Returns_With_Failure() throws Exception {
        
        when(orderBcAPI.getOrderDaily(
                anyLong(),
                anyString(),
                anyString()
        )).thenReturn(mockOrderDailyEntityCall);

        when(mockOrderDailyEntityCall.execute()).thenReturn(Response.error(500, ResponseBody.create(
                        MediaType.parse("application/json"),
                        ObjectExtensions.toJson(new APIResponseEntity<>(
                                500,
                                List.of(OrderDailyEntityMock.getDataDefault()),
                                "CO",
                                "",
                                "Order daily not be returned"
                        ))
                ))
        );

        orderDailyRepository.getOrderDaily(anyLong(), anyString(), anyString());

        verify(orderBcAPI).getOrderDaily(anyLong(), anyString(), anyString());
    }
}