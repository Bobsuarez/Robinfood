package com.robinfood.app.controllers.reports

import com.robinfood.app.usecases.getdailyreportevoucher.IGetDailyReportVoucherUseCase
import com.robinfood.app.usecases.getordercustomerinvoice.IOrderCustomerInvoiceUseCase
import com.robinfood.app.usecases.witnesstape.IGetWitnessTapeReportUseCase
import com.robinfood.core.constants.APIConstants
import com.robinfood.core.constants.APIConstants.API_V1
import com.robinfood.core.constants.APIConstants.REPORT
import com.robinfood.core.constants.APIConstants.WITNESS_TAPE
import com.robinfood.core.dtos.ApiResponseDTO
import com.robinfood.core.dtos.report.ReportResponseDTO
import com.robinfood.core.dtos.witnesstape.StoreOrderRequestDTO
import java.time.LocalDate
import javax.servlet.http.HttpServletRequest
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(API_V1 + REPORT)
class GetReportsPdfController(
    private val getWitnessTapeReportUseCase: IGetWitnessTapeReportUseCase,
    private val getDailyReportVoucherUseCase: IGetDailyReportVoucherUseCase,
    private val orderCustomerInvoiceUseCase: IOrderCustomerInvoiceUseCase
) : IGetReportsPdfController {

    @GetMapping(WITNESS_TAPE)
    override suspend fun getWitnessTapePdf(
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        @RequestParam(value = "localDateStart", required = true) localDateStart: LocalDate,
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        @RequestParam(value = "localDateEnd", required = true) localDateEnd: LocalDate,
        @PathVariable("storeId") storeId: Long,
        httpServletRequest: HttpServletRequest
    ): ResponseEntity<ApiResponseDTO<ReportResponseDTO>> {
        val timeZone = httpServletRequest.getHeader("TimeZone")
        val token = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION)

        val storeOrderRequestDTO = StoreOrderRequestDTO(
            localDateEnd,
            localDateStart,
            storeId,
            timeZone
        )

        val witnessTapeReportByteArray = getWitnessTapeReportUseCase.invoke(storeOrderRequestDTO, token)

        return ResponseEntity(
            ApiResponseDTO(
                witnessTapeReportByteArray,
                "Report Witness retrieved successfully"
            ),
            HttpStatus.OK
        )
    }

    @GetMapping(APIConstants.POS_DAILY_VOUCHER)
    override suspend fun getDailyReportVoucherPdf(
        @PathVariable("storeId") storeId: Long,
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        @RequestParam(value = "localDateStart", required = true) localDateStart: LocalDate,
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        @RequestParam(value = "localDateEnd", required = true) localDateEnd: LocalDate,
        httpServletRequest: HttpServletRequest
    ): ResponseEntity<ApiResponseDTO<ReportResponseDTO>> {
        val timeZone = httpServletRequest.getHeader("TimeZone")
        val token = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION)

        val storeOrderRequestDTO = StoreOrderRequestDTO(
            localDateEnd,
            localDateStart,
            storeId,
            timeZone
        )

        val dailyReportVoucherByteArray = getDailyReportVoucherUseCase.invoke(storeOrderRequestDTO, token)

        return ResponseEntity(
            ApiResponseDTO(
                dailyReportVoucherByteArray,
                "Report Daily Voucher retrieved successfully"
            ),
            HttpStatus.OK
        )

    }

    @GetMapping(APIConstants.CUSTOMER_INVOICE)
    override suspend fun getOrderCustomerInvoice(
        @PathVariable("orderId") orderId: Long,
        httpServletRequest: HttpServletRequest
    ): ResponseEntity<ApiResponseDTO<ReportResponseDTO>> {
        val token = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION)

        val orderCustomerInvoiceByteArray = orderCustomerInvoiceUseCase.invoke(orderId, token)

        return ResponseEntity(
            ApiResponseDTO(
                orderCustomerInvoiceByteArray,
                "Order Customer Invoice retrieved successfully"
            ),
            HttpStatus.OK
        )
    }
}