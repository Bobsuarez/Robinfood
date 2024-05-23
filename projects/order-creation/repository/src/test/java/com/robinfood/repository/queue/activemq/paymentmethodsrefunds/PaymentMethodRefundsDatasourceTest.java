package com.robinfood.repository.queue.activemq.paymentmethodsrefunds;

import com.robinfood.core.entities.ApiResponseEntity;
import com.robinfood.core.entities.paymentmethodpaidentities.PaymentMethodRefundEntity;
import com.robinfood.core.entities.paymentmethodpaidentities.PaymentMethodRefundResponseEntity;
import com.robinfood.core.extensions.ObjectExtensions;
import com.robinfood.network.api.PaymentMethodsBCApi;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PaymentMethodRefundsDatasourceTest {

    @Mock
    private PaymentMethodsBCApi paymentMethodsBCApi;

    @InjectMocks
    private PaymentMethodRefundsDatasource paymentMethodRefundsDatasource;

    @Mock
    private Call<ApiResponseEntity<PaymentMethodRefundResponseEntity>> paymentMethodRefundResponseEntity;

    final ApiResponseEntity<PaymentMethodRefundResponseEntity> apiResponsePaymentMethodRefundsEntity =
            ApiResponseEntity.<PaymentMethodRefundResponseEntity>builder()
                    .code(200)
                    .data(PaymentMethodRefundResponseEntity.builder().code(200).message("SOLICITUD EXITOSA").build())
                    .locale("CO")
                    .message("SOLICITUD EXITOSA")
                    .build();


    final PaymentMethodRefundEntity paymentMethodRefundEntity = PaymentMethodRefundEntity.builder()
            .entitySourceReference("entitySourceReference")
            .entityId(1L)
            .entitySourceId(1L)
            .reason("reason")
            .build();

    final ApiResponseEntity<PaymentMethodRefundResponseEntity> apiResponsePaymentMethodRefundsEntityError =
            ApiResponseEntity.<PaymentMethodRefundResponseEntity>builder()
                    .code(500)
                    .locale("CO")
                    .message("Error")
                    .build();

    final ApiResponseEntity<PaymentMethodRefundResponseEntity> apiResponsePaymentMethodRefundsEntityErrorUnauthorized =
            ApiResponseEntity.<PaymentMethodRefundResponseEntity>builder()
                    .code(401)
                    .locale("CO")
                    .message("unauthorized")
                    .build();

    @Test
    void test_PaymentMethodRefunds_Returns_Correctly() throws Exception {
        when(paymentMethodsBCApi.refundMessages(anyString(),  any()))
                .thenReturn(paymentMethodRefundResponseEntity);

        when(paymentMethodRefundResponseEntity.execute()).thenReturn(Response.success(apiResponsePaymentMethodRefundsEntity));

        ApiResponseEntity<PaymentMethodRefundResponseEntity>paymentMethodRefundResponse =  paymentMethodRefundsDatasource.sendRefundMessage("token",paymentMethodRefundEntity).join();

        System.out.println("paymentMethodRefundResponse " + paymentMethodRefundResponse);

        assertEquals(apiResponsePaymentMethodRefundsEntity.getData().getCode(), paymentMethodRefundResponse.getData().getCode());
    }

    @Test
    void test_PaymentMethodRefunds_Returns_Error() throws Exception {
        String token = "token";
        String responseJSON = ObjectExtensions.toJson(apiResponsePaymentMethodRefundsEntityError);

        when(paymentMethodsBCApi.refundMessages(anyString(),  any()))
                .thenReturn(paymentMethodRefundResponseEntity);

        when(paymentMethodRefundResponseEntity.execute()).thenReturn(Response.error(500, ResponseBody.create(
                MediaType.parse("application/json"),
                responseJSON
        )));

        try {
            paymentMethodRefundsDatasource.sendRefundMessage(token,paymentMethodRefundEntity).join();
        } catch (Exception exception) {
            assertTrue(exception.getLocalizedMessage().contains(apiResponsePaymentMethodRefundsEntityError.getMessage()));
        }
    }

    @Test
    void test_PaymentMethodRefunds_Returns_Unauthorized() throws Exception {
        String token = "token";
        String responseJSON = ObjectExtensions.toJson(apiResponsePaymentMethodRefundsEntityErrorUnauthorized);

        when(paymentMethodsBCApi.refundMessages(anyString(),  any()))
                .thenReturn(paymentMethodRefundResponseEntity);

        when(paymentMethodRefundResponseEntity.execute()).thenReturn(Response.error(401, ResponseBody.create(
                MediaType.parse("application/json"),
                responseJSON
        )));

        Request.Builder r = new Request.Builder().url("http://localhost:8083");

        when(paymentMethodRefundResponseEntity.request()).thenReturn(r.build());
        try {
            paymentMethodRefundsDatasource.sendRefundMessage(token,paymentMethodRefundEntity).join();
        } catch (Exception exception) {
            assertTrue(exception.getLocalizedMessage().contains(apiResponsePaymentMethodRefundsEntityErrorUnauthorized.getMessage()));
        }
    }
}
