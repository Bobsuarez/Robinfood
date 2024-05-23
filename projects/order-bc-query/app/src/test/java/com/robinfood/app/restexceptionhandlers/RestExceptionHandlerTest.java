package com.robinfood.app.restexceptionhandlers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.robinfood.core.dtos.apiresponsebuilder.ApiErrorResponseDTO;
import com.robinfood.core.dtos.apiresponsebuilder.ApiResponseDTO;

import java.nio.charset.StandardCharsets;

import java.util.Collections;

import com.robinfood.core.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;
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

@ExtendWith(MockitoExtension.class)
class RestExceptionHandlerTest {

    private final RestExceptionHandler handler = new RestExceptionHandler();

    @Test
    void test_Execute_Handle_Data_Access_Exception() {

        final String message = "Data source error";

        // Arrange
        DataAccessException exception = new DataIntegrityViolationException(message);

        // Act
        ResponseEntity<Object> response = handler.handleDataAccessException(exception);
        ApiErrorResponseDTO<String> apiErrorResponseDTO = (ApiErrorResponseDTO<String>) response.getBody();

        // Assert
        assertNotNull(response);
        assertNotNull(apiErrorResponseDTO);
        assertNotNull(apiErrorResponseDTO.getMessage());
        assertEquals(message, apiErrorResponseDTO.getMessage());
    }

    @Test
    void test_Handle_Method_Argument_Not_Valid() {

        // Arrange
        MethodArgumentNotValidException methodArgumentNotValidException = mock(MethodArgumentNotValidException.class);
        BindingResult bindingResult = mock(BindingResult.class);
        lenient().when(bindingResult.getFieldErrors()).thenReturn(
                Collections.singletonList(
                        new FieldError("station", "name", "should not be empty")
                )
        );

        when(methodArgumentNotValidException.getBindingResult()).thenReturn(bindingResult);

        // Act
        ResponseEntity<Object> response = handler.handleMethodArgumentNotValid(
                methodArgumentNotValidException,
                null,
                HttpStatus.BAD_REQUEST,
                null
        );
        ApiErrorResponseDTO<String> apiErrorResponseDTO = (ApiErrorResponseDTO<String>) response.getBody();

        // Assert
        assertNotNull(response);
        assertNotNull(apiErrorResponseDTO);

    }

    @Test
    void test_execute_handle_missing_servlet_request_parameter() {

        // Arrange
        String message = "Required request parameter 'flowId' for method parameter type typeTest is not present";

        MissingServletRequestParameterException exception =
                new MissingServletRequestParameterException(
                        "flowId",
                        "typeTest"
                );

        // Act
        ResponseEntity<Object> response = handler.handleMissingServletRequestParameter(
                exception,
                null,
                HttpStatus.BAD_REQUEST,
                null
        );

        ApiResponseDTO<String> apiResponseDTO = (ApiResponseDTO<String>) response.getBody();

        // Assert
        assertNotNull(response);
        assertNotNull(apiResponseDTO);
        assertNotNull(apiResponseDTO.getMessage());
        assertEquals(message, apiResponseDTO.getMessage());
    }

    @Test
    void test_execute_Handle_Http_Message_Not_Readable() {

        // Arrange
        String errorMessage = "Error in flow";

        HttpMessageNotReadableException exception = new HttpMessageNotReadableException(
                errorMessage,
                new MockHttpInputMessage("Error".getBytes(StandardCharsets.UTF_8))

        );

        // Act
        ResponseEntity<Object> response = handler.handleHttpMessageNotReadable(
                exception,
                null,
                HttpStatus.BAD_REQUEST,
                null
        );

        ApiResponseDTO<String> apiResponseDTO = (ApiResponseDTO<String>) response.getBody();

        // Assert
        assertNotNull(response);
        assertNotNull(apiResponseDTO);
        assertNotNull(apiResponseDTO.getMessage());
        assertEquals(errorMessage, apiResponseDTO.getMessage());

    }

    @Test
    void test_execute_Handle_Http_Message_Not_Found() {

        // Arrange
        String errorMessage = "Resource Not Found";

        ResourceNotFoundException exception = new ResourceNotFoundException(
            errorMessage
        );

        // Act
        ResponseEntity<Object> response = handler.resourceNotFoundException(
            exception
        );

        ApiResponseDTO<String> apiResponseDTO = (ApiResponseDTO<String>) response.getBody();

        // Assert
        assertNotNull(response);
        assertNotNull(apiResponseDTO);
        assertNotNull(apiResponseDTO.getMessage());
        assertEquals(errorMessage, apiResponseDTO.getMessage());

    }
}