package com.robinfood.customersbc.thirdparties.utils;

import com.robinfood.customersbc.thirdparties.constants.LogConstants;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.apache.commons.lang3.StringUtils.EMPTY;

@Slf4j
public final class LogUtils {

    private LogUtils() {}

    public static List<Object> transformProceedingJoinPointArgs(Object[] args) {
        return Arrays.stream(args)
            .map((Object object) -> {
                object = Optional.ofNullable(object).orElse(EMPTY);
                if (Objects.nonNull(LogConstants.CLASS_NAME_MAP.get(object.getClass().getSimpleName()))) {
                    return object.toString();
                }
                return object;
            })
            .toList();
    }
}
