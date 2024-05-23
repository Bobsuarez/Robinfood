package com.robinfood.changestatusbc.restexceptionhandlers;

import com.robinfood.changestatusbc.dtos.ApiResponseDTO;
import com.robinfood.changestatusbc.exceptions.GenericChangeStatusBcException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestValueException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.constraints.NotNull;
import java.util.HashMap;

@ControllerAdvice
@Slf4j
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @NotNull
    protected ResponseEntity<Object> handleMissingServletRequestParameter(
            @NotNull MissingServletRequestParameterException exception,
            @NotNull HttpStatus status
    ) {

        return new ResponseEntity<>(
                ApiResponseDTO.<Object>builder()
                        .code(status.value())
                        .message(exception.getMessage())
                        .error(Boolean.FALSE)
                        .build()
                , status
        );
    }

    @ExceptionHandler(GenericChangeStatusBcException.class)
    protected ResponseEntity<Object> genericException(
            GenericChangeStatusBcException exception,
            @NotNull HttpStatus status
    ) {

        return new ResponseEntity<>(
                ApiResponseDTO.<Object>builder()
                        .code(status.value())
                        .message(exception.getMessage())
                        .error(Boolean.FALSE)
                        .build()
                , status
        );
    }

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException exception,
            @NotNull HttpHeaders headers,
            @NotNull HttpStatus status,
            @NotNull WebRequest request
    ) {

        HashMap<String, String> errors = new HashMap<>();

        exception.getBindingResult().getFieldErrors().forEach((FieldError error) ->
                errors.put(error.getField(), error.getDefaultMessage())
        );

        return new ResponseEntity<>(
                ApiResponseDTO.<Object>builder()
                        .code(status.value())
                        .message(errors.toString())
                        .error(Boolean.FALSE)
                        .build()
                , status
        );
    }

    @ExceptionHandler(DataAccessException.class)
    protected ResponseEntity<Object> handleDataAccessException(
            DataAccessException exception,
            @NotNull HttpStatus status
    ) {

        return new ResponseEntity<>(
                ApiResponseDTO.<Object>builder()
                        .code(status.value())
                        .message(exception.getMessage())
                        .error(Boolean.FALSE)
                        .build()
                , status
        );
    }

    @NotNull
    @ExceptionHandler(Throwable.class)
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            @NotNull HttpMessageNotReadableException exception,
            @NotNull HttpStatus status
    ) {

        return new ResponseEntity<>(
                ApiResponseDTO.<Object>builder()
                        .code(status.value())
                        .message(exception.getMessage())
                        .error(Boolean.FALSE)
                        .build()
                , status
        );
    }

    @ExceptionHandler(MissingRequestValueException.class)
    protected ResponseEntity<Object> missingRequestValueException(
            MissingRequestValueException exception
    ) {

        return new ResponseEntity<>(
                ApiResponseDTO.<Object>builder()
                        .code(HttpStatus.BAD_REQUEST.value())
                        .message(exception.getMessage())
                        .error(Boolean.FALSE)
                        .build()
                , HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<Object> methodArgumentTypeMismatchException(
            MissingRequestValueException exception
    ) {

        return new ResponseEntity<>(
                ApiResponseDTO.<Object>builder()
                        .code(HttpStatus.BAD_REQUEST.value())
                        .message(exception.getMessage())
                        .error(Boolean.FALSE)
                        .build()
                , HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(ResponseStatusException.class)
    protected ResponseEntity<Object> responseStatusException(
            ResponseStatusException exception
    ) {

        return new ResponseEntity<>(
                ApiResponseDTO.<Object>builder()
                        .code(exception.getStatus().value())
                        .message(exception.getReason())
                        .error(Boolean.FALSE)
                        .build()
                , exception.getStatus()
        );
    }
}
