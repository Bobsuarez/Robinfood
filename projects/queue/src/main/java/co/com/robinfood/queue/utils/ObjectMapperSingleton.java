package co.com.robinfood.queue.utils;

import co.com.robinfood.queue.Exceptions.ApplicationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Slf4j
public final class ObjectMapperSingleton {

    private static ObjectMapper instance;

    private ObjectMapperSingleton() {
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
     *
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
     *
     * @return Class
     */
    @SneakyThrows
    public static <T> T jsonToClass(final String json, final Class<T> elementClass) {

        try {
            final ObjectMapper objectMapperJson = getInstance();

            objectMapperJson.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            return objectMapperJson.readValue(json, elementClass);

        } catch (Exception exception) {
            throw new ApplicationException(505, exception.getMessage());
        }
    }

    /**
     * @param json
     * @param elementClass
     * @param <T>
     *
     * @return
     */
    @SneakyThrows
    public static <T> List<T> jsonToListClass(final String json, final Class<T> elementClass) {

        try {
            final ObjectMapper objectMapperJson = getInstance();

            objectMapperJson.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            return objectMapperJson.readValue(json, objectMapperJson.getTypeFactory()
                    .constructCollectionType(List.class, elementClass));

        } catch (Exception exception) {
            throw new ApplicationException(505, exception.getMessage());
        }
    }

    /**
     * Method of convert object to class
     *
     * @param <T>          Class generic
     * @param json         to turn into
     * @param elementClass Class Generic
     *
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
     *
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
     *
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
            throw new ApplicationException(505, ioException.getMessage());
        }

    }

    public static JsonNode findAttribute(JsonNode rootNode, String attributeName) {
        if (rootNode.has(attributeName)) {
            return rootNode.get(attributeName);
        }
        Iterator<Map.Entry<String, JsonNode>> fields = rootNode.fields();
        while (fields.hasNext()) {
            Map.Entry<String, JsonNode> entry = fields.next();
            JsonNode childNode = entry.getValue();
            if (childNode.isObject()) {
                JsonNode result = findAttribute(childNode, attributeName);
                if (result != null) {
                    return result;
                }
            } else if (childNode.isArray()) {
                for (JsonNode arrayElement : childNode) {
                    JsonNode result = findAttribute(arrayElement, attributeName);
                    if (result != null) {
                        return result;
                    }
                }
            }
        }
        return null;
    }

}
