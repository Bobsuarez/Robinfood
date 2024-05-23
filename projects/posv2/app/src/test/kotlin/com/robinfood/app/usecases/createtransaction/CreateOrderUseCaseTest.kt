package com.robinfood.app.usecases.createtransaction

import com.robinfood.app.mocks.OrderRequestDTOsMocks
import com.robinfood.app.usecases.validateorigin.IValidateOriginUseCase
import com.robinfood.app.usecases.validateuuid.IValidateUuidUseCase
import com.robinfood.core.dtos.OrderCreatedDTO
import com.robinfood.core.dtos.transactionresponse.TransactionCreatedResponseDTO
import com.robinfood.core.dtos.transactionresponse.TransactionResponseDTO
import com.robinfood.core.enums.Result
import com.robinfood.core.mappers.toTransactionRequestEntity
import com.robinfood.repository.transactions.ITransactionRepository
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.junit.jupiter.MockitoExtension
import java.math.BigDecimal

@ExtendWith(MockitoExtension::class)
class CreateOrderUseCaseTest {

    @Mock
    private lateinit var transactionRepository: ITransactionRepository

    @Mock
    private lateinit var validateOriginUseCase: IValidateOriginUseCase

    @Mock
    private lateinit var validateUuidUseCase : IValidateUuidUseCase

    @InjectMocks
    private lateinit var createTransactionUseCase: CreateTransactionUseCase

    private val token = "token"

    private val orderRequestDTOsMocks = OrderRequestDTOsMocks()

    private val orderRequestDTO = orderRequestDTOsMocks.transactionRequest

    private val orderRequestEntity = orderRequestDTO.toTransactionRequestEntity()

    private val orderCreatedDTOS = listOf(
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

    private val orderCreatedResponseDTO = TransactionCreatedResponseDTO(
        transaction = TransactionResponseDTO(
            1L,
            orders = orderCreatedDTOS,
            uuid = "1234"
        )
    )

    @Test
    fun test_CreateOrder_when_service_returns_OK() {
        runBlocking {
            Mockito.`when`(
                transactionRepository.createTransaction(
                    token,
                    orderRequestEntity
                )
            ).thenReturn(Result.Success(orderCreatedResponseDTO))

            Mockito.`when`(
                    validateOriginUseCase.invoke(
                            orderRequestDTO
                    )
            ).thenReturn(orderRequestDTO)

            Mockito.`when`(
                    validateUuidUseCase.invoke(
                            orderRequestDTO
                    )
            ).thenReturn(orderRequestDTO)

            val result = createTransactionUseCase.invoke(
                token,
                orderRequestDTO
            )
            assertEquals(Result.Success(orderCreatedResponseDTO), result)
            verify(validateOriginUseCase).invoke(orderRequestDTO)
        }
    }
}