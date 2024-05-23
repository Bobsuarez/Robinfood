package com.robinfood.repository.changestateorder;

import com.robinfood.core.entities.ApiResponseEntity;
import com.robinfood.core.entities.changestateorderequestentities.ChangeStateOrderRequestEntity;
import com.robinfood.core.entities.changestateorderequestentities.ChangeStateOrderRespondEntity;
import com.robinfood.core.entities.redeemcoupon.PerformCouponResponseEntity;
import com.robinfood.core.extensions.ObjectExtensions;
import com.robinfood.network.api.OrderBCApi;
import com.robinfood.repository.changestateorders.ChangeStateOrderRemoteDataSource;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import retrofit2.Call;
import retrofit2.Response;

import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ChangeStateOrderRemoteDataSourceTest {

    @InjectMocks
    ChangeStateOrderRemoteDataSource changeStateOrderRemoteDataSource;

    @Mock
    private OrderBCApi orderBCApi;

    @Mock
    private Call<ApiResponseEntity<ChangeStateOrderRespondEntity>> mockChangeState;

    private final String token = "token";

    private final ChangeStateOrderRespondEntity changeStateOrderRequestEntity = new ChangeStateOrderRespondEntity();

    private final ApiResponseEntity<ChangeStateOrderRespondEntity> apiResponseEntityChangeStatus = ApiResponseEntity.<ChangeStateOrderRespondEntity>builder()
            .code(200)
            .data(changeStateOrderRequestEntity)
            .locale("CO")
            .message("Orders status updated success")
            .build();

    private final ApiResponseEntity<Boolean> apiUnauthorizedResponseChangeStatus = ApiResponseEntity.<Boolean>builder()
            .code(500)
            .error("Error")
            .locale("CO")
            .message("Orders status updated sync Error")
            .build();

    @Test
    void test_Change_State_Orders_Remote_Data_Source_Success() throws Exception {

        ChangeStateOrderRequestEntity changeStateOrderRequestEntity = new ChangeStateOrderRequestEntity();

        ChangeStateOrderRespondEntity changeStateOrderRespondEntity = new ChangeStateOrderRespondEntity();

        when(orderBCApi.changeState(changeStateOrderRequestEntity, token)).thenReturn(mockChangeState);

        when(mockChangeState.execute()).thenReturn(Response.success(apiResponseEntityChangeStatus));

        final ChangeStateOrderRespondEntity result = changeStateOrderRemoteDataSource.invoke(
                changeStateOrderRequestEntity, token)
                .join();

        assertEquals(changeStateOrderRespondEntity, result);
    }

    @Test
    void test_Change_Status_Orders_Remote_Data_Source_Unauthorized() throws Exception {

        ChangeStateOrderRequestEntity changeStateOrderRequestEntity = new ChangeStateOrderRequestEntity();

        final String responseJSON = ObjectExtensions.toJson(apiUnauthorizedResponseChangeStatus);

        when(orderBCApi.changeState(changeStateOrderRequestEntity, token))
                .thenReturn(mockChangeState);

        when(mockChangeState.execute())
                .thenReturn(Response.error(500, ResponseBody.create(
                        MediaType.parse("application/json"),
                        responseJSON
                )));

        final Exception exception = assertThrows(Exception.class, () -> {
            changeStateOrderRemoteDataSource.invoke(changeStateOrderRequestEntity, token);
        });

        final String expectedMessage = "Orders status updated sync Error";
        final String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}
