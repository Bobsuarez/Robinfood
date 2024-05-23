package com.robinfood.core.dtos.report.salebysegment;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Builder
@Getter
@Setter
public class DataGenericSegmentDTO {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("counter")
    public Long counter;

    @JsonProperty("value")
    @Schema(example = "150000.0")
    public BigDecimal value;

}
