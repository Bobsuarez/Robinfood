package com.robinfood.taxes.dto.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.robinfood.taxes.dto.v1.AbstractBaseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExpressionFormulaVariableDTO extends AbstractBaseDTO {

    @JsonProperty(value = "formula_variable_id")
    private Long formulaVariableId;

    @JsonProperty(value = "expression_id")
    private Long expressionId;
}
