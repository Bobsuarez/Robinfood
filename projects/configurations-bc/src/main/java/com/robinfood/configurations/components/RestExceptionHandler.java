package com.robinfood.configurations.components;

import com.robinfood.configurations.dto.v1.ApiResponseDTO;
import com.robinfood.configurations.exceptions.BusinessRuleException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NonUniqueResultException;
import javax.servlet.ServletException;
import javax.validation.ConstraintDeclarationException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

@Slf4j
@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(value = {AccessDeniedException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<ApiResponseDTO<Object>> handleUnauthorized(AccessDeniedException ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
            .body(ApiResponseDTO.builder()
                .code(HttpStatus.FORBIDDEN.value())
                .message(HttpStatus.FORBIDDEN.getReasonPhrase())
                .error(Collections.singletonList(ex.getMessage()))
                .build()
            );
    }

    @ExceptionHandler(value = {IOException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected ResponseEntity<ApiResponseDTO<Object>> handleIOException(IOException ex) {
        log.error(ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(ApiResponseDTO.builder()
                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .error(Collections.singletonList(ex.getMessage()))
                .build()
            );
    }

    @ExceptionHandler(value = {ServletException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<ApiResponseDTO<Object>> handleServletException(ServletException ex) {
        log.error(ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(ApiResponseDTO.builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .message(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .error(Collections.singletonList(ex.getMessage()))
                .build()
            );
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
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
                .error(errors)
                .data(null)
                .build());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiResponseDTO<Object>> handleConstraintViolationException(
            ConstraintViolationException constraintViolationException
    ) {
        Set<ConstraintViolation<?>> violations = constraintViolationException.getConstraintViolations();
        List<String>  errors = new ArrayList<>();

        if (!violations.isEmpty()) {
            errors = violations.stream().map(error -> error.getPropertyPath() + ": " + error.getMessage())
                    .collect(Collectors.toList());

        } else {
            log.error("ConstraintViolationException occurred");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ApiResponseDTO.builder()
                        .code(HttpStatus.BAD_REQUEST.value())
                        .message(HttpStatus.BAD_REQUEST.getReasonPhrase())
                        .error(errors)
                        .data(null)
                        .build());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = BindException.class)
    protected ResponseEntity<ApiResponseDTO<Object>> handleBindExceptions(
        BindException ex) {
        log.error(ex.getMessage(), ex);

        List<String> errors = ex.getBindingResult().getFieldErrors()
            .stream().map(error -> error.getField() + ": " + error.getDefaultMessage())
            .collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(ApiResponseDTO.builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .message(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .error(errors)
                .data(null)
                .build());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = ConstraintDeclarationException.class)
    protected ResponseEntity<ApiResponseDTO<Object>> handleConstraintDeclarationExceptions(
        ConstraintDeclarationException ex) {

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(ApiResponseDTO.builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .message(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .error(Collections.singletonList(ex.getMessage()))
                .data(null)
                .build());
    }

    @ExceptionHandler(value = EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected ResponseEntity<ApiResponseDTO<Object>> handleEntityNotFoundExceptions(
        EntityNotFoundException ex) {
        log.error(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(ApiResponseDTO.builder()
                .code(HttpStatus.NOT_FOUND.value())
                .message(HttpStatus.NOT_FOUND.getReasonPhrase())
                .error(Collections.singletonList(ex.getMessage()))
                .build()
            );
    }

    @ExceptionHandler(value = {BusinessRuleException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<ApiResponseDTO<Object>> handleBusinessRuleExceptions(
        BusinessRuleException ex) {
        log.error(ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(ApiResponseDTO.builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .message(HttpStatus.BAD_REQUEST.getReasonPhrase())
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

    @ExceptionHandler(value = {MissingServletRequestPartException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<ApiResponseDTO<Object>> handlerMissingServletRequestPartException(
        MissingServletRequestPartException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(ApiResponseDTO.builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .message(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .error(Collections.singletonList(ex.getMessage()))
                .build()
            );
    }

    @ExceptionHandler(value = {NonUniqueResultException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<ApiResponseDTO<Object>> handleNonUniqueResultExceptions(
        NonUniqueResultException ex) {
        log.error(ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(ApiResponseDTO.builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .message(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .error(Arrays.asList(
                    "There is no single record that matches the given parameters",
                    String.join(" ", "The", ex.getMessage())))
                .build()
            );
    }
}

