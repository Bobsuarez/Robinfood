package com.robinfood.app.usecases.getordercustomerinvoice

import com.robinfood.app.mocks.customerinvoice.PrintFormatDTOMock
import com.robinfood.core.enums.Result
import com.robinfood.repository.customerinvoice.IOrderCustomerInvoiceRepository
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException

@ExtendWith(MockitoExtension::class)
class GetOrderCustomerInvoiceUseCaseTest {

    @Mock
    private lateinit var orderCustomerInvoiceRepository: IOrderCustomerInvoiceRepository

    @InjectMocks
    private lateinit var getOrderCustomerInvoiceUseCase: GetOrderCustomerInvoiceUseCase

    private val token = "token"

    private val printFormatDTOMock = PrintFormatDTOMock().printFormatDTOMock

    private val orderId = 27L

    @Test
    fun test_Get_Order_Customer_Invoice_Use_Case_invoke_when_service_returns_OK() {
        runBlocking {
            Mockito.`when`(
                orderCustomerInvoiceRepository.getOrderCustomerInvoiceDetail(
                    orderId,
                    token
                )
            ).thenReturn(Result.Success(printFormatDTOMock))

            val result = getOrderCustomerInvoiceUseCase.invoke(
                orderId,
                token
            )
            Assertions.assertEquals(printFormatDTOMock, result)
        }
    }

    @Test
    @Throws(Exception::class)
    fun test_Get_Order_Customer_Invoice_Use_Case_invoke_when_service_returns_Error() {
        runBlocking {
            assertThrows<ResponseStatusException> {
                Mockito.`when`(
                    orderCustomerInvoiceRepository.getOrderCustomerInvoiceDetail(
                        orderId,
                        token
                    )
                ).thenReturn(Result.Error(Exception("Some error"), HttpStatus.BAD_REQUEST))

                getOrderCustomerInvoiceUseCase.invoke(
                    orderId,
                    token
                )
            }
        }
    }
}