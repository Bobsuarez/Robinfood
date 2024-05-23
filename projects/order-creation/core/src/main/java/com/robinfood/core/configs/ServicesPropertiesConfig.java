package com.robinfood.core.configs;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static com.robinfood.core.constants.GlobalConstants.NUMBER_DECIMAL_PLACES;


@Data
@RequiredArgsConstructor
public class ServicesPropertiesConfig {

    public BigDecimal getPercentageCalculation(BigDecimal value) {
        final BigDecimal valueToDivide = BigDecimal.valueOf(100);
        return value.divide(valueToDivide, NUMBER_DECIMAL_PLACES, RoundingMode.FLOOR).add(BigDecimal.ONE);
    }

}
