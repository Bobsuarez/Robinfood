package com.robinfood.util;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.robinfood.exceptions.BodyNotFoundException;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;

import java.util.Objects;

@Slf4j
public final class ObjectMapperSingletonUtil {

    private static ObjectMapper instance;

    private ObjectMapperSingletonUtil() {
    }

    public static ObjectMapper getInstance() {
        if (Objects.isNull(instance)) {
            instance = new ObjectMapper();
        }
        return instance;
    }

    /**
     * Method of parse object to String
     *
     * @param <T>    Param of Exit
     * @param object parameter of out
     * @return String
     */
    public static <T> String objectToJson(final T object) {

        final ObjectMapper objectMapper = getInstance();

        String dataJson = "";

        try {
            objectMapper.findAndRegisterModules();
            dataJson = objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException ex) {
            log.error(String.valueOf(ex));
        }
        return dataJson;
    }

    /**
     * Method of parse json to class
     *
     * @param <T>          Class generic
     * @param json         to turn into
     * @param elementClass Class Generic
     * @return Class
     */

    public static <T> T jsonToClass(final String json, final Class<T> elementClass) {

        try {
            final ObjectMapper objectMapperJson = getInstance();
            objectMapperJson.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            return objectMapperJson.readValue(json, elementClass);
        } catch (JsonProcessingException e) {
            throw new BodyNotFoundException(HttpStatus.SC_CONFLICT,
                    "status value could not be deserialized");
        }
    }

    /**
     * Method of convert object to class
     *
     * @param <T>          Class generic
     * @param json         to turn into
     * @param elementClass Class Generic
     * @return Class
     */
    @SneakyThrows
    public static <T> T objectToClassConvertValue(final Object json, final Class<T> elementClass) {

        final ObjectMapper objectMapperJson = getInstance();
        objectMapperJson.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapperJson.convertValue(json, elementClass);
    }
}

