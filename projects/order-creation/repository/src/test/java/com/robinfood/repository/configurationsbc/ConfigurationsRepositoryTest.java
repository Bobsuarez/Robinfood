package com.robinfood.repository.configurationsbc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.robinfood.core.dtos.transactionrequestdto.ChannelDTO;
import com.robinfood.core.entities.ApiResponseEntity;
import com.robinfood.core.exceptions.TransactionCreationException;
import com.robinfood.core.extensions.ObjectExtensions;
import com.robinfood.core.models.domain.configuration.Channel;
import com.robinfood.core.models.domain.configuration.Store;
import com.robinfood.core.models.domain.configuration.StoreInformation;
import com.robinfood.core.models.retrofit.configuration.PosResponse;
import com.robinfood.network.api.ConfigurationsBCAPI;

import java.io.IOException;

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
class ConfigurationsRepositoryTest {

    @Mock
    private ConfigurationsBCAPI configurationsBCAPI;

    @Mock
    private ConfigurationsDataSource mockConfigurationsDatasource;

    @Mock
    private Call<ApiResponseEntity<PosResponse>> mockApiCall;

    @Mock
    private Call<ApiResponseEntity<StoreInformation>> mockApiCallStore;


    @InjectMocks
    private ConfigurationsRepository repository;

    ApiResponseEntity<PosResponse> apiErrorResponseEntity = ApiResponseEntity.<PosResponse>builder()
            .code(500)
            .error("Error")
            .locale("CO")
            .message("Error in configurations")
            .build();

    ApiResponseEntity<StoreInformation> apiErrorResponseEntityStoreInfo = ApiResponseEntity.<StoreInformation>builder()
            .code(500)
            .error("Error")
            .locale("CO")
            .message("Error in configurations")
            .build();

    @Test
    void given_channel_then_return_channel_id()  throws  Exception {

        final String token = "token";

        final Long channelId = 10L;

        when(mockConfigurationsDatasource.getChannelById(anyString(), anyLong()))
                .thenReturn(
                        Channel.builder()
                                .id(10L)
                                .name("cash")
                                .build());

        // Act
        ChannelDTO response = repository.getChannel(
                token,
                channelId
        );

        // Assert
        assertEquals(10L, response.getId());
    }

    @Test
    void given_store_then_return_pos_id() throws Exception {
        // Arrange
        String token = "token";

        Store store = Store.builder()
                .id(1L)
                .posId(2L)
                .posTypeId(1L)
                .build();

        when(configurationsBCAPI.getPosId(anyString(), anyLong(), anyLong()))
                .thenReturn(mockApiCall);

        when(mockApiCall.execute()).thenReturn(
                Response.success(ApiResponseEntity.<PosResponse>builder()
                        .code(200)
                        .data(
                                PosResponse.builder()
                                        .posId(1L)
                                        .build()
                        )
                        .locale("CO")
                        .build()
                )
        );

        // Act
        Store response = repository.getPosIdByStoreIdAndPaymentMethodIds(
                token,
                store
        );

        // Assert
        assertEquals(1L, response.getPosId());
    }

    @Test
    void given_store_and_payment_methods_then_return_failure() throws Exception {
        // Arrange
        String token = "token";

        Store store = Store.builder()
                .id(1L)
                .posId(2L)
                .posTypeId(1L)
                .build();

        var responseJSON = ObjectExtensions.toJson(apiErrorResponseEntity);

        when(configurationsBCAPI.getPosId(anyString(), anyLong(), anyLong()))
                .thenReturn(mockApiCall);

        when(mockApiCall.execute()).thenReturn(Response.error(
                500, ResponseBody.create(
                        MediaType.parse("application/json"),
                        responseJSON
                )));

        // Act - Assert
        try {
            repository.getPosIdByStoreIdAndPaymentMethodIds(
                    token,
                    store
            );
        } catch (TransactionCreationException exception) {
            assertTrue(exception.getLocalizedMessage().contains("Error obtain pos_id with store_id [1]"));
        }
    }

    @Test
    void bring_store_info_test() throws IOException {
        String token = "token";


        when(configurationsBCAPI.getStore(anyString(), anyLong()))
                .thenReturn(mockApiCallStore);

        when(mockApiCallStore.execute()).thenReturn(
                Response.success(
                        ApiResponseEntity.<StoreInformation>builder()
                                .code(200)
                                .data(
                                        StoreInformation.builder()
                                                .id(1L)
                                                .build()
                                ).locale("CO")
                                .build()
                ));

        StoreInformation storeInfo = repository.getStoreConfiguration(token, 1L);
        assertEquals(1L, storeInfo.getId());
    }

    @Test
    void bring_store_info_test_exception() throws IOException {
        String token = "token";
        when(configurationsBCAPI.getStore(anyString(), anyLong()))
                .thenReturn(mockApiCallStore);

        var responseJSON = ObjectExtensions.toJson(apiErrorResponseEntityStoreInfo);

        when(mockApiCallStore.execute()).thenReturn(Response.error(
                500, ResponseBody.create(
                        MediaType.parse("application/json"),
                        responseJSON
                )));
        try {
            repository.getStoreConfiguration(
                    token,
                    1L
            );
        } catch (TransactionCreationException exception) {
            assertTrue(exception.getLocalizedMessage().contains("Error obtain store info with store_id [1]"));
        }
    }
}
