package com.robinfood.taxes.rules;

import com.robinfood.taxes.annotations.BasicLog;
import com.robinfood.taxes.domain.RuleVariable;
import com.robinfood.taxes.exceptions.BusinessRuleException;
import com.robinfood.taxes.utils.VariableUtil;
import java.math.BigDecimal;

public class Less extends AbstractRule {


    public Less(RuleVariable left, RuleVariable right) {
        super(left, right);
    }

    @BasicLog
    @Override
    public Boolean evaluate() throws BusinessRuleException {

        Object leftCast = VariableUtil
            .castToType(getLeft().getType(), getLeft().getValue());

        Object rightCast = VariableUtil
            .castToType(getRight().getType(), getRight().getValue());

        if (!leftCast.getClass().equals(BigDecimal.class) || !rightCast.getClass()
            .equals(BigDecimal.class)) {
            throw new IllegalArgumentException("Invalid data type for validation Greater");
        }

        return new BigDecimal(leftCast.toString())
            .compareTo(new BigDecimal(rightCast.toString())) < 0;
    }
}
