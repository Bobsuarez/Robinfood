package com.robinfood.repository.changestatusorders;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import com.robinfood.core.entities.ApiResponseEntity;
import com.robinfood.core.entities.changestatusordersrequestentities.ChangeStatusOrdersRequestEntity;
import com.robinfood.core.extensions.ObjectExtensions;
import com.robinfood.network.api.OrderBCApi;
import com.robinfood.repository.mocks.ChangeStatusOrdersRequestEntityMocks;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import retrofit2.Call;
import retrofit2.Response;

@ExtendWith(MockitoExtension.class)
class ChangeStatusOrdersRemoteDataSourceTest {

    @Mock
    private OrderBCApi mockOrderBCApi;

    @Mock
    private Call<ApiResponseEntity> mockApiResponseEntityCall;

    @InjectMocks
    private ChangeStatusOrdersRemoteDataSource changeStatusOrdersRemoteDataSource;

    private final String token = "token";
    private final ChangeStatusOrdersRequestEntity changeStatusOrdersRequestEntity = new
            ChangeStatusOrdersRequestEntityMocks().changeStatusOrdersRequestEntity;

    private final ApiResponseEntity<Object> apiResponseEntityChangeStatus = ApiResponseEntity.builder()
            .code(200)
            .data(true)
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
    void test_Change_Status_Orders_Remote_Data_Source_Success() throws Exception {
        when(mockOrderBCApi.changeStatus(changeStatusOrdersRequestEntity, token))
                .thenReturn(mockApiResponseEntityCall);

        when(mockApiResponseEntityCall.execute()).thenReturn(Response.success(apiResponseEntityChangeStatus));

        final Boolean result = changeStatusOrdersRemoteDataSource.invoke(changeStatusOrdersRequestEntity, token)
                .join();

        assertTrue(result);
    }

    @Test
    void test_Change_Status_Orders_Remote_Data_Source_Unauthorized() throws Exception {
        final String responseJSON = ObjectExtensions.toJson(apiUnauthorizedResponseChangeStatus);

        when(mockOrderBCApi.changeStatus(changeStatusOrdersRequestEntity, token))
                .thenReturn(mockApiResponseEntityCall);

        when(mockApiResponseEntityCall.execute())
                .thenReturn(Response.error(500, ResponseBody.create(
                        MediaType.parse("application/json"),
                        responseJSON
                )));

        final Exception exception = assertThrows(Exception.class, () -> {
            changeStatusOrdersRemoteDataSource.invoke(changeStatusOrdersRequestEntity, token);
        });

        final String expectedMessage = "Orders status updated sync Error";
        final String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}
