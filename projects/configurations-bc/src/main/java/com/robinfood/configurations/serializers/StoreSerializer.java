package com.robinfood.configurations.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.robinfood.configurations.models.Store;

import java.io.IOException;

@JsonDeserialize(as = Store.class)
public class StoreSerializer extends StdSerializer<Store> {

    private static final long serialVersionUID = 1482971888173250766L;

    private StoreSerializer() {
        super(Store.class);
    }

    protected StoreSerializer(Class<Store> t) {
        super(t);
    }

    @Override
    public void serialize(Store store, JsonGenerator jsonGenerator,
        SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("id", store.getId());
        jsonGenerator.writeStringField("created_at", store.getCreatedAt().toString());
        jsonGenerator.writeStringField("updated_at", store.getUpdatedAt().toString());
        jsonGenerator.writeNullField("deleted_at");
        if (store.getDeletedAt() != null) {
            jsonGenerator.writeStringField("deleted_at", store.getDeletedAt().toString());
        }
        jsonGenerator.writeStringField("name", store.getName());
        jsonGenerator.writeStringField("location", store.getLocation());
        jsonGenerator.writeStringField("phone", store.getPhone());
        jsonGenerator.writeStringField("email", store.getEmail());
        jsonGenerator.writeStringField("internal_name", store.getInternalName());
        jsonGenerator.writeStringField("identifier", store.getIdentifier());
        jsonGenerator.writeNumberField("city_id", store.getCity().getId());
        jsonGenerator.writeNumberField("company_id", store.getCompany().getId());
        jsonGenerator.writeNumberField("store_type_id", store.getStoreType().getId());
        jsonGenerator.writeNumberField("zone_id", store.getZones().getId());

        jsonGenerator.writeEndObject();
    }
}

