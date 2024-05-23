package com.robinfood.repository.ordersalesbycompanies;

import com.robinfood.core.dtos.orderSales.ResponseOrderActiveSalesDTO;
import com.robinfood.core.entities.APIResponseEntity;
import com.robinfood.core.extensions.ObjectExtensions;
import com.robinfood.network.api.OrderBcQueriesAPI;
import com.robinfood.repository.mocks.ResponseOrderActiveSalesDTOMock;
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

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OrderSalesByCompaniesRepositoryTest {

    @Mock
    private OrderBcQueriesAPI orderBcQueriesAPI;

    @Mock
    private Call<APIResponseEntity<ResponseOrderActiveSalesDTO>> responseEntityCall;

    @InjectMocks
    private OrderSalesByCompaniesRepository orderSalesByCompaniesRepository;

    private final String token = "token";

    @Test
    void test_OrderSalesByCompanies_Should_OK_When_DataIsCorrect() throws Exception {

        when(orderBcQueriesAPI.getActiveSalesToOrderByCompanies(
                anyList(),
                anyString(),
                anyList(),
                anyString()
        )).thenReturn(responseEntityCall);

        when(responseEntityCall.execute()).thenReturn(Response.success(
                new APIResponseEntity<>(
                        200,
                        ResponseOrderActiveSalesDTOMock.getDataDefault(),
                        "CO",
                        "Order filter returned",
                        "200"
                )
        ));

        orderSalesByCompaniesRepository.getSalesToOrderByCompanies(
                List.of(1),
                "2023-03-14 11:00:00",
                List.of("America/Bogota"),
                token
        );

        verify(orderBcQueriesAPI)
                .getActiveSalesToOrderByCompanies(
                        anyList(),
                        anyString(),
                        anyList(),
                        anyString()
                );
    }

    @Test
    void test_OrderSalesByCompanies_Should_InternalServerError_When_WrongAnswerOfOrderBC() throws Exception {

        when(orderBcQueriesAPI.getActiveSalesToOrderByCompanies(
                anyList(),
                anyString(),
                anyList(),
                anyString()
        )).thenReturn(responseEntityCall);

        when(responseEntityCall.execute()).thenReturn(Response.error(500, ResponseBody.create(
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

        orderSalesByCompaniesRepository.getSalesToOrderByCompanies(
                List.of(1),
                "2023-03-14 11:00:00",
                List.of("America/Bogota"),
                token
        );

        verify(orderBcQueriesAPI)
                .getActiveSalesToOrderByCompanies(
                        anyList(),
                        anyString(),
                        anyList(),
                        anyString()
                );
    }
}
