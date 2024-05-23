package com.robinfood.core.mappers;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import org.junit.jupiter.api.Test;

class RevertCouponMapperTest {

    @Test
    void test_RevertCouponMapper_Constructor()
            throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        Constructor<RevertCouponMapper> constructor = RevertCouponMapper.class.getDeclaredConstructor();
        assertTrue(Modifier.isPrivate(constructor.getModifiers()));
        constructor.setAccessible(true);

        assertThrows(
                InvocationTargetException.class, constructor::newInstance
        );
    }
}
