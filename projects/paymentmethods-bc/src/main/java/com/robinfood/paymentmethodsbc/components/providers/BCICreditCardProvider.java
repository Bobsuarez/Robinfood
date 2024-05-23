package com.robinfood.paymentmethodsbc.components.providers;

import java.util.Map;
import com.robinfood.paymentmethodsbc.dto.CreditCardTokenDTO;
import com.robinfood.paymentmethodsbc.exceptions.PaymentMethodsException;

public interface BCICreditCardProvider {
    
    /**
     * Service in charge of tokenizing credit cards
     *
     * @param config Gateway configuration
     * @param orchConfig Gateway orchestrator configuration (if any)
     * @param creditCardTokenDTO {@linkplain CreditCard} credit card to tokenize
     * @return {@linkplain String} tokenized card value
     * @throws PaymentMethodsException
     */
    String createToken(
        Map<String, String> config,
        Map<String, String> orchConfig,
        CreditCardTokenDTO creditCardTokenDTO
    ) throws PaymentMethodsException;

    /**
     * Service in charge of eliminating credit cards
     *
     * @param config Gateway configuration
     * @param orchConfig Gateway orchestrator configuration (if any)
     * @param userId {@linkplain String} user id
     * @param token {@linkplain String} card token
     * @throws PaymentMethodsException
     */
    void removeToken(
        Map<String, String> config,
        Map<String, String> orchConfig,
        String userId,
        String token
    ) throws PaymentMethodsException;

    /**
     * Service responsible for updating credit cards
     *
     * @param config config of gateway
     * @param orchConfig config of orchestrator of gateways (if exist)
     * @param creditCardTokenDTO {@linkplain CreditCard} credit card to tokenize
     * @param token credit card token
     * @return {@linkplain String} tokenized card value
     * @throws PaymentMethodsException
     */
    String updateToken(
        Map<String, String> config,
        Map<String, String> orchConfig,
        CreditCardTokenDTO creditCardTokenDTO,
        String token
    ) throws PaymentMethodsException;
}
