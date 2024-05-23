package com.robinfood.app.controllers.reports.sales;

import com.robinfood.core.dtos.apiresponsebuilder.ApiResponseDTO;
import com.robinfood.core.dtos.report.sale.SaleReportResponseDTO;
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

public interface ISalesController {

    /**
     * Get the information from the sales report by orders
     *
     * @param companies       list of companies to consult
     * @param dateTimeCurrent consultation date
     * @return company list with order calculation
     */
    @Operation(summary = "report of sales by orders")
    @ApiResponse(
            responseCode = "200",
            description = "report of sales by orders",
            content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = SaleReportResponseDTO.class))}
    )
    ResponseEntity<ApiResponseDTO<SaleReportResponseDTO>> invoke(
            @RequestParam
            @Parameter(
                    name = "companies",
                    description = "list of the companies to search"
            ) List<Integer> companies,
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            @Parameter(
                    name = DATE_TIME_CURRENT,
                    description = "End date to consult the records."
            ) LocalDateTime dateTimeCurrent,
            @RequestParam
            @Parameter(
                    name = "timezones",
                    description = "list of the timezones per company"
            ) List<String> timezones
    ) throws AsyncOrderBcException;

}
