package com.robinfood.paymentmethodsbc.components.providers.impl;

import static com.robinfood.paymentmethodsbc.constants.LoggerPaymentConstants.BCI_PAYMENT_RESPONSE;
import static com.robinfood.paymentmethodsbc.constants.LoggerPaymentConstants.BCI_VALIDATE_STATUS_ERROR;
import static com.robinfood.paymentmethodsbc.constants.LoggerPaymentConstants.PAYMENT_IS_PENDING;
import static com.robinfood.paymentmethodsbc.constants.LoggerPaymentConstants.PAYMENT_STATUS_PENDING;
import static com.robinfood.paymentmethodsbc.constants.TransactionConstants.STATUS_ERROR;
import static com.robinfood.paymentmethodsbc.utils.JsonUtils.convertToJson;

import com.robinfood.paymentmethodsbc.annotations.BasicLog;
import com.robinfood.paymentmethodsbc.configs.TransactionsConfig;
import com.robinfood.paymentmethodsbc.dto.CreditCardResponseDTO;
import com.robinfood.paymentmethodsbc.dto.CreditCardTokenDTO;
import com.robinfood.paymentmethodsbc.dto.PaymentResponseDTO;
import com.robinfood.paymentmethodsbc.dto.StatusResponseDTO;
import com.robinfood.paymentmethodsbc.dto.bci.creditcard.BCICreateCreditCardRequestDTO;
import com.robinfood.paymentmethodsbc.dto.bci.creditcard.BCICreditCardResponseDTO;
import com.robinfood.paymentmethodsbc.dto.bci.creditcard.BCIDeleteCreditCardRequestDTO;
import com.robinfood.paymentmethodsbc.dto.bci.transactions.BCICancelTransactionResponseDTO;
import com.robinfood.paymentmethodsbc.dto.bci.transactions.BCIPaymentRequestDTO;
import com.robinfood.paymentmethodsbc.dto.bci.transactions.BCIPaymentResponseDTO;
import com.robinfood.paymentmethodsbc.dto.bci.transactions.BCIRefundRequestDTO;
import com.robinfood.paymentmethodsbc.dto.bci.transactions.BCIRefundResponseDTO;
import com.robinfood.paymentmethodsbc.dto.bci.transactions.BCITransactionStatusRequestDTO;
import com.robinfood.paymentmethodsbc.dto.bci.transactions.BCITransactionStatusResponseDTO;
import com.robinfood.paymentmethodsbc.dto.bci.transactions.BCITransactionStatusResponseDTO.TransactionStatusResponse;
import com.robinfood.paymentmethodsbc.dto.common.response.ResponseDTO;
import com.robinfood.paymentmethodsbc.dto.steps.PaymentPipeDTO;
import com.robinfood.paymentmethodsbc.dto.steps.SettingsDTO;
import com.robinfood.paymentmethodsbc.dto.steps.TransactionStatusPipeDTO;
import com.robinfood.paymentmethodsbc.exceptions.PaymentMethodsException;
import com.robinfood.paymentmethodsbc.mappers.CreditCardMapper;
import com.robinfood.paymentmethodsbc.mappers.PaymentProviderMapper;
import com.robinfood.paymentmethodsbc.components.clients.BCIClient;
import com.robinfood.paymentmethodsbc.components.providers.BCIProvider;
import com.robinfood.paymentmethodsbc.components.providers.config.BCIProviderConfig;
import com.robinfood.paymentmethodsbc.utils.LoggerReportUtils;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.util.Map;

@Slf4j
@Component
@Scope(value = "prototype")
public class BCIProviderImpl implements BCIProvider {
    private final BCIClient bciHttpClient;
    private final BCIProviderConfig bciProviderConfig;
    private URI bciUri;
    private String serviceToken;

    public BCIProviderImpl(
        BCIClient bciHttpClient,
        BCIProviderConfig bciProviderConfig
    ) {
        this.bciHttpClient = bciHttpClient;
        this.bciProviderConfig = bciProviderConfig;
    }

    private void setConfiguration(
        Map<String, String> config,
        Map<String, String> orchConfig
    ) throws PaymentMethodsException {

        bciUri = URI.create(bciProviderConfig.getUrl(config, orchConfig));
        serviceToken = bciProviderConfig.getServiceToken(false);
    }


    @BasicLog
    @Override
    public PaymentResponseDTO doPayment(
        SettingsDTO settingsDTO,
        PaymentPipeDTO transactionCreatePipeDTO
    ) throws PaymentMethodsException {

        LoggerReportUtils.doPaymentLogger(
            PAYMENT_STATUS_PENDING, PAYMENT_IS_PENDING, transactionCreatePipeDTO
        );

        setConfiguration(settingsDTO.getGatewayConfig(), settingsDTO.getOrchConfig());

        BCIPaymentRequestDTO body = PaymentProviderMapper.getBCITransactionRequestDTO(
            settingsDTO.getGatewayConfig(),
            settingsDTO.getOrchConfig(),
            transactionCreatePipeDTO
        );
        PaymentResponseDTO response = doBCIPayment(body);

        if (response.getStatusCode() == HttpStatus.UNAUTHORIZED.value()) {
            serviceToken = bciProviderConfig.getServiceToken(true);
            response = doBCIPayment(body);
        }
        TransactionsConfig transactionsConfig = bciProviderConfig.getTransactionsConfig();
        if (!transactionsConfig.isStatusPending(response.getTransactionStatus())) {
            LoggerReportUtils.doPaymentLogger(
                BCI_PAYMENT_RESPONSE, transactionCreatePipeDTO, response
            );
        }

        return response;
    }

    private PaymentResponseDTO doBCIPayment(
        BCIPaymentRequestDTO body
    ) {
        TransactionsConfig transactionsConfig = bciProviderConfig.getTransactionsConfig();
        try {
            log.info("BCI Payment request to {}: \n{}", bciUri.toString(), convertToJson(body));

            ResponseDTO<BCIPaymentResponseDTO> transactionResponse =
                bciHttpClient.generateTransaction(bciUri, serviceToken, body);

            log.info(
                "BCI Payment response from {}: \n{}", bciUri.toString(), convertToJson(transactionResponse)
            );  

            return PaymentProviderMapper.processPaymentResponse(
                transactionResponse, transactionsConfig
            );
        } catch (FeignException e) {
            log.error("Error generating payment via BCI", e);

            return PaymentProviderMapper.getPaymentResponseDTOFromFeignException(
                e, transactionsConfig.getStatusId(TransactionsConfig.STATUS_ERROR_ID)
            );
        }
    }

    @BasicLog
    @Override
    public PaymentResponseDTO doRefund(
        Map<String, String> config,
        Map<String, String> orchConfig,
        String transactionReference,
        String transactionCode,
        String reason
    ) throws PaymentMethodsException {
        setConfiguration(config, orchConfig);

        BCIRefundRequestDTO body = PaymentProviderMapper.getBCIRefundRequestDTO(
            config,
            orchConfig,
            transactionReference,
            transactionCode,
            reason
        );

        PaymentResponseDTO response = doBCIRefund(body);

        if (response.getStatusCode() == HttpStatus.UNAUTHORIZED.value()) {
            serviceToken = bciProviderConfig.getServiceToken(true);
            response = doBCIRefund(body);
        }

        return response;
    }

    private PaymentResponseDTO doBCIRefund(
        BCIRefundRequestDTO body
    ) {
        TransactionsConfig transactionsConfig = bciProviderConfig.getTransactionsConfig();
        try {
            log.info(
                "BCI Refund request to {}: \n{}",
                bciUri.toString(), convertToJson(body)
            );

            ResponseDTO<BCIRefundResponseDTO> response = bciHttpClient.refundTransaction(
                bciUri,
                serviceToken,
                body
            );

            log.info(
                "BCI Refund response from {}: \n{}",
                bciUri.toString(), convertToJson(response)
            );

            return PaymentProviderMapper.processRefundResponse(
                response,
                transactionsConfig
            );
        } catch (FeignException e) {
            log.error("Error refunding transaction via BCI", e);

            return PaymentProviderMapper.getPaymentResponseDTOFromFeignException(
                e,
                transactionsConfig.getStatusId(TransactionsConfig.STATUS_REFUND_REJECTED_ID)
            );
        }
    }

    @Override
    public String doCreditCardTokenization(
        Map<String, String> config,
        Map<String, String> orchConfig,
        CreditCardTokenDTO creditCardTokenDTO
    ) throws PaymentMethodsException {
        setConfiguration(config, orchConfig);

        BCICreateCreditCardRequestDTO parameters = CreditCardMapper.getBCICreateCreditCardRequestDTO(
            config,
            orchConfig,
            creditCardTokenDTO
        );

        CreditCardResponseDTO response = doBCICreditCardTokenization(
            parameters
        );

        if (response.getStatusCode() == HttpStatus.UNAUTHORIZED.value()) {
            serviceToken = bciProviderConfig.getServiceToken(true);
            response = doBCICreditCardTokenization(parameters);
        }

        if (response.getErrorMessage() != null) {
            throw new PaymentMethodsException(response.getErrorMessage());
        }

        return response.getCreditCardToken();
    }

    private CreditCardResponseDTO doBCICreditCardTokenization(
        BCICreateCreditCardRequestDTO body
    ) {
        try {
            log.info(
                "BCI CreditCardTokenization request from {}: \n user: {}," +
                    "firstName: {}, lastName: {}, creditCardCode: {}, identificationNumber: {}",
                bciUri.toString(),
                body.getCreditCard().getUserId(),
                body.getCreditCard().getFirstName(),
                body.getCreditCard().getLastName(),
                body.getCreditCard().getCreditCardTypeCode(),
                body.getCreditCard().getIdentificationNumber()
            );

            ResponseDTO<BCICreditCardResponseDTO> response = bciHttpClient.createCreditCard(
                bciUri,
                serviceToken,
                body
            );

            log.info(
                "BCI CreditCardTokenization response from {}: \n{}",
                bciUri.toString(), convertToJson(response)
            );

            return CreditCardResponseDTO
                .builder()
                .creditCardToken(response.getData().getCreditCard().getToken())
                .statusCode(response.getCode())
                .build();
                
        } catch (FeignException e) {
            log.error("Error tokenizing credit card via BCI", e);

            return CreditCardResponseDTO
                .builder()
                .creditCardToken(null)
                .statusCode(e.status())
                .errorMessage(e.getMessage())
                .build();
        }
    }

    @BasicLog
    @Override
    public void doCreditCardRemove(
        Map<String, String> config,
        Map<String, String> orchConfig,
        String userId,
        String token
    ) throws PaymentMethodsException {
        setConfiguration(config, orchConfig);

        BCIDeleteCreditCardRequestDTO body = CreditCardMapper.getBCIDeleteCreditCardRequestDTO(
            config,
            orchConfig,
            token,
            userId
        );

        CreditCardResponseDTO response = doBCICreditCardRemove(
            body
        );

        if (response.getStatusCode() == HttpStatus.UNAUTHORIZED.value()) {
            serviceToken = bciProviderConfig.getServiceToken(true);
            response = doBCICreditCardRemove(body);
        }

        if (response.getErrorMessage() != null) {
            throw new PaymentMethodsException(response.getErrorMessage());
        }
    }

    private CreditCardResponseDTO doBCICreditCardRemove(
        BCIDeleteCreditCardRequestDTO body
    ) {
        try {
            log.info(
                "BCI CreditCardRemove request to {}: \n{}",
                bciUri.toString(), convertToJson(body)
            );

            ResponseDTO<BCICreditCardResponseDTO> response = bciHttpClient.deleteCreditCard(
                bciUri,
                serviceToken,
                body
            );

            log.info(
                "BCI CreditCardRemove response from {}: \n{}",
                bciUri.toString(), convertToJson(response)
            );

            return CreditCardResponseDTO
                .builder()
                .statusCode(response.getCode())
                .build();
        } catch (FeignException e) {
            log.error("Error removing credit card via BCI", e);

            return CreditCardResponseDTO
                .builder()
                .errorMessage(e.getMessage())
                .statusCode(e.status())
                .build();
        }
    }

    @BasicLog
    @Override
    public BCITransactionStatusResponseDTO doValidateStatus(
        final SettingsDTO settings,
        final TransactionStatusPipeDTO transactionStatusPipe
    ) throws PaymentMethodsException {
        setConfiguration(settings.getGatewayConfig(), settings.getOrchConfig());

        log.info(
            "Status validation request to BCI. TransactionCode: {}. Notification: {}",
            transactionStatusPipe.getTransactionStatusUpdateRequestDTO().getIdentificator(),
            transactionStatusPipe.getTransactionStatusUpdateRequestDTO().getNotification()
        );

        final BCITransactionStatusRequestDTO body = PaymentProviderMapper.getTransactionStatusRequestBody(
            settings,
            transactionStatusPipe
        );

        StatusResponseDTO response = doBCIValidateStatus(body);

        if (response.getStatusCode() == HttpStatus.UNAUTHORIZED.value()) {
            serviceToken = bciProviderConfig.getServiceToken(true);
            response = doBCIValidateStatus(body);
        }

        if (response.getErrorMessage() != null) {
            final String message = BCI_VALIDATE_STATUS_ERROR.concat(response.getErrorMessage());
            LoggerReportUtils.doValidateStatusErrorLogger(
                message, transactionStatusPipe, response
            );
            throw new PaymentMethodsException(response.getErrorMessage());
        }

        return response.getStatusResult();
    }

    @BasicLog
    @Override
    public BCICancelTransactionResponseDTO doCancelTransaction(
        SettingsDTO settingsDTO, String reference
    ) throws PaymentMethodsException {
        setConfiguration(settingsDTO.getGatewayConfig(), settingsDTO.getOrchConfig());

        BCICancelTransactionResponseDTO responseBci = doCancelTransaction(serviceToken, reference);

        if (responseBci.getStatusCode() == HttpStatus.UNAUTHORIZED.value()) {
            serviceToken = bciProviderConfig.getServiceToken(true);
            responseBci = doCancelTransaction(serviceToken, reference);
        }

        return responseBci;
    }

    private BCICancelTransactionResponseDTO doCancelTransaction(
        final String token,
        final String reference
    ) {
        try {
            log.info(
                "BCI cancel previous transaction request {}: \n{}",
                bciUri.toString(), convertToJson(reference)
            );

            ResponseDTO<BCICancelTransactionResponseDTO> bciResponse =
                bciHttpClient.cancelTransaction(bciUri, token, reference);

            log.info(
                "BCI cancel previous transaction response {}: \n{}",
                bciUri.toString(), convertToJson(bciResponse)
            );

            return bciResponse.getData().withStatusCode(bciResponse.getCode());
        } catch (FeignException e) {
            log.error("Error canceling previous transaction", e);

            return BCICancelTransactionResponseDTO
                .builder()
                .error("Error")
                .msg(e.getMessage())
                .statusCode(e.status())
                .build();
        }
    }

    private StatusResponseDTO doBCIValidateStatus(BCITransactionStatusRequestDTO body) {
        try {
            log.info("BCI UpdateTransactionStatus request to {}: \n{}", bciUri.toString(), convertToJson(body));
            ResponseDTO<BCITransactionStatusResponseDTO> response = bciHttpClient.validateStatus(
                bciUri,
                serviceToken,
                body
            );
            log.info("BCI UpdateTransactionStatus response from {}: \n{}", 
                bciUri.toString(), convertToJson(response)
            );

            return StatusResponseDTO.builder().statusResult(response.getData()).statusCode(response.getCode()).build();
        } catch (FeignException e) {
            log.error("Error updating status via BCI", e);

            return StatusResponseDTO
                .builder()
                .statusCode(e.status())
                .statusResult(
                    BCITransactionStatusResponseDTO
                        .builder()
                        .transaction(TransactionStatusResponse.builder().transactionStatus(STATUS_ERROR).build())
                        .build()
                )
                .errorMessage(e.getMessage())
                .build();
        }
    }

}
