package com.robinfood.taxes.dto.v1.create;


import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "CreateExpressionFormulaVariableDTO")
public class CreateExpressionFormulaVariableDTO {

    @NotNull
    @Min(value = 1, message = "Expression ID must be greater than zero.")
    @JsonProperty("expression_id")
    @Schema(example = "1")
    private Long expressionId;

    @NotNull
    @Min(value = 1, message = "Formula Variable ID must be greater than zero.")
    @JsonProperty("formula_variable_id")
    @Schema(example = "1")
    private Long formulaVariableId;
}
