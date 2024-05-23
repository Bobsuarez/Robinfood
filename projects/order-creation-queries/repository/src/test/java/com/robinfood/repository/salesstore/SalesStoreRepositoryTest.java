package com.robinfood.repository.salesstore;

import com.robinfood.core.dtos.salesstore.SalesStoresDTO;
import com.robinfood.core.entities.APIResponseEntity;
import com.robinfood.core.extensions.ObjectExtensions;
import com.robinfood.network.api.OrderBcQueriesAPI;
import com.robinfood.repository.mocks.GetSalesStoresDTOMock;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import retrofit2.Call;
import retrofit2.Response;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SalesStoreRepositoryTest {

    @Mock
    private OrderBcQueriesAPI orderBcQueriesAPI;

    @Mock
    private Call<APIResponseEntity<SalesStoresDTO>> mockSalesStoreResponse;

    @InjectMocks
    private SalesStoreRepository salesStoreRepository;

    private final String token = "token";

    @Test
    void test_GetSalesStore_Should_OK_When_DataIsCorrect() throws Exception {

        when(orderBcQueriesAPI.getSalesByStoreGroupByPaymentMethods(
                anyLong(),
                any(LocalDateTime.class),
                anyString(),
                anyString()
        )).thenReturn(mockSalesStoreResponse);

        when(mockSalesStoreResponse.execute()).thenReturn(Response.success(
                new APIResponseEntity<>(
                        200,
                        GetSalesStoresDTOMock.getDataDefault(),
                        "CO",
                        "Order filter returned",
                        "200"
                )
        ));

        salesStoreRepository.getSalesByStoreGroupByPaymentMethods(
                LocalDateTime.now(),
                164L,
                "America/Bogota",
                token);

        verify(orderBcQueriesAPI).getSalesByStoreGroupByPaymentMethods(
                anyLong(),
                any(LocalDateTime.class),
                anyString(),
                anyString()
        );
    }

    @Test
    void test_GetSalesStore_Should_BadRequest_When_ApiContainsError() throws Exception {

        when(orderBcQueriesAPI.getSalesByStoreGroupByPaymentMethods(
                anyLong(),
                any(LocalDateTime.class),
                anyString(),
                anyString()
        )).thenReturn(mockSalesStoreResponse);

        when(mockSalesStoreResponse.execute()).thenReturn(Response.error(500, ResponseBody.create(
                MediaType.parse("application/json"),
                ObjectExtensions.toJson(new APIResponseEntity<>(
                        500,
                        List.of(),
                        "CO",
                        "",
                        "The start date cannot be greater than the end date"
                ))
        )));

        salesStoreRepository.getSalesByStoreGroupByPaymentMethods(
                LocalDateTime.now(),
                164L,
                "America/Bogota",
                token);

        verify(orderBcQueriesAPI).getSalesByStoreGroupByPaymentMethods(
                anyLong(),
                any(LocalDateTime.class),
                anyString(),
                anyString()
        );

    }
}
