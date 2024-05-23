package com.robinfood.app.controllers.pos;

import com.robinfood.core.dtos.apiresponsebuilder.ApiResponseDTO;
import com.robinfood.core.dtos.posresolution.GetPosResolutionsDTO;
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

public interface IPosController {

    /**
     * Get order list ready resolutions
     *
     * @param posId          identifier the pos
     * @param timeZone       Geographic region
     * @param localDateStart Start date to consult the records
     * @param localDateEnd   End date to consult the records
     * @return records with detail
     */
    ResponseEntity<ApiResponseDTO<GetPosResolutionsDTO>> invoke(
            @PathVariable @Min(1) Long posId,
            @RequestHeader
            @Length(min = 1, max = 60)
            @Parameter(
                    name = "timeZone",
                    description = "Geographic region in which the same time is used."
            ) String timeZone,
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            @Parameter(
                    name = LOCAL_DATE_START,
                    description = "Start date to consult the records."
            ) LocalDate localDateStart,
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            @Parameter(
                    name = LOCAL_DATE_END,
                    description = "End date to consult the records."
            ) LocalDate localDateEnd
    );

    /**
     * Get resolutions filtering by store
     *
     * @param storeId        identifier the store
     * @param timeZone       Geographic region
     * @param localDateStart Start date to consult the records
     * @param localDateEnd   End date to consult the records
     * @return records with detail
     */
    ResponseEntity<ApiResponseDTO<List<GetPosResolutionsDTO>>> invoke(
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            @Parameter(
                    name = LOCAL_DATE_START,
                    description = "Start date to consult the records."
            ) LocalDate localDateStart,
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            @Parameter(
                    name = LOCAL_DATE_END,
                    description = "End date to consult the records."
            ) LocalDate localDateEnd,
            @PathVariable @Min(1) Long storeId,
            @RequestHeader
            @Length(min = 1, max = 60)
            @Parameter(
                    name = "timeZone",
                    description = "Geographic region in which the same time is used."
            ) String timeZone
    );

}
