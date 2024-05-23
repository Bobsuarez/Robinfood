package com.robinfood.configurations.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.robinfood.configurations.models.City;

import java.io.IOException;

@JsonDeserialize(as = City.class)
public class CitySerializer extends StdSerializer<City> {

    private static final long serialVersionUID = 1482971865177250761L;

    private CitySerializer() {
        super(City.class);
    }

    protected CitySerializer(Class<City> t) {
        super(t);
    }

    @Override
    public void serialize(City city, JsonGenerator jsonGenerator,
        SerializerProvider serializerProvider) throws IOException {

        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("id", city.getId());
        jsonGenerator.writeStringField("created_at", city.getCreatedAt().toString());
        jsonGenerator.writeStringField("updated_at", city.getUpdatedAt().toString());
        jsonGenerator.writeNullField("deleted_at");
        if (city.getDeletedAt() != null) {
            jsonGenerator.writeStringField("deleted_at", city.getDeletedAt().toString());
        }
        jsonGenerator.writeStringField("name", city.getName());
        jsonGenerator.writeStringField("timezone", city.getTimezone());
        jsonGenerator.writeNumberField("state_id", city.getState().getId());
        jsonGenerator.writeEndObject();

    }
}
