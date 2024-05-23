package com.robinfood.taxes.domain;

import com.robinfood.taxes.models.FormulaVariable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MappedVariable {
    private Long expressionId;
    private FormulaVariable variable;

}
