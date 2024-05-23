package com.robinfood.repository.orderhistory

import com.robinfood.core.dtos.HistoryPaginatedItemDTO
import com.robinfood.core.dtos.OrderStatusDTO
import com.robinfood.core.dtos.OrderUserDTO
import com.robinfood.core.dtos.HistoryDTO
import com.robinfood.core.dtos.PropertyDTO
import com.robinfood.core.dtos.orderhistory.OrderHistoryRequestDTO
import com.robinfood.core.dtos.transactionrequest.BrandDTO
import com.robinfood.core.dtos.transactionrequest.OriginDTO
import com.robinfood.core.entities.HistoryPaginatedItemEntity
import com.robinfood.core.entities.OrderStatusEntity
import com.robinfood.core.entities.OrderUserEntity
import com.robinfood.core.entities.HistoryEntity
import com.robinfood.core.entities.PropertyEntity
import com.robinfood.core.entities.APIResponseEntity
import com.robinfood.core.entities.transactionrequest.BrandEntity
import com.robinfood.core.entities.transactionrequest.OriginEntity
import com.robinfood.core.enums.Result
import com.robinfood.core.extensions.toJson
import com.robinfood.network.api.OrderCreationQueriesAPI
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import retrofit2.Response
import java.time.LocalDate

@ExtendWith(MockitoExtension::class)
class OrderHistoryRepositoryTest {

    @Mock
    private lateinit var orderCreationQueriesAPI: OrderCreationQueriesAPI

    @InjectMocks
    private lateinit var orderHistoryRepository: OrderHistoryRepository

    private val perPage = 10
    private val page = 2
    private val lastPage = 32
    private val token = "token"

    private val historyPaginatedItemEntities: List<HistoryPaginatedItemEntity> = listOf(
            HistoryPaginatedItemEntity(
                    brand = BrandEntity(123L, "Muy"),
                    createdAt = "2022-11-03T15:33:56",
                    deliveryTypeId = 123,
                    id = 123L,
                    orderInvoiceNumber = "00002",
                    orderNumber = "456789",
                    origin = OriginEntity(123L, "Rappi"),
                    total = "99000",
                    status = OrderStatusEntity(123L, "En preparacion"),
                    user = OrderUserEntity(
                            "email@something.com", "firstname",
                            1234L, "lastname",
                            1L, "123456789"
                    )
            ),
            HistoryPaginatedItemEntity(
                    brand = BrandEntity(345L, "Muy"),
                    createdAt = "2022-11-03T15:33:56",
                    deliveryTypeId = 345,
                    id = 345L,
                    orderInvoiceNumber = "00002",
                    orderNumber = "456789",
                    origin = OriginEntity(345L, "Rappi"),
                    total = "99000",
                    status = OrderStatusEntity(345L, "En preparacion"),
                    user = OrderUserEntity(
                            "email@something.com", "firstname",
                            67890L, "lastname",
                            4L, "123456789"
                    )
            )
    )

    private val historyEntity = HistoryEntity(
            historyPaginatedItemEntities,
            PropertyEntity(
                    perPage,
                    page,
                    lastPage,
                    historyPaginatedItemEntities.size
            )
    )

    private val historyEntityWithNullValues = HistoryEntity(
            null,
            null
    )

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

    private val apiResponseErrorEntity = APIResponseEntity(
        500,
        historyEntity,
        "CO",
        "Error",
        "Order history could not be returned"
    )

    private val apiResponseEntity = APIResponseEntity(
        200,
        historyEntity,
        "CO",
        "",
        "Order history returned"
    )

    private val apiResponseEntityWithHistoryNull = APIResponseEntity<HistoryEntity>(
            200,
            null,
            "CO",
            "",
            "Order history returned"
    )

    private val apiResponseEntityWithNullValues = APIResponseEntity(
        200,
        historyEntityWithNullValues,
        "CO",
        "",
        "Order history returned"
    )

    private val orderHistoryRequestDTOOkTrue = OrderHistoryRequestDTO.Builder()
            .channelsId(1)
            .currentPage(1)
            .localDateStart(LocalDate.now())
            .localDateEnd(LocalDate.now())
            .perPage(1)
            .isDelivery(true)
            .searchText("")
            .storeId(27L)
            .timeZone("America/Bogota")
            .build();

    @Test
    fun `test get order history when service returns successfully`() {
        runBlocking {
            `when`(orderCreationQueriesAPI.getOrderHistory(
                    27L,
                    "America/Bogota",
                    token,
                    1,
                    1,
                    LocalDate.now(),
                    LocalDate.now(),
                    1,
                    true,
                    ""
            )).thenReturn(Response.success(apiResponseEntity))

            val result = orderHistoryRepository.getOrderHistory(
                token, orderHistoryRequestDTOOkTrue
            )
            assertEquals(Result.Success(historyDTO), result)
        }
    }

    @Test
    fun `test get order history when service returns successfully but history is null`() {
        runBlocking {
            `when`(orderCreationQueriesAPI.getOrderHistory(
                    27L,
                    "America/Bogota",
                    token,
                    1,
                    1,
                    LocalDate.now(),
                    LocalDate.now(),
                    1,
                    true,
                    ""
            )).thenReturn(Response.success(apiResponseEntityWithHistoryNull))

            val result = orderHistoryRepository.getOrderHistory(
                    token,
                    orderHistoryRequestDTOOkTrue
            )
            assertTrue(result is Result.Error)
        }
    }

    @Test
    fun `test get order history when service returns with error`() {
        runBlocking {
            `when`(orderCreationQueriesAPI.getOrderHistory(
                    27L,
                    "America/Bogota",
                    token,
                    1,
                    1,
                    LocalDate.now(),
                    LocalDate.now(),
                    1,
                    true,
                    ""
            )).thenReturn(Response.error(500, ResponseBody.create(
                MediaType.parse("application/json"),
                apiResponseErrorEntity.toJson()
            )))

            val result = orderHistoryRepository.getOrderHistory(
                token, orderHistoryRequestDTOOkTrue
            )
            assertTrue(result is Result.Error)
        }
    }


    @Test
    fun `test get order history when service returns but actual history and properties are null`() {
        runBlocking {
            `when`(orderCreationQueriesAPI.getOrderHistory(
                    27L,
                    "America/Bogota",
                    token,
                    1,
                    1,
                    LocalDate.now(),
                    LocalDate.now(),
                    1,
                    true,
                    ""
            )).thenReturn(Response.success(apiResponseEntityWithNullValues))
            val result = orderHistoryRepository.getOrderHistory(
                token, orderHistoryRequestDTOOkTrue
            )
            assertTrue(result is Result.Error)
        }
    }
}