package com.robinfood.paymentmethodsbc.services;

import com.robinfood.paymentmethodsbc.dto.api.transactions.PaymentRequestDTO;
import com.robinfood.paymentmethodsbc.dto.api.transactions.PaymentInitResponseDTO;
import com.robinfood.paymentmethodsbc.dto.api.transactions.RefundRequestDTO;
import com.robinfood.paymentmethodsbc.dto.api.transactions.RefundResponseDTO;
import com.robinfood.paymentmethodsbc.dto.api.transactions.TransactionEntityDTO;
import com.robinfood.paymentmethodsbc.dto.api.transactions.TransactionStatusUpdateRequestDTO;
import com.robinfood.paymentmethodsbc.dto.jms.JmsEntityRefundRequestDTO;
import com.robinfood.paymentmethodsbc.dto.jms.JmsUpdateTransactionStatusDTO;
import com.robinfood.paymentmethodsbc.exceptions.BaseException;
import com.robinfood.paymentmethodsbc.exceptions.EntityNotFoundException;
import com.robinfood.paymentmethodsbc.exceptions.EntityNotFoundRuntimeException;
import com.robinfood.paymentmethodsbc.exceptions.PaymentStepException;

import java.util.List;

/**
 * Interface que permite generar transacciones
 *
 * @author Hernán A. Ramírez O.
 */
public interface TransactionService {
    /**
     * Metodo que permite crear una transacción inicial (en base de datos)
     *
     * @param transactionDTO {@linkplain PaymentRequestDTO} datos de transacción a realizar
     * @return PaymentResponseDTO
     *
     * @throws EntityNotFoundException error si no existe se encontró algun registro en BD
     * @throws BaseException           error genérico
     * @throws PaymentStepException 
     */
    PaymentInitResponseDTO createPaymentInitialTransaction(
        PaymentRequestDTO transactionDTO
    ) throws EntityNotFoundException, EntityNotFoundRuntimeException, BaseException, PaymentStepException;

    /**
     * Metodo que permite generar una transacción
     *
     * @param transactionDTO {@linkplain PaymentRequestDTO} datos de transacción a realizar
     * @throws EntityNotFoundException error si no existe se encontró algun registro en BD
     * @throws BaseException           error genérico
     * @throws PaymentStepException 
     */
    void doPayment(PaymentRequestDTO transactionDTO)
        throws EntityNotFoundException, BaseException, PaymentStepException;

    /**
     * Metodo que permite realizar el reembolso de una transacción
     *
     * @param refundRequestDTO {@linkplain RefundRequestDTO} datos de transacción
     * @return {@linkplain String}
     *
     * @throws EntityNotFoundException error si no existe se encontró algun registro en BD
     * @throws BaseException           error genérico
     * @throws PaymentStepException 
     */
    RefundResponseDTO doRefund(RefundRequestDTO refundRequestDTO)
        throws EntityNotFoundException, PaymentStepException;

    /**
     * Metodo que permite actualizar el estado de una transacción
     *
     * @param transactionStatusUpdateDTO {@linkplain TransactionStatusUpdateRequestDTO} datos de transacción
     * @return {@linkplain String}
     *
     * @throws EntityNotFoundException error si no existe se encontró algun registro en BD
     * @throws PaymentStepException 
     */
    String updateTransactionStatus(TransactionStatusUpdateRequestDTO transactionStatusUpdateDTO)
        throws EntityNotFoundException, PaymentStepException;

    /**
     * Metodo que permite actualizar el estado de una transacción desde consumer activemq
     *
     * @param jmsUpdateTransactionStatusDTO {@linkplain JmsUpdateTransactionStatusDTO} datos de transacción
     * @throws EntityNotFoundException error si no existe se encontró algun registro en BD
     * @throws BaseException           error genérico
     * @throws PaymentStepException 
     */
    void updateTransactionStatusJMS(
        JmsUpdateTransactionStatusDTO jmsUpdateTransactionStatusDTO,
        String rawMessage
    )
        throws EntityNotFoundException, PaymentStepException;


    /**
     * Lista transacciones por entidad
     *
     * @param entitySourceId  id de la entidad
     * @param entityReference referencia de la entidad
     * @return Listado de transacciones por entidad
     */
    List<TransactionEntityDTO> getTransactionsByEntityInfo(Long entitySourceId, String entityReference);

    /**
     * Metodo que permite solicitar el reembolso de las transacciones asociadas a una misma entidad (ordenes, etc)
     *
     * @param jmsEntityRefundRequestDTO {@linkplain JmsEntityRefundRequestDTO} datos de la entidad
     * @author Edwin Artunduaga
     */
    void entityRefund(JmsEntityRefundRequestDTO jmsEntityRefundRequestDTO);

    /**
     * List Transactions by entity , reference or uuid
     *
     * @param entitySourceId  id de la entidad
     * @param entityReference referencia de la entidad
     * @param uuid referencia de la entidad
     * @return Listado de transacciones por entidad
     */
    List<TransactionEntityDTO> getTransactionsByEntityInfo(Long entitySourceId, String entityReference, String uuid);

}
