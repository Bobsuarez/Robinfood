package com.robinfood.taxes.utils;

import com.robinfood.taxes.enums.RuleVariableTypeEnum;
import com.robinfood.taxes.constants.CalculatorConstants;
import com.robinfood.taxes.exceptions.BusinessRuleException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;

public final class VariableUtil {

    private VariableUtil() {

    }

    public static Object castToType(String type, String value) throws BusinessRuleException {
        Object result;
        RuleVariableTypeEnum typeEnum = RuleVariableTypeEnum.toEnum(type);
        switch (typeEnum) {
            case NUMBER:
                result = parseNumber(value);
                break;
            case BOOLEAN:
                result = Boolean.getBoolean(value);
                break;
            case STRING:
                result = String.format(value);
                break;
            case ARRAY_STRING:
                result = parseArrayString(value);
                break;
            case ARRAY_NUMBER:
                result = parseArrayNumber(value);
                break;
            default:
                throw new IllegalArgumentException("Unsupported variable type for in operation");
        }
        return result;
    }

    private static BigDecimal parseNumber(String value) throws BusinessRuleException {
        try {
            BigDecimal number = new BigDecimal(value);
            number = number.setScale(CalculatorConstants.DECIMAL_PLACES, RoundingMode.HALF_EVEN);
            return number;
        } catch (NumberFormatException e){
            throw new BusinessRuleException("Value not is valid for type number");
        }

    }

    private static BigDecimal[] parseArrayNumber(String values) throws BusinessRuleException {
        List<String> listValues = Arrays.asList(values.split(","));
        BigDecimal[] newArray = new BigDecimal[listValues.size()];
        int i;
        for (i = 0; i < listValues.size(); i++) {
            newArray[i] = parseNumber(listValues.get(i));
        }
        return newArray;
    }

    private static String[] parseArrayString(String values) {
        List<String> listValue = Arrays.asList(values.split(","));
        String[] stringArray = new String[listValue.size()];
        int i;
        for (i = 0; i < listValue.size(); i++) {
            stringArray[i] = String.format(listValue.get(i));
        }
        return stringArray;
    }
}
