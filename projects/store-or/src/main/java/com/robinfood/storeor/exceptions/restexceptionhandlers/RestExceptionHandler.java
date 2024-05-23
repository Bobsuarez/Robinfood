package com.robinfood.storeor.exceptions.restexceptionhandlers;

import com.robinfood.storeor.dtos.apiresponsebuilder.BadRequestAbstractApiResponseBuilderDTO;
import com.robinfood.storeor.dtos.apiresponsebuilder.InternalServerErrorAbstractApiErrorResponseBuilderDTO;
import com.robinfood.storeor.dtos.apiresponsebuilder.ManagementErrorAbstractApiErrorResponseBuilderDTO;
import com.robinfood.storeor.enums.ExceptionEnum;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

import static com.robinfood.storeor.configs.constants.APIConstants.DEFAULT_STRING_VALUE;

@ControllerAdvice
@Slf4j
public class RestExceptionHandler {

    @NotNull
    @ExceptionHandler(FeignException.class)
    public ResponseEntity<Object> handleFeignStatusException(
            @NotNull FeignException exception
    ) {

        log.error("FeignException [{}]", exception.getMessage());

        ManagementErrorAbstractApiErrorResponseBuilderDTO<String> apiErrorResponseBuilderDTO =
                new ManagementErrorAbstractApiErrorResponseBuilderDTO<>();

        int feignStatus = exception.status();

        apiErrorResponseBuilderDTO.build(exception.getMessage(), DEFAULT_STRING_VALUE,
                HttpStatus.resolve(feignStatus));

        return ResponseEntity.status(
                feignStatus
        ).body(apiErrorResponseBuilderDTO.getResponse());
    }

    @NotNull
    @ExceptionHandler({
            MissingServletRequestParameterException.class
    })
    public ResponseEntity<Object> handleMissingServletRequestParameter(
            @NotNull MissingServletRequestParameterException exception
    ) {

        log.error("MissingServletRequestParameterException [{}]", exception.getMessage());

        BadRequestAbstractApiResponseBuilderDTO<Map<String, String>> badRequestAbstractApiResponseBuilderDTO;
        new BadRequestAbstractApiResponseBuilderDTO<>();

        badRequestAbstractApiResponseBuilderDTO = new BadRequestAbstractApiResponseBuilderDTO<>();

        HashMap<String, String> errors = new HashMap<>();
        errors.put(exception.getParameterName(),
                String.format("Parameter '%s' is missing", exception.getParameterName()));

        badRequestAbstractApiResponseBuilderDTO.build(errors, "invalid arguments", HttpStatus.BAD_REQUEST);
        log.error(badRequestAbstractApiResponseBuilderDTO.getApiErrorResponseDTO().getMessage()
                , exception.getCause());

        return new ResponseEntity<>(
                badRequestAbstractApiResponseBuilderDTO.getApiErrorResponseDTO(),
                HttpStatus.BAD_REQUEST
        );
    }

    @NotNull
    @ExceptionHandler({
            MethodArgumentNotValidException.class
    })
    public ResponseEntity<Object> handleMethodArgumentNotValid(
            @NotNull MethodArgumentNotValidException exception
    ) {

        log.error("Cause MethodArgumentNotValidException [{}]", exception.getCause());
        log.error("MethodArgumentNotValidException [{}]", exception.getMessage());

        BadRequestAbstractApiResponseBuilderDTO<Map<String, String>> badRequestAbstractApiResponseBuilderDTO;
        new BadRequestAbstractApiResponseBuilderDTO<>();

        badRequestAbstractApiResponseBuilderDTO = new BadRequestAbstractApiResponseBuilderDTO<>();

        HashMap<String, String> errors = new HashMap<>();
        AtomicReference<String> message = new AtomicReference<>("");

        exception.getBindingResult().getFieldErrors().forEach((FieldError error) -> {
                    errors.put(error.getField(), error.getDefaultMessage());
                    message.set(error.getDefaultMessage());
                }
        );

        badRequestAbstractApiResponseBuilderDTO.build(errors, message.get(), HttpStatus.PRECONDITION_FAILED);
        log.error(badRequestAbstractApiResponseBuilderDTO.getApiErrorResponseDTO().getMessage()
                , exception.getCause());

        return new ResponseEntity<>(
                badRequestAbstractApiResponseBuilderDTO.getApiErrorResponseDTO(),
                HttpStatus.PRECONDITION_FAILED
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
            @NotNull HttpMessageNotReadableException exception
    ) {

        log.error("HttpMessageNotReadableException [{}]", exception.getMessage());

        BadRequestAbstractApiResponseBuilderDTO<Map<String, String>> badRequestAbstractApiResponseBuilderDTO;
        new BadRequestAbstractApiResponseBuilderDTO<>();

        badRequestAbstractApiResponseBuilderDTO = new BadRequestAbstractApiResponseBuilderDTO<>();

        HashMap<String, String> errors = new HashMap<>();
        errors.put("error", exception.getMessage());

        badRequestAbstractApiResponseBuilderDTO.build(errors, ExceptionEnum.ARGUMENT_NOT_VALID_EXCEPTION);

        return ResponseEntity.status(
                HttpStatus.BAD_REQUEST
        ).body(badRequestAbstractApiResponseBuilderDTO.getApiErrorResponseDTO());
    }

    @ExceptionHandler({
            MethodArgumentTypeMismatchException.class
    })
    public ResponseEntity<Object> handleConverterErrors(MethodArgumentTypeMismatchException exception) {

        log.error("MethodArgumentTypeMismatchException [{}]", exception.getMessage());

        ManagementErrorAbstractApiErrorResponseBuilderDTO<String> apiErrorResponseBuilderDTO =
                new ManagementErrorAbstractApiErrorResponseBuilderDTO<>();

        apiErrorResponseBuilderDTO.build(exception.getMessage(), ExceptionEnum.ARGUMENT_NOT_VALID_EXCEPTION);

        return ResponseEntity.status(
                HttpStatus.BAD_REQUEST
        ).body(apiErrorResponseBuilderDTO.getResponse());
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<Object> handleNotFoundError(NoHandlerFoundException exception) {
        log.error("NoHandlerFoundException [{}]", exception.getMessage());

        ManagementErrorAbstractApiErrorResponseBuilderDTO<String> apiErrorResponseBuilderDTO =
                new ManagementErrorAbstractApiErrorResponseBuilderDTO<>();

        apiErrorResponseBuilderDTO.build(exception.getMessage(), ExceptionEnum.NOT_FOUND);

        return ResponseEntity.status(
                HttpStatus.NOT_FOUND
        ).body(apiErrorResponseBuilderDTO.getResponse());
    }
}
