package com.robinfood.storeor.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import static com.fasterxml.jackson.databind.DeserializationFeature.ACCEPT_FLOAT_AS_INT;

@Slf4j
public final class ObjectMapperUtils {

    private static final ObjectMapper objectMapper;

    static {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        objectMapper.configure(ACCEPT_FLOAT_AS_INT, false);
        objectMapper.configure(MapperFeature.ALLOW_COERCION_OF_SCALARS, false);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    private ObjectMapperUtils() {
    }

    private static ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    /**
     * Method of parse object to String
     *
     * @param <T>    Param of Exit
     * @param object parameter of out
     * @return String
     */
    public static <T> String objectToJson(final T object) {

        final ObjectMapper objectMapper = getObjectMapper();

        String dataJson = StringUtils.EMPTY;

        try {
            objectMapper.findAndRegisterModules();
            dataJson = objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException ex) {
            log.error(String.valueOf(ex));
        }
        return dataJson;
    }

    public static String getObjectEmpty() {
        return objectMapper.createObjectNode().toString();
    }
}
