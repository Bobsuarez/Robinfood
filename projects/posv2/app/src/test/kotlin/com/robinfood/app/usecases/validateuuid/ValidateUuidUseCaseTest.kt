package com.robinfood.app.usecases.validateuuid

import com.robinfood.app.mocks.OrderRequestDTOsMocks
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class ValidateUuidUseCaseTest {

    @InjectMocks
    private lateinit var validateUuidUseCase: ValidateUuidUseCase

    private val orderRequestDTOsMocks = OrderRequestDTOsMocks()

    private val transactionRequest = orderRequestDTOsMocks.transactionResponseIntegrations


    @Test
    fun test_ValidateUuid_When_UuidIsTemplate() {

        val transactionRequestResponse = orderRequestDTOsMocks.transactionResponseIntegrations
        val transactionRequest = orderRequestDTOsMocks.transactionResponseIntegrations

        transactionRequestResponse.orders = orderRequestDTOsMocks.ordersDTOs
        transactionRequestResponse.orders.forEach { order ->
            order.uuid = "5d93fe21-230c-4343-be3b-a3701c2118bd"
        }
        transactionRequestResponse.uuid = "5d93fe21-230c-4343-be3b-a3701c2118bd"

        transactionRequest.uuid = "5d93fe21-230c-4343-be3b-a3701c2118bd-template"
        transactionRequest.orders = orderRequestDTOsMocks.ordersDTOs
        transactionRequest.orders.forEach { data -> data.uuid = "5d93fe21-230c-4343-be3b-a3701c2118bd-template" };

        runBlocking {
            val transactionModify = validateUuidUseCase.invoke(transactionRequest)
            assertEquals(transactionModify, transactionRequestResponse)
        }
    }

    @Test
    fun test_ValidateUuid_When_uuidNoTemplate() {

        val transactionRequestResponse = orderRequestDTOsMocks.transactionResponseIntegrations

        transactionRequestResponse.uuid = "5d93fe21-230c-4343-be3b-a3701c2118bd"

        transactionRequest.uuid = "5d93fe21-230c-4343-be3b-a3701c2118bd"

        runBlocking {
            val transactionModify = validateUuidUseCase.invoke(transactionRequest)
            assertEquals(transactionModify, transactionRequestResponse)
        }
    }

    @Test
    fun test_ValidateOrigin_When_Is_Empty_UUID() {

        val transactionRequestResponse = orderRequestDTOsMocks.transactionResponseIntegrations

        transactionRequestResponse.uuid = "5d93fe21-230c-4343-be3b-a3701c2118bd"

        transactionRequest.uuid = ""

        runBlocking {
            val transactionModify = validateUuidUseCase.invoke(transactionRequest)
            assertEquals(transactionModify, transactionRequest)
        }
    }

    @Test
    fun test_ValidateOrigin_When_Is_Null_UUID() {

        val transactionRequestResponse = orderRequestDTOsMocks.transactionResponseIntegrations

        transactionRequestResponse.uuid = null

        transactionRequest.uuid = null

        runBlocking {
            val transactionModify = validateUuidUseCase.invoke(transactionRequest)
            assertEquals(transactionModify, transactionRequest)
        }
    }
}
