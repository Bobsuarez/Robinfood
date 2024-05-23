package com.robinfood.app.controllers.reports.salescompanies;

import com.robinfood.core.dtos.ApiResponseDTO;
import com.robinfood.core.dtos.reportordersalescompanies.ResponseOrderSalesDTO;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;

import static com.robinfood.core.constants.GlobalConstants.FORMAT_DATE;

/**
 * Controller that exposes final point in relation to list order sales by companies.
 */
public interface IReportOrderSalesCompaniesController {

    /**
     * Orders sales by companies to order-bc
     *
     * @return Return orders sales by companies
     */
    ResponseEntity<ApiResponseDTO<ResponseOrderSalesDTO>> invoke(
            @RequestParam(
                    value = "companies",
                    required = true
            ) List<Integer> companies,
            @RequestParam(
                    value = "dateTimeCurrent",
                    required = true
            )
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime cateTimeCurrent
    );
}
