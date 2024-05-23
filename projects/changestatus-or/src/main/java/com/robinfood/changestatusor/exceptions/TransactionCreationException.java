package com.robinfood.changestatusor.exceptions;

import com.robinfood.changestatusor.enums.TransactionCreationErrors;
import org.springframework.http.HttpStatus;

/**
 * Exception generated when transaction creation is not success
 */
public class TransactionCreationException extends AbstractGenericAppException {

    /**
     * Constructor with HTTP status code, description and order creation error known
     *
     * @param status                   the HTTP status code value
     * @param description              the associated description
     * @param transactionCreationError the associated error
     */
    public TransactionCreationException(HttpStatus status, String description,
                                        TransactionCreationErrors transactionCreationError) {
        super(description, status, transactionCreationError);
    }

    /**
     * Constructor with HTTP status code and description. The error is unknown
     *
     * @param status      the HTTP status code value
     * @param description the associated description
     */
    public TransactionCreationException(HttpStatus status, String description) {
        super(description, status, TransactionCreationErrors.UNKNOWN_ERROR);
    }

    /**
     * Constructor with data, description, error code and status code.
     *
     * @param data                     the error data (optional)
     * @param description              the associated description (optional)
     * @param transactionCreationError the associated error
     * @param status                   the HTTP status code value
     */
    public TransactionCreationException(
            Object data, String description,
                                        TransactionCreationErrors transactionCreationError,
                                        HttpStatus status
    ) {
        super(data, description, transactionCreationError, status);
    }
}
