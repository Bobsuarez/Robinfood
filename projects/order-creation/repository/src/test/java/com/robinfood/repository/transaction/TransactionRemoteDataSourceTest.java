package com.robinfood.repository.transaction;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import com.robinfood.core.entities.ApiResponseEntity;
import com.robinfood.core.entities.transactionrequestentities.TransactionRequestEntity;
import com.robinfood.core.entities.transactionresponseentities.OrderResponseEntity;
import com.robinfood.core.entities.transactionresponseentities.TransactionCreationResponseEntity;
import com.robinfood.core.entities.transactionresponseentities.TransactionResponseEntity;
import com.robinfood.core.exceptions.TransactionCreationException;
import com.robinfood.core.extensions.ObjectExtensions;
import com.robinfood.core.models.retrofit.order.pickuptime.PickupTimeModelResponseRest;
import com.robinfood.core.models.retrofit.order.pickuptime.PickupTimeModelRest;
import com.robinfood.network.api.OrderBCApi;
import com.robinfood.repository.mocks.TransactionRequestEntityMocks;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
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
class TransactionRemoteDataSourceTest {

    @Mock
    private OrderBCApi mockOrderBCApi;

    @Mock
    private Call<ApiResponseEntity<TransactionCreationResponseEntity>> mockOrderCreationResult;

    @Mock
    private Call<ApiResponseEntity<List<PickupTimeModelResponseRest>>> mockSavePickupTime;

    @InjectMocks
    private TransactionRemoteDataSource orderRemoteDataSource;

    private final String token = "token";

    private final TransactionRequestEntityMocks transactionRequestEntityMocks = new TransactionRequestEntityMocks();

    private final TransactionRequestEntity transactionRequestEntity = transactionRequestEntityMocks.transactionRequestEntity;

    private final List<OrderResponseEntity> orderResponseEntities = Collections.singletonList(
            new OrderResponseEntity(
                    BigDecimal.ZERO,
                    Collections.emptyList(),
                    1L,
                    "000-111",
                    "1501",
                    null,
                    BigDecimal.valueOf(10000.0),
                    BigDecimal.ZERO,
                    BigDecimal.valueOf(10000.0),
                    "uid",
                    "0ee82497-834d-4943-9f4c-5c61eb87f4fc"
            )
    );

    private final TransactionResponseEntity transactionResponseEntity = new TransactionResponseEntity(
            1L,
            "",
            orderResponseEntities,
            "uuid"
    );

    private final TransactionCreationResponseEntity transactionCreationResponseEntity = new TransactionCreationResponseEntity(
            transactionResponseEntity
    );

    final ApiResponseEntity<TransactionCreationResponseEntity> apiResponseEntity = ApiResponseEntity.<TransactionCreationResponseEntity>builder()
            .code(200)
            .data(transactionCreationResponseEntity)
            .locale("CO")
            .message("Success")
            .build();

    final ApiResponseEntity<TransactionCreationResponseEntity> apiErrorResponseEntity = ApiResponseEntity.<TransactionCreationResponseEntity>builder()
            .code(500)
            .error("Error")
            .locale("CO")
            .message("Order could not be create")
            .build();

    final ApiResponseEntity<List<PickupTimeModelResponseRest>> apiSavePickupTimeResponseEntity = ApiResponseEntity.<List<PickupTimeModelResponseRest>>builder()
            .code(200)
            .data(List.of(PickupTimeModelResponseRest.builder().build(), PickupTimeModelResponseRest.builder().build()))
            .locale("CO")
            .message("Success")
            .build();

    final ApiResponseEntity<List<PickupTimeModelResponseRest>> apiSavePickupTimeErrorResponseEntity = ApiResponseEntity.<List<PickupTimeModelResponseRest>>builder()
            .code(500)
            .error("Error")
            .locale("CO")
            .message("Order could not be created")
            .build();

    @Test
    void test_CreateOrder_Returns_Correctly() throws Exception {

        when(mockOrderBCApi.createTransaction(token, transactionRequestEntity))
                .thenReturn(mockOrderCreationResult);

        when(mockOrderCreationResult.execute()).thenReturn(Response.success(apiResponseEntity));

        final TransactionCreationResponseEntity result = orderRemoteDataSource.createTransaction(token,
                transactionRequestEntity).get();

        assertEquals(transactionCreationResponseEntity, result);
    }

    @Test
    void test_CreateOrder_Returns_WithFailure() throws Exception {
        final String responseJSON = ObjectExtensions.toJson(apiErrorResponseEntity);

        when(mockOrderBCApi.createTransaction(token, transactionRequestEntity))
                .thenReturn(mockOrderCreationResult);

        when(mockOrderCreationResult.execute()).thenReturn(Response.error(500, ResponseBody.create(
                MediaType.parse("application/json"),
                responseJSON
        )));

        try {
            orderRemoteDataSource.createTransaction(token, transactionRequestEntity).get();
        } catch (Exception exception) {
            assertTrue(exception.getLocalizedMessage().contains(apiErrorResponseEntity.getMessage()));
        }
    }

    @Test
    void test_savePickupTime_Returns_Correctly() throws Exception {

        when(mockOrderBCApi.savePickupTime(eq(token), any(PickupTimeModelRest.class)))
                .thenReturn(mockSavePickupTime);

        when(mockSavePickupTime.execute()).thenReturn(Response.success(apiSavePickupTimeResponseEntity));

        final List<PickupTimeModelResponseRest> result = orderRemoteDataSource.savePickupTime(token,
                PickupTimeModelRest.builder().build());

        assertNotNull(result);
    }

    @Test
    void test_savePickupTime_Returns_WithFailure() throws Exception {
        final String responseJSON = ObjectExtensions.toJson(apiSavePickupTimeErrorResponseEntity);

        final PickupTimeModelRest request = PickupTimeModelRest.builder().build();

        when(mockOrderBCApi.savePickupTime(eq(token), any(PickupTimeModelRest.class)))
                .thenReturn(mockSavePickupTime);

        when(mockSavePickupTime.execute()).thenReturn(Response.error(500, ResponseBody.create(
                MediaType.parse("application/json"),
                responseJSON
        )));

        TransactionCreationException result = assertThrows(
                TransactionCreationException.class,
                () -> orderRemoteDataSource.savePickupTime(token, request)
        );

        assertTrue(result.getLocalizedMessage().contains(apiSavePickupTimeErrorResponseEntity.getMessage()));
    }
}
