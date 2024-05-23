package com.robinfood.repository.exitstransactionuuidorderuid;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

import com.robinfood.core.entities.ApiResponseEntity;
import com.robinfood.core.entities.ExistsTransactionUuidOrderUuidEntity;
import com.robinfood.core.extensions.ObjectExtensions;
import com.robinfood.network.api.OrderBCApi;

import java.util.List;

import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.ResponseBody;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import retrofit2.Call;
import retrofit2.Response;

@ExtendWith(MockitoExtension.class)
class ExitsTransactionUuidOrderUidDataSourceTest {

    @Mock
    private OrderBCApi mockOrderBCApi;

    @Mock
    private Call<ApiResponseEntity<ExistsTransactionUuidOrderUuidEntity>> apiResponseEntityCall;

    @InjectMocks
    private ExitsTransactionUuidOrderUuidDataSource exitsTransactionUuidOrderUidDataSource;

    final ApiResponseEntity<ExistsTransactionUuidOrderUuidEntity> apiOrdersEntity = ApiResponseEntity.<ExistsTransactionUuidOrderUuidEntity>builder()
            .code(200)
            .data(ExistsTransactionUuidOrderUuidEntity.builder()
                    .message("Exits")
                    .exits(true)
                    .build())
            .locale("CO")
            .message("OK")
            .build();

    final ApiResponseEntity<ExistsTransactionUuidOrderUuidEntity> apiOrdersEntityError = ApiResponseEntity.<ExistsTransactionUuidOrderUuidEntity>builder()
            .code(401)
            .error("Error")
            .locale("CO")
            .message("JWT token for service http://localhost:8083/ is unauthorized")
            .build();

    @Test
    void test_ExitsTransactionUuidOrderUidDataSource_Is_Successfully() throws Exception {

        when(mockOrderBCApi.getExitsTransactionUuidOrderUuids(any(), any(), anyList())).thenReturn(
                apiResponseEntityCall);
        when(apiResponseEntityCall.execute()).thenReturn(Response.success(apiOrdersEntity));

        ExistsTransactionUuidOrderUuidEntity result =
                exitsTransactionUuidOrderUidDataSource.invoke("token", "uuid",
                        List.of("erwetsg", "fwgsdrhdsh"));

        assertTrue(result.isExits());
    }

    @Test
    void test_ExitsTransactionUuidOrderUidDataSource_Error() throws Exception {

        String responseJSON = ObjectExtensions.toJson(apiOrdersEntityError);

        when(mockOrderBCApi.getExitsTransactionUuidOrderUuids(any(), any(), anyList())).thenReturn(
                apiResponseEntityCall);

        when(apiResponseEntityCall.execute()).thenReturn(Response.error(401, ResponseBody.create(
                MediaType.parse("application/json"),
                responseJSON)));

        Request.Builder requestBuilder = new Request.Builder().url("http://localhost:8083");

        when(apiResponseEntityCall.request()).thenReturn(requestBuilder.build());

        try {
            exitsTransactionUuidOrderUidDataSource.invoke("token", "uuid",
                    List.of("erwetsg", "fwgsdrhdsh"));
        } catch (Exception exception) {
            assertTrue(exception.getLocalizedMessage().contains(apiOrdersEntityError.getMessage()));
        }
    }

}
