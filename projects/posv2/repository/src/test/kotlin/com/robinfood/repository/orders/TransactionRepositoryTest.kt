package com.robinfood.repository.orders

import com.robinfood.core.dtos.OrderCreatedDTO
import com.robinfood.core.dtos.transactionresponse.TransactionCreatedResponseDTO
import com.robinfood.core.dtos.transactionresponse.TransactionResponseDTO
import com.robinfood.core.entities.APIResponseEntity
import com.robinfood.core.entities.OrderCreatedEntity
import com.robinfood.core.entities.TransactionCreatedResponseEntity
import com.robinfood.core.entities.TransactionResponseEntity
import com.robinfood.core.enums.Result
import com.robinfood.core.exceptions.TransactionCreationException
import com.robinfood.core.extensions.toJson
import com.robinfood.network.api.OrderCreationAPI
import com.robinfood.repository.mocks.OrderRequestEntitiesMocks
import com.robinfood.repository.transactions.TransactionRepository
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.BDDMockito.given
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException
import retrofit2.Response
import java.io.IOException
import java.math.BigDecimal

@ExtendWith(MockitoExtension::class)
class TransactionRepositoryTest {

    @Mock
    private lateinit var orderCreationAPI: OrderCreationAPI

    @InjectMocks
    private lateinit var transactionRepository: TransactionRepository

    private val token = "token"

    private val orderRequestEntitiesMocks = OrderRequestEntitiesMocks()

    private val orderRequestEntity = orderRequestEntitiesMocks.orderRequestEntity

    private val ordersCreatedEntities = listOf(
        OrderCreatedEntity(
            discountPrice = BigDecimal.ZERO,
            id =  1,
            orderInvoiceNumber = "123",
            orderNumber = "123",
            taxPrice = BigDecimal.ZERO,
            total = BigDecimal.valueOf(10000.0),
            subtotal = BigDecimal.ZERO,
            uid = "1234"
        )
    )

    private val transactionResponseEntity = TransactionResponseEntity(
        id = 1L,
        orders = ordersCreatedEntities,
        uuid = "1234"
    )

    private val transactionCreatedResponseEntity = TransactionCreatedResponseEntity(
        transaction = transactionResponseEntity
    )

    private val ordersCreatedDTOS = listOf(
        OrderCreatedDTO(
            discountPrice = BigDecimal.ZERO,
            id =  1,
            orderInvoiceNumber = "123",
            orderNumber = "123",
            taxPrice = BigDecimal.ZERO,
            total = BigDecimal.valueOf(10000.0),
            subtotal = BigDecimal.ZERO,
            uid = "1234"
        )
    )

    private val transactionResponseDTO = TransactionResponseDTO(
        id = 1L,
        orders = ordersCreatedDTOS,
        uuid = "1234"
    )

    private val transactionCreatedResponseDTO = TransactionCreatedResponseDTO(
        transaction = transactionResponseDTO
    )

    private val apiResponseErrorEntity = APIResponseEntity(
        500,
        transactionCreatedResponseEntity,
        "CO",
        "Error",
        "Order could not be created"
    )

    private val apiResponseEntity = APIResponseEntity(
        200,
        transactionCreatedResponseEntity,
        "CO",
        "",
        "Order created successfully"
    )

    private val apiResponseEntityWithNullTransaction = APIResponseEntity<TransactionCreatedResponseEntity>(
            200,
            null,
            "CO",
            "",
            "Order created successfully"
    )

    @Test
    fun test_CreateOrder_when_service_returns_OK() {
        runBlocking {
            `when`(orderCreationAPI.createTransaction(
                    token,
                    orderRequestEntity
            )).thenReturn(Response.success(apiResponseEntity))
            val result = transactionRepository.createTransaction(
                token,
                orderRequestEntity
            )
            assertEquals(Result.Success(transactionCreatedResponseDTO), result)
        }
    }

    @Test
    fun test_CreateOrder_when_service_returns_OK_but_transaction_is_null() {
        runBlocking {
            `when`(orderCreationAPI.createTransaction(
                    token,
                    orderRequestEntity
            )).thenReturn(Response.success(apiResponseEntityWithNullTransaction))
            val result = transactionRepository.createTransaction(
                    token,
                    orderRequestEntity
            )
            Assertions.assertTrue(result is Result.Error)
        }
    }

    @Test
    fun test_CreateOrder_when_service_returns_Error() {
        runBlocking {
            given(orderCreationAPI.createTransaction(
                    token,
                    orderRequestEntity
            )).willAnswer {
                throw IOException("Ooops")
            }
            try {
                transactionRepository.createTransaction(
                    token,
                    orderRequestEntity
                )
            } catch (exception: ResponseStatusException) {
                assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, exception.status)
            }
        }
    }

    @Test
    fun test_CreateOrder_when_service_returns_Error_And_Exception_is_not_OrchestratorException() {
        runBlocking {
            `when`(orderCreationAPI.createTransaction(
                    token,
                    orderRequestEntity
            )).thenReturn(Response.error(500, ResponseBody.create(
                    MediaType.parse("application/json"),
                    apiResponseErrorEntity.toJson()
            )))
            try {
                transactionRepository.createTransaction(
                        token,
                        orderRequestEntity
                )
            } catch (exception: TransactionCreationException) {
                assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, exception.status)
            }
        }
    }
}