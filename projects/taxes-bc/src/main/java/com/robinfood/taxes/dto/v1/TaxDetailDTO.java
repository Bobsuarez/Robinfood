package com.robinfood.taxes.dto.v1;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaxDetailDTO implements Serializable {

    private static final long serialVersionUID = -4819362272282165768L;

    @JsonProperty
    private Long familyId;

    @JsonProperty
    @Schema(example = "1")
    private Long id;

    @JsonProperty
    @Schema(example = "Iva 8%")
    private String name;

    @JsonProperty
    @Schema(example = "8")
    private BigDecimal rate;

    @JsonProperty
    @Schema(example = "299.0")
    private BigDecimal value;

    @JsonProperty
    @Schema(example = "ASB123")
    private String sapId;

    @JsonProperty
    private Long taxId;

    @JsonProperty
    @Schema(example = "1")
    private Long taxTypeId;

}
