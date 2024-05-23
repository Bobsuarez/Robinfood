package com.robinfood.customersbc.thirdparties.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.Locale;
import java.util.Optional;

import static org.apache.commons.lang3.StringUtils.EMPTY;

public final class SanitizeUtils {

    private SanitizeUtils() {}

    public static String sanitizeString(String string) {
        return StringUtils.defaultIfEmpty(
            Optional.ofNullable(string).orElse(EMPTY).toLowerCase(Locale.getDefault()).trim(), null
        );
    }
}
