package com.robinfood.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.robinfood.constants.HttpStatusConstant;
import com.robinfood.exceptions.JsonParseException;
import com.robinfood.mappers.ResponseDTOMapper;
import lombok.SneakyThrows;

import java.util.Objects;

import static com.robinfood.constants.Constants.DEFAULT_BOOLEAN_TRUE;
import static com.robinfood.constants.Constants.DEFAULT_STRING_EMPTY;

public class ObjectMapperSingletonUtil {

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
        String dataJson = DEFAULT_STRING_EMPTY;
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
    public static <T> T jsonToClass(final String json, final Class<T> elementClass) {

        try {

            final ObjectMapper objectMapperJson = getInstance();
            objectMapperJson.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            return objectMapperJson.readValue(json, elementClass);

        } catch (JsonProcessingException jsonProcessingException) {

            throw new JsonParseException(ResponseDTOMapper
                    .buildWithError(HttpStatusConstant.SC_INTERNAL_SERVER_ERROR.getCodeHttp(),
                            jsonProcessingException.getMessage(),
                            DEFAULT_BOOLEAN_TRUE), jsonProcessingException.getMessage());
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

}
