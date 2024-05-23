package com.robinfood.taxes.enums;

import java.util.HashMap;
import java.util.Map;
import javax.validation.constraints.NotNull;

public enum FormulaVariableTypeEnum {

    NUMBER("number"),
    INNER("inner"),
    PATH("path"),
    EXPRESSION("expression");

    private final String type;

    private static final Map<String, FormulaVariableTypeEnum> formulaVariableMap = new HashMap<>();

    static {
        formulaVariableMap.put("number", NUMBER);
        formulaVariableMap.put("inner", INNER);
        formulaVariableMap.put("path", PATH);
        formulaVariableMap.put("expression", EXPRESSION);
    }


    FormulaVariableTypeEnum(@NotNull final String type) {
        this.type = type;
    }

    public static FormulaVariableTypeEnum toEnum(String formulaVariable) {
        return formulaVariableMap.get(formulaVariable);
    }

}
