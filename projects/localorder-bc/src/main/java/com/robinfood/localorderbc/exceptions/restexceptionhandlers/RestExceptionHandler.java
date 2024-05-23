package com.robinfood.localorderbc.exceptions.restexceptionhandlers;

import com.robinfood.localorderbc.dtos.apiresponsebuilder.BadRequestAbstractApiResponseBuilderDTO;
import com.robinfood.localorderbc.dtos.apiresponsebuilder.InternalServerErrorAbstractApiErrorResponseBuilderDTO;
import com.robinfood.localorderbc.dtos.apiresponsebuilder.ManagementErrorAbstractApiErrorResponseBuilderDTO;
import com.robinfood.localorderbc.enums.ExceptionEnum;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@Slf4j
public class RestExceptionHandler {

    @NotNull
    @ExceptionHandler(FeignException.class)
    public ResponseEntity<Object> handleFeignStatusException(
            @NotNull FeignException exception
    ) {

        log.error("FeignException [{}]", exception.getMessage());
        exception.printStackTrace();

        ManagementErrorAbstractApiErrorResponseBuilderDTO<String> apiErrorResponseBuilderDTO =
                new ManagementErrorAbstractApiErrorResponseBuilderDTO<>();

        apiErrorResponseBuilderDTO.build(exception.contentUTF8(), ExceptionEnum.INTERNAL_SERVER_ERROR);

        return ResponseEntity.status(
                HttpStatus.INTERNAL_SERVER_ERROR
        ).body(apiErrorResponseBuilderDTO.getResponse());
    }

    @NotNull
    @ExceptionHandler({
            MissingServletRequestParameterException.class
    })
    public ResponseEntity<Object> handleMissingServletRequestParameter(
            @NotNull MissingServletRequestParameterException exception,
            @NotNull HttpHeaders headers,
            @NotNull HttpStatus status,
            @NotNull WebRequest request
    ) {

        log.error("MissingServletRequestParameterException [{}]", exception.getMessage());
        exception.printStackTrace();

        ManagementErrorAbstractApiErrorResponseBuilderDTO<String> apiErrorResponseBuilderDTO =
                new ManagementErrorAbstractApiErrorResponseBuilderDTO<>();

        apiErrorResponseBuilderDTO.build(exception.getMessage(), ExceptionEnum.ARGUMENT_NOT_VALID_EXCEPTION);

        return ResponseEntity.status(
                HttpStatus.INTERNAL_SERVER_ERROR
        ).body(apiErrorResponseBuilderDTO.getResponse());
    }

    @NotNull
    @ExceptionHandler({
            MethodArgumentNotValidException.class
    })
    public ResponseEntity<Object> handleMethodArgumentNotValid(
            @NotNull MethodArgumentNotValidException exception,
            @NotNull HttpHeaders headers,
            @NotNull HttpStatus status,
            @NotNull WebRequest request
    ) {

        log.error("Cause MethodArgumentNotValidException [{}]", exception.getCause());
        log.error("MethodArgumentNotValidException [{}]", exception.getMessage());
        exception.printStackTrace();

        BadRequestAbstractApiResponseBuilderDTO<Map<String, String>> badRequestAbstractApiResponseBuilderDTO;
        new BadRequestAbstractApiResponseBuilderDTO<>();

        badRequestAbstractApiResponseBuilderDTO = new BadRequestAbstractApiResponseBuilderDTO<>();

        HashMap<String, String> errors = new HashMap<>();

        exception.getBindingResult().getFieldErrors().forEach((FieldError error) ->
                errors.put(error.getField(), error.getDefaultMessage())
        );

        badRequestAbstractApiResponseBuilderDTO.build(errors, ExceptionEnum.ARGUMENT_NOT_VALID_EXCEPTION);
        log.error(badRequestAbstractApiResponseBuilderDTO.getApiErrorResponseDTO().getMessage()
                , exception.getCause());

        return new ResponseEntity<>(
                badRequestAbstractApiResponseBuilderDTO.getApiErrorResponseDTO(),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler({DataAccessException.class})
    public ResponseEntity<Object> handleDataAccessException(
            DataAccessException exception
    ) {

        log.error("DataAccessException [{}]", exception.getMessage());
        exception.printStackTrace();

        InternalServerErrorAbstractApiErrorResponseBuilderDTO<String> apiErrorResponseBuilderDTO;

        apiErrorResponseBuilderDTO = new InternalServerErrorAbstractApiErrorResponseBuilderDTO<>();
        apiErrorResponseBuilderDTO.build(exception.getMessage(), ExceptionEnum.DATA_ACCESS_EXCEPTION);

        return ResponseEntity.status(
                HttpStatus.INTERNAL_SERVER_ERROR
        ).body(apiErrorResponseBuilderDTO.getResponse());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAllUncaughtException(
            Throwable exception) {

        log.error("GenericException [{}]", exception.getMessage());

        InternalServerErrorAbstractApiErrorResponseBuilderDTO<String> apiErrorResponseBuilderDTO;

        apiErrorResponseBuilderDTO = new InternalServerErrorAbstractApiErrorResponseBuilderDTO<>();
        apiErrorResponseBuilderDTO.build(exception.getMessage(), ExceptionEnum.INTERNAL_SERVER_ERROR);

        return ResponseEntity.status(
                HttpStatus.INTERNAL_SERVER_ERROR
        ).body(apiErrorResponseBuilderDTO.getResponse());
    }

    @NotNull
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Object> handleHttpMessageNotReadable(
            @NotNull HttpMessageNotReadableException exception,
            @NotNull HttpHeaders headers,
            @NotNull HttpStatus status,
            @NotNull WebRequest request
    ) {

        log.error("HttpMessageNotReadableException [{}]", exception.getMessage());
        exception.printStackTrace();

        ManagementErrorAbstractApiErrorResponseBuilderDTO<String> apiErrorResponseBuilderDTO =
                new ManagementErrorAbstractApiErrorResponseBuilderDTO<>();

        apiErrorResponseBuilderDTO.build(exception.getMessage(), ExceptionEnum.DATA_ACCESS_EXCEPTION);

        return new ResponseEntity<>(apiErrorResponseBuilderDTO.getResponse(), status);
    }

    @ExceptionHandler({
            MethodArgumentTypeMismatchException.class
    })
    public ResponseEntity<Object> handleConverterErrors(MethodArgumentTypeMismatchException exception) {

        log.error("MethodArgumentTypeMismatchException [{}]", exception.getMessage());
        exception.printStackTrace();

        ManagementErrorAbstractApiErrorResponseBuilderDTO<String> apiErrorResponseBuilderDTO =
                new ManagementErrorAbstractApiErrorResponseBuilderDTO<>();

        apiErrorResponseBuilderDTO.build(exception.getMessage(), ExceptionEnum.ARGUMENT_NOT_VALID_EXCEPTION);

        return ResponseEntity.status(
                HttpStatus.BAD_REQUEST
        ).body(apiErrorResponseBuilderDTO.getResponse());
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<Object> handleNotFoundError( NoHandlerFoundException exception) {
        log.error("NoHandlerFoundException [{}]", exception.getMessage());
        exception.printStackTrace();

        ManagementErrorAbstractApiErrorResponseBuilderDTO<String> apiErrorResponseBuilderDTO =
                new ManagementErrorAbstractApiErrorResponseBuilderDTO<>();

        apiErrorResponseBuilderDTO.build(exception.getMessage(), ExceptionEnum.NOT_FOUND);

        return ResponseEntity.status(
                HttpStatus.NOT_FOUND
        ).body(apiErrorResponseBuilderDTO.getResponse());
    }
}
