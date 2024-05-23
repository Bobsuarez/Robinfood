package com.robinfood.localserver.commons.utilities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class JsonLogUtil {

    private static final ObjectMapper objectMapper = new ObjectMapper();


    private JsonLogUtil() {
    }

    /**
     * Receive the object to convert to Json and the calling class to log
     *
     * @param objectToLog  object to convert a Json
     * @param invokerClass invoker class to log
     */
    public static <T, Z> String logJson(T objectToLog, Class<Z> invokerClass) {
        try {
            return objectMapper.writeValueAsString(objectToLog);
        } catch (JsonProcessingException exception) {
            log.error("Error casting in {} this class {} origin object {} to JSON ", invokerClass.getSimpleName(),
                    objectToLog.getClass().getSimpleName(), objectToLog, exception);
            return objectToLog.toString();
        }
    }
}
