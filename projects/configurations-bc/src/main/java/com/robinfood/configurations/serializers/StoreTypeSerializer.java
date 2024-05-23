package com.robinfood.configurations.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.robinfood.configurations.models.StoreType;
import java.io.IOException;

@JsonDeserialize(as = StoreType.class)
public class StoreTypeSerializer extends StdSerializer<StoreType> {

    private static final long serialVersionUID = 1482971888173250766L;

    private StoreTypeSerializer() {
        super(StoreType.class);
    }

    protected StoreTypeSerializer(Class<StoreType> t) {
        super(t);
    }

    @Override
    public void serialize(StoreType storeType, JsonGenerator jsonGenerator,
        SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("id", storeType.getId());
        jsonGenerator.writeStringField("created_at", storeType.getCreatedAt().toString());
        jsonGenerator.writeStringField("updated_at", storeType.getUpdatedAt().toString());
        jsonGenerator.writeNullField("deleted_at");
        if (storeType.getDeletedAt() != null) {
            jsonGenerator.writeStringField("deleted_at", storeType.getDeletedAt().toString());
        }
        jsonGenerator.writeStringField("name", storeType.getName());

        jsonGenerator.writeEndObject();
    }
}

