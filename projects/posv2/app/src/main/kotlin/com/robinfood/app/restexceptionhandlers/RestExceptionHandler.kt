package com.robinfood.app.restexceptionhandlers

import com.robinfood.core.dtos.ApiResponseDTO
import com.robinfood.core.exceptions.TransactionCreationException
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.MissingServletRequestParameterException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class RestExceptionHandler : ResponseEntityExceptionHandler() {

    private val log: Logger = LoggerFactory.getLogger(this.javaClass)

    @ExceptionHandler(Throwable::class)
    public override fun handleHttpMessageNotReadable(
            ex: HttpMessageNotReadableException,
            headers: HttpHeaders,
            status: HttpStatus,
            request: WebRequest
    ): ResponseEntity<Any?> {
        log.error(ex.message, ex)
        return ResponseEntity(ApiResponseDTO<Any>(ex.mostSpecificCause.localizedMessage), status)
    }

    public override fun handleMissingServletRequestParameter(
            ex: MissingServletRequestParameterException,
            headers: HttpHeaders,
            status: HttpStatus,
            request: WebRequest
    ): ResponseEntity<Any> {
        log.error(ex.message, ex)
        return ResponseEntity(ApiResponseDTO<Any>( ex.localizedMessage), status)
    }

    @ExceptionHandler(TransactionCreationException::class)
    fun handleTransactionCreationException(
            ex: TransactionCreationException
    ): ResponseEntity<Any> {
        log.error(ex.message, ex)
        return ResponseEntity(ApiResponseDTO(ex.data, ex.message.orEmpty(), ex.status), ex.status)
    }
}