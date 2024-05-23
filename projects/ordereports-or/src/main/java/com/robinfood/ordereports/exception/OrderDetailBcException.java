package com.robinfood.ordereports.exception;

import com.robinfood.app.library.enums.ordercreation.transaction.EOrderCreationErrors;
import com.robinfood.app.library.exception.app.NetworkConnectionException;
import com.robinfood.app.library.exception.business.TransactionExecutionException;
import com.robinfood.app.library.exception.template.IGenericMessage;

public class OrderDetailBcException extends TransactionExecutionException {

    public OrderDetailBcException(NetworkConnectionException networkConnectionException, IGenericMessage transactionCreationErrors) {
        super(networkConnectionException, transactionCreationErrors);
    }
}
