package com.robinfood.app.controllers.ordercancellationform;

import com.robinfood.core.dtos.apiresponsebuilder.ApiResponseDTO;
import com.robinfood.core.dtos.report.ordercancellation.GetOrderCancellationResponseDTO;
import com.robinfood.core.dtos.response.EntityDTO;
import io.swagger.v3.oas.annotations.Parameter;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import java.time.LocalDate;
import java.util.List;

import static com.robinfood.core.constants.GlobalConstants.DEFAULT_CURRENT_PAGE;
import static com.robinfood.core.constants.GlobalConstants.DEFAULT_PER_PAGE;
import static com.robinfood.core.constants.GlobalConstants.LOCAL_DATE_END;
import static com.robinfood.core.constants.GlobalConstants.LOCAL_DATE_START;

/**
 * Controller of the information of the order cancellation report through the filters
 */
public interface IOrderCancellationController {

    /**
     * Get the information from the report by filters - channel - brand - store - paymentMethod
     */
    ResponseEntity<ApiResponseDTO<EntityDTO<GetOrderCancellationResponseDTO>>> getResponseOrderCancellation(

            @RequestParam(required = false, name = "brandIds")
            @Parameter(
                    name = "brandIds",
                    description = "id of the brands to search"
            ) List<Long> brandsIds,

            @RequestParam(required = false, name = "companyId")
            @Parameter(
                    name = "companyId",
                    description = "id of the company to search"
            ) Long companyId,

            @RequestParam(required = false, defaultValue = DEFAULT_CURRENT_PAGE)
            @Positive()
            @Min(1)
            @Parameter(
                    name = "currentPage",
                    description = "Current Page of Records."
            ) Integer currentPage,

            @RequestParam(required = false, name = "idCustomFilter")
            @Parameter(
                    name = "idCustomFilter",
                    description = "code status to search"
            ) Long idCustomFilter,

            @RequestParam(required = false, name = LOCAL_DATE_START)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            @Parameter(
                    name = LOCAL_DATE_START,
                    description = "date start to consult the records."
            ) LocalDate localDateStart,

            @RequestParam(required = false, name = LOCAL_DATE_END)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            @Parameter(
                    name = LOCAL_DATE_END,
                    description = "date final to consult the records."
            ) LocalDate localDateEnd,

            @RequestParam(required = false, name = "originIds")
            @Parameter(
                    name = "originIds",
                    description = "id of the channels to search"
            ) List<Long> originIds,

            @RequestParam(required = false, name = "paymentMethodIds")
            @Parameter(
                    name = "paymentMethodIds",
                    description = "id of the paymentMethod to search"
            ) List<Long> paymentMethodIds,

            @RequestParam(required = false, defaultValue = DEFAULT_PER_PAGE)
            @Min(1)
            @Parameter(
                    name = "perPage",
                    description = "Records by pages."
            ) Integer perPage,

            @RequestParam(required = false, name = "statusCode")
            @Parameter(
                    name = "statusCode",
                    description = "code status to search"
            ) String statusCode,

            @RequestParam(required = false, name = "storeIds")
            @Parameter(
                    name = "storeIds",
                    description = "id of the stores to search"
            ) List<Long> storesIds,

            @RequestHeader
            @Length(min = 1, max = 60)
            @Parameter(
                    name = "timeZone",
                    description = "Geographic region in which the same time is used."
            ) String timeZone,

            @RequestParam(required = false, name = "valueCustomFilter")
            @Parameter(
                    name = "valueCustomFilter",
                    description = "code status to search"
            ) String valueCustomFilter
    );
}
