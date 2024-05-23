package com.robinfood.repository.configuration.store;

import com.robinfood.core.dtos.configuration.StoresDTO;
import com.robinfood.core.entities.APIResponseEntity;
import com.robinfood.core.entities.configurations.StoreEntity;
import com.robinfood.core.extensions.ObjectExtensions;
import com.robinfood.network.api.ConfigurationBcAPI;
import com.robinfood.repository.mocks.StoresDTOMock;
import com.robinfood.repository.mocks.StoreEntityMock;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StoreRepositoryTest {

    @Mock
    private ConfigurationBcAPI configurationBcAPI;

    @Mock
    private Call<APIResponseEntity<StoreEntity>> mockStoreEntityCall;

    @Mock
    private Call<APIResponseEntity<StoresDTO>> responseConfigStoreEntityCall;

    @InjectMocks
    private StoreRepository storeRepository;

    private final String token = "token";

    @Test
    void test_Get_Store_Returns_Successfully() throws Exception {

        when(configurationBcAPI.getStore(
                anyLong(),
                anyString()
        )).thenReturn(mockStoreEntityCall);

        when(mockStoreEntityCall.execute()).thenReturn(Response.success(new APIResponseEntity<>(
                200,
                StoreEntityMock.getDataDefault(),
                "CO",
                "",
                "Order daily returned"
        )));

        storeRepository.getStore(anyLong(), anyString());

        verify(configurationBcAPI).getStore(anyLong(), anyString());
    }

    @Test
    void test_Get_Store_Returns_With_Failure() throws Exception {

        when(configurationBcAPI.getStore(
                anyLong(),
                anyString()
        )).thenReturn(mockStoreEntityCall);

        when(mockStoreEntityCall.execute()).thenReturn(Response.error(500, ResponseBody.create(
                MediaType.parse("application/json"),
                ObjectExtensions.toJson(new APIResponseEntity<>(
                        500,
                        StoreEntityMock.getDataDefault(),
                        "CO",
                        "",
                        "Order daily not be returned"
                ))
                ))
        );

        storeRepository.getStore(anyLong(), anyString());

        verify(configurationBcAPI).getStore(anyLong(), anyString());
    }

    @Test
    void test_GetConfigStore_Should_OK_When_DataIsCorrect() throws Exception {

        when(configurationBcAPI.getStores(
                any(),
                anyString()
        )).thenReturn(responseConfigStoreEntityCall);

        when(responseConfigStoreEntityCall.execute()).thenReturn(Response.success(
                new APIResponseEntity<>(
                        200,
                        StoresDTOMock.getDataDefault(),
                        "CO",
                        "Order filter returned",
                        "200"
                )
        ));

        storeRepository.getStores(token);

        verify(configurationBcAPI).getStores(any(), anyString());
    }

    @Test
    void test_GetConfigStore_Should_InternalServerError_When_WrongAnswerOfConfigurationBC() throws Exception {

        when(configurationBcAPI.getStores(
                any(),
                anyString()
        )).thenReturn(responseConfigStoreEntityCall);

        when(responseConfigStoreEntityCall.execute()).thenReturn(Response.error(500, ResponseBody.create(
                MediaType.parse("application/json"),
                ObjectExtensions.toJson(new APIResponseEntity<>(
                        500,
                        List.of(),
                        "CO",
                        "",
                        "Order filter not be returned"
                ))
                ))
        );

        storeRepository.getStores(token);

        verify(configurationBcAPI).getStores(any(), anyString());
    }
}