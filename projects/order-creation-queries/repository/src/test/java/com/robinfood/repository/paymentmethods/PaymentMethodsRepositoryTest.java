package com.robinfood.repository.paymentmethods;

import com.robinfood.core.entities.APIResponseEntity;
import com.robinfood.core.entities.PaymentMethodsEntity;
import com.robinfood.core.extensions.ObjectExtensions;
import com.robinfood.network.api.OrderBcQueriesAPI;
import com.robinfood.repository.mocks.PaymentMethodsEntityDTOMock;
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

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PaymentMethodsRepositoryTest {

    @Mock
    private OrderBcQueriesAPI orderBcQueriesAPI;

    @Mock
    private Call<APIResponseEntity<List<PaymentMethodsEntity>>> mockOrderTotalDailySales;

    @InjectMocks
    private PaymentMethodsRepository paymentMethodsRepository;

    private final String token = "token";

    @Test
    void test_GeDataListPaymentMethods_Should_OK_When_DataIsCorrect() throws Exception {

        when(orderBcQueriesAPI.getPaymentMethodsList(anyString()))
                .thenReturn(mockOrderTotalDailySales);

        when(mockOrderTotalDailySales.execute()).thenReturn(Response.success(
                new APIResponseEntity<>(
                        200,
                        List.of(PaymentMethodsEntityDTOMock.getDataDefault()),
                        "CO",
                        "OK",
                        "200"
                )
        ));

        paymentMethodsRepository.getDataPaymentMethods(token);

        verify(orderBcQueriesAPI)
                .getPaymentMethodsList(
                        anyString()
                );
    }

    @Test
    void test_GeDataListPaymentMethods_Should_BadRequest_When_ApiContainsError() throws Exception {

        when(orderBcQueriesAPI.getPaymentMethodsList(anyString()))
                .thenReturn(mockOrderTotalDailySales);

        when(mockOrderTotalDailySales.execute()).thenReturn(Response.error(500, ResponseBody.create(
                MediaType.parse("application/json"),
                ObjectExtensions.toJson(new APIResponseEntity<>(
                        500,
                        List.of(),
                        "CO",
                        "",
                        "The start date cannot be greater than the end date"
                ))
        )));

        paymentMethodsRepository.getDataPaymentMethods(token);

        verify(orderBcQueriesAPI)
                .getPaymentMethodsList(
                        anyString()
                );
    }
}
