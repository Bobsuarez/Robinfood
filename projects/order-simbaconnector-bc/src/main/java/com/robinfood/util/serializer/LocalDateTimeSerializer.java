package com.robinfood.util.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.robinfood.util.DateUtil;

import java.io.IOException;
import java.time.ZonedDateTime;

public class LocalDateTimeSerializer extends JsonSerializer<ZonedDateTime> {
    @Override
    public void serialize(ZonedDateTime t, JsonGenerator jg, SerializerProvider sp)
            throws IOException {
        jg.writeString(DateUtil.parseDateAndHourNow(t));
    }
}