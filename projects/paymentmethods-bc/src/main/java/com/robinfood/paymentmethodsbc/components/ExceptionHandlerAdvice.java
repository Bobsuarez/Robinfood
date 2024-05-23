package com.robinfood.paymentmethodsbc.components;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.robinfood.paymentmethodsbc.dto.common.response.ResponseDTO;
import com.robinfood.paymentmethodsbc.enums.ResponseCode;
import com.robinfood.paymentmethodsbc.exceptions.BaseException;
import com.robinfood.paymentmethodsbc.exceptions.EntityNotFoundException;
import com.robinfood.paymentmethodsbc.exceptions.EntityNotFoundRuntimeException;
import com.robinfood.paymentmethodsbc.exceptions.ErrorValidationException;
import com.robinfood.paymentmethodsbc.exceptions.PaymentStepException;
import com.robinfood.paymentmethodsbc.exceptions.ResourceNotFoundException;
import com.robinfood.paymentmethodsbc.utils.ResponseUtils;
import com.robinfood.paymentmethodsbc.utils.TransactionLogger;
import io.jsonwebtoken.MalformedJwtException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletionException;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import static com.robinfood.paymentmethodsbc.constants.MessageConstants.ENTITY_NOT_FOUND;
import static com.robinfood.paymentmethodsbc.constants.MessageConstants.ENTITY_NOT_FOUND_MESSAGE_FORMAT;
import static com.robinfood.paymentmethodsbc.constants.MessageConstants.VALIDATE_MESSAGE_ERROR;
import static com.robinfood.paymentmethodsbc.constants.MessageConstants.VALIDATE_MESSAGE_FORMAT;
import static com.robinfood.paymentmethodsbc.utils.ResponseUtils.getErrorDTOMap;

@ControllerAdvice
@RestControllerAdvice
@ResponseBody
@Slf4j
public class ExceptionHandlerAdvice {

    /**
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Exception.class)
    public ResponseDTO<Object> handleException(Exception e) {
        log.error(e.getMessage(), e);
        TransactionLogger.clear();
        return new ResponseDTO<>(
            ResponseCode.SERVICE_ERROR.getCode(),
            ResponseCode.SERVICE_ERROR.getMessage(),
            null,
            getErrorDTOMap(
                ResponseCode.SERVICE_ERROR.getMessage(),
                "EXCEPTION",
                e.getLocalizedMessage()
            )
        );
    }

    /**
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RuntimeException.class)
    public ResponseDTO<Object> handleRuntimeException(RuntimeException e) {
        log.error(e.getMessage(), e);
        TransactionLogger.clear();
        return new ResponseDTO<>(
            ResponseCode.SERVICE_ERROR.getCode(),
            ResponseCode.SERVICE_ERROR.getMessage(),
            null,
            getErrorDTOMap(
                ResponseCode.SERVICE_ERROR.getMessage(),
                "RUNTIME_EXCEPTION",
                e.getLocalizedMessage()
            )
        );
    }

    /**
     * @param e
     * @return ResponseResultDTO<Object>
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BaseException.class)
    public ResponseDTO<Object> handleBaseException(BaseException e) {
        log.error(e.getMessage(), e);
        TransactionLogger.clear();
        ResponseCode code = e.getCode();
        return new ResponseDTO<>(
            code.getCode(),
            e.getMessage(),
            null,
            getErrorDTOMap(
                "GENERAL_ERROR",
                String.valueOf(code.getCode()),
                e.getMessage()
            )
        );
    }

    /**
     * @param e
     * @return ResponseResultDTO<Object>
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseDTO<Object> handleEntityNotFoundException(
        EntityNotFoundException e
    ) {
        log.error(e.getMessage(), e);
        TransactionLogger.clear();
        return ResponseUtils.getExceptionResponseResultDTO(
            ResponseCode.ENTITY_NOT_FOUND, ENTITY_NOT_FOUND, e.getEntity(),
            String.format(ENTITY_NOT_FOUND_MESSAGE_FORMAT, e.getEntity(), e.getId())
        );
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(JsonProcessingException.class)
    public ResponseDTO<Object> handleJsonProcessingException(
        JsonProcessingException e
    ) {
        log.error(e.getMessage(), e);
        TransactionLogger.clear();
        return new ResponseDTO<>(
            ResponseCode.CLIENT_ERROR.getCode(),
            ResponseCode.CLIENT_ERROR.getMessage(),
            null,
            getErrorDTOMap(
                "JSON_PROCESSING",
                "json",
                ResponseCode.CLIENT_ERROR.getMessage()
            )
        );
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(EntityNotFoundRuntimeException.class)
    public ResponseDTO<Object> handleEntityNotFoundRuntimeException(
        EntityNotFoundRuntimeException e
    ) {
        log.error(e.getMessage(), e);
        TransactionLogger.clear();
        return ResponseUtils.getExceptionResponseResultDTO(
            ResponseCode.ENTITY_NOT_FOUND, ENTITY_NOT_FOUND, e.getEntity(),
            String.format(ENTITY_NOT_FOUND_MESSAGE_FORMAT, e.getEntity(), e.getId())
        );
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseDTO<Object> handleResourceNotFoundException(
        ResourceNotFoundException e
    ) {
        log.error(e.getMessage(), e);
        TransactionLogger.clear();
        return ResponseUtils.getExceptionResponseResultDTO(
            ResponseCode.RESOURCES_NOT_EXIST, ENTITY_NOT_FOUND, e.getResource(),
            String.format(ENTITY_NOT_FOUND_MESSAGE_FORMAT, e.getResource(), e.getId())
        );
    }


    /**
     * @param e
     * @return ResponseResultDTO<Object>
     */
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(ErrorValidationException.class)
    public ResponseDTO<Object> handleErrorValidationException(
        ErrorValidationException e
    ) {
        log.error(e.getMessage(), e);
        TransactionLogger.clear();
        return ResponseUtils.getExceptionResponseResultDTO(
            ResponseCode.VALIDATION_ERROR, VALIDATE_MESSAGE_ERROR, e.getType(),
            String.format(VALIDATE_MESSAGE_FORMAT, e.getType(), e.getMessage())
        );
    }

    /**
     * @param e
     * @return ResponseResultDTO<Object>
     */
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseDTO<Object> handleMissingServletRequestParameterException(
        MissingServletRequestParameterException e
    ) {
        log.error(e.getMessage(), e);
        TransactionLogger.clear();
        String message =
            "parámetro '" +
            e.getParameterName() +
            "' no se encuentra en la petición";
        return new ResponseDTO<>(
            ResponseCode.PARAMETER_NOT_FOUND.getCode(),
            ResponseCode.PARAMETER_NOT_FOUND.getMessage(),
            null,
            getErrorDTOMap(
                "PARAMETER_NOT_FOUND",
                e.getParameterName(),
                message
            )
        );
    }

    /**
     * @param e
     * @return ResponseResultDTO<Object>
     */
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseDTO<Object> handleAccessDeniedException(
        AccessDeniedException e
    ) {
        log.error(e.getMessage(), e);
        TransactionLogger.clear();
        return new ResponseDTO<>(
            ResponseCode.FORBIDEN.getCode(),
            ResponseCode.FORBIDEN.getMessage(),
            null,
            getErrorDTOMap(
                "ACCESS_DENIED",
                "middleware",
                ResponseCode.FORBIDEN.getMessage()
            )
        );
    }

    /**
     * @param e
     * @return ResponseResultDTO<Object>
     */
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(MalformedJwtException.class)
    public ResponseDTO<Object> handleMalformedJwtException(
        MalformedJwtException e
    ) {
        log.error(e.getMessage(), e);
        TransactionLogger.clear();
        return new ResponseDTO<>(
            ResponseCode.UNAUTHORIZED.getCode(),
            ResponseCode.UNAUTHORIZED.getMessage(),
            null,
            getErrorDTOMap(
                "MALFORMED_JWT",
                "jwt",
                ResponseCode.UNAUTHORIZED.getMessage()
            )
        );
    }

    /**
     * @param e
     * @return ResponseResultDTO<Object>
     */
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseDTO<Object> handleMethodArgumentTypeMismatchException(
        MethodArgumentTypeMismatchException e
    ) {
        log.error(e.getMessage(), e);
        String message = String.format(
            "el parámetro debe ser de tipo '%s' ",
            e.getRequiredType()
        );
        TransactionLogger.clear();
        return new ResponseDTO<>(
            ResponseCode.PARAMETER_TYPE_ERROR.getCode(),
            ResponseCode.PARAMETER_TYPE_ERROR.getMessage(),
            null,
            getErrorDTOMap(
                "PARAMETER_TYPE_ERROR",
                e.getName(),
                message
            )
        );
    }

    /**
     * Error de SQL
     *
     * @param e
     * @return ResponseResultDTO<Object>
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(
        org.springframework.dao.DataIntegrityViolationException.class
    )
    public ResponseDTO<Object> handleSQLException(
        org.springframework.dao.DataIntegrityViolationException e
    ) {
        log.error(e.getMessage(), e);
        String message = String.format(
            "Error Base de datos ['%s]",
            e.getRootCause().getLocalizedMessage()
        );
        TransactionLogger.clear();
        return new ResponseDTO<>(
            ResponseCode.DATABASE_ERROR.getCode(),
            ResponseCode.DATABASE_ERROR.getMessage(),
            null,
            getErrorDTOMap("DATABASE_ERROR_FOUND", "error", message)
        );
    }

    /**
     * @param e
     * @return ResponseResultDTO<Object>
     */
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseDTO<Object> handleValidationExceptions(
        MethodArgumentNotValidException e
    ) {
        log.error(e.getMessage(), e);
        Map<String, String> errors = new HashMap<>();
        e
            .getBindingResult()
            .getAllErrors()
            .forEach(
                (ObjectError error) -> {
                    FieldError field = (FieldError) error;
                    String fieldName = field.getField();
                    String errorMessage = error.getDefaultMessage();
                    errors.put(fieldName, errorMessage);
                }
            );
        TransactionLogger.clear();
        return new ResponseDTO<>(
            ResponseCode.PARAMETER_NOT_FOUND.getCode(),
            ResponseCode.PARAMETER_NOT_FOUND.getMessage(),
            null,
            getErrorDTOMap("ARGUMENT_NOTVALID", errors)
        );
    }

    /**
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(
        value = org.springframework.http.converter.HttpMessageNotReadableException.class
    )
    public ResponseDTO<Object> handleInvalidFormatException(
        org.springframework.http.converter.HttpMessageNotReadableException e
    ) {
        log.error(
            "handleInvalidFormatException= ",
            e.getMostSpecificCause().getCause()
        );
        TransactionLogger.clear();
        return new ResponseDTO<>(
            ResponseCode.PARAMETER_TYPE_ERROR.getCode(),
            ResponseCode.PARAMETER_TYPE_ERROR.getMessage(),
            null,
            getErrorDTOMap(
                "INVALID_FORMAT",
                "format",
                e.getLocalizedMessage()
            )
        );
    }

    /**
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public ResponseDTO<Object> handleHttpRequestMethodNotSupportedException(
        HttpRequestMethodNotSupportedException e
    ) {
        log.error(e.getMessage(), e);
        TransactionLogger.clear();
        return new ResponseDTO<>(
            ResponseCode.METHOD_NOT_EXIST.getCode(),
            ResponseCode.METHOD_NOT_EXIST.getMessage(),
            null,
            getErrorDTOMap(
                "METHOD_NOT_ALLOWED",
                "method",
                e.getLocalizedMessage()
            )
        );
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CompletionException.class)
    public ResponseDTO<Object> handlePaymentStepException(
        CompletionException e
    ) {
        String key = "COMPLETION_EXCEPTION";
        String message = e.getMessage();
        if(e.getCause() instanceof PaymentStepException){
            PaymentStepException paymentStepException = (PaymentStepException) e.getCause();
            key = paymentStepException.getStep().name();
            message = paymentStepException.getMessage();
        }
        log.error(e.getMessage(), e);
        TransactionLogger.clear();
        return new ResponseDTO<>(
            ResponseCode.CLIENT_ERROR.getCode(),
            ResponseCode.CLIENT_ERROR.getMessage(),
            null,
            getErrorDTOMap(
                "PAYMENT_STEP",
                key,
                message
            )
        );
    }
    
}
