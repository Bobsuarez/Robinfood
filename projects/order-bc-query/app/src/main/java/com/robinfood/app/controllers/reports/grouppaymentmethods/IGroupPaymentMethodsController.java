package com.robinfood.app.controllers.reports.grouppaymentmethods;

import com.robinfood.core.dtos.apiresponsebuilder.ApiResponseDTO;
import com.robinfood.core.dtos.report.salebystore.GetSaleByStoreResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.Min;
import java.time.LocalDateTime;
import java.util.List;

import static com.robinfood.core.constants.GlobalConstants.DATE_TIME_CURRENT;
import static com.robinfood.core.constants.GlobalConstants.MIN_INTEGER_VALUE;

public interface IGroupPaymentMethodsController {

    /**
     * get sales information by store and grouped by payment methods
     *
     * @param storeId         identifier the store
     * @param dateTimeCurrent End date to consult the records
     * @return GetSaleByStoreResponseDTO by filter
     */
    @Operation(summary = "sales report by store and date to filter")
    ResponseEntity<ApiResponseDTO<GetSaleByStoreResponseDTO>> groupPaymentMethodsByStore(
            @PathVariable @Min(MIN_INTEGER_VALUE) List<Long> storeId,
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            @Parameter(
                    name = DATE_TIME_CURRENT,
                    description = "End date to consult the records."
            ) LocalDateTime dateTimeCurrent,
            @RequestParam(name = "timezone")
            @Parameter(
                    name = "timezone",
                    description = "timezone per store"
            ) String timezone
    );

}
