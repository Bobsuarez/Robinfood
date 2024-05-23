package com.robinfood.repository.paymentmethodspaid;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import com.robinfood.core.entities.ApiResponseEntity;
import com.robinfood.core.entities.paymentmethodpaidentities.PaymentMethodEntity;
import com.robinfood.core.entities.paymentmethodpaidentities.PaymentMethodPaidOriginEntity;
import com.robinfood.core.entities.paymentmethodpaidentities.PaymentMethodPaidPaymentEntity;
import com.robinfood.core.entities.paymentmethodpaidentities.PaymentMethodPaidReferenceEntity;
import com.robinfood.core.entities.paymentmethodpaidentities.PaymentMethodPaidRequestEntity;
import com.robinfood.core.entities.paymentmethodpaidentities.PaymentMethodPaidResponseEntity;
import com.robinfood.core.entities.paymentmethodpaidentities.PaymentMethodPaidStatusEntity;
import com.robinfood.core.entities.paymentmethodpaidentities.PaymentMethodPaidUserEntity;
import com.robinfood.core.extensions.ObjectExtensions;
import com.robinfood.network.api.PaymentMethodsBCApi;
import com.robinfood.repository.paymentmethods.PaymentMethodPaidRemoteDatasource;
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
class PaymentMethodPaidRemoteDatasourceTest {

    @Mock
    private PaymentMethodsBCApi mockPaymentMethodsBCApi;

    @Mock
    private Call<ApiResponseEntity<PaymentMethodPaidResponseEntity>> mockApiResponse;

    @InjectMocks
    private PaymentMethodPaidRemoteDatasource paymentMethodPaidRemoteDatasource;

    private final static String token = "token";

    // ################## API RESPONSE MOCK ##################
    private final PaymentMethodPaidResponseEntity methodPaidResponse = PaymentMethodPaidResponseEntity
        .builder()
        .generated(true)
        .uuid("8b56cbad-78a2-4da7-ba4d-e8271397a37b")
        .status(PaymentMethodPaidStatusEntity.builder()
            .id(1L)
            .name("Accepted")
            .build()
        )
        .platformTypeId(1)
        .build();

    final ApiResponseEntity<PaymentMethodPaidResponseEntity> apiResponseEntity = ApiResponseEntity.<PaymentMethodPaidResponseEntity>builder()
            .code(200)
            .locale("CO")
            .message("Success")
            .build();

    final ApiResponseEntity<PaymentMethodPaidResponseEntity> apiResponseFailureEntity = ApiResponseEntity.<PaymentMethodPaidResponseEntity>builder()
            .code(500)
            .error("Error")
            .locale("CO")
            .message("Order could not be created")
            .build();

    // ################## MOCK REQUEST DATASOURCE ##################
    private final PaymentMethodPaidReferenceEntity entity = PaymentMethodPaidReferenceEntity
        .builder()
        .id(1L)
        .source(12345)
        .reference("ae34fdsdfcx")
        .build();

    private final PaymentMethodEntity platformTypeInfo = PaymentMethodEntity
        .builder()
        .creditCardId(4L)
        .installmentsNumber(3L)
        .build();

    private final PaymentMethodPaidUserEntity user = PaymentMethodPaidUserEntity
        .builder()
        .userId(1L)
        .firstName("Miguel")
        .lastName("Boada")
        .phoneCode("57")
        .phoneNumber("3102900986")
        .build();

    private final PaymentMethodPaidPaymentEntity payment = PaymentMethodPaidPaymentEntity
        .builder()
        .subtotal(18000.0)
        .tax(2000.0)
        .total(20000.0)
        .build();

    private final PaymentMethodPaidOriginEntity origin = PaymentMethodPaidOriginEntity
        .builder()
        .platformId(1L)
        .storeId(1L)
        .build();

    // REQUEST MOCK
    private final PaymentMethodPaidRequestEntity paymentMethodPaidRequestEntity = PaymentMethodPaidRequestEntity
        .builder()
        .platformTypeId(1)
        .countryId(1L)
        .entity(entity)
        .paymentMethod(platformTypeInfo)
        .user(user)
        .payment(payment)
        .origin(origin)
        .build();

    @Test
    void test_Build_Payment_Method_Happy_Path() throws Exception {

        when(mockApiResponse.execute()).thenReturn(
            Response.success(apiResponseEntity)
        );

        when(mockPaymentMethodsBCApi.createTransaction(
            token,
            paymentMethodPaidRequestEntity
        )).thenReturn(
            mockApiResponse
        );

        ApiResponseEntity<PaymentMethodPaidResponseEntity> result =
            paymentMethodPaidRemoteDatasource.buildPaymentMethod(
                token,
                paymentMethodPaidRequestEntity
            ).join();

        assertNotNull(result);
    }

    @Test
    void test_Build_Payment_Method_Result_Error() throws Exception {

        final String responseJSON = ObjectExtensions.toJson(apiResponseFailureEntity);

        when(mockPaymentMethodsBCApi.createTransaction(
            token,
            paymentMethodPaidRequestEntity
        )).thenReturn(
            mockApiResponse
        );

        when(mockApiResponse.execute()).thenReturn(Response.error(500, ResponseBody.create(
            MediaType.parse("application/json"),
            responseJSON
        )));

        try {
            paymentMethodPaidRemoteDatasource.buildPaymentMethod(token, paymentMethodPaidRequestEntity).get();
        } catch (Exception exception) {
            assertTrue(exception.getLocalizedMessage().contains(apiResponseFailureEntity.getMessage()));
        }
    }
}
