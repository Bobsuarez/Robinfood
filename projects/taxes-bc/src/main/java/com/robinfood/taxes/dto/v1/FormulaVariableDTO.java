package com.robinfood.taxes.dto.v1;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotNull;

public class FormulaVariableDTO {

    @NotNull
    @JsonProperty
    @Schema(example = "Name")
    private String name;

    @NotNull
    @JsonProperty
    @Schema(example = "Inner")
    private String type;

    @NotNull
    @JsonProperty
    @Schema(example = "Description")
    private String description;

    @NotNull
    @JsonProperty
    @Schema(example = "Value")
    private String value;
}
