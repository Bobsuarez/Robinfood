package com.robinfood.app.controllers.orders

import com.robinfood.app.usecases.deleteorder.IDeleteOrderUseCase
import com.robinfood.app.usecases.getorderhistory.IGetOrderHistoryUseCase
import com.robinfood.app.usecases.orderdetail.IGetOrdersDetailUseCase
import com.robinfood.app.usecases.orderdaily.IGetOrdersDailyUseCase
import com.robinfood.core.constants.APIConstants.API_V1
import com.robinfood.core.constants.APIConstants.ORDERS
import com.robinfood.core.constants.APIConstants.ORDERS_DAILY
import com.robinfood.core.constants.APIConstants.ORDER_DETAIL
import com.robinfood.core.constants.APIConstants.ORDER_HISTORY
import com.robinfood.core.dtos.ApiResponseDTO
import com.robinfood.core.dtos.HistoryDTO
import com.robinfood.core.dtos.OrderDetailDTO
import com.robinfood.core.dtos.OrderDailyResponseDTO
import com.robinfood.core.dtos.ApiResponseWithConfigurationsDTO
import com.robinfood.core.dtos.flags.FlagsDTO
import com.robinfood.core.dtos.orderhistory.OrderHistoryRequestDTO
import com.robinfood.core.enums.Result
import org.slf4j.Logger
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MissingServletRequestParameterException
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate
import javax.servlet.http.HttpServletRequest
import org.slf4j.LoggerFactory

/**
 * Implementation of 'IOrdersController'
 */
@RestController
@RequestMapping(API_V1 + ORDERS)
class OrdersController(
        private val deleteOrderUseCase: IDeleteOrderUseCase,
        private val getOrderHistoryUseCase: IGetOrderHistoryUseCase,
        private val getOrdersDetailUseCase: IGetOrdersDetailUseCase,
        private val getOrdersDailyUseCase: IGetOrdersDailyUseCase
) : IOrdersController {

    private val log: Logger = LoggerFactory.getLogger(this.javaClass)

    @DeleteMapping("/{orderId}")
    override fun deleteOrder(@PathVariable orderId: Long): ResponseEntity<ApiResponseDTO<Any>> {
        val orderHasBeenDeleted = deleteOrderUseCase.invoke(orderId)
        val message: String
        val httpStatus: HttpStatus
        if (orderHasBeenDeleted) {
            message = String.format("Order with id %s was deleted successfully.", orderId)
            httpStatus = HttpStatus.OK
        } else {
            message = String.format("Order with id %s not found.", orderId)
            httpStatus = HttpStatus.NOT_FOUND
        }
        val apiResponseDTO = ApiResponseDTO<Any>(message)
        return ResponseEntity(apiResponseDTO, httpStatus)
    }

    @GetMapping("/{storeId}$ORDER_HISTORY")
    override suspend fun getOrderHistory(
            @PathVariable("storeId") storeId: Long,
            @RequestParam(value = "channelsId", required = false) channelsId: Int?,
            @RequestParam(value = "currentPage", required = false) currentPage: Int?,
            @DateTimeFormat(pattern = "yyyy-MM-dd")
            @RequestParam(value = "localDateStart", required = true) localDateStart: LocalDate,
            @DateTimeFormat(pattern = "yyyy-MM-dd")
            @RequestParam(value = "localDateEnd", required = true) localDateEnd: LocalDate,
            @RequestParam(value = "perPage", required = false) perPage: Int?,
            @RequestParam(value = "isDelivery", required = true) isDelivery: Boolean,
            @RequestParam(value = "searchText", required = false) searchText: String?,
            httpServletRequest: HttpServletRequest
    ): ResponseEntity<ApiResponseDTO<HistoryDTO>> {

        log.info("Started request to get the orders history");
        val token = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION)
        val timeZone = httpServletRequest.getHeader("TimeZone")

        val orderHistoryRequestDTO = OrderHistoryRequestDTO.Builder()
                .channelsId(channelsId)
                .currentPage(currentPage)
                .localDateStart(localDateStart)
                .localDateEnd(localDateEnd)
                .perPage(perPage)
                .isDelivery(isDelivery)
                .searchText(searchText)
                .storeId(storeId)
                .timeZone(timeZone)
                .build()

        val historyDTO = getOrderHistoryUseCase.invoke(
                token,
                orderHistoryRequestDTO
        )
        log.info("This is orderHistoryResult response from use case: {}", historyDTO);

        return when (historyDTO) {
            is Result.Error -> ResponseEntity(
                    ApiResponseDTO(historyDTO.exception.localizedMessage),
                    historyDTO.httpStatus
            )
            is Result.Success -> ResponseEntity(ApiResponseDTO(historyDTO.data), HttpStatus.OK)
        }
    }

    @GetMapping(ORDER_DETAIL)
    override suspend fun getOrdersDetail(
            @RequestParam(value = "country_id", required = true) countryId: Long,
            @RequestParam(value = "flow_id", required = true) flowId: Long,
            httpServletRequest: HttpServletRequest,
            @RequestParam(value = "orderIds") orderIds: List<Long>,
            @RequestParam(value = "platform_id", required = false) platformId: Long?,
            @RequestParam(value = "store_id", required = true) storeId: Long
    ): ResponseEntity<ApiResponseWithConfigurationsDTO<List<OrderDetailDTO>, FlagsDTO>> {

        val token = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION)
        val ordersDetailResult = getOrdersDetailUseCase.invoke(
                token,
                orderIds,
                countryId,
                flowId,
                storeId,
                platformId
        )

        if (orderIds.isEmpty()) {
            throw MissingServletRequestParameterException("Order id's can not be empty", "orderIds")
        }
        return when (ordersDetailResult) {
            is Result.Error -> ResponseEntity(
                ApiResponseWithConfigurationsDTO(ordersDetailResult.exception.localizedMessage),
                    ordersDetailResult.httpStatus
            )
            is Result.Success -> ResponseEntity(ApiResponseWithConfigurationsDTO(
                ordersDetailResult.data.listOrderDetail, ordersDetailResult.data.flags), HttpStatus.OK)
        }
    }
    @GetMapping(ORDERS_DAILY)
    override suspend fun getAllOrderDaily(
        @RequestHeader(value = "timeZone", required = true) timeZone: String,
        @RequestParam(value = "storeId", required = true) storeId: Long,
        httpServletRequest: HttpServletRequest
    ): ResponseEntity<ApiResponseDTO<OrderDailyResponseDTO>> {
        val token = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION)
        val ordersToInvoiceResult = getOrdersDailyUseCase.invoke(
                token,
                timeZone,
                storeId
        )
        return when (ordersToInvoiceResult) {
            is Result.Error -> ResponseEntity(
                ApiResponseDTO(ordersToInvoiceResult.exception.localizedMessage),
                ordersToInvoiceResult.httpStatus
            )
            is Result.Success -> ResponseEntity(ApiResponseDTO(ordersToInvoiceResult.data), HttpStatus.OK)
        }
    }
}