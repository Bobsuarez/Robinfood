package com.robinfood.taxes.dto.v1;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaxableItemDTO {

    @JsonProperty
    @Schema(example = "1")
    private Long productTypeId;

    @JsonProperty
    @Schema(example = "1")
    private Long articleId;

    @JsonProperty
    @Schema(example = "8900.00")
    private BigDecimal price;

    @JsonProperty
    @Schema(example = "500.00")
    private BigDecimal discount;

    @JsonProperty
    @Schema(example = "1")
    private Integer quantity;

    @JsonProperty
    @Schema(example = "789.0")
    private BigDecimal totalTax;

    @JsonProperty
    @Schema(example = "1")
    private List<TaxDetailDTO> taxes;
}
