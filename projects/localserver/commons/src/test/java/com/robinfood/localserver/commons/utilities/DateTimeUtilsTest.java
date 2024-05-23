package com.robinfood.localserver.commons.utilities;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.Assert.assertNotNull;

@ExtendWith(MockitoExtension.class)
class DateTimeUtilsTest {

    @Test
    void test_When_DateTimeUtils_getCurrentDateTimeInTimeZone_OK() {

        String response = DateTimeUtils.getCurrentDateTimeInTimeZone("America/Sao_Paulo", "yyyy-MM-dd HH:mm:ss");
        assertNotNull(response);

    }

    @Test
    void test_When_DateTimeUtils_getMinutesBetween_OK() {

        Long response = DateTimeUtils.getMinutesBetween(LocalDateTime.now(), LocalDateTime.now());
        assertNotNull(response);

    }

}
