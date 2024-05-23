package com.robinfood.app.controllers.orders

import com.robinfood.core.dtos.ApiResponseDTO
import com.robinfood.core.dtos.HistoryDTO
import com.robinfood.core.dtos.OrderDetailDTO
import com.robinfood.core.dtos.OrderDailyResponseDTO
import com.robinfood.core.dtos.ApiResponseWithConfigurationsDTO
import com.robinfood.core.dtos.flags.FlagsDTO
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import java.time.LocalDate
import javax.servlet.http.HttpServletRequest

/**
 * Exposes the API that handles all data related to orders
 */
@Tag(name = "Orders", description = "Requests for orders related data")
interface IOrdersController {

    /**
     * Changes the order state to canceled
     * @return The details of the operation made
     */
    @Operation(summary = "Changes the orders status in BD")
    @ApiResponses(value = [ApiResponse(responseCode = "200", description = "This changes the state of an order")])
    fun deleteOrder(orderId: Long): ResponseEntity<ApiResponseDTO<Any>>

    /**
     * Retrieves the order history based on the params
     * @return the order history containing the orders detailed info
     */
    @Operation(summary = "Gets order history based on the params")
    @ApiResponses(value = [ApiResponse(responseCode = "200", description = "Gets order history based on the params")])
    suspend fun getOrderHistory(
            storeId: Long,
            channelsId: Int?,
            currentPage: Int?,
            localDateStart: LocalDate,
            localDateEnd: LocalDate,
            perPage: Int?,
            isDelivery: Boolean,
            searchText: String?,
            httpServletRequest: HttpServletRequest
    ): ResponseEntity<ApiResponseDTO<HistoryDTO>>


    /**
     * Retrieves the orders detail based on orders identifiers given
     * [countryId] country identifier of the product
     * [flowId] flow identifier of the product
     * [httpServletRequest] the authentication token to be used
     * [orderIds] the orders identifiers
     * [platformId] platform identifier of the product
     * [storeId] store identifier of the product
     * @return the orders detailed info
     */
    @Operation(summary = "Gets the order detail of one or more orders")
    @ApiResponses(value = [ApiResponse(responseCode = "200", description = "Gets the order detail of one or more orders")])
    suspend fun getOrdersDetail(
            countryId: Long,
            flowId: Long,
            httpServletRequest: HttpServletRequest,
            orderIds: List<Long>,
            platformId: Long?,
            storeId: Long
    ): ResponseEntity<ApiResponseWithConfigurationsDTO<List<OrderDetailDTO>, FlagsDTO>>

    /**
     * Service in charge of searching for orders to be billed per day.
     * [storeId] store identifier
     * @return orders to be billed per day
     */
    @Operation(summary = "Get all order to be invoiced per day")
    @ApiResponses(value = [ApiResponse(responseCode = "200", description = "Get all order to be invoiced per day")])
    suspend fun getAllOrderDaily(
            timeZone: String,
            storeId: Long,
            httpServletRequest: HttpServletRequest
    ): ResponseEntity<ApiResponseDTO<OrderDailyResponseDTO>>
}