package com.robinfood.repository.customerinvoice

import com.robinfood.core.entities.APIResponseEntity
import com.robinfood.core.entities.customerinvoice.ResultPrintFormatEntity
import com.robinfood.core.enums.Result
import com.robinfood.core.extensions.toJson
import com.robinfood.core.mappers.customerinvoice.toPrintFormatDTO
import com.robinfood.network.api.RickOrAPI
import com.robinfood.network.di.DispatcherProvider
import com.robinfood.repository.mocks.customerinvoice.ResultPrintFormatEntityMock
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import retrofit2.Response

@ExtendWith(MockitoExtension::class)
@ExperimentalCoroutinesApi
class OrderCustomerInvoiceRepositoryTest {

    @Mock
    private lateinit var rickOrAPI: RickOrAPI

    @Mock
    private lateinit var dispatchers: DispatcherProvider

    @InjectMocks
    private lateinit var orderCustomerInvoiceRepository: OrderCustomerInvoiceRepository

    private val testDispatcher = TestCoroutineDispatcher()

    private val token = "token"

    private val resultPrintFormatEntityMock = ResultPrintFormatEntityMock().resultPrintFormatEntityMock

    private val printFormatDTO = resultPrintFormatEntityMock.printFormat.toPrintFormatDTO()

    private val apiResponseRickEntity = APIResponseEntity(
        200,
        resultPrintFormatEntityMock,
        "CO",
        "Order Customer Invoice response is null",
        "OK"
    )

    private val apiResponseRickError = APIResponseEntity(
        500,
        resultPrintFormatEntityMock,
        "CO",
        "Order Customer Invoice response is null",
        "OK"
    )

    private val apiResponseRickNull = APIResponseEntity<ResultPrintFormatEntity>(
        200,
        null,
        "CO",
        "Order Customer Invoice response is null",
        "OK"
    )

    private val orderId = 1L

    @Test
    fun `test get Orders Customer Invoice Repository when service returns successfully`() {
        runBlocking {
            Mockito.`when`(dispatchers.io).thenReturn(testDispatcher)
            Mockito.`when`(
                rickOrAPI.getOrderCustomerInvoiceDetail(
                    token,
                    1,
                    orderId
                )
            ).thenReturn(Response.success(apiResponseRickEntity))

            val result = orderCustomerInvoiceRepository.getOrderCustomerInvoiceDetail(
                orderId,
                token
            )
            Assertions.assertEquals(Result.Success(printFormatDTO), result)
        }
    }

    @Test
    fun test_getOrderCustomerInvoiceDetail_when_repository_returns_Error() {
        runBlocking {
            Mockito.`when`(dispatchers.io).thenReturn(testDispatcher)
            Mockito.`when`(
                rickOrAPI.getOrderCustomerInvoiceDetail(
                    token,
                    1,
                    orderId
                )
            ).thenReturn(
                Response.error(
                    500, ResponseBody.create(
                        MediaType.parse("application/json"),
                        apiResponseRickError.toJson()
                    )
                )
            )

            val result = orderCustomerInvoiceRepository.getOrderCustomerInvoiceDetail(
                orderId,
                token
            )
            Assertions.assertTrue(result is Result.Error)
        }
    }

    @Test
    fun test_getOrderCustomerInvoiceDetail_when_repository_returns_Null() {
        runBlocking {
            Mockito.`when`(dispatchers.io).thenReturn(testDispatcher)
            Mockito.`when`(
                rickOrAPI.getOrderCustomerInvoiceDetail(
                    token,
                    1,
                    orderId
                )
            ).thenReturn(Response.success(apiResponseRickNull))

            val result = orderCustomerInvoiceRepository.getOrderCustomerInvoiceDetail(
                orderId,
                token
            )
            Assertions.assertTrue(result is Result.Error)
        }
    }
}