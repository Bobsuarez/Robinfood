package com.robinfood.localprinterbc.utils.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.robinfood.localprinterbc.utils.DateUtil;

import java.io.IOException;
import java.time.LocalDateTime;

public class LocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {

    @Override
    public LocalDateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException, JsonProcessingException {

        return DateUtil.parseFull(jsonParser.getValueAsString());
    }
}
