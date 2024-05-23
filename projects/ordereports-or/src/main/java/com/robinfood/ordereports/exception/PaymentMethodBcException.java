package com.robinfood.ordereports.exception;

import com.robinfood.app.library.enums.ordercreation.transaction.EOrderCreationErrors;
import com.robinfood.app.library.exception.app.NetworkConnectionException;
import com.robinfood.app.library.exception.business.TransactionExecutionException;
import com.robinfood.app.library.exception.template.IGenericMessage;

public class PaymentMethodBcException extends TransactionExecutionException {

    public PaymentMethodBcException(NetworkConnectionException networkConnectionException, IGenericMessage transactionCreationErrors) {
        super(networkConnectionException, transactionCreationErrors);
    }

    public PaymentMethodBcException(IGenericMessage genericMessage, Integer codeHttps) {
        super(genericMessage, codeHttps);
    }

    public PaymentMethodBcException(String message) {
        super(message);
    }

    public PaymentMethodBcException(String message, Integer codeHttps) {
        super(message, codeHttps);
    }

    public PaymentMethodBcException(String message, EOrderCreationErrors creationErrors, Integer codeHttps) {
        super(message, creationErrors, codeHttps);
    }

    public PaymentMethodBcException(String message, IGenericMessage genericMessage, Integer codeHttps) {
        super(message, genericMessage, codeHttps);
    }
}
