package com.robinfood.taxes.rules;

import com.robinfood.taxes.annotations.BasicLog;
import com.robinfood.taxes.domain.RuleVariable;
import com.robinfood.taxes.exceptions.BusinessRuleException;

public class In extends AbstractRule {

    public In(RuleVariable left, RuleVariable right) {
        super(left, right);
    }

    @BasicLog
    @Override
    public Boolean evaluate() throws BusinessRuleException {
        return EvaluateInNotInRule.evaluate(getLeft(), getRight());
    }
}
