package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Slf4j
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
    @SneakyThrows
    public static <T> T jsonToClass(final String json, final Class<T> elementClass) {
        final ObjectMapper objectMapperJson = getInstance();
        objectMapperJson.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//        try {
        return objectMapperJson.readValue(json, elementClass);
//        } catch (JsonProcessingException e) {
//
//            System.out.println(ObjectMapperSingletonUtil.objectToJson(e));
//            throw new Excep(e.getOriginalMessage());
//        }
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
        try {
            return objectMapperJson.convertValue(json, elementClass);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
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

    @SneakyThrows
    public static boolean stringToJsonValidate(final String json) {
        final ObjectMapper objectMapperJson = getInstance();
        System.out.println(json);
        try {
            objectMapperJson.readTree(json);
            System.out.println("La cadena es un JSON válido.");
            return true;
        } catch (JsonProcessingException e) {
            System.out.println("La cadena no es un JSON válido.");
            return false;
        }
    }


    /**
     * Method of parse json a entity
     *
     * @param <T>                        class to which you want to convert
     * @param json                       l array json parameter
     * @param classOnWhichArrayIsDefined
     * @return List data
     */
    public static <T> List<T> parseJsonToArray(
            final String json,
            final Class<T> classOnWhichArrayIsDefined
    ) {
        ObjectMapper mapper = new ObjectMapper();
        T[] objects = null;
        try {
            Class<T[]> arrayClass = (Class<T[]>) Class.forName("[L" + classOnWhichArrayIsDefined.getName() + ";");
            objects = mapper.readValue(json, arrayClass);
        } catch (IOException e) {
            log.error(" [IOException parseJson] : " + e.getMessage());
        } catch (ClassNotFoundException ex) {
            log.error(" [ClassNotFoundException parseJson] : " + ex.getMessage());
        }
        return Arrays.asList(objects);
    }

}
