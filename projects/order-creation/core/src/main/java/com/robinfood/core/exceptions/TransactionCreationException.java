package com.robinfood.core.exceptions;

import com.robinfood.core.dtos.transactionexception.ResponseTransactionCreation;
import com.robinfood.core.enums.TransactionCreationErrors;
import com.robinfood.core.util.LogsUtil;
import com.robinfood.core.util.ObjectMapperSingleton;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

/**
 * Exception generated when transaction creation is not success
 */
@Slf4j
public class TransactionCreationException extends AbstractGenericAppException {

    /**
     * Constructor with HTTP status code, description and order creation error known
     *
     * @param status                   the HTTP status code value
     * @param description              the associated description
     * @param transactionCreationError the associated error
     */
    public TransactionCreationException(
            HttpStatus status,
            String description,
            TransactionCreationErrors transactionCreationError
    ) {
        super(description, status, transactionCreationError);
        LogsUtil.buildErrorLog(description, transactionCreationError, status.value());
    }

    /**
     * Constructor with HTTP status code and description. The error is unknown
     *
     * @param status      the HTTP status code value
     * @param description the associated description
     */
    public TransactionCreationException(HttpStatus status, String description) {
        super(description, status, TransactionCreationErrors.UNKNOWN_ERROR);
        LogsUtil.buildErrorLog(status.value(), description);
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
            String data,
            String description,
            TransactionCreationErrors transactionCreationError,
            HttpStatus status
    ) {
        super(data, description, transactionCreationError, status);

        if (!ObjectMapperSingleton.isValidJson(data)) {

            LogsUtil.buildErrorLog(
                    description,
                    transactionCreationError.getErrorCode(),
                    data,
                    transactionCreationError.name(),
                    status.value());
            return;
        }

        ResponseTransactionCreation responseTransactionCreation = ObjectMapperSingleton
                .jsonToClass(data, ResponseTransactionCreation.class);

        LogsUtil.buildErrorLog(
                ObjectMapperSingleton.objectToJson(responseTransactionCreation.getMessage()),
                transactionCreationError.getErrorCode(),
                ObjectMapperSingleton.objectToJson(responseTransactionCreation.getData()),
                ObjectMapperSingleton.objectToJson(responseTransactionCreation.getError()),
                transactionCreationError.name(),
                status.value());
    }


}
