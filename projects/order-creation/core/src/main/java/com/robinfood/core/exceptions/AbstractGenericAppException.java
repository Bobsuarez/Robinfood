package com.robinfood.core.exceptions;

import com.robinfood.core.enums.TransactionCreationErrors;
import org.springframework.http.HttpStatus;

/**
 * Base class for exceptions associated with order creation system
 */
public abstract class AbstractGenericAppException extends RuntimeException {

    public static final String NO_ERROR_DESCRIPTION = "No error description";
    private final transient Object data;

    private final String description;

    private final TransactionCreationErrors transactionCreationError;

    private final HttpStatus status;

    /**
     * Constructor with data, description, response status and order creation error
     * to add to the exception
     *
     * @param data                     the error data (optional)
     * @param description              the associated description (optional)
     * @param transactionCreationError the associated error
     * @param status                   the HTTP status code value
     */
    protected AbstractGenericAppException(
            Object data,
            String description,
            TransactionCreationErrors transactionCreationError,
            HttpStatus status
    ) {
        super(description);
        this.data = data;
        this.description = description;
        this.transactionCreationError = transactionCreationError;
        this.status = status;
    }

    /**
     * Constructor with description, response status and order creation error
     * to add to the exception
     *
     * @param description              the associated description (optional)
     * @param status                   the HTTP status code value
     * @param transactionCreationError the associated error
     */
    protected AbstractGenericAppException(
            String description,
            HttpStatus status,
            TransactionCreationErrors transactionCreationError
    ) {
        super(description);
        this.data = null;
        this.description = description;
        this.status = status;
        this.transactionCreationError = transactionCreationError;
    }

    /**
     * Constructor with response status and order creation error
     * to add to the exception
     *
     * @param status                   the HTTP status code value
     * @param transactionCreationError the associated error
     */
    protected AbstractGenericAppException(
            HttpStatus status,
            TransactionCreationErrors transactionCreationError
    ) {
        super(NO_ERROR_DESCRIPTION);
        this.data = null;
        this.description = NO_ERROR_DESCRIPTION;
        this.status = status;
        this.transactionCreationError = transactionCreationError;
    }

    public Object getData() {
        return this.data;
    }

    public String getDescription() {
        return this.description;
    }

    public TransactionCreationErrors getTransactionCreationError() {
        return this.transactionCreationError;
    }

    public HttpStatus getStatus() {
        return this.status;
    }
}
