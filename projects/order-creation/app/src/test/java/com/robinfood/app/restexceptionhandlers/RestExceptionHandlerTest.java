package com.robinfood.app.restexceptionhandlers;

import com.robinfood.app.commandhandler.IInvokerCommand;
import com.robinfood.core.exceptions.TransactionCreationException;
import com.robinfood.core.exceptions.WriteInTransactionException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.mock.http.MockHttpInputMessage;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class RestExceptionHandlerTest {

    @Mock
    private IInvokerCommand invokerCommand;

    @InjectMocks
    private RestExceptionHandler restExceptionHandler;

    @Test
    void test_handleGenericException_Should_ReturnApiResponse_When_Invoked() {

        final TransactionCreationException transactionCreationException = new TransactionCreationException(
                HttpStatus.BAD_REQUEST, "Error with transaction"
        );

        doNothing().when(invokerCommand).rollback();

        final ResponseEntity<Object> exception = restExceptionHandler.handleGenericException(transactionCreationException);

        assertNotNull(exception.getBody());
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
    }

    @Test
    void test_handleGenericException_Should_WriteInTransactionException_When_Invoked() {

        final WriteInTransactionException transactionCreationException = new WriteInTransactionException(
                HttpStatus.BAD_REQUEST, "Error with transaction"
        );

        doNothing().when(invokerCommand).rollback();

        final ResponseEntity<Object> exception = restExceptionHandler.handleGenericException(transactionCreationException);

        assertNotNull(exception.getBody());
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
    }

    @Test
    void test_handleHttpMessageNotReadable_Should_ReturnApiResponse_When_Invoked() {
        HttpMessageNotReadableException httpMessageNotReadableException = new HttpMessageNotReadableException(
                "test Message", new MockHttpInputMessage("test".getBytes(StandardCharsets.UTF_8)));

        ResponseEntity<Object> exception = restExceptionHandler.handleHttpMessageNotReadable(httpMessageNotReadableException);

        assertNotNull(exception.getBody());
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
    }

    @Test
    void test_handleMethodArgumentNotValid_Should_ReturnBadRequest_When_Invoked() {
        BindingResult bindingResult = new BeanPropertyBindingResult(mock(Object.class), "property");
        MethodArgumentNotValidException methodArgumentNotValidException = new MethodArgumentNotValidException(
                mock(MethodParameter.class), bindingResult);

        ResponseEntity<Object> exception =
                restExceptionHandler.handleMethodArgumentNotValid(methodArgumentNotValidException);

        assertNotNull(exception.getBody());
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
    }

    @Test
    void test_handleResponseStatusException_Should_ReturnApiResponse_When_Invoked() {
        ResponseStatusException responseStatusException = new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Error with transaction"
        );

        ResponseEntity<Object> exception
                = restExceptionHandler.handleResponseStatusException(responseStatusException, HttpStatus.BAD_REQUEST);

        assertNotNull(exception.getBody());
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
    }
}
