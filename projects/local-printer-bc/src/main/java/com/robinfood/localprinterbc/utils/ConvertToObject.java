package com.robinfood.localprinterbc.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Slf4j
public final class ConvertToObject {

    public ConvertToObject() {
    }

    public static Map<String, Object> getMapObject(String rule) {
        final ObjectMapper oMapper = new ObjectMapper();

        Map<String, Object> mapObjet = null;
        try {
            mapObjet = oMapper.readValue(rule, new TypeReference<>(){});
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return mapObjet;

    }
}
