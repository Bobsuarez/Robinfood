package com.robinfood.factories;

import com.robinfood.utils.LogsUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.clearAllCaches;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mockStatic;

class ReplicateEventFactoryTest {

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void test_GetReplicateEventStrategy_Should_ReturnStrategy_When_WithHttpSubscriberType() {

        mockStatic(LogsUtil.class);
        doNothing().when(LogsUtil.class);
        LogsUtil.info(anyString());

        String subscriberType = "HTTP";

        boolean strategy = ReplicateEventFactory.isExistHttp(subscriberType);

        assertTrue(strategy);
        clearAllCaches();
    }

    @Test
    void test_GetReplicateEventStrategy_Should_NullStrategy_When_WithNonHttpSubscriberType() {

        mockStatic(LogsUtil.class);
        doNothing().when(LogsUtil.class);
        LogsUtil.info(anyString());

        String subscriberType = "someOtherType";

        boolean strategy = ReplicateEventFactory.isExistHttp(subscriberType);

        assertFalse(strategy);
        clearAllCaches();
    }

    @Test
    void test_GetReplicateEventStrategy_Should_BuildConstructor_When_MethodInvoke() {

        try {
            Constructor<ReplicateEventFactory> constructor = ReplicateEventFactory.class.getDeclaredConstructor();
            constructor.setAccessible(true);
            ReplicateEventFactory instance = constructor.newInstance();
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException |
                 InvocationTargetException e) {
            assertEquals("java.lang.reflect.InvocationTargetException", e.toString());
        }
    }
}