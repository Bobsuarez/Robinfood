package com.robinfood.app.restexceptionhandlers

import com.robinfood.core.exceptions.TransactionCreationException
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.mock.http.MockHttpInputMessage
import org.springframework.web.bind.MissingServletRequestParameterException
import org.springframework.web.context.request.WebRequest
import java.nio.charset.StandardCharsets

@ExtendWith(MockitoExtension::class)
class RestExceptionHandlerTest {

    @InjectMocks
    private lateinit var restExceptionHandler: RestExceptionHandler

    @Test
    fun test_handleHttpMessageNotReadableException_Should_ReturnApiResponse_When_Invoked() {
        val httpMessageNotReadableException = HttpMessageNotReadableException(
                "test Message", MockHttpInputMessage("test".toByteArray(StandardCharsets.UTF_8)))

        val exception = restExceptionHandler.handleHttpMessageNotReadable(
                httpMessageNotReadableException,
                HttpHeaders.EMPTY,
                HttpStatus.BAD_REQUEST,
                Mockito.mock(WebRequest::class.java)
        )

        Assertions.assertNotNull(exception.body)
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, exception.statusCode)
    }

    @Test
    fun test_handleMissingServletRequestParameter_Should_ReturnApiResponse_When_Invoked() {
        val missingServletRequestParameterException = MissingServletRequestParameterException(
                "testName",
                "testType"
        )

        val exception = restExceptionHandler.handleMissingServletRequestParameter(
                missingServletRequestParameterException,
                HttpHeaders.EMPTY,
                HttpStatus.BAD_REQUEST,
                Mockito.mock(WebRequest::class.java)
        )

        Assertions.assertNotNull(exception.body)
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, exception.statusCode)
    }

    @Test
    fun test_handleTransactionCreationException_Should_ReturnApiResponse_When_Invoked() {
        val transactionCreationException = TransactionCreationException(
                "data",
                "testName",
                HttpStatus.BAD_REQUEST
        )

        val exception = restExceptionHandler.handleTransactionCreationException(transactionCreationException)

        Assertions.assertNotNull(exception.body)
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, exception.statusCode)
    }

    @Test
    fun test_handleTransactionCreationException_Should_ReturnApiResponse_With_No_Message_When_Invoked() {
        val transactionCreationException = TransactionCreationException(
                "data",
                null,
                HttpStatus.BAD_REQUEST
        )

        val exception = restExceptionHandler.handleTransactionCreationException(transactionCreationException)

        Assertions.assertNotNull(exception.body)
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, exception.statusCode)
    }
}