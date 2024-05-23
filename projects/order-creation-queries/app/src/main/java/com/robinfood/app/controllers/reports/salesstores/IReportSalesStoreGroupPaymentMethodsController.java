package com.robinfood.app.controllers.reports.salesstores;

import com.robinfood.core.dtos.ApiResponseDTO;
import com.robinfood.core.dtos.reportsalesstore.ReportSalesStoresDTO;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.Min;
import java.time.LocalDateTime;

public interface IReportSalesStoreGroupPaymentMethodsController {

    /**
     * Get sales group by payment methods filtering by store
     *
     * @param storeId        identifier the store
     * @param dateTimeCurrent date to consult the records
     * @return records with detail sales store group by payment methods
     */
    ResponseEntity<ApiResponseDTO<ReportSalesStoresDTO>> invoke(
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime dateTimeCurrent,
            @PathVariable @Min(1) Long storeId
    );
}
