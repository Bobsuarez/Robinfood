package com.robinfood.paymentmethodsbc.mappers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.robinfood.paymentmethodsbc.configs.TransactionsConfig;
import com.robinfood.paymentmethodsbc.constants.AppConstants;
import com.robinfood.paymentmethodsbc.dto.CreditCardTokenDTO;
import com.robinfood.paymentmethodsbc.dto.PaymentResponseDTO;
import com.robinfood.paymentmethodsbc.dto.api.transactions.PaymentRequestDTO;
import com.robinfood.paymentmethodsbc.dto.api.transactions.TransactionStatusUpdateRequestDTO;
import com.robinfood.paymentmethodsbc.dto.api.transactions.PaymentRequestDTO.PaymentMethodDTO.PaymentReferencePaymentMethod;
import com.robinfood.paymentmethodsbc.dto.bci.BCISettingsDTO;
import com.robinfood.paymentmethodsbc.dto.bci.creditcard.BCIUpdateCreditCardRequestDTO;
import com.robinfood.paymentmethodsbc.dto.bci.transactions.BCIPaymentRequestDTO;
import com.robinfood.paymentmethodsbc.dto.bci.transactions.BCIPaymentRequestDTO.TransactionDetailsDTO.TransactionReferencePaymentMethod;
import com.robinfood.paymentmethodsbc.dto.bci.transactions.BCIPaymentRequestDTO.UserDetailsDTO;
import com.robinfood.paymentmethodsbc.dto.bci.transactions.BCIPaymentResponseDTO;
import com.robinfood.paymentmethodsbc.dto.bci.transactions.BCIRefundRequestDTO;
import com.robinfood.paymentmethodsbc.dto.bci.transactions.BCIRefundResponseDTO;
import com.robinfood.paymentmethodsbc.dto.bci.transactions.BCITransactionStatusRequestDTO;
import com.robinfood.paymentmethodsbc.dto.common.response.ResponseDTO;
import com.robinfood.paymentmethodsbc.dto.steps.PaymentPipeDTO;
import com.robinfood.paymentmethodsbc.dto.steps.SettingsDTO;
import com.robinfood.paymentmethodsbc.dto.steps.TransactionStatusPipeDTO;
import com.robinfood.paymentmethodsbc.model.CreditCard;
import com.robinfood.paymentmethodsbc.model.PaymentGatewayCreditCard;
import com.robinfood.paymentmethodsbc.model.Transaction;
import com.robinfood.paymentmethodsbc.model.TransactionDetail;
import com.robinfood.paymentmethodsbc.utils.JsonUtils;
import com.robinfood.paymentmethodsbc.utils.SafeCastUtils;
import com.robinfood.paymentmethodsbc.utils.Utilities;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.robinfood.paymentmethodsbc.utils.nullsafety.ModelsNullSafety.validateTransaction;
import static com.robinfood.paymentmethodsbc.utils.nullsafety.ModelsNullSafety.validateTransactionDetail;
import static com.robinfood.paymentmethodsbc.utils.nullsafety.PaymentPipeDTONullSafe.getTerminalName;
import static com.robinfood.paymentmethodsbc.utils.nullsafety.PaymentPipeDTONullSafe.getTerminalUuid;
import static com.robinfood.paymentmethodsbc.utils.nullsafety.PaymentPipeDTONullSafe.validatePaymentPipeDTO;
import static com.robinfood.paymentmethodsbc.utils.nullsafety.PaymentPipeDTONullSafe.validatePaymentRequestDTO;

@Slf4j
public final class PaymentProviderMapper {

    private PaymentProviderMapper() {}

    public static BCISettingsDTO getBCISettingsWithTerminalDTO(
        Map<String, String> config,
        Map<String, String> orchConfig,
        PaymentPipeDTO paymentPipeDTO
    ) {
        return BCISettingsDTO
            .builder()
            .orchestrator(orchConfig)
            .gateway(config)
            .terminal(
                Optional.ofNullable(paymentPipeDTO.getSettings())
                        .map(SettingsDTO::getTerminalConfig)
                        .orElse(null)
            )
            .build();
    }

    public static BCISettingsDTO getBCISettingsDTO(
        Map<String, String> config,
        Map<String, String> orchConfig
    ) {
        return BCISettingsDTO
            .builder()
            .orchestrator(orchConfig)
            .gateway(config)
            .build();
    }

    public static BCIRefundRequestDTO getBCIRefundRequestDTO(
        Map<String, String> config,
        Map<String, String> orchConfig,
        String transactionReference,
        String transactionCode,
        String reason
    ) {
        return BCIRefundRequestDTO
            .builder()
            .transaction(
                BCIRefundRequestDTO
                    .BCIRefundTransaction.builder()
                    .transactionCode(transactionCode)
                    .transactionReference(transactionReference)
                    .reason(reason)
                    .build()
            )
            .settings(getBCISettingsDTO(config, orchConfig))
            .build();
    }

    private static int getCurrencyDecimals(Map<String, String> config) {
        int defaultCurrencyDecimals = AppConstants.DEFAULT_CURRENCY_DECIMALS;

        String configCurrencyDecimals = config.get(AppConstants.CURRENCY_DECIMALS_SETTINGS_NAME);

        if (Objects.isNull(configCurrencyDecimals)) {
            return defaultCurrencyDecimals;
        }

        Long currencyDecimals = Utilities.stringToLong(configCurrencyDecimals);

        if (Objects.isNull(currencyDecimals)) {
            return defaultCurrencyDecimals;
        }

        return currencyDecimals.intValue();
    }

    public static BCIPaymentRequestDTO getBCITransactionRequestDTO(
        Map<String, String> config,
        Map<String, String> orchConfig,
        PaymentPipeDTO paymentPipeDTO
    ) {
        paymentPipeDTO = validatePaymentPipeDTO(paymentPipeDTO);
        Transaction transaction = validateTransaction(paymentPipeDTO.getTransaction());
        TransactionDetail transactionDetail = validateTransactionDetail(paymentPipeDTO.getTransactionDetail());
        PaymentRequestDTO request = validatePaymentRequestDTO(paymentPipeDTO.getTransactionRequestDTO());

        Optional<PaymentGatewayCreditCard> paymentGatewayCreditCard = Optional.ofNullable(
            paymentPipeDTO.getPaymentGatewayCreditCard()
        );

        Optional<CreditCard> creditCard = Optional.ofNullable(paymentPipeDTO.getCreditCard());

        String userAgent = request.getOrigin().getUserAgent();

        if (userAgent == null || userAgent.isBlank()) {
            userAgent = AppConstants.DEFAULT_USER_AGENT;
        }

        int decimals = getCurrencyDecimals(config);

        RoundingMode roundMode = RoundingMode.HALF_UP;

        BigDecimal total = request
            .getPayment()
            .getTotal()
            .setScale(decimals, roundMode);
        BigDecimal tax = request
            .getPayment()
            .getTax()
            .setScale(decimals, roundMode);
        BigDecimal subtotal = total.subtract(tax);

        return BCIPaymentRequestDTO
            .builder()
            .countryId(request.getCountryId())
            .transaction(
                BCIPaymentRequestDTO
                    .TransactionDetailsDTO.builder()
                    .id(transaction.getId())
                    .uuid(transaction.getUuid())
                    .reference(transactionDetail.getEntityReference())
                    .sourceId(transaction.getEntitySource())
                    .creditCardToken(
                        paymentGatewayCreditCard.map(PaymentGatewayCreditCard::getToken).orElse(null)
                    )
                    .creditCardTypeCode(
                        creditCard
                            .map(x -> x.getCreditCardType().getCode())
                            .orElse(null)
                    )
                    .installments(request.getPaymentMethod().getInstallmentsNumber())
                    .total(total)
                    .tax(tax)
                    .subtotal(subtotal)
                    .referencePaymentMethods(getRefPaymentMethodIds(paymentPipeDTO))
                    .dataphoneCode(transactionDetail.getDataphoneCode())
                    .terminalUuid(getTerminalUuid(paymentPipeDTO))
                    .terminalName(getTerminalName(paymentPipeDTO))
                    .build()
            )
            .settings(getBCISettingsWithTerminalDTO(config, orchConfig, paymentPipeDTO))
            .user(getBCITransactionRequestUserDetailsDTO(paymentPipeDTO, userAgent))
            .origin(BCIPaymentRequestDTO
                        .OriginDTO.builder()
                        .channelId(request.getOrigin().getChannelId())
                        .storeId(request.getOrigin().getStoreId())
                        .build())
            .build();
    }

    private static UserDetailsDTO getBCITransactionRequestUserDetailsDTO(
        final PaymentPipeDTO paymentPipeDTO,
        final String userAgent
    ) {
        CreditCard creditCard = Optional.ofNullable(
            paymentPipeDTO.getCreditCard()
        ).orElse(CreditCard.builder().build());

        PaymentRequestDTO request = paymentPipeDTO.getTransactionRequestDTO();

        return BCIPaymentRequestDTO
            .UserDetailsDTO.builder()
            .id(request.getUser().getUserId())
            .address(creditCard.getUserAddress())
            .city(creditCard.getUserCity())
            .identificationType(creditCard.getUserIdentificationType())
            .identificationNumber(creditCard.getUserIdentificationNumber())
            .email(creditCard.getUserEmail())
            .phone(request.getUser().getPhoneCode().concat(request.getUser().getPhoneNumber()))
            .name(request.getUser().getFirstName() + " " + request.getUser().getLastName())
            .ipAddress(request.getOrigin().getIpAddress())
            .isUpdated(creditCard.getIsUpdated())
            .userAgent(userAgent)
            .build();
    }

    public static PaymentResponseDTO processPaymentResponse(
        ResponseDTO<BCIPaymentResponseDTO> transactionResponse,
        TransactionsConfig transactionsConfig
    ) {
        BCIPaymentResponseDTO response = transactionResponse.getData();
        final long DEFAULT_STATUS_VALUE = BigDecimal.ZERO.longValue();

        Long transactionStatus = Optional
            .ofNullable(response.getTransactionStatus())
            .orElse(DEFAULT_STATUS_VALUE);
        
        transactionStatus =
            Optional
                .ofNullable(transactionStatus)
                .filter((Long statusId) -> statusId.equals(DEFAULT_STATUS_VALUE))
                .filter((Long statusId) -> response.isSucceeded())
                .map((Long statusId) -> transactionsConfig.getStatusId(TransactionsConfig.STATUS_ACCEPTED_ID))
                .orElse(transactionStatus);

        transactionStatus =
            Optional
                .ofNullable(transactionStatus)
                .filter((Long statusId) -> statusId.equals(DEFAULT_STATUS_VALUE))
                .filter((Long statusId) -> response.isPending())
                .map((Long statusId) -> transactionsConfig.getStatusId(TransactionsConfig.STATUS_PENDING_ID))
                .orElse(transactionStatus);

        transactionStatus =
            Optional
                .ofNullable(transactionStatus)
                .filter((Long statusId) -> statusId.equals(DEFAULT_STATUS_VALUE))
                .filter((Long statusId) -> response.isCanceled())
                .map((Long statusId) -> transactionsConfig.getStatusId(TransactionsConfig.STATUS_CANCELED_ID))
                .orElse(transactionStatus);

        transactionStatus =
            Optional
                .ofNullable(transactionStatus)
                .filter((Long statusId) -> statusId.equals(DEFAULT_STATUS_VALUE))
                .map((Long statusId) -> transactionsConfig.getStatusId(TransactionsConfig.STATUS_REJECTED_ID))
                .orElse(transactionStatus);

        BCIPaymentResponseDTO.TransactionGatewayResponse gwResponse = response.getGatewayResponse();

        String authorizationCode = null;
        String transactionCode = null;
        String transactionReference = null;
        String errorCode = null;
        String errorDescription = null;
        String message = null;
        String dataphoneCode = null;
        String creditCardMaskedNumber = null;
        String accountType = null;
        String franchise = null;
        String dataphoneReceiptNumber = null;
        Integer installments = null;
        String establishmentCode = null;
        BigDecimal transactionValue = BigDecimal.ZERO;
        BigDecimal transactionTaxValue = BigDecimal.ZERO;

        if (Objects.nonNull(gwResponse)) {
            authorizationCode = gwResponse.getAuthorizationCode();
            transactionCode = gwResponse.getTransactionCode();
            transactionReference = gwResponse.getTransactionReference();
            errorCode = gwResponse.getErrorCode();
            errorDescription = gwResponse.getErrorDescription();
            message = gwResponse.getMessage();
            dataphoneCode = gwResponse.getDataphoneCode();
            creditCardMaskedNumber = gwResponse.getCreditCardMaskedNumber();
            accountType = gwResponse.getAccountType();
            franchise = gwResponse.getFranchise();
            dataphoneReceiptNumber = gwResponse.getDataphoneReceiptNumber();
            installments = gwResponse.getInstallments();
            establishmentCode = gwResponse.getEstablishmentCode();
            transactionValue = gwResponse.getTransactionValue();
            transactionTaxValue = gwResponse.getTransactionTaxValue();
        }

        return PaymentResponseDTO.builder()
            .transactionStatus(transactionStatus)
            .authorizationCode(authorizationCode)
            .transactionCode(transactionCode)
            .transactionReference(transactionReference)
            .errorCode(errorCode)
            .errorDescription(errorDescription)
            .message(message)
            .bciResponseBody(JsonUtils.convertToJson(transactionResponse))
            .statusCode(transactionResponse.getCode())
            .statusCode(transactionResponse.getCode())
            .dataphoneCode(dataphoneCode)
            .creditCardMaskedNumber(creditCardMaskedNumber)
            .accountType(accountType)
            .franchise(franchise)
            .dataphoneReceiptNumber(dataphoneReceiptNumber)
            .installments(installments)
            .establishmentCode(establishmentCode)
            .transactionValue(transactionValue)
            .transactionTaxValue(transactionTaxValue)
            .build();
    }

    public static PaymentResponseDTO processRefundResponse(
        ResponseDTO<BCIRefundResponseDTO> refundResponse,
        TransactionsConfig transactionsConfig
    ) {
        BCIRefundResponseDTO response = refundResponse.getData();
        Long transactionStatus = transactionsConfig.getStatusId(TransactionsConfig.STATUS_REFUND_REJECTED_ID);

        if (response.isSucceeded()) {
            transactionStatus = transactionsConfig.getStatusId(TransactionsConfig.STATUS_REFUND_PENDING_ID);
        }

        return PaymentResponseDTO
            .builder()
            .transactionStatus(transactionStatus)
            .transactionCode(response.getGatewayResponse().getTransactionCode())
            .transactionReference(response.getGatewayResponse().getTransactionReference())
            .authorizationCode(response.getGatewayResponse().getAuthorizationCode())
            .statusCode(refundResponse.getCode())
            .bciResponseBody(JsonUtils.convertToJson(refundResponse))
            .build();
    }

    public static PaymentResponseDTO getPaymentResponseDTOFromFeignException(
        FeignException e,
        Long transactionStatus
    ) {
        String transactionCode = null;
        String transactionReference = null;
        String errorCode = null;
        String errorDescription = null;
        String message = null;
        String response = e.contentUTF8();

        if (Objects.nonNull(response) && !response.isBlank()) {
            try {
                final ResponseDTO<?> responseDTO = JsonUtils.jsonToObject(response, ResponseDTO.class);

                if (responseDTO.getData() != null) {
                    Map<String, Object> dataMap = (Map) responseDTO.getData();
                    transactionCode = safeExtract(dataMap, AppConstants.GW_TRANSACTION_CODE);
                    transactionReference = safeExtract(dataMap, AppConstants.GW_TRANSACTION_REFERENCE);
                    errorCode = safeExtract(dataMap, AppConstants.GW_ERROR_CODE);
                    errorDescription = safeExtract(dataMap, AppConstants.GW_ERROR_DESCRIPTION);
                    message = safeExtract(dataMap, AppConstants.GW_MESSAGE);
                }
            } catch (JsonProcessingException jpe) {
                log.error(
                    String.format("Error obteniendo data desde FeignException %s", response),
                    jpe
                );
            }
        }

        return PaymentResponseDTO
            .builder()
            .transactionStatus(transactionStatus)
            .transactionReference(transactionReference)
            .transactionCode(transactionCode)
            .errorCode(errorCode)
            .errorDescription(errorDescription)
            .message(message)
            .bciResponseBody(response)
            .statusCode(e.status())
            .build();
    }

    private static String safeExtract(Map<String, Object> dataMap, String key) {
        return SafeCastUtils.safeObjectToString(dataMap.get(key));
    }

    public static BCITransactionStatusRequestDTO getTransactionStatusRequestBody(
        SettingsDTO settings,
        TransactionStatusPipeDTO transactionStatusPipe
    ) {
        TransactionStatusUpdateRequestDTO request = transactionStatusPipe.getTransactionStatusUpdateRequestDTO();
        Transaction transaction = transactionStatusPipe.getTransaction();
        TransactionDetail transactionDetail = transactionStatusPipe.getTransactionDetail();

        return BCITransactionStatusRequestDTO
            .builder()
            .notification(request.getNotification())
            .settings(
                BCISettingsDTO
                    .builder()
                    .gateway(settings.getGatewayConfig())
                    .orchestrator(settings.getOrchConfig())
                    .terminal(settings.getTerminalConfig())
                    .build()
            )
            .transaction(
                BCITransactionStatusRequestDTO
                    .TransactionDetailsDTO
                    .builder()
                    .id(transaction.getId())
                    .uuid(transaction.getUuid())
                    .reference(transactionDetail.getEntityReference())
                    .sourceId(transaction.getEntitySource())
                    .total(transaction.getTotal())
                    .tax(transaction.getTax())
                    .subtotal(transaction.getSubtotal())
                    .build()
            )
            .build();
    }


    private static List<TransactionReferencePaymentMethod> getRefPaymentMethodIds(PaymentPipeDTO paymentPipeDTO){

        List<PaymentReferencePaymentMethod> paymentMethodList =  paymentPipeDTO
            .getTransactionRequestDTO()
            .getPaymentMethod()
            .getReferencePaymentMethods();

        return Optional.ofNullable(paymentMethodList)
            .orElseGet(Collections::emptyList)
            .stream()
            .filter(Objects::nonNull)
            .map((PaymentReferencePaymentMethod method) -> 
                TransactionReferencePaymentMethod.builder()
                .id(method.getId())
                .build()
            ).collect(Collectors.toList());
    }

    public static BCIUpdateCreditCardRequestDTO getBCIUpdateCreditCardRequestDTO(
        Map<String, String> config,
        Map<String, String> orchConfig,
        CreditCardTokenDTO creditCardTokenDTO,
        String token
    ) {
        BCIUpdateCreditCardRequestDTO.CreditCardDetailsDTO creditCard = BCIUpdateCreditCardRequestDTO
            .CreditCardDetailsDTO.builder()
            .token(token)
            .userId(creditCardTokenDTO.getPayerId())
            .creditCardTypeCode(creditCardTokenDTO.getCompanyCreditCard().getName())
            .firstName(creditCardTokenDTO.getPayerFirstName())
            .lastName(creditCardTokenDTO.getPayerLastName())
            .identificationNumber(creditCardTokenDTO.getPayerIdentification())
            .number(creditCardTokenDTO.getCreditCardNumber())
            .month(Integer.valueOf(creditCardTokenDTO.getCreditCardExpirationMonth()))
            .year(Integer.valueOf(creditCardTokenDTO.getCreditCardExpirationYear()))
            .verificationValue(Integer.valueOf(creditCardTokenDTO.getCreditCardVerificationCode()))
            .build();

        return BCIUpdateCreditCardRequestDTO
            .builder()
            .creditCard(creditCard)
            .settings(getBCISettingsDTO(config, orchConfig))
            .build();
    }
}
