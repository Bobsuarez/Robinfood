package com.robinfood.paymentmethodsbc.utils;

import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

@Slf4j
public final class SafeCastUtils {

    private SafeCastUtils() {
        throw new UnsupportedOperationException("Not supported yet.");
    }


    public static String safeObjectToString(Object object) {
        if (Objects.nonNull(object)) {
            return object.toString();
        }
        return null;
    }


    public static Boolean toBoolean(Object object) {
        return Boolean.valueOf(String.valueOf(object));
    }
}
