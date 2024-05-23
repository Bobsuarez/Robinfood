package com.robinfood.core.helpers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public final class UuidHelperTest {

    @Test
    void test_Get_Uuid_When_PassCompleteDatetime() {
        Assertions.assertNotNull(UuidHelper.getByLong(1L));
    }
}
