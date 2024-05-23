package com.robinfood.core.utilities;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

public class CalculatorPercentageUtilTest {

    @Test
    void test_getPercentageDifference_OK() {

        BigDecimal salesCurrent = new BigDecimal(100L);
        BigDecimal salesAWeekBefore = new BigDecimal(100L);

        Assertions.assertThat(CalculatorPercentageUtil
                        .getPercentageDifference(salesCurrent,salesAWeekBefore))
                .isEqualTo("0.0");
    }

    @Test
    void test_getPercentageDifference_ERROR() {
        BigDecimal salesCurrent = new BigDecimal(100L);
        BigDecimal salesAWeekBefore = BigDecimal.ZERO;

        Assertions.assertThat(CalculatorPercentageUtil
                        .getPercentageDifference(salesCurrent,salesAWeekBefore))
                .isEqualTo("100");
    }

    @Test
    public void test_ConstructorIsPrivate() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, InvocationTargetException {
        Constructor<CalculatorPercentageUtil> c = CalculatorPercentageUtil.class.getDeclaredConstructor();
        c.setAccessible(true);
        CalculatorPercentageUtil u = c.newInstance();
    }
}
