package com.robinfood.taxes.rules;

import com.robinfood.taxes.annotations.BasicLog;
import com.robinfood.taxes.domain.RuleVariable;
import com.robinfood.taxes.exceptions.BusinessRuleException;
import com.robinfood.taxes.utils.VariableUtil;

public class NotEqual extends AbstractRule {

    public NotEqual(RuleVariable left, RuleVariable right) {
        super(left, right);
    }

    @BasicLog
    @Override
    public Boolean evaluate() throws BusinessRuleException {

        if (!getLeft().getType().equals(getRight().getType())) {
            throw new IllegalArgumentException(
                "Required the same type on the left and right to validate the equal rule");
        }
        
        Object leftCast = VariableUtil
            .castToType(getLeft().getType(), getLeft().getValue());

        Object rightCast = VariableUtil
            .castToType(getRight().getType(), getRight().getValue());

        return !leftCast.equals(rightCast);
    }
}
