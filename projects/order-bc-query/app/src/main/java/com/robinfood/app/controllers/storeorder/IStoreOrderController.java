package com.robinfood.app.controllers.storeorder;

import com.robinfood.core.dtos.apiresponsebuilder.ApiResponseDTO;
import com.robinfood.core.dtos.storeorder.StoreOrderDTO;
import io.swagger.v3.oas.annotations.Parameter;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.Min;
import java.time.LocalDate;
import java.util.List;

import static com.robinfood.core.constants.GlobalConstants.LOCAL_DATE_END;
import static com.robinfood.core.constants.GlobalConstants.LOCAL_DATE_START;

/**
 * Get Store Orders Controller
 */
public interface IStoreOrderController {

    /**
     * Get orders by storeId
     *
     * @param localDateEnd End date to consult the records
     * @param localDateStart Start date to consult the records
     * @param storeId Identifier the store
     * @param timeZone Geographic region
     * @return List orders by storeId
     */
    ResponseEntity<ApiResponseDTO<List<StoreOrderDTO>>> invoke(
            @RequestParam
                @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                @Parameter(
                        name = LOCAL_DATE_END,
                        description = "End date to consult the records."
                ) LocalDate localDateEnd,
            @RequestParam
                @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                @Parameter(
                        name = LOCAL_DATE_START,
                        description = "Start date to consult the records."
                ) LocalDate localDateStart,
            @PathVariable @Min(1) Long storeId,
            @RequestHeader
                @Length(min = 1, max = 60)
                @Parameter(
                        name = "timeZone",
                        description = "Geographic region in which the same time is used."
                ) String timeZone
    );
}
