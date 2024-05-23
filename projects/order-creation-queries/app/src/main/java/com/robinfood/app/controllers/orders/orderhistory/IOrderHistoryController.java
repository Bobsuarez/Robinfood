package com.robinfood.app.controllers.orders.orderhistory;

import com.robinfood.core.dtos.ApiResponseDTO;
import com.robinfood.core.dtos.EntityDTO;
import com.robinfood.core.dtos.orderhistory.response.OrderHistoryItemDTO;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import java.time.LocalDate;
import java.util.List;

import static com.robinfood.core.constants.GlobalConstants.DEFAULT_CURRENT_PAGE;
import static com.robinfood.core.constants.GlobalConstants.DEFAULT_PER_PAGE;

@Tag(
        name = "Get Order History By Store",
        description = "Allows you to obtain order history information"
)
public interface IOrderHistoryController {

    /**
     * Get Order History By Store
     *
     * @param channelsId Filter by Id from the channels
     * @param currentPage Current Page of Records.
     * @param isDelivery Orders Delivery filter
     * @param localDateEnd End date to consult the records.
     * @param localDateStart Start date to consult the records.
     * @param perPage Records by pages.
     * @param searchText Filter text for records by (orderInvoiceNumber, Brand and User Name).
     * @param storeId Number to filter by Id from the store.
     * @param timeZone Geographic region in which the same time is used.
     * @return Order history detail
     */
    @Operation(summary = "Get Order History By Store")
    @ApiResponse(
            responseCode = "200", description = "Orders information.",
            content = {
                    @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ApiResponseDTO.class)
                    )
            }
    )
    ResponseEntity<ApiResponseDTO<EntityDTO<OrderHistoryItemDTO>>> getOrderHistoryByStore(
            @RequestParam(
                    name = "channelsId",
                    required = false
            ) List<Long> channelsId,
            @RequestParam(required = false, defaultValue = DEFAULT_CURRENT_PAGE)
                @Positive()
                @Min(1)
                @Parameter(
                        name = "currentPage",
                        description = "Current Page of Records."
                ) Integer currentPage,
            @RequestParam(
                    name = "isDelivery",
                    required = false,
                    defaultValue = "false"
            ) Boolean isDelivery,
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
            @RequestParam(required = false, defaultValue = DEFAULT_PER_PAGE) @Min(1)
                @Parameter(
                        name = "perPage",
                        description = "Records by pages."
                ) Integer perPage,
            @RequestParam(required = false)
                @Length(min = 3, max = 60)
                @Parameter(
                        name = "searchText",
                        description = "Search Text for records by (orderNumber and orderInvoiceNumber)."
                ) String searchText,
            @PathVariable(value = "storeId", required = true)
                @Min(1) Long storeId,
            @RequestHeader
                @Length(min = 1, max = 60)
                @Parameter(
                        name = "timeZone",
                        description = "Geographic region in which the same time is used."
                ) String timeZone
    );
}
