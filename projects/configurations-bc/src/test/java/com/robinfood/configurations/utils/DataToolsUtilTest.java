package com.robinfood.configurations.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DataToolsUtilTest {

    @Test
    void test_ProcessTrue_Should_ResultFirst() {
        int first = DataToolsUtil.convertToStatusInt(Boolean.TRUE);
        Assertions.assertEquals(1, first);
    }

    @Test
    void test_ProcessFalse_Should_ResultZero() {
        int Zero = DataToolsUtil.convertToStatusInt(Boolean.FALSE);
        Assertions.assertEquals(0, Zero);
    }
}