package com.robinfood.paymentmethodsbc.sample;

import com.robinfood.paymentmethodsbc.dto.jms.JmsTransactionDetailDTO;
import com.robinfood.paymentmethodsbc.dto.jms.JmsTransactionStatusChangeDTO;
import java.math.BigDecimal;

/**
 * Obtiene objetos de prueba para test unitarios
 */
public class JmsTransactionStatusChangeDTOSample {

    public static JmsTransactionStatusChangeDTO getJmsTransactionStatusChangeDTO() {
        final JmsTransactionStatusChangeDTO.EntityTransactionStatusChangeDTO entity = JmsTransactionStatusChangeDTO
            .EntityTransactionStatusChangeDTO.builder()
            .id(1L)
            .sourceId(1L)
            .reference("entityReference")
            .build();

        final JmsTransactionStatusChangeDTO.TransactionStatusChangeDTO status = JmsTransactionStatusChangeDTO
            .TransactionStatusChangeDTO.builder()
            .id(1L)
            .name("Accepted")
            .build();

        final JmsTransactionStatusChangeDTO.PaymentDTO payment = JmsTransactionStatusChangeDTO
            .PaymentDTO.builder()
            .subtotal(BigDecimal.TEN)
            .tax(BigDecimal.ZERO)
            .total(BigDecimal.TEN)
            .build();

        final JmsTransactionStatusChangeDTO.PaymentGatewayDTO paymentGateway = JmsTransactionStatusChangeDTO
            .PaymentGatewayDTO.builder()
            .id(1L)
            .name("PayU")
            .build();

        return JmsTransactionStatusChangeDTO
            .builder()
            .transactionId(1L)
            .transactionStatus(status)
            .entity(entity)
            .payment(payment)
            .paymentGateway(paymentGateway)
            .build();
    }

    public static JmsTransactionDetailDTO getJmsTransactionDetailDTO() {
        return JmsTransactionDetailDTO
            .builder()
            .status(TransactionSamples.getTransactionStatus().getId())
            .paymentGatewayId(
                TransactionSamples.getTransaction().getPaymentGateway().getId()
            )
            .countryId(TransactionSamples.getTransaction().getCountry().getId())
            .entityId(TransactionSamples.getTransaction().getEntity().getId())
            .build();
    }
}
