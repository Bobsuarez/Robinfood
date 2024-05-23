package com.robinfood.core.util;

import com.robinfood.core.enums.TransactionCreationErrors;
import lombok.extern.slf4j.Slf4j;

import static com.robinfood.core.constants.GlobalConstants.NO_APPLIED;
import static com.robinfood.core.enums.logs.OrderErrorLogEnum.ERROR_TRANSACTION_CREATION_EXCEPTION;

@Slf4j
public final class LogsUtil {

    private LogsUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static void buildErrorLog(
            Integer statusCode,
            String description
    ) {
        buildErrorLog(
                description,
                TransactionCreationErrors.UNKNOWN_ERROR.getErrorCode(),
                NO_APPLIED,
                NO_APPLIED,
                TransactionCreationErrors.UNKNOWN_ERROR.name(),
                statusCode
        );
    }

    public static void buildErrorLog(
            String description,
            TransactionCreationErrors transactionCreationError,
            Integer statusCode
    ) {
        buildErrorLog(
                description,
                transactionCreationError.getErrorCode(),
                NO_APPLIED,
                NO_APPLIED,
                transactionCreationError.name(),
                statusCode
        );
    }

    public static void buildErrorLog(
            String cause,
            Integer code,
            Object data,
            String errorName,
            Integer statusCode
    ) {
        buildErrorLog(cause, code, data, NO_APPLIED, errorName, statusCode);
    }

    public static void buildErrorLog(
            String cause,
            Integer code,
            Object data,
            String errorMessage,
            String errorName,
            Integer statusCode
    ) {
        log.error(ERROR_TRANSACTION_CREATION_EXCEPTION.getMessage(),
                code,
                errorName,
                ObjectMapperSingleton.objectToJson(errorMessage),
                statusCode,
                ObjectMapperSingleton.objectToJson(cause),
                ObjectMapperSingleton.objectToJson(data)
        );
    }
}
