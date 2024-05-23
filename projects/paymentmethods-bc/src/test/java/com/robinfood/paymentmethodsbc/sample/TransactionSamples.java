package com.robinfood.paymentmethodsbc.sample;

import com.robinfood.paymentmethodsbc.constants.AppConstants;
import com.robinfood.paymentmethodsbc.dto.api.transactions.PaymentRequestDTO;
import com.robinfood.paymentmethodsbc.dto.api.transactions.PaymentInitResponseDTO;
import com.robinfood.paymentmethodsbc.dto.api.transactions.RefundRequestDTO;
import com.robinfood.paymentmethodsbc.dto.api.transactions.RefundResponseDTO;
import com.robinfood.paymentmethodsbc.dto.api.transactions.TransactionStatusUpdateRequestDTO;
import com.robinfood.paymentmethodsbc.dto.api.transactions.PaymentRequestDTO.PaymentMethodDTO.PaymentReferencePaymentMethod;
import com.robinfood.paymentmethodsbc.dto.jms.JmsUpdateTransactionStatusDTO;
import com.robinfood.paymentmethodsbc.dto.steps.JmsTransactionStatusPipeDTO;
import com.robinfood.paymentmethodsbc.dto.steps.PaymentPipeDTO;
import com.robinfood.paymentmethodsbc.dto.steps.RefundPipeDTO;
import com.robinfood.paymentmethodsbc.dto.steps.SettingsDTO;
import com.robinfood.paymentmethodsbc.dto.steps.TransactionStatusPipeDTO;
import com.robinfood.paymentmethodsbc.model.Transaction;
import com.robinfood.paymentmethodsbc.model.TransactionDetail;
import com.robinfood.paymentmethodsbc.model.TransactionLog;
import com.robinfood.paymentmethodsbc.model.TransactionStatus;
import com.robinfood.paymentmethodsbc.model.TransactionStatusLog;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.robinfood.paymentmethodsbc.constants.AppConstants.TRANSACTION_TYPE_PURCHASE;
import static com.robinfood.paymentmethodsbc.constants.AppConstants.TRANSACTION_TYPE_REFUND;
import static com.robinfood.paymentmethodsbc.constants.AppConstants.TRANSACTION_TYPE_UNKNOWN;

public class TransactionSamples {

    // Entidades
    public static Transaction getTransaction() {
        return Transaction
            .builder()
            .id(1L)
            .succeeded(true)
            .paymentGateway(PaymentGatewaySamples.getPaymentGateway())
            .succeeded(true)
            .entity(EntitySample.getEntity())
            .transactionStatus(getTransactionStatus())
            .platform(PlatformSample.getPlatform())
            .country(CountrySample.getCountry())
            .total(BigDecimal.TEN)
            .paymentMethod(GeneralPaymentMethodSample.getGeneralPaymentMethod())
            .build();
    }

    public static Transaction getTransactionWithAuthorizationCode() {
        return Transaction
            .builder()
            .id(1L)
            .succeeded(true)
            .paymentGateway(PaymentGatewaySamples.getPaymentGateway())
            .succeeded(true)
            .authorizationCode("123455")
            .entity(EntitySample.getEntity())
            .transactionStatus(getTransactionStatus())
            .platform(PlatformSample.getPlatform())
            .country(CountrySample.getCountry())
            .total(BigDecimal.TEN)
            .paymentMethod(GeneralPaymentMethodSample.getGeneralPaymentMethod())
            .build();
    }

    public static Transaction getTransaction1() {
        return Transaction
            .builder()
            .id(1L)
            .succeeded(true)
            .paymentGateway(PaymentGatewaySamples.getPaymentGateway())
            .succeeded(true)
            .entity(EntitySample.getEntity())
            .transactionStatus(getTransactionStatus1())
            .platform(PlatformSample.getPlatform())
            .country(CountrySample.getCountry())
            .total(BigDecimal.TEN)
            .paymentMethod(GeneralPaymentMethodSample.getGeneralPaymentMethod())
            .build();
    }

    public static Transaction getTransactionPending() {
        return Transaction
            .builder()
            .id(2L)
            .succeeded(true)
            .paymentGateway(PaymentGatewaySamples.getPaymentGateway())
            .succeeded(true)
            .entity(EntitySample.getEntity())
            .transactionStatus(getTransactionStatus())
            .platform(PlatformSample.getPlatform())
            .country(CountrySample.getCountry())
            .total(BigDecimal.TEN)
            .paymentMethod(GeneralPaymentMethodSample.getGeneralPaymentMethod())
            .build();
    }
    public static Transaction getTransactionValidateGateway() {
        return Transaction
            .builder()
            .id(2L)
            .succeeded(true)
            .paymentGateway(PaymentGatewaySamples.getPaymentValidateGateway())
            .succeeded(true)
            .entity(EntitySample.getEntity())
            .transactionStatus(getTransactionStatus1())
            .platform(PlatformSample.getPlatform())
            .country(CountrySample.getCountry())
            .total(BigDecimal.TEN)
            .paymentMethod(GeneralPaymentMethodSample.getGeneralPaymentMethod())
            .build();
    }
    public static Transaction getTransactionValidateDifferentGateway() {
        return Transaction
            .builder()
            .id(6L)
            .succeeded(true)
            .paymentGateway(PaymentGatewaySamples.getPaymentValidateDifferentGateway())
            .succeeded(true)
            .entity(EntitySample.getEntity())
            .transactionStatus(getTransactionStatus())
            .platform(PlatformSample.getPlatform())
            .country(CountrySample.getCountry())
            .total(BigDecimal.TEN)
            .paymentMethod(GeneralPaymentMethodSample.getGeneralPaymentMethod())
            .build();
    }
    public static Transaction getTransactionValidateDifferentGatewayStatus() {
        return Transaction
            .builder()
            .id(6L)
            .succeeded(true)
            .paymentGateway(PaymentGatewaySamples.getPaymentValidateDifferentGateway())
            .succeeded(true)
            .entity(EntitySample.getEntity())
            .transactionStatus(getTransactionStatus1())
            .platform(PlatformSample.getPlatform())
            .country(CountrySample.getCountry())
            .total(BigDecimal.ZERO)
            .paymentMethod(GeneralPaymentMethodSample.getGeneralPaymentMethod())
            .build();
    }
    public static Transaction getTransactionAccepted() {
        return Transaction
            .builder()
            .id(1L)
            .succeeded(true)
            .paymentGateway(PaymentGatewaySamples.getPaymentGateway())
            .succeeded(true)
            .entity(EntitySample.getEntity())
            .transactionStatus(getTransactionStatusAccepted())
            .platform(PlatformSample.getPlatform())
            .country(CountrySample.getCountry())
            .total(BigDecimal.TEN)
            .paymentMethod(GeneralPaymentMethodSample.getGeneralPaymentMethod())
            .build();
    }
    public static Transaction getTransactionRejected() {
        return Transaction
            .builder()
            .id(3L)
            .succeeded(true)
            .paymentGateway(PaymentGatewaySamples.getPaymentGateway())
            .succeeded(true)
            .entity(EntitySample.getEntity())
            .transactionStatus(getTransactionStatusNoAccept())
            .platform(PlatformSample.getPlatform())
            .country(CountrySample.getCountry())
            .total(BigDecimal.ZERO)
            .paymentMethod(GeneralPaymentMethodSample.getGeneralPaymentMethod())
            .build();
    }

    public static Transaction getTransactionAuthCodeNotNull() {
        return Transaction
            .builder()
            .id(1L)
            .authorizationCode("AUTH_CODE")
            .succeeded(true)
            .paymentGateway(PaymentGatewaySamples.getPaymentGateway())
            .succeeded(true)
            .entity(EntitySample.getEntity())
            .transactionStatus(getTransactionStatus())
            .platform(PlatformSample.getPlatform())
            .country(CountrySample.getCountry())
            .total(BigDecimal.TEN)
            .paymentMethod(GeneralPaymentMethodSample.getGeneralPaymentMethod())
            .build();
    }

    public static TransactionDetail getTransactionDetail() {
        return TransactionDetail
            .builder()
            .transactionId(1L)
            .transactionCode("transactionCode")
            .transactionReference("transactionReference")
            .transactionType("transactionType")
            .originPlatformId(1L)
            .storeId(1L)
            .entityReference("entityReference")
            .build();
    }

    public static TransactionDetail getTransactionDetailWithoutCodeAndReference() {
        return TransactionDetail
            .builder()
            .transactionId(1L)
            .transactionType("transactionType")
            .originPlatformId(1L)
            .storeId(1L)
            .entityReference("entityReference")
            .build();
    }

    public static TransactionDetail getTransactionDetailWithAdditionalData() {
        return TransactionDetail
            .builder()
            .transactionId(1L)
            .transactionCode("transactionCode")
            .transactionReference("transactionReference")
            .transactionType("transactionType")
            .dataphoneCode("A12345Z")
            .creditCardMaskedNumber("653287**321")
            .accountType("AH")
            .franchise("MASTERCARD")
            .dataphoneReceiptNumber("49761")
            .installments(1)
            .establishmentCode("0010203040")
            .originPlatformId(1L)
            .storeId(1L)
            .entityReference("entityReference")
            .build();
    }

    public static TransactionStatus getTransactionStatus() {
        return TransactionStatus
            .builder()
            .id(2L)
            .name("Transaction status sample")
            .description("Transaction status description sample")
            .build();
    }
    public static TransactionStatus getTransactionStatus1() {
        return TransactionStatus
            .builder()
            .id(1L)
            .name("Transaction status 1")
            .description("Transaction status description sample 1")
            .build();
    }
    public static TransactionStatus getTransactionStatusAccepted() {
        return TransactionStatus
            .builder()
            .id(1L)
            .name("Transaction status sample")
            .description("Transaction status description sample")
            .build();
    }

    public static TransactionStatus getTransactionStatusRefundAccepted() {
        return TransactionStatus
            .builder()
            .id(4L)
            .name("Transaction status sample")
            .description("Transaction status description sample")
            .build();
    }

    public static TransactionStatus getTransactionStatusRejected() {
        return TransactionStatus
            .builder()
            .id(3L)
            .name("Transaction status sample")
            .description("Transaction status description sample")
            .build();
    }

    public static TransactionStatus getTransactionStatusNoAccept() {
        return TransactionStatus
            .builder()
            .id(3L)
            .name("Transaction status sample")
            .description("Transaction status description sample")
            .build();
    }
    public static Transaction getTransactionWthiStatusRejected() {
        return Transaction
            .builder()
            .transactionStatus(
                TransactionStatus.builder()
                    .id(3L)
                    .name("Transaction status sample")
                    .description("Transaction status description sample")
                    .build()
            )
            .id(3L)
            .total(BigDecimal.TEN)
            .build();
    }

    public static TransactionStatusLog getTransactionStatusLog() {
        return TransactionStatusLog
            .builder()
            .id(1L)
            .transaction(
                Transaction
                    .builder()
                    .transactionDetail(getTransactionDetail())
                    .build()
            )
            .transactionStatus(getTransactionStatus())
            .comment("Comment")
            .build();
    }

    // Requests

    public static RefundRequestDTO getRefundRequestDTO() {
        return RefundRequestDTO
            .builder()
            .transactionId(1L)
            .reason("me cae bien")
            .build();
    }

    public static PaymentRequestDTO getTransactionRequestDTO() {
        return PaymentRequestDTO
            .builder()
            .countryId(1L)
            .transactionId(1L)
            .entity(
                PaymentRequestDTO
                    .EntityDTO.builder()
                    .id(1L)
                    .source(123L)
                    .reference("abcdefg")
                    .build()
            )
            .paymentMethod(
                PaymentRequestDTO
                    .PaymentMethodDTO.builder()
                    .id(1L)
                    .creditCardId(1L)
                    .installmentsNumber(1L)
                    .terminalUuid("abc-def-ghi")
                    .referencePaymentMethods(List.of(
                            PaymentReferencePaymentMethod
                                .builder()
                                .id(5)
                                .build()
                        ))
                    .build()
            )
            .user(
                PaymentRequestDTO
                    .UserDTO.builder()
                    .userId(1L)
                    .firstName("First name")
                    .lastName("Last name")
                    .phoneCode("57")
                    .phoneNumber("3111111111")
                    .build()
            )
            .payment(
                PaymentRequestDTO
                    .PaymentDTO.builder()
                    .subtotal(BigDecimal.TEN)
                    .tax(BigDecimal.TEN)
                    .total(BigDecimal.TEN)
                    .build()
            )
            .origin(
                PaymentRequestDTO
                    .OriginDTO.builder()
                    .channelId(1L)
                    .storeId(1L)
                    .ipAddress("127.0.0.1")
                    .userAgent("Mi PC")
                    .build()
            )
            .build();
    }

    public static TransactionStatusUpdateRequestDTO getTransactionStatusUpdateRequestDTO() {
        return TransactionStatusUpdateRequestDTO
            .builder()
            .notification("{data}")
            .identificator("reference")
            .key("reference")
            .type(TRANSACTION_TYPE_PURCHASE)
            .build();
    }

    public static TransactionStatusUpdateRequestDTO getTransactionStatusUpdateRequestDTORefund() {
        return TransactionStatusUpdateRequestDTO
            .builder()
            .notification("{data}")
            .identificator("reference")
            .key("reference")
            .type(TRANSACTION_TYPE_REFUND)
            .build();
    }

    public static TransactionStatusUpdateRequestDTO getTransactionStatusUpdateRequestDTOUnknow() {
        return TransactionStatusUpdateRequestDTO
            .builder()
            .notification("{data}")
            .identificator("reference")
            .key("reference")
            .type(TRANSACTION_TYPE_UNKNOWN)
            .build();
    }

    public static TransactionStatusUpdateRequestDTO getTransactionStatusUpdateRequestDTOOtherType() {
        return TransactionStatusUpdateRequestDTO
            .builder()
            .notification("{data}")
            .identificator("reference")
            .key("reference")
            .type("other")
            .build();
    }

    // Responses

    public static RefundResponseDTO getRefundResponseDTO() {
        return RefundResponseDTO
            .builder()
            .transactionId(1L)
            .status(
                RefundResponseDTO
                    .StatusRefundDTO.builder()
                    .id(1L)
                    .name("")
                    .build()
            )
            .build();
    }

    public static PaymentInitResponseDTO getTransactionResponseDTO() {
        return PaymentInitResponseDTO
            .builder()
            .generated(true)
            .authorizarionCode("Code")
            .entityTransactionDTO(
                PaymentInitResponseDTO
                    .EntityDTO.builder()
                    .id(1L)
                    .reference("ref")
                    .source(1L)
                    .build()
            )
            .paymentGateway(
                PaymentInitResponseDTO
                    .PaymentGatewayTransactionDTO.builder()
                    .paymentGatewayId(1L)
                    .paymentGatewayName("PayGw")
                    .build()
            )
            .paymentTransactionDTO(
                PaymentInitResponseDTO
                    .PaymentDTO.builder()
                    .subtotal(BigDecimal.TEN)
                    .tax(BigDecimal.TEN)
                    .total(BigDecimal.TEN)
                    .build()
            )
            .build();
    }

    // Pipes

    public static PaymentPipeDTO getTransactionCreatePipeDTO() {
        return PaymentPipeDTO
            .builder()
            .country(CountrySample.getCountry())
            .creditCard(CreditCardSamples.getCreditCard(false))
            .entity(EntitySample.getEntity())
            .paymentGateway(PaymentGatewaySamples.getPaymentGateway())
            .paymentMethod(GeneralPaymentMethodSample.getGeneralPaymentMethod())
            .paymentGatewayCreditCard(
                PaymentGatewaySamples.getPaymentGatewayCreditCard()
            )
            .transaction(getTransaction())
            .transactionDetail(getTransactionDetail())
            .transactionRequestDTO(getTransactionRequestDTO())
            .terminal(TerminalSample.getTerminal())
            .settings(SettingsDTO
                          .builder()
                          .gatewayConfig(new HashMap<>())
                          .orchConfig(new HashMap<>())
                          .terminalConfig(new HashMap<>()).build()
            )
            .notifyStatus(true)
            .build();
    }

    public static RefundPipeDTO getRefundPipeDTO() {
        return RefundPipeDTO
            .builder()
            .originalTransactionId(1L)
            .originalTransaction(getTransaction())
            .originalTransactionDetail(getTransactionDetail())
            .refundReason("")
            .bciProcessRefund(true)
            .settings(SettingsDTO
                          .builder()
                          .gatewayConfig(new HashMap<>())
                          .orchConfig(new HashMap<>())
                          .terminalConfig(new HashMap<>()).build()
            )
            .build();
    }

    public static TransactionStatusPipeDTO getTransactionStatusPipeDTO() {
        return TransactionStatusPipeDTO
            .builder()
            .transaction(getTransaction())
            .transactionDetail(getTransactionDetail())
            .transactionStatusUpdateRequestDTO(
                getTransactionStatusUpdateRequestDTO()
            )
            .notifyStatus(true)
            .settings(SettingsDTO
                          .builder()
                          .gatewayConfig(new HashMap<>())
                          .orchConfig(new HashMap<>())
                          .terminalConfig(new HashMap<>()).build()
            )
            .build();
    }

    public static TransactionStatusPipeDTO getTransactionStatusPipeDTORefund() {
        return TransactionStatusPipeDTO
            .builder()
            .transaction(getTransaction())
            .transactionDetail(getTransactionDetail())
            .transactionStatusUpdateRequestDTO(
                getTransactionStatusUpdateRequestDTORefund()
            )
            .notifyStatus(true)
            .settings(SettingsDTO
                .builder()
                .gatewayConfig(new HashMap<>())
                .orchConfig(new HashMap<>())
                .terminalConfig(new HashMap<>()).build()
            )
            .build();
    }

    public static TransactionStatusPipeDTO getTransactionStatusPipeDTOAuthCodeNotNull() {
        return TransactionStatusPipeDTO
            .builder()
            .transaction(getTransactionAuthCodeNotNull())
            .transactionDetail(getTransactionDetail())
            .transactionStatusUpdateRequestDTO(
                getTransactionStatusUpdateRequestDTO()
            )
            .notifyStatus(true)
            .settings(SettingsDTO
                .builder()
                .gatewayConfig(new HashMap<>())
                .orchConfig(new HashMap<>())
                .terminalConfig(new HashMap<>()).build()
            )
            .build();
    }

    public static TransactionStatusPipeDTO getTransactionStatusPipeDTOUnknow() {
        return TransactionStatusPipeDTO
            .builder()
            .transaction(getTransaction())
            .transactionDetail(getTransactionDetail())
            .transactionStatusUpdateRequestDTO(
                getTransactionStatusUpdateRequestDTOUnknow()
            )
            .notifyStatus(true)
            .settings(SettingsDTO
                .builder()
                .gatewayConfig(new HashMap<>())
                .orchConfig(new HashMap<>())
                .terminalConfig(new HashMap<>()).build()
            )
            .build();
    }

    public static TransactionStatusPipeDTO getTransactionStatusPipeDTOAccepted() {
        return TransactionStatusPipeDTO
            .builder()
            .transaction(getTransaction1())
            .transactionDetail(getTransactionDetail())
            .transactionStatusUpdateRequestDTO(
                getTransactionStatusUpdateRequestDTO()
            )
            .notifyStatus(true)
            .settings(SettingsDTO
                .builder()
                .gatewayConfig(new HashMap<>())
                .orchConfig(new HashMap<>())
                .terminalConfig(new HashMap<>()).build()
            )
            .build();
    }
    public static TransactionStatusPipeDTO getTransactionStatusPipeDTOPending() {
        return TransactionStatusPipeDTO
            .builder()
            .transaction(getTransaction1())
            .transactionDetail(getTransactionDetail())
            .transactionStatusUpdateRequestDTO(
                getTransactionStatusUpdateRequestDTO()
            )
            .notifyStatus(true)
            .settings(SettingsDTO
                .builder()
                .gatewayConfig(new HashMap<>())
                .orchConfig(new HashMap<>())
                .terminalConfig(new HashMap<>()).build()
            )
            .build();
    }
    public static TransactionStatusPipeDTO getTransactionStatusPipeDTODifferentGateway() {
        return TransactionStatusPipeDTO
            .builder()
            .transaction(getTransactionValidateDifferentGateway())
            .transactionDetail(getTransactionDetail())
            .transactionStatusUpdateRequestDTO(
                getTransactionStatusUpdateRequestDTO()
            )
            .notifyStatus(true)
            .settings(SettingsDTO
                .builder()
                .gatewayConfig(
                    Map.of("retryStatusFlag","true"))
                .orchConfig(new HashMap<>())
                .terminalConfig(new HashMap<>()).build()
            )
            .build();
    }

    public static TransactionStatusPipeDTO getTransactionStatusPipeDTOListStatusRedebanNotNotification() {
        return TransactionStatusPipeDTO
            .builder()
            .transaction(getTransactionValidateDifferentGateway())
            .transactionDetail(getTransactionDetail())
            .transactionStatusUpdateRequestDTO(
                getTransactionStatusUpdateRequestDTO()
            )
            .notifyStatus(true)
            .settings(SettingsDTO
                .builder()
                .gatewayConfig(
                    Map.of("listStatusRedebanNotNotification","2"))
            .orchConfig(new HashMap<>())
            .terminalConfig(new HashMap<>()).build()
            )
            .build();
    }

    public static TransactionStatusPipeDTO getTransactionStatusPipeDTOOtherType() {
        return TransactionStatusPipeDTO
            .builder()
            .transaction(getTransaction())
            .transactionDetail(getTransactionDetail())
            .transactionStatusUpdateRequestDTO(
                getTransactionStatusUpdateRequestDTOOtherType()
            )
            .notifyStatus(true)
            .settings(SettingsDTO
                .builder()
                .gatewayConfig(Map.of("retryStatusFlag","true"))
                .orchConfig(new HashMap<>())
                .terminalConfig(new HashMap<>()).build()
            )
            .build();
    }


    public static Transaction getTransactionObject() {
        return  Transaction
            .builder()
            .id(1L)
            .uuid("123")
            .createdAt(LocalDateTime.of(2022,
                                        Month.MAY,
                                        25,
                                        11,
                                        39,
                                        40
            ))
            .succeeded(true)
            .authorizationCode("123")
            .transactionStatus(
                TransactionStatusSample.getTransactionStatus()
            )
            .entity(
                EntitySample.getEntity()
            )
            .total( new BigDecimal("500"))
            .subtotal(new BigDecimal("500"))
            .tax(new BigDecimal("50"))
            .paymentMethod(GeneralPaymentMethodSample.getGeneralPaymentMethod())
            .transactionDetail(
                TransactionDetail
                    .builder()
                    .transactionId(1L)
                    .transactionReference("Reference")
                    .transactionCode("Transaction Code")
                    .transactionType("Type")
                    .terminal(TerminalSample.getTerminal())
                    .build()
            )
            .build();
    }

    public static List<Transaction> getTransactionList() {
        List<Transaction> transactionsList = new ArrayList<>();

        transactionsList.add(getTransactionObject());
        transactionsList.add(getTransactionObject());
        return transactionsList;
    }

    public static JmsTransactionStatusPipeDTO getJmsTransactionStatusPipeDTOWithoutCodeAndReference() {
        return JmsTransactionStatusPipeDTO
            .builder()
            .jmsUpdateTransactionStatusJsonMessage("{}")
            .jmsUpdateTransactionStatusDTO(JmsUpdateTransactionStatusDTO.builder()
                .type(TRANSACTION_TYPE_PURCHASE)
                .key(AppConstants.STATUS_KEY_UUID)
                .identificator("abc")
                .transactionCode("")
                .transactionReference("")
                .authorizationCode("")
                .message("")
                .errorCode("")
                .errorDescription("")
                .transactionStatus(1L)
                .transactionValue(BigDecimal.TEN)
                .build()
            )
            .transaction(getTransaction())
            .transactionDetail(getTransactionDetailWithoutCodeAndReference())
            .notifyStatus(true)
            .build();
    }

    public static JmsTransactionStatusPipeDTO getJmsTransactionStatusPipeDTOWithAdditionalInfo() {
        return JmsTransactionStatusPipeDTO
            .builder()
            .jmsUpdateTransactionStatusJsonMessage("{}")
            .jmsUpdateTransactionStatusDTO(JmsUpdateTransactionStatusDTO.builder()
                .type(TRANSACTION_TYPE_PURCHASE)
                .key(AppConstants.STATUS_KEY_UUID)
                .identificator("abc")
                .transactionCode("")
                .transactionReference("")
                .authorizationCode("")
                .message("")
                .errorCode("")
                .errorDescription("")
                .transactionStatus(1L)
                .build()
            )
            .transaction(getTransactionWithAuthorizationCode())
            .transactionDetail(getTransactionDetailWithAdditionalData())
            .notifyStatus(true)
            .build();
    }

    public static JmsTransactionStatusPipeDTO getJmsTransactionStatusPipeDTO() {
        return JmsTransactionStatusPipeDTO
            .builder()
            .jmsUpdateTransactionStatusJsonMessage("{}")
            .jmsUpdateTransactionStatusDTO(JmsUpdateTransactionStatusDTO.builder()
                .type(TRANSACTION_TYPE_PURCHASE)
                .key(AppConstants.STATUS_KEY_UUID)
                .identificator("abc")
                .transactionCode("")
                .transactionReference("")
                .authorizationCode("")
                .message("")
                .errorCode("")
                .errorDescription("")
                .transactionStatus(1L)
                .transactionValue(BigDecimal.ONE)
                .build()
            )
            .transaction(getTransaction())
            .transactionDetail(getTransactionDetail())
            .notifyStatus(true)
            .build();
    }
    public static JmsTransactionStatusPipeDTO getJmsTransactionStatusPipeDTOWhitSettings() {
        return JmsTransactionStatusPipeDTO
            .builder()
            .jmsUpdateTransactionStatusJsonMessage("{}")
            .jmsUpdateTransactionStatusDTO(JmsUpdateTransactionStatusDTO.builder()
                .type(AppConstants.TRANSACTION_TYPE_PURCHASE)
                .key(AppConstants.STATUS_KEY_UUID)
                .identificator("abc")
                .transactionCode("")
                .transactionReference("")
                .authorizationCode("")
                .message("")
                .errorCode("")
                .errorDescription("")
                .transactionStatus(1L)
                .transactionValue(BigDecimal.TEN)
                .build()
            )
            .settings(SettingsDTO
                .builder()
                .gatewayConfig(
                    Map.of("retryStatusFlag","true"))
                .orchConfig(new HashMap<>())
                .terminalConfig(new HashMap<>())
                .build()
            )
            .transaction(getTransaction())
            .transactionDetail(getTransactionDetail())
            .notifyStatus(true)
            .build();
    }

    public static TransactionLog getTransactionLog() {
        return TransactionLog.builder()
            .transaction(getTransaction())
            .comment("Log")
            .id(1L)
            .build();
    }
}
