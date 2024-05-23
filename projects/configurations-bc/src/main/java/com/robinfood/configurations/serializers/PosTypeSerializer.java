package com.robinfood.configurations.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.robinfood.configurations.models.PosType;
import java.io.IOException;

@JsonDeserialize(as = PosType.class)
public class PosTypeSerializer extends StdSerializer<PosType> {

    private static final long serialVersionUID = 8318147867887658935L;

    protected PosTypeSerializer(Class<PosType> t) {
        super(t);
    }

    private PosTypeSerializer() {
        super(PosType.class);
    }

    @Override
    public void serialize(PosType posType, JsonGenerator jsonGenerator,
        SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("id", posType.getId());
        jsonGenerator.writeStringField("created_at", posType.getCreatedAt().toString());
        jsonGenerator.writeStringField("updated_at", posType.getUpdatedAt().toString());
        jsonGenerator.writeNullField("deleted_at");
        if (posType.getDeletedAt() != null) {
            jsonGenerator.writeStringField("deleted_at", posType.getDeletedAt().toString());
        }
        jsonGenerator.writeStringField("name", posType.getName());
        jsonGenerator.writeEndObject();
    }
}
