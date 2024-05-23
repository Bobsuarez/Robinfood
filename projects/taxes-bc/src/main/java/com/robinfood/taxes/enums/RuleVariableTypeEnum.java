package com.robinfood.taxes.enums;

import java.util.HashMap;
import java.util.Map;
import javax.validation.constraints.NotNull;

public enum RuleVariableTypeEnum {

    NUMBER("number"),
    STRING("string"),
    ARRAY_STRING("arrayString"),
    ARRAY_NUMBER("arrayNumber"),
    BOOLEAN("boolean");

    private final String type;

    private static final Map<String, RuleVariableTypeEnum> ruleVariableMap = new HashMap<>();

    static {
        ruleVariableMap.put("number", NUMBER);
        ruleVariableMap.put("string", STRING);
        ruleVariableMap.put("arrayString", ARRAY_STRING);
        ruleVariableMap.put("arrayNumber", ARRAY_NUMBER);
        ruleVariableMap.put("boolean", BOOLEAN);
    }


    RuleVariableTypeEnum(@NotNull final String type) {
        this.type = type;
    }

    public static RuleVariableTypeEnum toEnum(String ruleVariable) {
        return ruleVariableMap.get(ruleVariable);
    }

}
