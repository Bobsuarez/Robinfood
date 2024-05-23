package com.robinfood.app.usecases.orderdaily

import com.robinfood.app.mocks.OrderDailyDTOsMocks
import com.robinfood.core.dtos.OrderDailyResponseDTO
import com.robinfood.core.enums.Result
import com.robinfood.repository.orderdaily.IOrdersDailyRepository
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
class GetOrdersDailyUseCaseTest {

    @Mock
    private lateinit var ordersDailyRepository: IOrdersDailyRepository

    @InjectMocks
    private lateinit var getOrdersDailyUseCase: GetOrdersDailyUseCase

    private val orderDailyDTOsMocks = OrderDailyDTOsMocks()

    private val token = "token"
    private val timeZone = "America/Bogota"
    private val storeId = 1L
    private val orderDailyDTO = listOf(orderDailyDTOsMocks.orderDailyDTO)
    private val orderDailyDTOResult =OrderDailyResponseDTO(listOf(orderDailyDTOsMocks.orderDailyDTOResult))
    private val customException = Exception("Some error")
    private val customHttpStatus = HttpStatus.BAD_REQUEST

    @Test
    fun `test that get orders daily use case returns correctly`() {
        runBlocking {
            Mockito.`when`(ordersDailyRepository.getAllOrdersDaily(
                token,
                timeZone,
                storeId
            )).thenReturn(Result.Success(orderDailyDTO))

            val result = getOrdersDailyUseCase.invoke(
                token,
                timeZone,
                storeId
            )

            Assertions.assertEquals(Result.Success(orderDailyDTOResult), result)
        }
    }

    @Test
    fun `test that get orders daily use case returns error`() {
        runBlocking {

            Mockito.`when`(ordersDailyRepository.getAllOrdersDaily(
                    token,
                    timeZone,
                    storeId
            )).thenReturn(Result.Error(customException, customHttpStatus))

            val result = getOrdersDailyUseCase.invoke(
                    token,
                    timeZone,
                    storeId
            )

            Assertions.assertTrue(result is Result.Error)
        }
    }
}
