package com.robinfood.app.controllers.reports.salesbysegment;

import com.robinfood.core.dtos.apiresponsebuilder.ApiResponseDTO;
import com.robinfood.core.dtos.report.salebysegment.GetSaleBySegmentResponseDTO;
import com.robinfood.core.exceptions.AsyncOrderBcException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;

import static com.robinfood.core.constants.GlobalConstants.DATE_TIME_CURRENT;


/**
 * Controller of the information from the sales report by segments that contains the company - brand - channels -
 * payment methods - stores
 */
public interface ISalesBySegmentController {


    /**
     * Get the information from the sales report by filters - channels - brands - stores - paymentMethods
     *
     * @param brandsList         list of the brands to search
     * @param companiesList      list of the companies to search
     * @param channelsList       list of the channels to search
     * @param dateTimeCurrent    End date to consult the records
     * @param paymentMethodsList list of the paymentMethods to search
     * @param storesList         list of the stores to search
     * @return SaleBySegmentResponseDTO
     */
    @Operation(summary = "report of sales by filters - channels - brands - stores - paymentMethods")
    @ApiResponse(
            responseCode = "200",
            description = "report of sales by orders",
            content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = GetSaleBySegmentResponseDTO.class))}
    )
    ResponseEntity<ApiResponseDTO<GetSaleBySegmentResponseDTO>> getSalesBySegment(
            @RequestParam(required = false, name = "brands")
            @Parameter(
                    name = "brands",
                    description = "list of the brands to search"
            ) List<Long> brandsList,
            @RequestParam(name = "companies")
            @Parameter(
                    name = "companies",
                    description = "list of the companies to search"
            ) List<Long> companiesList,
            @RequestParam(required = false, name = "channels")
            @Parameter(
                    name = "channels",
                    description = "list of the channels to search"
            ) List<Long> channelsList,
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            @Parameter(
                    name = DATE_TIME_CURRENT,
                    description = "End date to consult the records."
            ) LocalDateTime dateTimeCurrent,
            @RequestParam(required = false, name = "paymentMethods")
            @Parameter(
                    name = "paymentMethods",
                    description = "list of the paymentMethods to search"
            ) List<Long> paymentMethodsList,
            @RequestParam(required = false, name = "stores")
            @Parameter(
                    name = "stores",
                    description = "list of the stores to search"
            ) List<Long> storesList,
            @RequestParam(name = "timezones")
            @Parameter(
                    name = "timezones",
                    description = "list of the timezones per company"
            ) List<String> timezones

    ) throws AsyncOrderBcException;
}
