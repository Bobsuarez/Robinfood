package com.robinfood.repository.paymentmethodspaid;

import com.robinfood.core.dtos.paymentmethodpaiddto.PaymentMethodPaidOriginDTO;
import com.robinfood.core.dtos.paymentmethodpaiddto.PaymentMethodPaidPaymentDTO;
import com.robinfood.core.dtos.paymentmethodpaiddto.PaymentMethodPaidPlatformTypeInfoDTO;
import com.robinfood.core.dtos.paymentmethodpaiddto.PaymentMethodPaidReferenceDTO;
import com.robinfood.core.dtos.paymentmethodpaiddto.PaymentMethodPaidRequestDTO;
import com.robinfood.core.dtos.paymentmethodpaiddto.PaymentMethodPaidResponseDTO;
import com.robinfood.core.dtos.paymentmethodpaiddto.PaymentMethodPaidUserDTO;
import com.robinfood.core.dtos.paymentmethodpaiddto.ReferencePaymentMethodsDTO;
import com.robinfood.core.entities.ApiResponseEntity;
import com.robinfood.core.entities.paymentmethodpaidentities.PaymentMethodEntity;
import com.robinfood.core.entities.paymentmethodpaidentities.PaymentMethodPaidOriginEntity;
import com.robinfood.core.entities.paymentmethodpaidentities.PaymentMethodPaidPaymentEntity;
import com.robinfood.core.entities.paymentmethodpaidentities.PaymentMethodPaidPlatformTypeInfoEntity;
import com.robinfood.core.entities.paymentmethodpaidentities.PaymentMethodPaidReferenceEntity;
import com.robinfood.core.entities.paymentmethodpaidentities.PaymentMethodPaidRequestEntity;
import com.robinfood.core.entities.paymentmethodpaidentities.PaymentMethodPaidResponseEntity;
import com.robinfood.core.entities.paymentmethodpaidentities.PaymentMethodPaidStatusEntity;
import com.robinfood.core.entities.paymentmethodpaidentities.PaymentMethodPaidUserEntity;
import com.robinfood.core.entities.paymentmethodpaidentities.ReferencePaymentMethodsEntity;
import com.robinfood.repository.paymentmethods.PaymentMethodPaidRemoteDatasource;
import com.robinfood.repository.paymentmethods.PaymentMethodPaidRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import static com.robinfood.core.constants.GlobalConstants.PAYMENT_METHOD_ID_PAY_U;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PaymentMethodPaidRepositoryTest {

    @Mock
    private PaymentMethodPaidRemoteDatasource mockPaymentMethodPaidDatasource;

    @InjectMocks
    private PaymentMethodPaidRepository paymentMethodPaidRepository;

    private final static String token = "token";

    // ################## MOCK REQUEST DATASOURCE ##################
    private final PaymentMethodPaidReferenceEntity entity = PaymentMethodPaidReferenceEntity
        .builder()
        .id(1L)
        .source(12345)
        .reference("ae34fdsdfcx")
        .build();

    private final PaymentMethodEntity platformTypeInfo = PaymentMethodEntity
            .builder()
            .id(PAYMENT_METHOD_ID_PAY_U)
            .creditCardId(4L)
            .installmentsNumber(3L)
            .referencePaymentMethods(List.of(ReferencePaymentMethodsEntity.builder().id(4L).build()))
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

    // ################## MOCK REQUEST REPOSITORY ##################
    private final PaymentMethodPaidReferenceDTO entityDTO = PaymentMethodPaidReferenceDTO
        .builder()
        .id(1L)
        .source(12345)
        .reference("ae34fdsdfcx")
        .build();

    private final PaymentMethodPaidPlatformTypeInfoDTO platform_type_infoDTO = PaymentMethodPaidPlatformTypeInfoDTO
            .builder()
            .id(PAYMENT_METHOD_ID_PAY_U)
            .creditCardId(4L)
            .installmentsNumber(3L)
            .referencePaymentMethods(List.of(ReferencePaymentMethodsDTO.builder().id(4L).build()))
            .build();

    private final PaymentMethodPaidUserDTO userDTO = PaymentMethodPaidUserDTO
        .builder()
        .userId(1L)
        .firstName("Miguel")
        .lastName("Boada")
        .phoneCode("57")
        .phoneNumber("3102900986")
        .build();

    private final PaymentMethodPaidPaymentDTO paymentDTO = PaymentMethodPaidPaymentDTO
        .builder()
        .subtotal(18000.0)
        .tax(2000.0)
        .total(20000.0)
        .build();

    private final PaymentMethodPaidOriginDTO originDTO = PaymentMethodPaidOriginDTO
        .builder()
        .platformId(1L)
        .storeId(1L)
        .build();

    private final PaymentMethodPaidRequestDTO paymentMethodPaidRequestDTO = PaymentMethodPaidRequestDTO
        .builder()
        .platformTypeId(1)
        .countryId(1L)
        .entity(entityDTO)
        .paymentMethod(platform_type_infoDTO)
        .user(userDTO)
        .payment(paymentDTO)
        .origin(originDTO)
        .build();

    // ################## MOCK RESPONSE DATASOURCE ##################
    private final PaymentMethodPaidResponseEntity paymentMethodPaidResponseEntity = PaymentMethodPaidResponseEntity
        .builder()
        .generated(true)
        .uuid("0ee82497-834d-4943-9f4c-5c61eb87f4fc")
        .status(PaymentMethodPaidStatusEntity.builder()
            .id(1L)
            .name("Accepted")
            .build()
        )
        .platformTypeId(1)
        .platformTypeInfo(PaymentMethodPaidPlatformTypeInfoEntity.builder()
            .paymentGatewayId(1L)
            .paymentGatewayName("PayU")
            .build())
        .entity(entity)
        .payment(payment)
        .build();

    private final ApiResponseEntity<PaymentMethodPaidResponseEntity> apiResponseEntity = ApiResponseEntity.<PaymentMethodPaidResponseEntity>builder()
            .code(201)
            .data(paymentMethodPaidResponseEntity)
            .message("Success")
            .build();

    // ################## TESTS ##################
    @Test
    void test_Build_Payment_Method_Happy_Path() {

        when(mockPaymentMethodPaidDatasource.buildPaymentMethod(
            token,
            paymentMethodPaidRequestEntity
        )).thenReturn(
            CompletableFuture.completedFuture(apiResponseEntity)
        );

        CompletableFuture<PaymentMethodPaidResponseDTO> result =
            paymentMethodPaidRepository.send(token, paymentMethodPaidRequestDTO);

        assertNotNull(result.join());
    }
}
