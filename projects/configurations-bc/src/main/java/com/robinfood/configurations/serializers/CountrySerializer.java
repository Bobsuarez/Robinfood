package com.robinfood.configurations.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.robinfood.configurations.models.Country;

import java.io.IOException;

@JsonDeserialize(as = Country.class)
public class CountrySerializer extends StdSerializer<Country> {

    private static final long serialVersionUID = 8318146787934653737L;

    protected CountrySerializer(Class<Country> t) {
        super(t);
    }

    private CountrySerializer() {
        super(Country.class);
    }

    @Override
    public void serialize(Country country, JsonGenerator jsonGenerator,
        SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("id", country.getId());
        jsonGenerator.writeStringField("created_at", country.getCreatedAt().toString());
        jsonGenerator.writeStringField("updated_at", country.getUpdatedAt().toString());
        jsonGenerator.writeNullField("deleted_at");
        if (country.getDeletedAt() != null) {
            jsonGenerator.writeStringField("deleted_at", country.getDeletedAt().toString());
        }
        jsonGenerator.writeStringField("name", country.getName());
        jsonGenerator.writeEndObject();
    }
}
