package com.robinfood.paymentmethodsbc.commands.transaction;

import com.robinfood.paymentmethodsbc.model.Transaction;
import com.robinfood.paymentmethodsbc.services.TransactionsCommonOperations;

public interface SaveTransactionHandler {

    Transaction saveTransaction(
        TransactionsCommonOperations transactionsCommonOperations,
        Transaction transaction
    );
}
