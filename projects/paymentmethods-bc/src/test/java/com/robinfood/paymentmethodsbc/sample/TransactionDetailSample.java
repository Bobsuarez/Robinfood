package com.robinfood.paymentmethodsbc.sample;

import com.robinfood.paymentmethodsbc.model.Transaction;
import com.robinfood.paymentmethodsbc.model.TransactionDetail;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

/**
 * Obtiene objetos de prueba para test unitarios
 */
public class TransactionDetailSample {

    public static TransactionDetail getTransactionDetail() {
        return TransactionDetail
            .builder()
            .transactionId(1L)
            .transactionReference("Reference")
            .transactionCode("Transaction Code")
            .transactionType("Type")
            .terminal(TerminalSample.getTerminal())
            .transaction(TransactionSamples.getTransaction())
            .build();
    }

    public static TransactionDetail getTransactionDetailComplite() {
        return TransactionDetail
            .builder()
            .transactionId(1L)
            .transactionReference("Reference")
            .transactionCode("Transaction Code")
            .transactionType("Type")
            .terminal(TerminalSample.getTerminal())
            .transaction(Transaction
                             .builder()
                             .id(1L)
                             .uuid("123")
                             .createdAt(LocalDateTime.of(2022,
                                                         Month.MAY,
                                                         25,
                                                         11,
                                                         39,
                                                         40
                             ))
                             .succeeded(true)
                             .authorizationCode("123")
                             .transactionStatus(
                                 TransactionStatusSample.getTransactionStatus()
                             )
                             .entity(
                                 EntitySample.getEntity()
                             )
                             .total( new BigDecimal("500"))
                             .subtotal(new BigDecimal("500"))
                             .tax(new BigDecimal("50"))
                             .paymentMethod(GeneralPaymentMethodSample.getGeneralPaymentMethod())
                             .build())
            .build();
    }

    public static List<TransactionDetail> getListTransactionDetails(){
        List<TransactionDetail> listTransactionDetails = new ArrayList<>();

         listTransactionDetails.add(getTransactionDetailComplite());
        return listTransactionDetails;

    }
}
