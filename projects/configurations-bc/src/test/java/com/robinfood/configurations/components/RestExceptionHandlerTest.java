package com.robinfood.configurations.components;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.robinfood.configurations.dto.v1.ApiResponseDTO;
import com.robinfood.configurations.exceptions.BusinessRuleException;

import java.io.IOException;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NonUniqueResultException;
import javax.servlet.ServletException;
import javax.validation.ConstraintDeclarationException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

@ExtendWith(MockitoExtension.class)
class RestExceptionHandlerTest {

    @InjectMocks
    private RestExceptionHandler restControllerExceptionHandler;

    @Test
    void test_HandleUnauthorized_Should_ReturnApiResponse403Code_When_Invoked() {
        AccessDeniedException exceptionInvoke = new AccessDeniedException("Error");
        ResponseEntity<ApiResponseDTO<Object>> exception =
                restControllerExceptionHandler.handleUnauthorized(exceptionInvoke);

        assertNotNull(exception.getBody());
        assertEquals(HttpStatus.FORBIDDEN.value(), exception.getBody().getCode());
        assertEquals("Forbidden", exception.getBody().getMessage());
    }

    @Test
    void test_HandleIOException_Should_ReturnApiResponse500Code_When_Invoked() {
        IOException exceptionInvoke = new IOException("Error", new Throwable());

        ResponseEntity<ApiResponseDTO<Object>> exception =
                restControllerExceptionHandler.handleIOException(exceptionInvoke);

        assertNotNull(exception.getBody());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getBody().getCode());
        assertEquals("Internal Server Error", exception.getBody().getMessage());
    }

    @Test
    void test_RestControllerExceptionHandler_Should_ReturnApiResponse400Code_When_Invoked() {
        ServletException exceptionInvoke = new ServletException("Error", new Throwable());

        ResponseEntity<ApiResponseDTO<Object>> exception =
                restControllerExceptionHandler.handleServletException(exceptionInvoke);

        assertNotNull(exception.getBody());
        assertEquals(HttpStatus.BAD_REQUEST.value(), exception.getBody().getCode());
        assertEquals("Bad Request", exception.getBody().getMessage());
    }

    @Test
    void test_HandleValidationExceptions_Should_ReturnApiResponse400Code_When_Invoked() {
        BindingResult bindingResult = new BeanPropertyBindingResult(mock(Object.class), "property");
        MethodArgumentNotValidException methodArgumentNotValidException = new MethodArgumentNotValidException(
                mock(MethodParameter.class), bindingResult);

        ResponseEntity<ApiResponseDTO<Object>> exception =
                restControllerExceptionHandler.handleValidationExceptions(
                        methodArgumentNotValidException);

        assertNotNull(exception.getBody());
        assertEquals(HttpStatus.BAD_REQUEST.value(), exception.getBody().getCode());
        assertEquals("Bad Request", exception.getBody().getMessage());
    }

    @Test
    void test_HandleBindException_Should_ReturnApiResponse400Code_When_Invoked() {
        BindException bindException = new BindException(
                mock(MethodParameter.class),
                "property"
        );

        ResponseEntity<ApiResponseDTO<Object>> exception =
                restControllerExceptionHandler.handleBindExceptions(
                        bindException
                );

        assertNotNull(exception.getBody());
        assertEquals(HttpStatus.BAD_REQUEST.value(), exception.getBody().getCode());
        assertEquals("Bad Request", exception.getBody().getMessage());
    }

    @Test
    void test_HandleNotFoundExceptions_Should_ReturnApiResponse404Code_When_Invoked() {
        EntityNotFoundException exceptionInvoke = new EntityNotFoundException();
        ResponseEntity<ApiResponseDTO<Object>> exception =
                restControllerExceptionHandler.handleEntityNotFoundExceptions(exceptionInvoke);
        assertNotNull(exception.getBody());
        assertEquals(HttpStatus.NOT_FOUND.value(), exception.getBody().getCode());
    }

    @Test
    void test_HandleBusinessRuleExceptions_Should_ReturnApiResponse400Code_When_Invoked() {
        BusinessRuleException exceptionInvoke = new BusinessRuleException("Business Error.");
        ResponseEntity<ApiResponseDTO<Object>> exception =
                restControllerExceptionHandler.handleBusinessRuleExceptions(exceptionInvoke);
        assertNotNull(exception.getBody());
        assertEquals(HttpStatus.BAD_REQUEST.value(), exception.getBody().getCode());
    }

    @Test
    void test_HandleConstraintDeclarationExceptions_Should_ReturnApiResponse400Code_When_Invoked() {
        ConstraintDeclarationException exceptionInvoke = new ConstraintDeclarationException();
        ResponseEntity<ApiResponseDTO<Object>> exception =
                restControllerExceptionHandler.handleConstraintDeclarationExceptions(exceptionInvoke);
        assertNotNull(exception.getBody());
        assertEquals(HttpStatus.BAD_REQUEST.value(), exception.getBody().getCode());
    }

    @Test
    void test_RestControllerExceptionHandler_Should_ConvertHttpMessageNotReadableException_When_Invoked() {
        //Arrange
        HttpMessageNotReadableException httpMessageNotReadableException = new HttpMessageNotReadableException(
                "Error");

        //Call
        ResponseEntity<ApiResponseDTO<Object>> exception =
                restControllerExceptionHandler.handleNotReadable(httpMessageNotReadableException);

        //Asserts
        assertNotNull(exception.getBody());
        assertEquals(HttpStatus.BAD_REQUEST.value(), exception.getBody().getCode());
        assertEquals("Bad Request", exception.getBody().getMessage());
    }

    @Test
    void test_RestExceptionHandler_Should_ConvertMissingServletRequestPartException_When_Invoked() {
        //Arrange
        MissingServletRequestPartException missingServletRequestPartException = new MissingServletRequestPartException(
                "Error message");

        //Call
        ResponseEntity<ApiResponseDTO<Object>> exception =
                restControllerExceptionHandler.handlerMissingServletRequestPartException(
                        missingServletRequestPartException
                );

        //Asserts
        assertNotNull(exception.getBody());
        assertEquals(HttpStatus.BAD_REQUEST.value(), exception.getBody().getCode());
        assertEquals("Bad Request", exception.getBody().getMessage());
    }

    @Test
    void test_HandleNonUniqueResultExceptions_Should_ReturnApiResponse400Code_When_Invoked() {
        NonUniqueResultException exceptionInvoke = new NonUniqueResultException();
        ResponseEntity<ApiResponseDTO<Object>> exception =
                restControllerExceptionHandler.handleNonUniqueResultExceptions(exceptionInvoke);
        assertNotNull(exception.getBody());
        assertEquals(HttpStatus.BAD_REQUEST.value(), exception.getBody().getCode());
    }

    @Test
    void test_HandleConstraintViolationException_Should_ReturnApiResponse400Code_When_Invoked() {

        // Arrange
        ConstraintViolationException constraintViolationException = mock(ConstraintViolationException.class);
        ConstraintViolation<?> constraintViolation = mock(ConstraintViolation.class);
        Set<ConstraintViolation<?>> violations = Collections.singleton(constraintViolation);

        // Call
        when(constraintViolation.getMessage()).thenReturn("The field is required.");
        when(constraintViolationException.getConstraintViolations()).thenReturn(violations);

        ResponseEntity<ApiResponseDTO<Object>> response = restControllerExceptionHandler
                .handleConstraintViolationException(constraintViolationException);

        // Asserts
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(HttpStatus.BAD_REQUEST.value(), Objects.requireNonNull(response.getBody()).getCode());
        assertEquals(HttpStatus.BAD_REQUEST.getReasonPhrase(), response.getBody().getMessage());
        assertEquals(1, response.getBody().getError().size());
    }

    @Test
    void test_HandleConstraintViolationException_Should_Violations_IsEmpty_When_Invoked() {

        // Arrange
        ConstraintViolationException constraintViolationException = mock(ConstraintViolationException.class);
        Set<ConstraintViolation<?>> violations = Collections.emptySet();

        // Call
        when(constraintViolationException.getConstraintViolations()).thenReturn(violations);

        ResponseEntity<ApiResponseDTO<Object>> response = restControllerExceptionHandler
                .handleConstraintViolationException(constraintViolationException);

        // Asserts
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(HttpStatus.BAD_REQUEST.value(), Objects.requireNonNull(response.getBody()).getCode());
        assertEquals(HttpStatus.BAD_REQUEST.getReasonPhrase(), response.getBody().getMessage());
    }
}
