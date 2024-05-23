package com.robinfood.app.controllers.transactions

import com.robinfood.app.POSApplication
import com.robinfood.app.mocks.OrderRequestDTOsMocks
import com.robinfood.app.usecases.createtransaction.ICreateTransactionUseCase
import com.robinfood.core.constants.APIConstants.API_V1
import com.robinfood.core.constants.APIConstants.TRANSACTION
import com.robinfood.core.dtos.ApiResponseDTO
import com.robinfood.core.dtos.OrderCreatedDTO
import com.robinfood.core.dtos.transactionresponse.TransactionCreatedResponseDTO
import com.robinfood.core.dtos.transactionresponse.TransactionResponseDTO
import com.robinfood.core.enums.Result
import com.robinfood.core.extensions.toJson
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.context.TestPropertySource
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.math.BigDecimal

@AutoConfigureMockMvc
@SpringBootTest(classes = [POSApplication::class])
@TestPropertySource(properties = [
    "jwt-token-prefix=Bearer ",
    "jwt-token-secret=secretForTesting",
    "jwt-token-aud=internal",
    "jwt-token-mod=posv2"
])
class TransactionControllerTest {

    companion object {
        private const val BEARER_AUTH = "Bearer "
        private const val TOKEN =
            "eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJhbmRyb2lkLnYxIiwianRpIjoiNEEyMDc5MkUtODA4NS00QzAyLTg4RDgtQzc2ODc0RDBENjE3Iiwic3ViIjoiMTIzNDU2NyIsIm5hbWUiOiJKb2huIERvZSIsImlhdCI6MTUxNjIzOTAyMiwiZXhwIjoxODE2MjM5MDIyLCJhdWQiOiJpbnRlcm5hbCIsIm1vZCI6WyJvcmRlckJjIiwib3JkZXJDcmVhdGlvblF1ZXJpZXMiLCJwb3N2MiJdLCJwZXIiOlsicG9zX2NyZWF0ZV9vcmRlciIsIm9yZGVyc19yZWplY3Rfb3JkZXIiXSwidXNlciI6eyJ1c2VyX2lkIjoxMjM0NTY3LCJlbWFpbCI6ImpvaG5kb2VAbXljb21wYW55LmNvbSIsImNvdW50cnlfaWQiOjEsImNvbXBhbnlfaWQiOjEsImZpcnN0X25hbWUiOiJKaG9uIiwibGFzdF9uYW1lIjoiRG9lIiwicGhvbmUiOiI1NTUtNjM4MzAyMiIsIm1ldGFkYXRhIjp7InN0b3JlX2lkIjo1fX19.vUjmr0KoXohM-3bjMX8FmaSsPZDR5X_9soqhpFiwyBLpDW6WbfkRQzJf00lTzYEjpUbsXxk-khhkOcpQQhIwrQ"
    }

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var mockCreateTransactionUseCase: ICreateTransactionUseCase

    private val orderRequestDTOsMocks = OrderRequestDTOsMocks()

    private val transactionRequestDTO = orderRequestDTOsMocks.transactionRequest

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
    @Throws(Exception::class)
    fun test_CreateOrder_Should_Return_Ok() {
        runBlocking {
            `when`(mockCreateTransactionUseCase.invoke(
                BEARER_AUTH + TOKEN,
                transactionRequestDTO
            )).thenReturn(Result.Success(orderCreatedResponseDTO))
            val result = mockMvc.perform(MockMvcRequestBuilders
                .post("$API_V1$TRANSACTION")
                .header(HttpHeaders.AUTHORIZATION, BEARER_AUTH + TOKEN)
                .contentType(MediaType.APPLICATION_JSON)
                .content(transactionRequestDTO.toJson())
                .accept(MediaType.APPLICATION_JSON))
                .andReturn()
            mockMvc.perform(asyncDispatch(result))
                .andExpect(status().isCreated)
                .andExpect(content().json(ApiResponseDTO(orderCreatedResponseDTO, "Transaction created successfully").toJson()))
        }
    }

    @Test
    @Throws(Exception::class)
    fun test_CreateOrder_Should_Return_Failure() {
        runBlocking {
            `when`(mockCreateTransactionUseCase.invoke(
                BEARER_AUTH + TOKEN,
                transactionRequestDTO
            )).thenReturn(Result.Error(Exception("Some error"), HttpStatus.BAD_REQUEST))
            val result = mockMvc.perform(MockMvcRequestBuilders
                .post("$API_V1$TRANSACTION")
                .header(HttpHeaders.AUTHORIZATION, BEARER_AUTH + TOKEN)
                .contentType(MediaType.APPLICATION_JSON)
                .content(transactionRequestDTO.toJson())
                .accept(MediaType.APPLICATION_JSON))
                .andReturn()
            mockMvc.perform(asyncDispatch(result))
                .andExpect(status().isBadRequest)
        }
    }
}