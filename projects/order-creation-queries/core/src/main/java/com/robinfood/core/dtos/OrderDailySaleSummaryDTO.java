package com.robinfood.core.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDailySaleSummaryDTO {

    @Schema(example = "24000.00")
    private Double salesSummary;

    @Schema(example = "10")
    private Integer ordersNumber;
}
