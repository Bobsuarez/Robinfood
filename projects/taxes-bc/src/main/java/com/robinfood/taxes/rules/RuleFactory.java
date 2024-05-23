package com.robinfood.taxes.rules;

import com.robinfood.taxes.domain.RuleVariable;
import com.robinfood.taxes.enums.RuleTypeEnum;
import org.springframework.stereotype.Component;

@Component
public class RuleFactory {

    public RuleFactory() {
        //Constructor RuleFactory
    }

    public AbstractRule createRule(String type, RuleVariable left, RuleVariable right) {
        AbstractRule rule = null;
        RuleTypeEnum typeEnum = RuleTypeEnum.toEnum(type);
        switch (typeEnum) {
            case EQUAL:
                rule = new Equal(left, right);
                break;
            case GREATER:
                rule = new Greater(left, right);
                break;
            case IN:
                rule = new In(left, right);
                break;
            case LESS:
                rule = new Less(left, right);
                break;
            case NOT_EQUAL:
                rule = new NotEqual(left, right);
                break;
            case NOT_IN:
                rule = new NotIn(left, right);
                break;
            default:
                throw new IllegalArgumentException("Invalid type");
        }
        return rule;
    }

}
