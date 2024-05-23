package com.robinfood.core.utilities;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.robinfood.core.utilities.DateUtil.FORMAT;

public class DateUtilTest {

    @Test
    void test_formatDate_OK(){

        DateTimeFormatter f = DateTimeFormatter.ofPattern(FORMAT);

        LocalDateTime localDateTime = LocalDateTime.now();

        Assertions.assertThat(DateUtil
                .formatDate(localDateTime))
                .isEqualTo(f.format(localDateTime));
    }

    @Test
    void test_lastOfWeekSameHour_OK(){
        LocalDateTime localDateTime = LocalDateTime.now();

        Assertions.assertThat(DateUtil
                        .lastOfWeekSameHour(localDateTime))
                .isBefore(LocalDateTime.now());
    }

    @Test
    public void test_ConstructorIsPrivate_OK() throws NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        Constructor<DateUtil> c = DateUtil.class.getDeclaredConstructor();
        c.setAccessible(true);
        DateUtil u = c.newInstance();
    }
}
