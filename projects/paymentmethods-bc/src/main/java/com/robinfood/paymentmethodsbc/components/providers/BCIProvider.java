package com.robinfood.paymentmethodsbc.components.providers;

import com.robinfood.paymentmethodsbc.dto.CreditCardTokenDTO;
import com.robinfood.paymentmethodsbc.dto.PaymentResponseDTO;
import com.robinfood.paymentmethodsbc.dto.bci.transactions.BCICancelTransactionResponseDTO;
import com.robinfood.paymentmethodsbc.dto.bci.transactions.BCITransactionStatusResponseDTO;
import com.robinfood.paymentmethodsbc.dto.steps.PaymentPipeDTO;
import com.robinfood.paymentmethodsbc.dto.steps.SettingsDTO;
import com.robinfood.paymentmethodsbc.dto.steps.TransactionStatusPipeDTO;
import com.robinfood.paymentmethodsbc.exceptions.PaymentMethodsException;
import com.robinfood.paymentmethodsbc.model.CreditCard;
import java.util.Map;

/**
 * Interface que define servicios disponibles para tokenizar tarjetas
 * @author Hernan Ramirez
 */
public interface BCIProvider {
    /**
     * Servicio encargado de tokenizar tarjetas de crédito
     *
     * @param config Configuracion de gateway
     * @param orchConfig Configuración de orquestador de gateways (En caso de existir)
     * @param creditCardTokenDTO {@linkplain CreditCard} tarjeta de crédito a tokenizar
     * @return {@linkplain String} valor de tarjeta tokenizada
     * @throws PaymentMethodsException
     */
    String doCreditCardTokenization(
        Map<String, String> config,
        Map<String, String> orchConfig,
        CreditCardTokenDTO creditCardTokenDTO
    ) throws PaymentMethodsException;

    /**
     * Servicio encargado de eiminar tarjetas de crédito
     *
     * @param config Configuracion de gateway
     * @param orchConfig Configuración de orquestador de gateways (En caso de existir)
     * @param userId {@linkplain String} id de usuario
     * @param token {@linkplain String} token de la tarjeta
     * @throws PaymentMethodsException
     */
    void doCreditCardRemove(
        Map<String, String> config,
        Map<String, String> orchConfig,
        String userId,
        String token
    ) throws PaymentMethodsException;

    /**
     * Servicio encargado de eiminar tarjetas de crédito
     *
     * @param settingsDTO Configuraciones
     * @param transactionPipeDTO {@linkplain PaymentPipeDTO}
     * @throws PaymentMethodsException
     */
    PaymentResponseDTO doPayment(
        SettingsDTO settingsDTO,
        PaymentPipeDTO transactionPipeDTO
    ) throws PaymentMethodsException;

    /**
     * Servicio encargado de realizar el reembolso para una transacción a través de un BCI
     *
     * @param config Configuracion de gateway
     * @param orchConfig Configuración de orquestador de gateways (En caso de existir)
     * @param transactionReference Id secundario de transacción
     * @param transactionCode Id de transacción 
     * @return
     * @throws PaymentMethodsException
     */
    PaymentResponseDTO doRefund(
        Map<String, String> config,
        Map<String, String> orchConfig,
        String transactionReference,
        String transactionCode,
        String reason
    ) throws PaymentMethodsException;

    /**
     * Servicio encargado de validar notificaciones para cambios de estado de
     * transacciones
     * @param settings {@linkplain Map} Configuraciones de gateway, orquestador y terminal (En caso de existir)
     * @param transactionStatusPipe {@linkplain TransactionStatusPipeDTO}
     * @return
     */
    BCITransactionStatusResponseDTO doValidateStatus(
        SettingsDTO settings,
        TransactionStatusPipeDTO transactionStatusPipe
    ) throws PaymentMethodsException;

    /**
     * Servicio encargado de cancelar transacciones según referencia
     * @param settingsDTO {@linkplain SettingsDTO}
     * @param reference {@linkplain String}
     * @return {@linkplain BCICancelTransactionResponseDTO}
     * @throws PaymentMethodsException
     */
    BCICancelTransactionResponseDTO doCancelTransaction(
        SettingsDTO settingsDTO,
        String reference
    ) throws PaymentMethodsException;
}
