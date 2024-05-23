package com.robinfood.app.mocks;

import com.robinfood.core.dtos.queue.paymentmethod.EntityDTO;
import com.robinfood.core.dtos.queue.paymentmethod.PaymentDTO;
import com.robinfood.core.dtos.queue.paymentmethod.TransactionDTO;
import com.robinfood.core.dtos.queue.paymentmethod.TransactionStatusDTO;

import java.math.BigDecimal;

public class TransactionResultPaymentMethodMock {

    public final TransactionDTO buildTransaction(){

        EntityDTO entity = new EntityDTO();
        entity.setId(1L);
        entity.setReference("4289b395-ef11-4c54-bdbf-a89672a84725");
        entity.setSourceId(5707451L);

        PaymentDTO payment = new PaymentDTO();
        payment.setSubtotal(new BigDecimal("10092.5926"));
        payment.setTax(new BigDecimal("807.4074"));
        payment.setTotal(new BigDecimal("10900"));

        TransactionStatusDTO transactionStatus = new TransactionStatusDTO();
        transactionStatus.setId(3L);
        transactionStatus.setName("Rejected");


        return TransactionDTO.builder()
                .entity(entity)
                .payment(payment)
                .transactionId(465206L)
                .transactionStatus(transactionStatus)
                .build();
    }
}