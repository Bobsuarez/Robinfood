package com.robinfood.factories;

import com.robinfood.utils.LogsUtil;

import java.util.Objects;

import static com.robinfood.constants.Constants.SUBSCRIBER_HTTP_TYPE;

public final class ReplicateEventFactory {

    private ReplicateEventFactory() {
        throw new IllegalStateException("java.lang.reflect.InvocationTargetException");
    }

    public static Boolean isExistHttp(String subscriberType) {

        LogsUtil.info("Execute get replicate event strategy %s", subscriberType);

        if (Objects.equals(subscriberType, SUBSCRIBER_HTTP_TYPE)) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
}
