package com.robinfood.configurations.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.robinfood.configurations.models.StoreMultiBrand;

import java.io.IOException;

@JsonDeserialize(as = StoreMultiBrand.class)
public class    MultiBrandStoreSerializer extends StdSerializer<StoreMultiBrand> {

    private static final long serialVersionUID = 1482976065173250762L;

    private MultiBrandStoreSerializer() {
        super(StoreMultiBrand.class);
    }

    protected MultiBrandStoreSerializer(Class<StoreMultiBrand> t) {
        super(t);
    }

    @Override
    public void serialize(StoreMultiBrand storeMultiBrand, JsonGenerator jsonGenerator,
                          SerializerProvider serializerProvider) throws IOException {

        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("id", storeMultiBrand.getId());
        jsonGenerator.writeStringField("created_at", storeMultiBrand.getCreatedAt().toString());
        jsonGenerator.writeStringField("updated_at", storeMultiBrand.getUpdatedAt().toString());
        jsonGenerator.writeNullField("deleted_at");
        if (storeMultiBrand.getDeletedAt() != null) {
            jsonGenerator.writeStringField("deleted_at", storeMultiBrand.getDeletedAt().toString());
        }
        jsonGenerator.writeStringField("color", storeMultiBrand.getColor());
        jsonGenerator.writeStringField("description", storeMultiBrand.getDescription());
        jsonGenerator.writeStringField("image", storeMultiBrand.getImage());
        jsonGenerator.writeStringField("name", storeMultiBrand.getName());
        jsonGenerator.writeEndObject();
    }
}
