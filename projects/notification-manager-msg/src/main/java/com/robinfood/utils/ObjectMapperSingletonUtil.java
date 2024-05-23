package com.robinfood.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.robinfood.constants.HttpStatusConstant;
import com.robinfood.exceptions.JsonParseException;
import com.robinfood.mappers.ResponseMapper;
import lombok.SneakyThrows;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;

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
            LogsUtil.error(String.valueOf(ex));
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
        objectMapperJson.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapperJson.readValue(json, elementClass);
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

    /**
     * Method of parse json to class
     *
     * @param json to turn into
     * @return Class
     */
    @SneakyThrows
    public static JsonNode stringToJsonNode(final String json) {
        final ObjectMapper objectMapperJson = getInstance();
        objectMapperJson.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapperJson.readTree(json);
    }

    /**
     * build string a Map<String, Object>
     *
     * @param json
     * @return
     */
    public static Map<String, Object> parseJsonToMap(
            final String json
    ) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            return mapper.readValue(json, new TypeReference<Map<String, Object>>() {
            });
        } catch (IOException ioException) {
            throw new JsonParseException(ResponseMapper
                    .buildWithError(HttpStatusConstant.SC_INTERNAL_SERVER_ERROR.getCodeHttp(),
                            ioException.getMessage(),
                            Boolean.TRUE), ioException.getMessage());
        }

    }
}
