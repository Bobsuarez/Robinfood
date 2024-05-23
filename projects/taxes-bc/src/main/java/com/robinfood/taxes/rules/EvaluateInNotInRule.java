package com.robinfood.taxes.rules;

import com.robinfood.taxes.annotations.BasicLog;
import com.robinfood.taxes.domain.RuleVariable;
import com.robinfood.taxes.exceptions.BusinessRuleException;
import com.robinfood.taxes.utils.VariableUtil;

import java.math.BigDecimal;

public final class EvaluateInNotInRule {

    private EvaluateInNotInRule() {}

    @BasicLog
    public static Boolean evaluate(RuleVariable left, RuleVariable right) throws BusinessRuleException {

        Object leftCast = VariableUtil.castToType(left.getType(), left.getValue());

        Object rightCast = VariableUtil.castToType(right.getType(), right.getValue());

        if (String[].class.equals(rightCast.getClass())) {
            String[] rightString = (String[]) rightCast;
            for (String string : rightString) {
                if (string.equals(leftCast)) {
                    return true;
                }
            }
        } else if (BigDecimal[].class.equals(rightCast.getClass())) {
            BigDecimal[] rightValues = (BigDecimal[]) rightCast;
            for (BigDecimal rightValue : rightValues) {
                if (rightValue.equals(leftCast)) {
                    return true;
                }
            }
        } else {
            throw new IllegalArgumentException("Invalid data type for validation EvaluateInNotInRule");
        }
        return false;
    }

}
