package com.robinfood.taxes.models;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.robinfood.taxes.serializers.tax.ExpressionFormulaVariableSerializer;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "expression_formula_variables")
@JsonSerialize(using = ExpressionFormulaVariableSerializer.class)
public class ExpressionFormulaVariable extends AbstractBaseEntity {

    @ManyToOne
    @JoinColumn(name = "expression_id")
    private Expression expression;

    @ManyToOne
    @JoinColumn(name = "formula_variable_id")
    private FormulaVariable formulaVariable;

    public ExpressionFormulaVariable(Long id) {
        super(id);
    }
}
