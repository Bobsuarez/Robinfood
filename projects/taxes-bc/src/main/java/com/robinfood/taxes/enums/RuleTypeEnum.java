package com.robinfood.taxes.enums;

import java.util.HashMap;
import java.util.Map;
import javax.validation.constraints.NotNull;

public enum RuleTypeEnum {

    EQUAL("Equal"),
    GREATER("Greater"),
    IN("In"),
    LESS("Less"),
    NOT_EQUAL("NotEqual"),
    NOT_IN("NotIn");

    private final String rule;

    private static final Map<String, RuleTypeEnum> ruleMap = new HashMap<>();

    static {
        ruleMap.put("Equal", EQUAL);
        ruleMap.put("Greater", GREATER);
        ruleMap.put("In", IN);
        ruleMap.put("Less", LESS);
        ruleMap.put("NotEqual", NOT_EQUAL);
        ruleMap.put("NotIn", NOT_IN);
    }

    RuleTypeEnum(@NotNull final String rule) {
        this.rule = rule;
    }

    public static RuleTypeEnum toEnum(String rule) {
        return ruleMap.get(rule);
    }

}
