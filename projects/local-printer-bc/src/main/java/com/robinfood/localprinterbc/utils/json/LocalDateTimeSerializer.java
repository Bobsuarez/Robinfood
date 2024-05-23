package com.robinfood.localprinterbc.utils.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.robinfood.localprinterbc.utils.DateUtil;

import java.io.IOException;
import java.time.LocalDateTime;

public class LocalDateTimeSerializer extends JsonSerializer<LocalDateTime>
{
    @Override
    public void serialize(LocalDateTime t, JsonGenerator jg, SerializerProvider sp)
            throws IOException
    {
        jg.writeString(DateUtil.formatStandard(t));
    }
}
