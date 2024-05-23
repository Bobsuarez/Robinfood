package com.robinfood.configurations.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.robinfood.configurations.models.StoreIdentifierType;
import java.io.IOException;

@JsonDeserialize(as = StoreIdentifierType.class)
public class StoreIdentifierTypeSerializer extends StdSerializer<StoreIdentifierType> {

    private static final long serialVersionUID = 1482971888173250766L;

    private StoreIdentifierTypeSerializer() {
        super(StoreIdentifierType.class);
    }

    protected StoreIdentifierTypeSerializer(Class<StoreIdentifierType> t) {
        super(t);
    }

    @Override
    public void serialize(StoreIdentifierType storeIdentifierType, JsonGenerator jsonGenerator,
        SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("id", storeIdentifierType.getId());
        jsonGenerator.writeStringField("created_at", storeIdentifierType.getCreatedAt().toString());
        jsonGenerator.writeStringField("updated_at", storeIdentifierType.getUpdatedAt().toString());
        jsonGenerator.writeNullField("deleted_at");
        if (storeIdentifierType.getDeletedAt() != null) {
            jsonGenerator.writeStringField("deleted_at", storeIdentifierType.getDeletedAt().toString());
        }
        jsonGenerator.writeStringField("name", storeIdentifierType.getName());

        jsonGenerator.writeEndObject();
    }
}

