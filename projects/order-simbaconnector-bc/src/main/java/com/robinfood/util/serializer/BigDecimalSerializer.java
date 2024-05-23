package com.robinfood.util.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

import static com.robinfood.constants.GeneralConstants.DEFAULT_INTEGER_TWO;

public class BigDecimalSerializer extends JsonSerializer<BigDecimal> {

    @Override
    public void serialize(BigDecimal value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeObject(value.setScale(DEFAULT_INTEGER_TWO, RoundingMode.HALF_UP));
    }
}
