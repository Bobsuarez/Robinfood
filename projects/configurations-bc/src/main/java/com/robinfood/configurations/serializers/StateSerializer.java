package com.robinfood.configurations.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.robinfood.configurations.models.State;

import java.io.IOException;

@JsonDeserialize(as = State.class)
public class StateSerializer extends StdSerializer<State> {

    private static final long serialVersionUID = 8318147867937658935L;

    protected StateSerializer(Class<State> t) {
        super(t);
    }

    private StateSerializer() {
        super(State.class);
    }

    @Override
    public void serialize(State state, JsonGenerator jsonGenerator,
        SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("id", state.getId());
        jsonGenerator.writeStringField("created_at", state.getCreatedAt().toString());
        jsonGenerator.writeStringField("updated_at", state.getUpdatedAt().toString());
        jsonGenerator.writeNullField("deleted_at");
        if (state.getDeletedAt() != null) {
            jsonGenerator.writeStringField("deleted_at", state.getDeletedAt().toString());
        }
        jsonGenerator.writeStringField("name", state.getName());
        jsonGenerator.writeNumberField("country_id", state.getCountry().getId());
        jsonGenerator.writeEndObject();
    }
}
