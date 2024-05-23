package com.robinfood.taxes.dto.v1;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ArticleTaxDetailDTO {

    @JsonProperty
    @Schema(example = "1")
    private Long articleTypeId;

    @JsonProperty
    @Schema(example = "1")
    private Long articleId;

    @JsonProperty
    @Schema(example = "1")
    private Long familyTaxTypeId;

    @JsonProperty
    @Schema(example = "1")
    private Long dicTaxId;

    @JsonProperty
    @Schema(example = "1")
    private Double taxValue;
}
