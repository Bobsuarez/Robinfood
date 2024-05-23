package com.robinfood.taxes.dto.v1;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemDTO {

    @NotNull
    @JsonProperty
    @Schema(example = "1")
    private Long productTypeId;

    @NotNull
    @JsonProperty
    @Schema(example = "1")
    private Long articleId;

    @NotNull
    @JsonProperty
    @Schema(example = "8900.00")
    @Digits(integer=10, fraction=4)
    private BigDecimal price;

    @NotNull
    @JsonProperty
    @Schema(example = "500.00")
    @Digits(integer=10, fraction=4)
    private BigDecimal discount;

    @NotNull
    @JsonProperty
    @Schema(example = "2")
    private Integer quantity;
}
