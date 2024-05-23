package com.robinfood.app.usecases.validateorigin

import com.robinfood.app.mocks.OrderRequestDTOsMocks
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.InjectMocks
import org.junit.jupiter.api.Assertions.assertEquals

@ExtendWith(MockitoExtension::class)
class ValidateOriginUseCaseTest {

    @InjectMocks
    private lateinit var validateOriginUseCase: ValidateOriginUseCase

    private val orderRequestDTOsMocks = OrderRequestDTOsMocks()

    private val transactionRequest = orderRequestDTOsMocks.transactionRequestIntegrations

    private val transactionRequestDTO = orderRequestDTOsMocks.transactionRequest


    @Test
    fun test_ValidateOrigin_When_Is_Integration_Ok() {

        val transactionRequestResponse = orderRequestDTOsMocks.transactionResponseIntegrations

        runBlocking {
            val transactionModify = validateOriginUseCase.invoke(transactionRequest)
            assertEquals(transactionModify, transactionRequestResponse)
        }

    }

    @Test
    fun test_ValidateOrigin_When_Is_Not_Integration() {

        runBlocking {
            val transactionModify = validateOriginUseCase.invoke(transactionRequestDTO)
            assertEquals(transactionModify, transactionRequestDTO)
        }
    }
}