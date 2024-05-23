package com.robinfood.app.controllers.reports.salesbysegment;

import com.robinfood.core.dtos.ApiResponseDTO;
import com.robinfood.core.dtos.report.salebysegment.response.SaleBySegmentResponseDTO;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;

public interface ISalesBySegmentController {

    /**
     * Get the information from the sales report by filters - channels - brands - stores - paymentMethods
     *
     * @param companiesList      list of the companies to search
     * @param channelsList       list of the channels to search
     * @param dateTimeCurrent    End date to consult the records
     * @param paymentMethodsList list of the paymentMethods to search
     * @param storesList         list of the stores to search
     * @return SaleBySegmentResponseDTO
     */
    ResponseEntity<ApiResponseDTO<SaleBySegmentResponseDTO>> invoke(
            @RequestParam(required = false, name = "brands")
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
                    name = "dateTimeCurrent",
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
            ) List<Long> storesList
    );
}
