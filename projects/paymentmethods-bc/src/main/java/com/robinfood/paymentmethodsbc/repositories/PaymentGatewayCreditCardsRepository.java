package com.robinfood.paymentmethodsbc.repositories;

import com.robinfood.paymentmethodsbc.model.PaymentGatewayCreditCard;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentGatewayCreditCardsRepository
    extends JpaRepository<PaymentGatewayCreditCard, Long> {
    /**
     * Permite realizar la busqueda de token de una tarjeta de crédito en un gateway de pagos específico
     * @param creditCardId
     * @param paymentGatewayId
     * @return Optional<PaymentGatewayCreditCard>
     */
    Optional<PaymentGatewayCreditCard> findByCreditCardIdAndPaymentGatewayId(
        Long creditCardId,
        Long paymentGatewayId
    );

    /**
     * Permite realizar la busqueda de token de una tarjeta de crédito en un gateway de pagos específico
     * @param creditCardId
     * @param paymentGatewayId
     * @return List<PaymentGatewayCreditCard>
     */
    List<PaymentGatewayCreditCard> findByCreditCardIdAndDeletedAt(
        Long creditCardId,
        LocalDateTime deletedAt
    );

    /**
     * Permite realizar la busqueda los token de multiples tarjetas de crédito en un gateway de pagos específico
     * @param creditCardIdList
     * @param paymentGatewayId
     * @return List<PaymentGatewayCreditCard>
     */
    @Query(
        "SELECT pgcc " +
        "FROM PaymentGatewayCreditCard pgcc " +
        "WHERE pgcc.paymentGateway.id = :paymentGatewayId " +
        "AND pgcc.creditCard.id IN (:creditCardIdList) AND pgcc.deletedAt IS NULL"
    )
    List<PaymentGatewayCreditCard> findByCreditCardIdAndPaymentGatewayIdAndDeletedAtIsNull(
        List<Long> creditCardIdList,
        Long paymentGatewayId
    );
}
