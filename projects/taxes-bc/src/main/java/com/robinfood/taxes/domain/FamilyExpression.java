package com.robinfood.taxes.domain;

import com.robinfood.taxes.models.Expression;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FamilyExpression {
    private Long familyId;
    private Expression expression;
}
