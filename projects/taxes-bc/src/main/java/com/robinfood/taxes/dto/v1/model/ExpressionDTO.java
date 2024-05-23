package com.robinfood.taxes.dto.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.robinfood.taxes.dto.v1.AbstractBaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "ExpressionDTO")
public class ExpressionDTO extends AbstractBaseDTO {

    private static final long serialVersionUID = 3571764400356291513L;

    @JsonProperty
    @Schema(example = "(price - discount)")
    private String value;

    @JsonProperty
    @Schema(example = "Price minus discount.")
    private String description;

    @JsonProperty(value = "status")
    @Schema(example = "1")
    private Integer status;

}
