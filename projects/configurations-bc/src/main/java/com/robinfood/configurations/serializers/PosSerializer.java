package com.robinfood.configurations.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.robinfood.configurations.models.Pos;

import java.io.IOException;

@JsonDeserialize(as = Pos.class)
public class PosSerializer extends StdSerializer<Pos> {

    private static final long serialVersionUID = 8318143667934653733L;

    protected PosSerializer(Class<Pos> t) {
        super(t);
    }

    private PosSerializer() {
        super(Pos.class);
    }

    @Override
    public void serialize(Pos pos, JsonGenerator jsonGenerator,
                          SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("id", pos.getId());
        jsonGenerator.writeStringField("created_at", pos.getCreatedAt().toString());
        jsonGenerator.writeStringField("updated_at", pos.getUpdatedAt().toString());
        jsonGenerator.writeNullField("deleted_at");
        if (pos.getDeletedAt() != null) {
            jsonGenerator.writeStringField("deleted_at", pos.getDeletedAt().toString());
        }
        jsonGenerator.writeStringField("name", pos.getName());
        jsonGenerator.writeStringField("code", pos.getCode());
        jsonGenerator.writeEndObject();
    }
}
