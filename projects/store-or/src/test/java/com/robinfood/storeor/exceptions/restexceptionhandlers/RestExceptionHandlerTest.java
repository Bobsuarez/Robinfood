package com.robinfood.storeor.exceptions.restexceptionhandlers;

import com.robinfood.storeor.dtos.apiresponsebuilder.ApiErrorResponseDTO;
import feign.FeignException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.mock.http.MockHttpInputMessage;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RestExceptionHandlerTest {

    private final RestExceptionHandler handler = new RestExceptionHandler();

    @Test
    void test_Execute_Handle_Data_Access_Exception() {

        final String message = "Data source error";

        DataAccessException dataAccessException = new DataIntegrityViolationException(message);

        ResponseEntity<Object> responseEntity = handler.handleDataAccessException(dataAccessException);

        ApiErrorResponseDTO<String> apiErrorResponseDTO = (ApiErrorResponseDTO<String>) responseEntity.getBody();

        assertNotNull(responseEntity);
        assertNotNull(apiErrorResponseDTO);
        assertNotNull(apiErrorResponseDTO.getMessage());
        assertEquals(message, apiErrorResponseDTO.getMessage());
    }

    @Test
    void test_execute_handle_missing_servlet_request_parameter() {

        MissingServletRequestParameterException missingServletRequestParameterException =
                new MissingServletRequestParameterException("flowId", "typeTest");

        ResponseEntity<Object> responseEntity = handler.handleMissingServletRequestParameter(
                missingServletRequestParameterException
        );

        ApiErrorResponseDTO<Object> apiErrorResponseDTO = (ApiErrorResponseDTO<Object>) responseEntity.getBody();

        assertNotNull(responseEntity);
        assertNotNull(apiErrorResponseDTO);
        assertNotNull(apiErrorResponseDTO.getMessage());
    }

    @Test
    void test_execute_Handle_Http_Message_Not_Readable() {

        String errorMessage = "Error in flow";

        HttpMessageNotReadableException httpMessageNotReadableException = new HttpMessageNotReadableException(
                errorMessage
        );

        ResponseEntity<Object> responseEntity = handler.handleHttpMessageNotReadable(
                httpMessageNotReadableException
        );

        ApiErrorResponseDTO<String> apiErrorResponseDTO = (ApiErrorResponseDTO<String>) responseEntity.getBody();

        assertNotNull(responseEntity);
        assertNotNull(apiErrorResponseDTO);
        assertNotNull(apiErrorResponseDTO.getMessage());
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals(400, apiErrorResponseDTO.getCode());
    }

    @Test
    void test_Handle_Method_Argument_Not_Valid() {

        MethodArgumentNotValidException methodArgumentNotValidException = mock(MethodArgumentNotValidException.class);

        BindingResult bindingResult = mock(BindingResult.class);

        lenient().when(bindingResult.getFieldErrors()).thenReturn(
                Collections.singletonList(
                        new FieldError("station", "name", "should not be empty")
                )
        );

        when(methodArgumentNotValidException.getBindingResult()).thenReturn(bindingResult);

        ResponseEntity<Object> responseEntity = handler.handleMethodArgumentNotValid(
                methodArgumentNotValidException
        );

        ApiErrorResponseDTO<String> apiErrorResponseDTO = (ApiErrorResponseDTO<String>) responseEntity.getBody();

        assertNotNull(responseEntity);
        assertNotNull(apiErrorResponseDTO);
    }

    @Test
    void test_Handle_Method_Argument_Type_Mismatch_Exception_Not_Valid() {

        MethodArgumentTypeMismatchException methodArgumentTypeMismatchException =
                mock(MethodArgumentTypeMismatchException.class);

        ResponseEntity<Object> responseEntity = handler.handleConverterErrors(methodArgumentTypeMismatchException);

        ApiErrorResponseDTO<String> apiErrorResponseDTO = (ApiErrorResponseDTO<String>) responseEntity.getBody();

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("Argument not valid exception", apiErrorResponseDTO.getMessage());
        assertEquals(400, apiErrorResponseDTO.getCode());
    }

    @Test
    void test_Handle_Generic_Exception_Not_Valid() {

        Exception exception = mock(Exception.class);

        ResponseEntity<Object> responseEntity = handler.handleAllUncaughtException(exception);

        ApiErrorResponseDTO<String> apiErrorResponseDTO = (ApiErrorResponseDTO<String>) responseEntity.getBody();

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertEquals("internal server error", apiErrorResponseDTO.getMessage());
        assertEquals(5001, apiErrorResponseDTO.getCode());
    }

    @Test
    void test_Handle_Feign_Exception_Not_Valid() {

        int feignStatus = HttpStatus.INTERNAL_SERVER_ERROR.value();

        FeignException feignException = mock(FeignException.class);
        when(feignException.status()).thenReturn(feignStatus);

        ResponseEntity<Object> responseEntity = handler.handleFeignStatusException(feignException);

        ApiErrorResponseDTO<String> apiErrorResponseDTO = (ApiErrorResponseDTO<String>) responseEntity.getBody();

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertEquals(500, apiErrorResponseDTO.getCode());
    }

    @Test
    void test_Handle_Not_Found_Error() {

        NoHandlerFoundException NoHandlerFoundException = mock(NoHandlerFoundException.class);

        ResponseEntity<Object> responseEntity = handler.handleNotFoundError(NoHandlerFoundException);

        ApiErrorResponseDTO<String> apiErrorResponseDTO = (ApiErrorResponseDTO<String>) responseEntity.getBody();

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals("resource not found", apiErrorResponseDTO.getMessage());
        assertEquals(4004, apiErrorResponseDTO.getCode());
    }
}
