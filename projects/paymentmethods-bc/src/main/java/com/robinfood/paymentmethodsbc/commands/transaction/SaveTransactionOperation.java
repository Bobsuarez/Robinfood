package com.robinfood.paymentmethodsbc.commands.transaction;

import com.robinfood.paymentmethodsbc.model.Transaction;
import com.robinfood.paymentmethodsbc.services.TransactionsCommonOperations;

public class SaveTransactionOperation implements SaveTransactionHandler {

    @Override
    public Transaction saveTransaction(
        TransactionsCommonOperations transactionsCommonOperations,
        Transaction transaction
    ) {
        return transactionsCommonOperations.saveTransaction(transaction);
    }
}
