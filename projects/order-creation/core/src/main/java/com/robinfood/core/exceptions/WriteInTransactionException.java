package com.robinfood.core.exceptions;

import com.robinfood.core.enums.TransactionCreationErrors;
import org.springframework.http.HttpStatus;

public class WriteInTransactionException extends AbstractGenericAppException {

    /**
     * Constructor with HTTP status code, description and order creation error known
     *
     * @param status                   the HTTP status code value
     * @param description              the associated description
     * @param transactionCreationError the associated error
     */
    public WriteInTransactionException(HttpStatus status, String description,
            TransactionCreationErrors transactionCreationError) {
        super(description, status, transactionCreationError);
    }

    /**
     * Constructor with HTTP status code and description. The error is unknown
     *
     * @param status      the HTTP status code value
     * @param description the associated description
     */
    public WriteInTransactionException(HttpStatus status, String description) {
        super(description, status, TransactionCreationErrors.UNKNOWN_ERROR);
    }
}
