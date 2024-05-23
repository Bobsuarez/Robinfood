package com.robinfood.configurations.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.robinfood.configurations.models.Zone;
import java.io.IOException;

@JsonDeserialize(as = Zone.class)
public class ZoneSerializer extends StdSerializer<Zone> {

    private static final long serialVersionUID = -2612936104501546419L;

    private ZoneSerializer() {
        super(Zone.class);
    }

    protected ZoneSerializer(Class<Zone> t) {
        super(t);
    }

    @Override
    public void serialize(Zone zone, JsonGenerator jsonGenerator,
        SerializerProvider serializerProvider) throws IOException {

        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("id", zone.getId());
        jsonGenerator.writeStringField("created_at", zone.getCreatedAt().toString());
        jsonGenerator.writeStringField("updated_at", zone.getUpdatedAt().toString());
        jsonGenerator.writeNullField("deleted_at");
        if (zone.getDeletedAt() != null) {
            jsonGenerator.writeStringField("deleted_at", zone.getDeletedAt().toString());
        }
        jsonGenerator.writeNumberField("city_id", zone.getCity().getId());

        jsonGenerator.writeEndObject();
    }
}
