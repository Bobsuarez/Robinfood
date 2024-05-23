package com.robinfood.app.usecases.getorderhistory

import com.robinfood.core.dtos.HistoryPaginatedItemDTO
import com.robinfood.core.dtos.OrderStatusDTO
import com.robinfood.core.dtos.OrderUserDTO
import com.robinfood.core.dtos.HistoryDTO
import com.robinfood.core.dtos.PropertyDTO
import com.robinfood.core.dtos.orderhistory.OrderHistoryRequestDTO
import com.robinfood.core.dtos.transactionrequest.BrandDTO
import com.robinfood.core.dtos.transactionrequest.OriginDTO
import com.robinfood.core.enums.Result
import com.robinfood.repository.orderhistory.IOrderHistoryRepository
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import java.time.LocalDate

@ExtendWith(MockitoExtension::class)
class GetOrderHistoryUseCaseTest {

    @Mock
    private lateinit var orderHistoryRepository: IOrderHistoryRepository

    @InjectMocks
    private lateinit var getOrderHistoryUseCase: GetOrderHistoryUseCase

    private val perPage = 10
    private val page = 2
    private val lastPage = 32
    private val token = "token"
    
    private val historyPaginatedItemDTOs: List<HistoryPaginatedItemDTO> = listOf(
            HistoryPaginatedItemDTO(
                    brand = BrandDTO(123L, "Muy"),
                    createdAt = "2022-11-03T15:33:56",
                    deliveryTypeId = 123,
                    id = 123L,
                    orderInvoiceNumber = "00002",
                    orderNumber = "456789",
                    origin = OriginDTO(123L, "Rappi"),
                    total = "99000",
                    status = OrderStatusDTO(123L, "En preparacion"),
                    user = OrderUserDTO(
                            "email@something.com", "firstname",
                            1234L, "lastname",
                            1L, "123456789"
                    )
            ),
            HistoryPaginatedItemDTO(
                    brand = BrandDTO(345L, "Muy"),
                    createdAt = "2022-11-03T15:33:56",
                    deliveryTypeId = 345,
                    id = 345L,
                    orderInvoiceNumber = "00002",
                    orderNumber = "456789",
                    origin = OriginDTO(345L, "Rappi"),
                    total = "99000",
                    status = OrderStatusDTO(345L, "En preparacion"),
                    user = OrderUserDTO(
                            "email@something.com", "firstname",
                            67890L, "lastname",
                            4L, "123456789"
                    )
            )
    )

    private val historyDTO = HistoryDTO(
            historyPaginatedItemDTOs,
            PropertyDTO(
                    perPage,
                    page,
                    lastPage,
                    historyPaginatedItemDTOs.size
            )
    )

    @Test
    fun `test that get order history use case returns correctly`() {
        val orderHistoryRequestDTO = OrderHistoryRequestDTO.Builder()
                .channelsId(1)
                .currentPage(1)
                .localDateStart(LocalDate.now())
                .localDateEnd(LocalDate.now())
                .perPage(10)
                .isDelivery(true)
                .searchText("")
                .storeId(27)
                .timeZone("America/Bogota")
                .build()

        runBlocking {
            `when`(orderHistoryRepository.getOrderHistory(
                    token,
                    orderHistoryRequestDTO
            )).thenReturn(Result.Success(historyDTO))

            val result = getOrderHistoryUseCase.invoke(
                    token,
                    orderHistoryRequestDTO
            )
            assertEquals(Result.Success(historyDTO), result)
        }
    }
}