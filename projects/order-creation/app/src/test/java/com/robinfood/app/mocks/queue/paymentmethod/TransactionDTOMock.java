package com.robinfood.app.mocks.queue.paymentmethod;

import com.robinfood.core.dtos.queue.paymentmethod.EntityDTO;
import com.robinfood.core.dtos.queue.paymentmethod.PaymentDTO;
import com.robinfood.core.dtos.queue.paymentmethod.TransactionDTO;
import com.robinfood.core.dtos.queue.paymentmethod.TransactionStatusDTO;

import java.math.BigDecimal;

public class TransactionDTOMock {

    public static TransactionDTO build() {

        EntityDTO entity = new EntityDTO(1L, 3L, "refernce");
        PaymentDTO payment = new PaymentDTO(BigDecimal.valueOf(10000.0), BigDecimal.valueOf(500.0), BigDecimal.valueOf(10500.0));
        TransactionStatusDTO transactionStatus = new TransactionStatusDTO(
            1L,
            "2022-01-11T22:40:39.780379Z",
            "Accepted");

        return TransactionDTO.builder()
            .entity(entity)
            .payment(payment)
            .transactionId(3L)
            .transactionStatus(transactionStatus)
            .transactionUuid("891aeab4-5756-4214-ac6d-5b29bc549cac")
            .build();
    }

}
