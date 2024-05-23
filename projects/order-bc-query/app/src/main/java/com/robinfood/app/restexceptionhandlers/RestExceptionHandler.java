package com.robinfood.app.restexceptionhandlers;

import com.robinfood.core.dtos.apiresponsebuilder.AbstractApiResponseBuilderDTO;
import com.robinfood.core.dtos.apiresponsebuilder.ApiResponseDTO;
import com.robinfood.core.dtos.apiresponsebuilder.BadRequestAbstractApiResponseBuilderDTO;
import com.robinfood.core.dtos.apiresponsebuilder.DataResponseDTO;
import com.robinfood.core.dtos.apiresponsebuilder.InternalServerErrorAbstractApiErrorResponseBuilderDTO;
import com.robinfood.core.dtos.apiresponsebuilder.NotFoundAbstractApiResponseBuilderDTO;
import com.robinfood.core.enums.ExceptionEnum;
import com.robinfood.core.exceptions.AsyncOrderBcException;
import com.robinfood.core.exceptions.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import com.robinfood.core.exceptions.GenericOrderBcException;
import org.jetbrains.annotations.NotNull;
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

import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@Slf4j
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    private final AbstractApiResponseBuilderDTO<DataResponseDTO<Object>> apiResponseDTOBuilder =
            new BadRequestAbstractApiResponseBuilderDTO<>();

    @NotNull
    protected ResponseEntity<Object> handleMissingServletRequestParameter(
            @NotNull MissingServletRequestParameterException exception,
            @NotNull HttpHeaders headers,
            @NotNull HttpStatus status,
            @NotNull WebRequest request
    ) {
        apiResponseDTOBuilder.build(exception.getLocalizedMessage());
        return new ResponseEntity<>(apiResponseDTOBuilder.getApiResponseDTO(), status);
    }

    @ExceptionHandler(GenericOrderBcException.class)
    protected ResponseEntity<Object> genericException(
            GenericOrderBcException ex
    ) {
        apiResponseDTOBuilder.build(ex.getLocalizedMessage());
        return new ResponseEntity<>(apiResponseDTOBuilder.getApiResponseDTO(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @NotNull
    public ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException exception,
            @NotNull HttpHeaders headers,
            @NotNull HttpStatus status,
            @NotNull WebRequest request
    ) {
        BadRequestAbstractApiResponseBuilderDTO<Map<String, String>> badRequestAbstractApiResponseBuilderDTO;
        new BadRequestAbstractApiResponseBuilderDTO<>();

        badRequestAbstractApiResponseBuilderDTO = new BadRequestAbstractApiResponseBuilderDTO<>();

        HashMap<String, String> errors = new HashMap<>();

        exception.getBindingResult().getFieldErrors().forEach((FieldError error) ->
                errors.put(error.getField(), error.getDefaultMessage())
        );

        badRequestAbstractApiResponseBuilderDTO.build(errors, ExceptionEnum.ARGUMENT_NOT_VALID_EXCEPTION);
        log.error(badRequestAbstractApiResponseBuilderDTO.getApiErrorResponseDTO().getMessage(), exception.getCause());

        return new ResponseEntity<>(
                badRequestAbstractApiResponseBuilderDTO.getApiErrorResponseDTO(),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(DataAccessException.class)
    protected ResponseEntity<Object> handleDataAccessException(
            DataAccessException exception
    ) {

        InternalServerErrorAbstractApiErrorResponseBuilderDTO<ExceptionEnum> apiErrorResponseBuilderDTO;

        apiErrorResponseBuilderDTO = new InternalServerErrorAbstractApiErrorResponseBuilderDTO<>();
        apiErrorResponseBuilderDTO.build(ExceptionEnum.DATA_ACCESS_EXCEPTION);

        log.error(apiErrorResponseBuilderDTO.getResponse().getMessage(), exception.getCause());

        return ResponseEntity.status(
                HttpStatus.INTERNAL_SERVER_ERROR
        ).body(apiErrorResponseBuilderDTO.getResponse());
    }

    @NotNull
    @ExceptionHandler(Throwable.class)
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            @NotNull HttpMessageNotReadableException exception,
            @NotNull HttpHeaders headers,
            @NotNull HttpStatus status,
            @NotNull WebRequest request
    ) {

        log.error("Cause: ", exception.getCause());
        log.error(exception.getLocalizedMessage());

        apiResponseDTOBuilder.build(exception.getMessage());
        return new ResponseEntity<>(apiResponseDTOBuilder.getApiResponseDTO(), status);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    protected ResponseEntity<Object> resourceNotFoundException(
            ResourceNotFoundException ex
    ) {
        AbstractApiResponseBuilderDTO<DataResponseDTO<Object>> apiResponseNotFoundDTOBuilder =
                new NotFoundAbstractApiResponseBuilderDTO<>();

        apiResponseNotFoundDTOBuilder.build(ex.getLocalizedMessage());
        return new ResponseEntity<>(apiResponseNotFoundDTOBuilder.getApiResponseDTO(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MissingRequestValueException.class)
    protected ResponseEntity<Object> missingRequestValueException(
            MissingRequestValueException ex
    ) {
        apiResponseDTOBuilder.build(ex.getLocalizedMessage());
        return new ResponseEntity<>(apiResponseDTOBuilder.getApiResponseDTO(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<Object> methodArgumentTypeMismatchException(
            MethodArgumentTypeMismatchException ex
    ) {
        apiResponseDTOBuilder.build(ex.getLocalizedMessage());
        return new ResponseEntity<>(apiResponseDTOBuilder.getApiResponseDTO(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResponseStatusException.class)
    protected ResponseEntity<Object> handleResponseStatusException(
            @NotNull ResponseStatusException exception
    ) {

        ApiResponseDTO<?> apiResponseDTO = new ApiResponseDTO<>(
                exception.getStatus().value(),
                exception.getReason(),
                exception.getStatus()
        );

        return new ResponseEntity<>(apiResponseDTO, exception.getStatus());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<Object> handleConstraintViolationException(
            @NotNull ConstraintViolationException exception
    ) {

        ApiResponseDTO<?> apiResponseDTO = new ApiResponseDTO<>(
                HttpStatus.BAD_REQUEST.value(),
                exception.getMessage(),
                HttpStatus.BAD_REQUEST
        );

        return new ResponseEntity<>(apiResponseDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AsyncOrderBcException.class)
    protected ResponseEntity<Object> asyncOrderBcException(
            @NotNull AsyncOrderBcException ex
    ) {
        apiResponseDTOBuilder.build(ex.getLocalizedMessage());
        return new ResponseEntity<>(apiResponseDTOBuilder.getApiResponseDTO(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
