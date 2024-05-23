package com.robinfood.app.restexceptionhandlers;

import com.robinfood.app.commandhandler.IInvokerCommand;
import com.robinfood.core.dtos.apiresponsebuilder.AbstractBuilderApiResponseDTO;
import com.robinfood.core.dtos.apiresponsebuilder.BadRequestApiResponseDTO;
import com.robinfood.core.dtos.apiresponsebuilder.DataResponseDTO;
import com.robinfood.core.dtos.apiresponsebuilder.ErrorDataResponseDTO;
import com.robinfood.core.dtos.apiresponsebuilder.PreconditionFailedApiResponseDTO;
import com.robinfood.core.dtos.apiresponsebuilder.TransactionCreationFailedApiResponseDTO;
import com.robinfood.core.enums.TransactionCreationErrors;
import com.robinfood.core.exceptions.TransactionCreationException;
import com.robinfood.core.exceptions.WriteInTransactionException;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.lang.NonNull;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@Slf4j
public class RestExceptionHandler {

    private final IInvokerCommand invokerCommand;

    public RestExceptionHandler(IInvokerCommand invokerCommand) {
        this.invokerCommand = invokerCommand;
    }

    @ExceptionHandler(TransactionCreationException.class)
    public ResponseEntity<Object> handleGenericException(
            @NotNull TransactionCreationException ex
    ) {
        final AbstractBuilderApiResponseDTO<ErrorDataResponseDTO<Object>> apiResponseDTOBuilder =
                new TransactionCreationFailedApiResponseDTO<>(
                        ex.getStatus().value(),
                        ex.getStatus()
                );

        final ErrorDataResponseDTO<Object> data = new ErrorDataResponseDTO<>(
                ex.getTransactionCreationError().getErrorCode(),
                ex.getData(),
                ex.getDescription(),
                ex.getTransactionCreationError()
        );

        apiResponseDTOBuilder.build(data);

        invokerCommand.rollback();
        MDC.clear();

        return new ResponseEntity<>(apiResponseDTOBuilder.getApiResponseDTO(), ex.getStatus());
    }

    @ExceptionHandler(WriteInTransactionException.class)
    public ResponseEntity<Object> handleGenericException(
            @NotNull WriteInTransactionException ex
    ) {
        final AbstractBuilderApiResponseDTO<ErrorDataResponseDTO<Object>> apiResponseDTOBuilder =
                new TransactionCreationFailedApiResponseDTO<>(
                        ex.getStatus().value(),
                        ex.getStatus()
                );

        apiResponseDTOBuilder.build(ex.getMessage());

        invokerCommand.rollback();
        MDC.clear();

        return new ResponseEntity<>(apiResponseDTOBuilder.getApiResponseDTO(), ex.getStatus());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @NonNull
    public ResponseEntity<Object> handleHttpMessageNotReadable(
            @NotNull HttpMessageNotReadableException ex
    ) {
        final AbstractBuilderApiResponseDTO<ErrorDataResponseDTO<Object>> apiResponseDTOBuilder =
                new PreconditionFailedApiResponseDTO<>();

        final ErrorDataResponseDTO<Object> data = new ErrorDataResponseDTO<>(
                TransactionCreationErrors.HTTP_MESSAGE_NOT_READABLE.getErrorCode(),
                null,
                ex.getMostSpecificCause().getMessage(),
                TransactionCreationErrors.HTTP_MESSAGE_NOT_READABLE
        );

        apiResponseDTOBuilder.build(data);
        MDC.clear();

        return new ResponseEntity<>(apiResponseDTOBuilder.getApiResponseDTO(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @NotNull
    public ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex
    ) {
        AbstractBuilderApiResponseDTO<ErrorDataResponseDTO<Object>> apiResponseDTOBuilder;

        apiResponseDTOBuilder = new BadRequestApiResponseDTO<>();

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach((FieldError error) -> {

            String fieldName = error.getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        final ErrorDataResponseDTO<Object> data = new ErrorDataResponseDTO<>(
                TransactionCreationErrors.METHOD_ARGUMENT_NOT_VALID.getErrorCode(),
                null,
                errors.toString(),
                TransactionCreationErrors.METHOD_ARGUMENT_NOT_VALID
        );

        apiResponseDTOBuilder.build(data);
        MDC.clear();

        return new ResponseEntity<>(apiResponseDTOBuilder.getApiResponseDTO(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResponseStatusException.class)
    @NonNull
    protected ResponseEntity<Object> handleResponseStatusException(
            @NotNull Throwable ex,
            @NotNull HttpStatus httpStatus
    ) {

        final AbstractBuilderApiResponseDTO<DataResponseDTO<Object>> apiResponseDTOBuilder =
                new PreconditionFailedApiResponseDTO<>();

        final DataResponseDTO<Object> data = new DataResponseDTO<>(ex.getMessage());
        apiResponseDTOBuilder.build(data);
        MDC.clear();

        return new ResponseEntity<>(apiResponseDTOBuilder.getApiResponseDTO(), httpStatus);
    }
}
