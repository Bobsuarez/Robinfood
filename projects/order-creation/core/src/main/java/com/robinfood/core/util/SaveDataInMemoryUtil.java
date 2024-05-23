package com.robinfood.core.util;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;

@Slf4j
public final class SaveDataInMemoryUtil {

    private static final Map<String, AtomicReference<Object>> data = new ConcurrentHashMap<>();

    private SaveDataInMemoryUtil() {
        //Constructor empty
    }

    public static void setData(String key, Object data) {

        AtomicReference<Object> atomicReference = SaveDataInMemoryUtil.data
                .computeIfAbsent(key, getKey -> new AtomicReference<>());
        atomicReference.set(data);

        log.debug(
                "\n Set key: {} \n with value: {} in memory \n",
                key, data
        );
    }

    public static Object getValue(String key) {

        AtomicReference<Object> atomicValue = SaveDataInMemoryUtil.data.getOrDefault(key, new AtomicReference<>());
        log.debug(
                "\n Get key: {} \n with value: {} in memory \n get data: {}",
                key, atomicValue, data
        );
        return atomicValue.get();
    }

    public static void removeValue(String key) {
        SaveDataInMemoryUtil.data.remove(key);
        log.info("Cleared key {}", key);
    }
}
