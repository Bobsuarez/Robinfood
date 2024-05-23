package com.robinfood.paymentmethodsbc.commands.transaction;

import com.robinfood.paymentmethodsbc.model.Transaction;
import com.robinfood.paymentmethodsbc.services.TransactionsCommonOperations;

import java.util.Optional;

public class SaveTransactionDefault implements SaveTransactionHandler {

    @Override
    public Transaction saveTransaction(
        TransactionsCommonOperations transactionsCommonOperations,
        Transaction transaction
    ) {
        return Optional.ofNullable(transaction).orElse(Transaction.builder().build());
    }
}
