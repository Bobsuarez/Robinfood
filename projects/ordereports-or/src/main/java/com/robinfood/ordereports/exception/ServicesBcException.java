package com.robinfood.ordereports.exception;

import com.robinfood.app.library.enums.ordercreation.transaction.EOrderCreationErrors;
import com.robinfood.app.library.exception.app.NetworkConnectionException;
import com.robinfood.app.library.exception.business.TransactionExecutionException;
import com.robinfood.app.library.exception.template.IGenericMessage;

public class ServicesBcException extends TransactionExecutionException {

    public ServicesBcException(NetworkConnectionException networkConnectionException, IGenericMessage transactionCreationErrors) {
        super(networkConnectionException, transactionCreationErrors);
    }

    public ServicesBcException(IGenericMessage genericMessage, Integer codeHttps) {
        super(genericMessage, codeHttps);
    }

    public ServicesBcException(String message) {
        super(message);
    }

    public ServicesBcException(String message, Integer codeHttps) {
        super(message, codeHttps);
    }

    public ServicesBcException(String message, EOrderCreationErrors creationErrors, Integer codeHttps) {
        super(message, creationErrors, codeHttps);
    }

    public ServicesBcException(String message, IGenericMessage genericMessage, Integer codeHttps) {
        super(message, genericMessage, codeHttps);
    }
}
