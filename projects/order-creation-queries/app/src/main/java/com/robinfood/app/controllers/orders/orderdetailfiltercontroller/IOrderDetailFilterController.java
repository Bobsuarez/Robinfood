package com.robinfood.app.controllers.orders.orderdetailfiltercontroller;

import com.robinfood.core.dtos.ApiResponseDTO;
import com.robinfood.core.dtos.EntityDTO;
import com.robinfood.core.dtos.OrderDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.Min;
import java.time.LocalDate;

/**
 * Controller that exposes final point in relation to the orders filters.
 */
@Tag(
        name = "Order Detail Filter",
        description = "It allows obtaining order information by different fields (store, orderNumber)"
)
public interface IOrderDetailFilterController {

    /**
     * Order detail filter order information by different fields
     *
     * @param currentPage Current Page of Records.
     * @param filterText Filter text for records by (orderNumber and orderInvoiceNumber).
     * @param localDateEnd End date to consult the records.
     * @param localDateStart Start date to consult the records.
     * @param perPage Records by pages.
     * @param storeId Number to filter by Id from the store.
     * @param timeZone Geographic region in which the same time is used.
     * @return Return order detail
     */
    @Operation(summary = "Get orders info by different fields (store, orderNumber, etc.)")
    @ApiResponse(
            responseCode = "200", description = "Orders information.",
            content = {
                    @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ApiResponseDTO.class)
                    )
            }
    )
    ResponseEntity<ApiResponseDTO<EntityDTO<OrderDTO>>> invoke(
            @RequestParam(required = false)
                @Min(1)
                @Parameter(
                        name = "currentPage",
                        description = "Current Page of Records."
                ) Integer currentPage,
            @RequestParam(required = false)
                @Length(min = 3, max = 60)
                @Parameter(
                        name = "filterText",
                        description = "Filter text for records by (orderNumber and orderInvoiceNumber)."
                ) String filterText,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                @Parameter(
                        name = "localDateEnd",
                        description = "End date to consult the records."
                ) LocalDate localDateEnd,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                @Parameter(
                        name = "localDateStart",
                        description = "Start date to consult the records."
                ) LocalDate localDateStart,
            @RequestParam(required = false) @Min(1)
                @Parameter(
                        name = "perPage",
                        description = "Records by pages."
                ) Integer perPage,
            @RequestParam(required = false)
                @Min(1)
                @Parameter(name = "storeId", description = "Number to filter by Id from the store.") Long storeId,
            @RequestHeader
                @Length(min = 1, max = 60)
                @Parameter(
                        name = "timeZone",
                        description = "Geographic region in which the same time is used."
                ) String timeZone
    );
}
