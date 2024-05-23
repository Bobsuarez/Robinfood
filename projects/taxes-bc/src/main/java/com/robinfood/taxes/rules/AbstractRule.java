package com.robinfood.taxes.rules;

import com.robinfood.taxes.domain.RuleVariable;
import com.robinfood.taxes.exceptions.BusinessRuleException;

public abstract class AbstractRule {

    private final RuleVariable left;
    private final RuleVariable right;

    AbstractRule(RuleVariable left, RuleVariable right) {
        this.left = left;
        this.right = right;
    }

    public RuleVariable getLeft() {
        return left;
    }

    public RuleVariable getRight() {
        return right;
    }

    public abstract Boolean evaluate() throws BusinessRuleException;
}
