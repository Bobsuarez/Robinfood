package com.robinfood.paymentmethodsbc.mappers;

import com.robinfood.paymentmethodsbc.dto.api.transactions.TransactionEntityDTO;
import com.robinfood.paymentmethodsbc.model.Transaction;

public final class TransactionEntityMapper {

    private TransactionEntityMapper() {
    }

    public static TransactionEntityDTO getTransactionEntityDTO(Transaction transaction) {
        return TransactionEntityDTO.builder()
            .uuid(transaction.getUuid())
            .id(transaction.getTransactionDetail().getTransactionId())
            .createdAt(transaction.getCreatedAt())
            .transactionReference(transaction.getTransactionDetail().getTransactionReference())
            .transactionCode(transaction.getTransactionDetail().getTransactionCode())
            .succeeded(transaction.isSucceeded())
            .authorizationCode(transaction.getAuthorizationCode())
            .status(TransactionEntityDTO.StatusDTO.builder()
                        .id(transaction.getTransactionStatus().getId())
                        .name(transaction.getTransactionStatus().getName())
                        .build())
            .entity(TransactionEntityDTO.EntityDTO.builder()
                        .id(transaction.getEntity().getId())
                        .sourceId(transaction.getEntitySource())
                        .reference(transaction.getTransactionDetail().getEntityReference())
                        .build())
            .payment(TransactionEntityDTO.PaymentDTO.builder()
                         .subtotal(transaction.getSubtotal())
                         .total(transaction.getTotal())
                         .tax(transaction.getTax())
                         .build())
            .paymentMethod(TransactionEntityDTO.PaymentMethodsDTO.builder()
                               .id(transaction.getPaymentMethod().getId())
                               .name(transaction.getPaymentMethod().getName())
                               .build())
            .build();
    }
}
