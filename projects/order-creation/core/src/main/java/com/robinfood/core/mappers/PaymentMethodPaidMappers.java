package com.robinfood.core.mappers;

import com.robinfood.core.constants.GlobalConstants;
import com.robinfood.core.dtos.paymentmethodpaiddto.PaymentMethodPaidOriginDTO;
import com.robinfood.core.dtos.paymentmethodpaiddto.PaymentMethodPaidPaymentDTO;
import com.robinfood.core.dtos.paymentmethodpaiddto.PaymentMethodPaidPlatformTypeInfoDTO;
import com.robinfood.core.dtos.paymentmethodpaiddto.PaymentMethodPaidReferenceDTO;
import com.robinfood.core.dtos.paymentmethodpaiddto.PaymentMethodPaidRequestDTO;
import com.robinfood.core.dtos.paymentmethodpaiddto.PaymentMethodPaidResponseDTO;
import com.robinfood.core.dtos.paymentmethodpaiddto.PaymentMethodPaidUserDTO;
import com.robinfood.core.dtos.transactionrequestdto.NewUserDTO;
import com.robinfood.core.dtos.transactionrequestdto.PaymentMethodDTO;
import com.robinfood.core.dtos.transactionrequestdto.PaymentMethodDetailDTO;
import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import com.robinfood.core.dtos.transactionresponsedto.OrderResponseDTO;
import com.robinfood.core.dtos.transactionresponsedto.TransactionCreationResponseDTO;
import com.robinfood.core.entities.ApiResponseEntity;
import com.robinfood.core.entities.paymentmethodpaidentities.PaymentMethodEntity;
import com.robinfood.core.entities.paymentmethodpaidentities.PaymentMethodPaidOriginEntity;
import com.robinfood.core.entities.paymentmethodpaidentities.PaymentMethodPaidPaymentEntity;
import com.robinfood.core.entities.paymentmethodpaidentities.PaymentMethodPaidReferenceEntity;
import com.robinfood.core.entities.paymentmethodpaidentities.PaymentMethodPaidRequestEntity;
import com.robinfood.core.entities.paymentmethodpaidentities.PaymentMethodPaidResponseEntity;
import com.robinfood.core.entities.paymentmethodpaidentities.PaymentMethodPaidUserEntity;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

import static com.robinfood.core.constants.GlobalConstants.DEFAULT_INTEGER_VALUE;
import static com.robinfood.core.constants.GlobalConstants.ENTITY_ID;
import static com.robinfood.core.constants.GlobalConstants.NUMBER_DECIMAL_PLACES;
import static com.robinfood.core.constants.GlobalConstants.PLATFORM_TYPE_ID;

@Slf4j
public final class PaymentMethodPaidMappers {

    // Static Finals
    private static final int HUNDRED_PERCENT = 100;

    private PaymentMethodPaidMappers() {
        throw new IllegalStateException("Utility class");
    }

    public static PaymentMethodPaidRequestDTO buildPaymentMethodPaidRequestDTO(
            Long countryId,
            Long platformId,
            Long paymentMethodId,
            PaymentMethodDTO payuPaymentMethod,
            TransactionRequestDTO transactionRequest,
            TransactionCreationResponseDTO transactionResponse
    ) {
        return PaymentMethodPaidRequestDTO.builder()
                .countryId(countryId)
                .platformId(platformId)
                .platformTypeId(PLATFORM_TYPE_ID)
                .entity(
                        buildEntityRequest(transactionResponse)
                )
                .paymentMethod(
                        buildPlatformTypeInfoRequest(
                                paymentMethodId,
                                payuPaymentMethod.getDetail(),
                                payuPaymentMethod.getId()
                        )
                )
                .user(buildUserRequest(transactionRequest.getUser()))
                .payment(
                        buildPaymentRequest(transactionResponse.getTransaction().getOrders(), payuPaymentMethod)
                )
                .origin(
                        buildOriginRequest(platformId, transactionRequest)
                )
                .build();
    }

    private static PaymentMethodPaidReferenceDTO buildEntityRequest(
            TransactionCreationResponseDTO transactionResponse
    ) {

        return PaymentMethodPaidReferenceDTO.builder()
                .id(ENTITY_ID)
                .source(transactionResponse.getTransaction().getId().intValue())
                .reference(transactionResponse.getTransaction().getUuid())
                .build();
    }

    private static PaymentMethodPaidPlatformTypeInfoDTO buildPlatformTypeInfoRequest(
            Long paymentMethodId,
            PaymentMethodDetailDTO paymentMethodDetailDTO,
            Long referencePaymentMethod
    ) {

        return PaymentMethodPaidPlatformTypeInfoDTO.builder()
                .creditCardId(paymentMethodDetailDTO.getCreditCard())
                .id(paymentMethodId)
                .installmentsNumber(paymentMethodDetailDTO.getInstallmentsNumber())
                .terminalUuid(paymentMethodDetailDTO.getSelfManagementCode())
                .referencePaymentMethods(List.of(
                        ReferencePaymentMethodsMappers
                                .buildReferencePaymentMethodsDTO(referencePaymentMethod)))
                .build();
    }

    private static PaymentMethodPaidUserDTO buildUserRequest(NewUserDTO user) {

        return PaymentMethodPaidUserDTO.builder()
                .userId(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .phoneCode(user.getPhoneCode())
                .phoneNumber(user.getMobile())
                .build();
    }

    public static PaymentMethodPaidPaymentDTO buildPaymentRequest(
            List<OrderResponseDTO> orders,
            PaymentMethodDTO payuPaymentMethod
    ) {

        BigDecimal transactionSubTotal = orders.stream().map(OrderResponseDTO::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .setScale(
                        GlobalConstants.NUMBER_DECIMAL_PLACES,
                        RoundingMode.FLOOR);

        final BigDecimal discounts = orders.stream().map(OrderResponseDTO::getDiscountPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .setScale(
                        GlobalConstants.NUMBER_DECIMAL_PLACES,
                        RoundingMode.FLOOR);

        transactionSubTotal = transactionSubTotal.subtract(discounts);

        BigDecimal transactionTax = orders.stream().map(OrderResponseDTO::getTaxPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .setScale(
                        GlobalConstants.NUMBER_DECIMAL_PLACES,
                        RoundingMode.FLOOR);

        BigDecimal transactionTotal = orders.stream()
                .map(OrderResponseDTO::getTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .setScale(
                        GlobalConstants.NUMBER_DECIMAL_PLACES,
                        RoundingMode.FLOOR);

        BigDecimal paymentMethodTax;
        BigDecimal paymentMethodTotal = payuPaymentMethod.getValue();

        log.info("\n ############ BUILD PAYMENT TO SEND OF PAYMENT METHOD BC ############ \n");
        log.info("transactionSubTotal -> " + transactionSubTotal);
        log.info("transactionTax -> " + transactionTax);
        log.info("transactionTotal -> " + transactionTotal);
        log.info("payuPaymentMethodValue -> " + payuPaymentMethod.getValue());

        BigDecimal payUPercent = payuPaymentMethod.getValue()
                .multiply(BigDecimal.valueOf(HUNDRED_PERCENT))
                .divide(transactionTotal, NUMBER_DECIMAL_PLACES, RoundingMode.CEILING)
                .divide(BigDecimal.valueOf(HUNDRED_PERCENT), NUMBER_DECIMAL_PLACES, RoundingMode.CEILING);

        paymentMethodTax = payUPercent
                .multiply(transactionTax)
                .setScale(NUMBER_DECIMAL_PLACES, RoundingMode.FLOOR);

        log.info("\n paymentMethodTax calculated -> " + paymentMethodTax + "\n");

        BigDecimal paymentMethodSubTotal = paymentMethodTotal.subtract(paymentMethodTax);

        log.info("paymentMethodSubTotal -> " + paymentMethodSubTotal);
        log.info("paymentMethodTax -> " + paymentMethodTax);
        log.info("paymentMethodTotal -> " + paymentMethodTotal);

        return PaymentMethodPaidPaymentDTO.builder()
                .subtotal(paymentMethodSubTotal.doubleValue())
                .tax(paymentMethodTax.doubleValue())
                .total(paymentMethodTotal.doubleValue())
                .build();

    }

    private static PaymentMethodPaidOriginDTO buildOriginRequest(
            Long platformId,
            TransactionRequestDTO transactionRequest
    ) {
        return PaymentMethodPaidOriginDTO.builder()
                .platformId(platformId)
                .channelId(transactionRequest.getOrigin().getId())
                .storeId(transactionRequest.getOrders().get(DEFAULT_INTEGER_VALUE).getStore().getId())
                .ipAddress(transactionRequest.getDevice().getIp())
                .build();
    }

    public static PaymentMethodPaidRequestEntity toPaymentMethodPaidRequestEntity(
            PaymentMethodPaidRequestDTO paymentMethodPaidRequestDTO
    ) {

        return PaymentMethodPaidRequestEntity
                .builder()
                .platformTypeId(paymentMethodPaidRequestDTO.getPlatformTypeId())
                .countryId(paymentMethodPaidRequestDTO.getCountryId())
                .entity(buildEntity(paymentMethodPaidRequestDTO.getEntity()))
                .paymentMethod(buildPlatformTypeInfo(paymentMethodPaidRequestDTO.getPaymentMethod()))
                .user(buildUser(paymentMethodPaidRequestDTO.getUser()))
                .payment(buildPayment(paymentMethodPaidRequestDTO.getPayment()))
                .origin(buildOrigin(paymentMethodPaidRequestDTO.getOrigin()))
                .build();
    }

    private static PaymentMethodPaidReferenceEntity buildEntity(
            PaymentMethodPaidReferenceDTO entityDTO
    ) {

        return PaymentMethodPaidReferenceEntity
                .builder()
                .id(entityDTO.getId())
                .source(entityDTO.getSource())
                .reference(entityDTO.getReference())
                .build();
    }

    private static PaymentMethodEntity buildPlatformTypeInfo(
            PaymentMethodPaidPlatformTypeInfoDTO platformTypeInfoDTO
    ) {

        return PaymentMethodEntity
                .builder()
                .creditCardId(platformTypeInfoDTO.getCreditCardId())
                .id(platformTypeInfoDTO.getId())
                .installmentsNumber(platformTypeInfoDTO.getInstallmentsNumber())
                .terminalUuid(platformTypeInfoDTO.getTerminalUuid())
                .referencePaymentMethods(platformTypeInfoDTO.getReferencePaymentMethods().stream()
                        .map(ReferencePaymentMethodsMappers::referencePaymentMethodsDTOToEntity)
                        .collect(Collectors.toList()))
                .build();
    }

    private static PaymentMethodPaidUserEntity buildUser(
            PaymentMethodPaidUserDTO user
    ) {

        return PaymentMethodPaidUserEntity
                .builder()
                .userId(user.getUserId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .phoneCode(user.getPhoneCode())
                .phoneNumber(user.getPhoneNumber())
                .build();
    }

    private static PaymentMethodPaidPaymentEntity buildPayment(
            PaymentMethodPaidPaymentDTO payment
    ) {
        return new PaymentMethodPaidPaymentEntity(
                payment.getSubtotal(),
                payment.getTax(),
                payment.getTotal()
        );
    }

    private static PaymentMethodPaidOriginEntity buildOrigin(
            PaymentMethodPaidOriginDTO origin
    ) {
        return new PaymentMethodPaidOriginEntity(
                origin.getChannelId(),
                origin.getPlatformId(),
                origin.getStoreId(),
                origin.getIpAddress()
        );
    }

    // Receive an Entity and convert it into a DTO (Repository to -> Controller)
    public static PaymentMethodPaidResponseDTO toPaymentMethodPaidResponseDTO(
            ApiResponseEntity<PaymentMethodPaidResponseEntity> paymentMethodPaidResponseEntity
    ) {
        return new PaymentMethodPaidResponseDTO(
                paymentMethodPaidResponseEntity.getData().getUuid(),
                paymentMethodPaidResponseEntity.getData().getGenerated(),
                paymentMethodPaidResponseEntity.getMessage(),
                paymentMethodPaidResponseEntity.getData().getPlatformTypeInfo().getPaymentGatewayName(),
                paymentMethodPaidResponseEntity.getData().getStatus().getName()
        );
    }
}
