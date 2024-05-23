package com.robinfood.paymentmethodsbc.repositories;

import com.robinfood.paymentmethodsbc.model.TransactionDetail;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface que permite habilitar el acceso a la capa de datos a la entidad
 * TransactionDetails, utilizando como interface JpaRepository
 *
 * @author Hernán A. Ramírez O.
 */
public interface TransactionDetailsRepository extends JpaRepository<TransactionDetail, Long> {

    /**
     * Permite realizar la búsqueda del detalle de transacción según el transaction_id
     *
     * @param transactionId id de transacción
     * @return {@linkplain Optional<TransactionDetail>}
     */
    Optional<TransactionDetail> findByTransactionId(Long transactionId);

    /**
     * Permite realiza la búsqueda del detalle de transacción según transaction_reference
     *
     * @param transactionReference {@linkplain String} referencia de transacción
     * @return {@linkplain Optional<TransactionDetail>}
     */
    Optional<TransactionDetail> findFirstByTransactionReference(String transactionReference);

    /**
     * Permite realiza la búsqueda del detalle de transacción según transaction_code
     *
     * @param transactionCode {@linkplain String} referencia de transacción
     * @return {@linkplain Optional<TransactionDetail>}
     */
    Optional<TransactionDetail> findFirstByTransactionCode(String transactionCode);

    /**
     * Permite realizar la búsqueda del detalle de transacciones según estado
     * y codigo de datafono
     *
     * @param transactionStatusId {@linkplain Long} estado de transaccion
     * @param terminalId          {@linkplain String} terminal que gestiona el pago
     * @return {@linkplain List<TransactionDetail>}
     */
    List<TransactionDetail> findByTransactionTransactionStatusIdAndTerminalIdAndTransactionCodeIsNotNull(
        Long transactionStatusId,
        Long terminalId
    );

}

