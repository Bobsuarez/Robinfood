package com.robinfood.taxes.components;

import com.robinfood.taxes.dto.v1.ApiResponseDTO;
import com.robinfood.taxes.exceptions.BusinessRuleException;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.persistence.EntityNotFoundException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@Slf4j
@ControllerAdvice
public class RestControllerExceptionHandler {

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    protected ResponseEntity<ApiResponseDTO<Object>> handleValidationExceptions(
        MethodArgumentNotValidException ex) {

        List<String> errors = ex.getBindingResult().getFieldErrors()
            .stream().map(error -> error.getField() + ": " + error.getDefaultMessage())
            .collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(ApiResponseDTO.builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .message(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .error(Collections.singletonList("Invalid type for parameter"))
                .data(errors)
                .build()
            );
    }

    @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<ApiResponseDTO<Object>> handleMethodArgumentTypeMismatchException(
        MethodArgumentTypeMismatchException ex) {
        log.error(ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(ApiResponseDTO.builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .message(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .error(Collections.singletonList(ex.getMessage()))
                .build());
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected ResponseEntity<ApiResponseDTO<Object>> handleException(Exception ex) {
        log.error(ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(ApiResponseDTO.builder()
                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .error(Collections.singletonList(ex.getMessage()))
                .build());
    }

    @ExceptionHandler(value = IOException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected ResponseEntity<ApiResponseDTO<Object>> handleIOException(IOException ex) {
        log.error(ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(ApiResponseDTO.builder()
                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .error(Collections.singletonList(ex.getMessage()))
                .build());
    }

    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<ApiResponseDTO<Object>> handleMissingParameterException(
        MissingServletRequestParameterException ex) {
        log.error(ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(ApiResponseDTO.builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .message(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .error(Collections.singletonList(ex.getMessage()))
                .build());
    }

    @ExceptionHandler(value = BusinessRuleException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<ApiResponseDTO<Object>> handleBusinessRuleException(
        BusinessRuleException ex) {
        log.error(ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(ApiResponseDTO.builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .message(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .error(Collections.singletonList(ex.getMessage()))
                .build());
    }

    @ExceptionHandler(value = {EntityNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected ResponseEntity<ApiResponseDTO<Object>> handleNotFound(EntityNotFoundException ex) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(ApiResponseDTO.builder()
                .code(HttpStatus.NOT_FOUND.value())
                .message(HttpStatus.NOT_FOUND.getReasonPhrase())
                .error(Collections.singletonList(ex.getMessage()))
                .build()
            );
    }

    @ExceptionHandler(value = {HttpMessageNotReadableException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<ApiResponseDTO<Object>> handleNotReadable(
        HttpMessageNotReadableException ex) {

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(ApiResponseDTO.builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .message(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .error(Collections.singletonList(ex.getMessage()))
                .build()
            );
    }

    @ExceptionHandler(value = {AccessDeniedException.class})
    @ResponseStatus(HttpStatus.FORBIDDEN)
    protected ResponseEntity<ApiResponseDTO<Object>> handlerForbidden(AccessDeniedException ex) {

        return ResponseEntity.status(HttpStatus.FORBIDDEN)
            .body(ApiResponseDTO.builder()
                .code(HttpStatus.FORBIDDEN.value())
                .message(HttpStatus.FORBIDDEN.getReasonPhrase())
                .error(Collections.singletonList(ex.getMessage()))
                .build()
            );
    }

}
