package com.robinfood.core.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.robinfood.core.exceptions.AppException;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

@Slf4j
public final class ObjectMapperSingleton {

    private static ObjectMapper instance;

    private ObjectMapperSingleton() {
        // Constructor privado para evitar la creación de instancias adicionales
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

        String dataJson = StringUtils.EMPTY;

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
    @SneakyThrows
    public static <T> T jsonToClass(final String json, final Class<T> elementClass) {

        final ObjectMapper objectMapperJson = getInstance();

        try {
            objectMapperJson.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            return objectMapperJson.readValue(json, elementClass);
        } catch (JsonProcessingException ex) {
            String errorMessage = "Error [jsonToClass]: " + ex.getMessage();
            log.error(errorMessage);
            throw new AppException(ex);

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

        try {
            objectMapperJson.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            return objectMapperJson.convertValue(json, elementClass);
        } catch (IllegalArgumentException ex) {
            String errorMessage = "Error [objectToClassConvertValue]: " + ex.getMessage();
            log.error(errorMessage);
            throw new AppException(ex);
        }
    }


    public static boolean isValidJson(String jsonString) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(jsonString);
            return jsonNode.isObject();
        } catch (Exception e) {
            log.error("Exception :", e);
            return false;
        }
    }
}
