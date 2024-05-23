package com.robinfood.core.utilities;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import static com.robinfood.core.constants.GlobalConstants.DEFAULT_INTEGER_VALUE;
import static com.robinfood.core.constants.GlobalConstants.LONG_VALUE_ONE_HUNDRED;
import static com.robinfood.core.constants.GlobalConstants.NUMBER_OF_DECIMALS;

public final class CalculatorPercentageUtil {

    private CalculatorPercentageUtil() {
        // this constructor is empty because it is a configuration class
    }

    public static BigDecimal getPercentageDifference(
            @NotNull BigDecimal salesCurrent,
            @NotNull BigDecimal salesAWeekBefore
    ) {

        if (salesAWeekBefore.compareTo(BigDecimal.ZERO) == DEFAULT_INTEGER_VALUE &&
                salesCurrent.compareTo(BigDecimal.ZERO) == DEFAULT_INTEGER_VALUE
        ) {
            return BigDecimal.valueOf(0.0);
        }

        if (salesAWeekBefore.compareTo(BigDecimal.ZERO) == DEFAULT_INTEGER_VALUE) {
            return BigDecimal.valueOf(LONG_VALUE_ONE_HUNDRED);
        }

        return salesCurrent.divide(salesAWeekBefore, NUMBER_OF_DECIMALS, RoundingMode.DOWN)
                .multiply(new BigDecimal(LONG_VALUE_ONE_HUNDRED),
                        new MathContext(NUMBER_OF_DECIMALS, RoundingMode.HALF_DOWN))
                .subtract(new BigDecimal(LONG_VALUE_ONE_HUNDRED));
    }
}
