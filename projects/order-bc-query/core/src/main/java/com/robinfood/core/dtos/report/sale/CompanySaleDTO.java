package com.robinfood.core.dtos.report.sale;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Builder
@Getter
@Setter
public class CompanySaleDTO {

    @NotBlank
    @Schema(example = "1")
    public Long id;

    @JsonProperty("orders")
    public OrdersSalesDTO ordersSales;

}
