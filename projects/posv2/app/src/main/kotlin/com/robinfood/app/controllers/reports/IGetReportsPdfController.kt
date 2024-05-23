package com.robinfood.app.controllers.reports

import com.robinfood.core.dtos.ApiResponseDTO
import com.robinfood.core.dtos.report.ReportResponseDTO
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.ArraySchema
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import java.time.LocalDate
import javax.servlet.http.HttpServletRequest
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity

/**
 * Exposes the API that handles all data related to report witness Tape
 */
@Tag(name = "WitnessTape", description = "Requests for get report witness Tape related data")
interface IGetReportsPdfController {

    /**
     * Sends request to get user pos configuration
     *
     * [storeId] The Store id to get pdf witness tape
     * [localDateStart] The date local start
     * [localDateEnd] The date local end
     * [httpServletRequest] the authentication token to be used
     * @return The array bytes for report pdf
     */
    @Operation(summary = "Sends a request to get array bytes for report")
    @ApiResponses(
        ApiResponse(
            responseCode = "200",
            description = "This sends a request to array bytes",
            content = [(Content(
                array = ArraySchema(schema = Schema(implementation = ReportResponseDTO::class)),
                mediaType = MediaType.APPLICATION_JSON_VALUE
            ))]
        )
    )
    suspend fun getWitnessTapePdf(
        localDateStart: LocalDate,
        localDateEnd: LocalDate,
        storeId: Long,
        httpServletRequest: HttpServletRequest
    ): ResponseEntity<ApiResponseDTO<ReportResponseDTO>>

    
    /**
     * Sends request to get array bytes for report daily
     *
     * [posId] The Pos id to get configuration
     * [localDateStart] The date local start
     * [localDateEnd] The date local end
     * [httpServletRequest] the authentication token to be used
     * @return The array bytes for report pdf
     */
    @Operation(summary = "Sends a request to get array bytes for report")
    @ApiResponses(
        ApiResponse(
            responseCode = "200",
            description = "This sends a request to array bytes",
            content = [(Content(
                array = ArraySchema(schema = Schema(implementation = ReportResponseDTO::class)),
                mediaType = MediaType.APPLICATION_JSON_VALUE
            ))]
        )
    )
    suspend fun getDailyReportVoucherPdf(
        posId: Long,
        localDateStart: LocalDate,
        localDateEnd: LocalDate,
        httpServletRequest: HttpServletRequest
    ): ResponseEntity<ApiResponseDTO<ReportResponseDTO>>

    /**
     * Sends request to get array bytes for customer invoice
     *
     * [orderId] The Pos id to get customer invoice
     * [httpServletRequest] the authentication token to be used
     * @return The array bytes for report pdf
     */
    @Operation(summary = "Sends a request to get array bytes for report")
    @ApiResponses(
        ApiResponse(
            responseCode = "200",
            description = "This sends a request to array bytes",
            content = [(Content(
                array = ArraySchema(schema = Schema(implementation = ReportResponseDTO::class)),
                mediaType = MediaType.APPLICATION_JSON_VALUE
            ))]
        )
    )
    suspend fun getOrderCustomerInvoice(
        orderId: Long,
        httpServletRequest: HttpServletRequest
    ): ResponseEntity<ApiResponseDTO<ReportResponseDTO>>
}