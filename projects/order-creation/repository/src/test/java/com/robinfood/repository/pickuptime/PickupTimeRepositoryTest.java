package com.robinfood.repository.pickuptime;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.robinfood.core.entities.ApiResponseEntity;
import com.robinfood.core.entities.DiscountParentEntity;
import com.robinfood.core.exceptions.WriteInTransactionException;
import com.robinfood.core.extensions.ObjectExtensions;
import com.robinfood.core.mappers.PickupTimeMappers;
import com.robinfood.core.models.domain.pickuptime.PickupTime;
import com.robinfood.core.models.domain.pickuptime.Store;
import com.robinfood.core.models.retrofit.pickuptime.PickupTimeResponse;
import com.robinfood.network.api.StoreConfigurationsAPI;
import com.robinfood.repository.mocks.TransactionRequestDTOMocks;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import retrofit2.Call;
import retrofit2.Response;

@ExtendWith(MockitoExtension.class)
class PickupTimeRepositoryTest {

    private static final Long STORE_ID = 312L;

    @Mock
    private StoreConfigurationsAPI storeConfigurationsAPI;

    @Mock
    private PickupTimeMappers pickupTimeMappers;

    @Mock
    private Call<ApiResponseEntity<List<PickupTimeResponse>>> responseRetrofit;

    @InjectMocks
    private PickupTimeRepository repository;

    final ApiResponseEntity<DiscountParentEntity> apiErrorResponseEntity = ApiResponseEntity.<DiscountParentEntity>builder()
            .code(500)
            .error("Error")
            .locale("CO")
            .message("Error in store configurations")
            .build();

    @Test
    void given_transaction_then_return_pickup_time() throws IOException {
        // Arrange
        var token = "token";
        var transactionRequest = new TransactionRequestDTOMocks().transactionRequestDTO;
        var pickupTime = PickupTime.builder()
                .stores(Collections.singletonList(
                        Store.builder()
                                .id(STORE_ID)
                                .build()
                ))
                .build();

        when(storeConfigurationsAPI.getByTransaction(anyString(), any())).thenReturn(responseRetrofit);
        when(responseRetrofit.execute()).thenReturn(
                Response.success(ApiResponseEntity.<List<PickupTimeResponse>>builder()
                        .code(200)
                        .data(Collections.singletonList(PickupTimeResponse.builder().build()))
                        .locale("CO")
                        .build()
                )
        );

        when(pickupTimeMappers.toDomain(any())).thenReturn(pickupTime);

        // Act
        var response = repository.getByTransaction(token, transactionRequest);

        // Assert
        Assertions.assertNotNull(response);
        Assertions.assertEquals(pickupTime.getStores().get(0).getId(),
                response.getStores().get(0).getId());
    }

    @Test
    void test_validate_pickup_time_when_api_response_with_failure() throws Exception {
        // Arrange
        var token = "token";
        var responseJSON = ObjectExtensions.toJson(apiErrorResponseEntity);
        var transactionRequest = new TransactionRequestDTOMocks().transactionRequestDTO;

        when(storeConfigurationsAPI.getByTransaction(any(), any())).thenReturn(responseRetrofit);
        when(responseRetrofit.execute()).thenReturn(Response.error(
                500, ResponseBody.create(
                        MediaType.parse("application/json"),
                        responseJSON
                )));

        // Act - Assert
        try {
            repository.getByTransaction(
                    token,
                    transactionRequest
            );
        } catch (WriteInTransactionException exception) {
            assertTrue(exception.getLocalizedMessage().contains(apiErrorResponseEntity.getMessage()));
        }
    }

}
