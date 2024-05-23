package com.robinfood.taxes.dto.v1;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaxSummaryDTO {

    @JsonProperty
    @Schema(example = "1")
    private Long id;

    @JsonProperty
    @Schema(example = "Iva")
    private String name;

}
