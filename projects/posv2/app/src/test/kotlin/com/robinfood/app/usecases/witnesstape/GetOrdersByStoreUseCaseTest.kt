package com.robinfood.app.usecases.witnesstape

import com.robinfood.app.mocks.witnesstape.StoreOrderRequestDTOMocks
import com.robinfood.app.mocks.witnesstape.StoreOrdersDTOMocks
import com.robinfood.core.enums.Result
import com.robinfood.repository.witnesstape.IOrdersByStoreRepository
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
class GetOrdersByStoreUseCaseTest {

    @Mock
    private lateinit var ordersByStoreRepository: IOrdersByStoreRepository

    @InjectMocks
    private lateinit var getOrdersByStoreUseCase: GetOrdersByStoreUseCase

    private val token = "token"

    private val ordersStoreDTOsMocks = StoreOrdersDTOMocks().storeOrdersDTOsMocks

    private val storeOrderRequestDTOMocks = StoreOrderRequestDTOMocks().storeOrderRequestDTOMocks

    @Test
    fun test_Get_Orders_By_Store_UseCase_invoke_when_service_returns_OK() {
        runBlocking {
            Mockito.`when`(
                ordersByStoreRepository.getOrdersByStore(
                    storeOrderRequestDTOMocks,
                    token
                )
            ).thenReturn(Result.Success(ordersStoreDTOsMocks))

            val result = getOrdersByStoreUseCase.invoke(
                storeOrderRequestDTOMocks,
                token
            )
            Assertions.assertEquals(ordersStoreDTOsMocks, result)
        }
    }

    @Test
    @Throws(Exception::class)
    fun test_Get_Orders_By_Store_UseCase_invoke_when_service_returns_Error() {
        runBlocking {
            assertThrows<ResponseStatusException> {
                Mockito.`when`(
                    ordersByStoreRepository.getOrdersByStore(
                        storeOrderRequestDTOMocks,
                        token
                    )
                ).thenReturn(Result.Error(Exception("Some error"), HttpStatus.BAD_REQUEST))

                getOrdersByStoreUseCase.invoke(
                    storeOrderRequestDTOMocks,
                    token
                )
            }
        }
    }
}