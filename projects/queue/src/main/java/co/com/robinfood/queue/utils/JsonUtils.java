/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.com.robinfood.queue.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @author Bobsu
 */
@Slf4j
public class JsonUtils {

    private static ObjectMapper instance;

    private JsonUtils() {
        // Constructor privado para evitar la creación de instancias adicionales
    }

    public static ObjectMapper getInstance() {
        if (Objects.isNull(instance)) {
            instance = new ObjectMapper();
        }
        return instance;
    }

    public static String formatJSON(String inputJSON) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

            Object jsonObject = objectMapper.readValue(inputJSON, Object.class);
            return objectMapper.writeValueAsString(jsonObject);
        } catch (IOException e) {
            e.printStackTrace();
            return "Error formatting JSON.";
        }
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
    public static <T> T jsonToClass(final String json, final Class<T> elementClass) throws JsonProcessingException {

        final ObjectMapper objectMapperJson = getInstance();
        //            objectMapperJson.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapperJson.readValue(json, elementClass);

    }

    public static <T> String jsonToClassToString(final String json, final Class<T> elementClass) throws JsonProcessingException {
        var objectClass = jsonToClass(json, elementClass);
        return objectToJson(objectClass);

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
        try {
            Class<T[]> arrayClass = (Class<T[]>) Class.forName("[L" + classOnWhichArrayIsDefined.getName() + ";");
            return Arrays.asList(mapper.readValue(json, arrayClass));
        } catch (IOException e) {
            log.error(" [IOException parseJson] : " + e.getMessage());
        } catch (ClassNotFoundException ex) {
            log.error(" [ClassNotFoundException parseJson] : " + ex.getMessage());
        }
        return null;
    }

}
