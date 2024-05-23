package com.robinfood.paymentmethodsbc.components.providers.impl;

import static com.robinfood.paymentmethodsbc.utils.JsonUtils.convertToJson;

import java.net.URI;
import java.util.Map;

import com.robinfood.paymentmethodsbc.mappers.CreditCardMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import com.robinfood.paymentmethodsbc.annotations.BasicLog;
import com.robinfood.paymentmethodsbc.components.clients.BCIClient;
import com.robinfood.paymentmethodsbc.components.providers.BCICreditCardProvider;
import com.robinfood.paymentmethodsbc.components.providers.config.BCIProviderConfig;
import com.robinfood.paymentmethodsbc.dto.CreditCardResponseDTO;
import com.robinfood.paymentmethodsbc.dto.CreditCardTokenDTO;
import com.robinfood.paymentmethodsbc.dto.bci.creditcard.BCICreateCreditCardRequestDTO;
import com.robinfood.paymentmethodsbc.dto.bci.creditcard.BCICreditCardResponseDTO;
import com.robinfood.paymentmethodsbc.dto.bci.creditcard.BCIDeleteCreditCardRequestDTO;
import com.robinfood.paymentmethodsbc.dto.bci.creditcard.BCIUpdateCreditCardRequestDTO;
import com.robinfood.paymentmethodsbc.dto.common.response.ResponseDTO;
import com.robinfood.paymentmethodsbc.exceptions.PaymentMethodsException;
import com.robinfood.paymentmethodsbc.mappers.PaymentProviderMapper;

import feign.FeignException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class BCICreditCardProviderImpl implements BCICreditCardProvider {
    
    private final BCIClient bciHttpClient;
    private final BCIProviderConfig bciProviderConfig;
    private URI bciUri;
    private String serviceToken;

    public BCICreditCardProviderImpl(
        BCIClient bciHttpClient,
        BCIProviderConfig bciProviderConfig
    ) {
        this.bciHttpClient = bciHttpClient;
        this.bciProviderConfig = bciProviderConfig;
    }

    private void setConfig(
        Map<String, String> config,
        Map<String, String> orchConfig
    ) throws PaymentMethodsException {

        bciUri = URI.create(bciProviderConfig.getUrl(config, orchConfig));
        serviceToken = bciProviderConfig.getServiceToken(false);
    }


    @Override
    public String createToken(
        Map<String, String> config,
        Map<String, String> orchConfig,
        CreditCardTokenDTO creditCardTokenDTO
    ) throws PaymentMethodsException {
        setConfig(config, orchConfig);

        BCICreateCreditCardRequestDTO parameters = CreditCardMapper.getBCICreateCreditCardRequestDTO(
            config,
            orchConfig,
            creditCardTokenDTO
        );

        CreditCardResponseDTO response = creditCardTokenization(
            parameters
        );

        if (response.getStatusCode() == HttpStatus.UNAUTHORIZED.value()) {
            serviceToken = bciProviderConfig.getServiceToken(true);
            response = creditCardTokenization(parameters);
        }

        if (response.getErrorMessage() != null) {
            throw new PaymentMethodsException(response.getErrorMessage());
        }

        return response.getCreditCardToken();
    }

    private CreditCardResponseDTO creditCardTokenization(
        BCICreateCreditCardRequestDTO body
    ) {
        try {
            log.info(
                "BCI CreditCardTokenization from {}: \n user: {}," +
                    "firstName: {}, lastName: {}",
                bciUri.toString(),
                body.getCreditCard().getUserId(),
                body.getCreditCard().getFirstName(),
                body.getCreditCard().getLastName()
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
    public void removeToken(
        Map<String, String> config,
        Map<String, String> orchConfig,
        String userId,
        String token
    ) throws PaymentMethodsException {
        setConfig(config, orchConfig);

        BCIDeleteCreditCardRequestDTO body = CreditCardMapper.getBCIDeleteCreditCardRequestDTO(
            config,
            orchConfig,
            token,
            userId
        );

        CreditCardResponseDTO response = creditCardRemove(
            body
        );

        if (response.getStatusCode() == HttpStatus.UNAUTHORIZED.value()) {
            serviceToken = bciProviderConfig.getServiceToken(true);
            response = creditCardRemove(body);
        }

        if (response.getErrorMessage() != null) {
            throw new PaymentMethodsException(response.getErrorMessage());
        }
    }

    private CreditCardResponseDTO creditCardRemove(
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

    @Override
    public String updateToken(
        Map<String, String> config, 
        Map<String, String> orchConfig,
        CreditCardTokenDTO creditCardTokenDTO,
        String token
    ) throws PaymentMethodsException {
        setConfig(config, orchConfig);

        BCIUpdateCreditCardRequestDTO parameters = PaymentProviderMapper.getBCIUpdateCreditCardRequestDTO(
            config,
            orchConfig,
            creditCardTokenDTO,
            token
        );

        CreditCardResponseDTO response = updateTokenization(
            parameters
        );

        if (response.getStatusCode() == HttpStatus.UNAUTHORIZED.value()) {
            serviceToken = bciProviderConfig.getServiceToken(true);
            response = updateTokenization(parameters);
        }

        if (response.getErrorMessage() != null) {
            throw new PaymentMethodsException(response.getErrorMessage());
        }

        return response.getCreditCardToken();
    }



    private CreditCardResponseDTO updateTokenization(
        BCIUpdateCreditCardRequestDTO body
    ) {
        try {
            log.info(
                "BCI CreditCardUpdateTokenization request from {}: \n user: {}," +
                    "firstName: {}, lastName: {}, creditCardCode: {}, identificationNumber: {}",
                bciUri.toString(),
                body.getCreditCard().getUserId(),
                body.getCreditCard().getFirstName(),
                body.getCreditCard().getLastName(),
                body.getCreditCard().getCreditCardTypeCode(),
                body.getCreditCard().getIdentificationNumber()
            );

            ResponseDTO<BCICreditCardResponseDTO> response = bciHttpClient.updateCreditCard(
                bciUri,
                serviceToken,
                body
            );

            log.info(
                "BCI CreditCardUpdateTokenization response from {}: \n{}",
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
}
