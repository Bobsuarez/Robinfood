package com.robinfood.configurations.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.robinfood.configurations.models.Headquarter;

import java.io.IOException;

@JsonDeserialize(as = Headquarter.class)
public class HeadquarterSerializer extends StdSerializer<Headquarter> {

    private static final long serialVersionUID = 1482976065173250762L;

    private HeadquarterSerializer() {
        super(Headquarter.class);
    }

    protected HeadquarterSerializer(Class<Headquarter> t) {
        super(t);
    }

    @Override
    public void serialize(Headquarter headquarter, JsonGenerator jsonGenerator,
        SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("id", headquarter.getId());
        jsonGenerator.writeStringField("created_at", headquarter.getCreatedAt().toString());
        jsonGenerator.writeStringField("updated_at", headquarter.getUpdatedAt().toString());
        jsonGenerator.writeNullField("deleted_at");
        if (headquarter.getDeletedAt() != null) {
            jsonGenerator.writeStringField("deleted_at", headquarter.getDeletedAt().toString());
        }
        jsonGenerator.writeStringField("phone", headquarter.getPhone());
        jsonGenerator.writeStringField("email", headquarter.getEmail());
        jsonGenerator.writeStringField("address", headquarter.getAddress());
        jsonGenerator.writeStringField("city_name", headquarter.getCityName());
        jsonGenerator.writeEndObject();
    }
}
