package com.robinfood.taxes.components;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

import com.robinfood.taxes.dto.v1.ApiResponseDTO;
import com.robinfood.taxes.exceptions.BusinessRuleException;
import java.io.IOException;
import javax.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ExtendWith(MockitoExtension.class)
class RestControllerErrorHandlerTest {

    @InjectMocks
    private RestControllerExceptionHandler restControllerExceptionHandler;

    @Test
    void test_RestControllerExceptionHandler_Should_BusinessRuleException_When_Invoked(){
        //Arrange
        BusinessRuleException businessRuleException = new BusinessRuleException("Error");

        //Call
        ResponseEntity<ApiResponseDTO<Object>> exception =
              restControllerExceptionHandler.handleBusinessRuleException(businessRuleException);

        //Asserts
        assertNotNull(exception.getBody());
        assertEquals(HttpStatus.BAD_REQUEST.value(), exception.getBody().getCode());
        assertEquals("Bad Request", exception.getBody().getMessage());
    }

    @Test
    void test_RestControllerExceptionHandler_Should_HttpMessageNotReadableException_When_Invoked(){
        //Arrange
        HttpMessageNotReadableException httpMessageNotReadableException = new HttpMessageNotReadableException("Error");

        //Call
        ResponseEntity<ApiResponseDTO<Object>> exception =
              restControllerExceptionHandler.handleNotReadable(httpMessageNotReadableException);

        //Asserts
        assertNotNull(exception.getBody());
        assertEquals(HttpStatus.BAD_REQUEST.value(), exception.getBody().getCode());
        assertEquals("Bad Request", exception.getBody().getMessage());
    }

    @Test
    void test_RestControllerExceptionHandler_Should_Exception_When_Invoked(){
        //Arrange
        Exception exceptionInvoke = new Exception("Error");

        //Call
        ResponseEntity<ApiResponseDTO<Object>> exception =
              restControllerExceptionHandler.handleException(exceptionInvoke);

        //Asserts
        assertNotNull(exception.getBody());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getBody().getCode());
        assertEquals("Internal Server Error", exception.getBody().getMessage());
    }

    @Test
    void test_RestControllerExceptionHandler_Should_IOException_When_Invoked(){
        //Arrange
        IOException ioExceptionInvoke = new IOException("Error");

        //Call
        ResponseEntity<ApiResponseDTO<Object>> exception =
            restControllerExceptionHandler.handleIOException(ioExceptionInvoke);

        //Asserts
        assertNotNull(exception.getBody());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getBody().getCode());
        assertEquals("Internal Server Error", exception.getBody().getMessage());
    }

    @Test
    void test_RestExceptionHandler_Should_EntityNotFoundException_When_Invoked(){
        //Arrange
        EntityNotFoundException entityNotFoundException = new EntityNotFoundException("Error Entity");

        //Call
        ResponseEntity<ApiResponseDTO<Object>> exception =
              restControllerExceptionHandler.handleNotFound(entityNotFoundException);

        //Asserts
        assertNotNull(exception.getBody());
        assertEquals(HttpStatus.NOT_FOUND.value(), exception.getBody().getCode());
        assertEquals("Not Found", exception.getBody().getMessage());
    }

    @Test
    void test_RestControllerExceptionHandler_Should_MethodArgumentTypeMismatchException_When_Invoked(){
        //Arrange
        MethodArgumentTypeMismatchException methodArgumentTypeMismatchException = mock(MethodArgumentTypeMismatchException.class);

        //Call
        ResponseEntity<ApiResponseDTO<Object>> exception =
            restControllerExceptionHandler.handleMethodArgumentTypeMismatchException(methodArgumentTypeMismatchException);

        //Asserts
        assertNotNull(exception.getBody());
        assertEquals(HttpStatus.BAD_REQUEST.value(), exception.getBody().getCode());
        assertEquals("Bad Request", exception.getBody().getMessage());
    }

    @Test
    void test_RestControllerExceptionHandler_Should_MissingServletRequestParameterException_When_Invoked(){
        //Arrange
        MissingServletRequestParameterException missingServletRequestParameterException = mock(MissingServletRequestParameterException.class);

        //Call
        ResponseEntity<ApiResponseDTO<Object>> exception =
            restControllerExceptionHandler.handleMissingParameterException(missingServletRequestParameterException);

        //Asserts
        assertNotNull(exception.getBody());
        assertEquals(HttpStatus.BAD_REQUEST.value(), exception.getBody().getCode());
        assertEquals("Bad Request", exception.getBody().getMessage());
    }

    @Test
    void test_RestControllerExceptionHandler_Should_AccessDeniedException_When_Invoked(){
        //Arrange
        AccessDeniedException accessDeniedException = mock(AccessDeniedException.class);

        //Call
        ResponseEntity<ApiResponseDTO<Object>> exception =
            restControllerExceptionHandler.handlerForbidden(accessDeniedException);

        //Asserts
        assertNotNull(exception.getBody());
        assertEquals(HttpStatus.FORBIDDEN.value(), exception.getBody().getCode());
        assertEquals(HttpStatus.FORBIDDEN.getReasonPhrase(), exception.getBody().getMessage());
    }

}
