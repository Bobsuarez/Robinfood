package com.robinfood.repository.orderpayment;

import com.robinfood.core.dtos.orderpayment.DataOrderPaymentRequestDTO;
import com.robinfood.core.dtos.orderpayment.OrderPaymentDTO;
import com.robinfood.core.entities.APIResponseEntity;
import com.robinfood.core.extensions.ObjectExtensions;
import com.robinfood.network.api.OrderBcQueriesAPI;
import com.robinfood.repository.mocks.DataOrderPaymentRequestDTOMock;
import com.robinfood.repository.mocks.OrderPaymentDTOMock;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import retrofit2.Call;
import retrofit2.Response;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderPaymentRepositoryTest {

    @Mock
    private OrderBcQueriesAPI orderBcQueriesAPI;

    @Mock
    private Call<APIResponseEntity<List<OrderPaymentDTO>>>  responseEntityCall;

    @InjectMocks
    private OrderPaymentRepository orderPaymentRepository;

    private final String token = "token";

    @Test
    void test_GetOrderPaymentMethods_Should_OK_When_DataIsCorrect() throws Exception {

        when(orderBcQueriesAPI.getOrderPaymentMethods(
                anyLong(),
                any(LocalDate.class),
                any(LocalDate.class),
                anyString(),
                anyString()
        )).thenReturn(responseEntityCall);

        when(responseEntityCall.execute()).thenReturn(Response.success(
                new APIResponseEntity<>(
                        200,
                        OrderPaymentDTOMock.getDataDefault(),
                        "CO",
                        "Order filter returned",
                        "200"
                )
        ));

        orderPaymentRepository.getDataOrderPayment(
                DataOrderPaymentRequestDTOMock.getDataDefault(),
                token
        );

        verify(orderBcQueriesAPI)
                .getOrderPaymentMethods(
                        anyLong(),
                        any(LocalDate.class),
                        any(LocalDate.class),
                        anyString(),
                        anyString()
                );

    }

    @Test
    void test_GetOrderPaymentMethods_Should_BadRequest_When_ApiContainsError() throws Exception {

        when(orderBcQueriesAPI.getOrderPaymentMethods(
                anyLong(),
                any(LocalDate.class),
                any(LocalDate.class),
                anyString(),
                anyString()
        )).thenReturn(responseEntityCall);

        when(responseEntityCall.execute()).thenReturn(Response.error(500, ResponseBody.create(
                MediaType.parse("application/json"),
                ObjectExtensions.toJson(new APIResponseEntity<>(
                        500,
                        OrderPaymentDTOMock.getDataDefault(),
                        "CO",
                        "",
                        "The start date cannot be greater than the end date"
                ))
        )));

        orderPaymentRepository.getDataOrderPayment(DataOrderPaymentRequestDTO.builder()
                .localDateStart(LocalDate.now())
                .localDateEnd(LocalDate.now())
                .posId(1L)
                .timeZone("America/Bogotá")
                .build(), token);

        verify(orderBcQueriesAPI)
                .getOrderPaymentMethods(
                        anyLong(),
                        any(LocalDate.class),
                        any(LocalDate.class),
                        anyString(),
                        anyString()
                );

    }
}
