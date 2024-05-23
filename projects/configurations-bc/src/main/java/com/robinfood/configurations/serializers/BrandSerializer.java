package com.robinfood.configurations.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.robinfood.configurations.models.Brand;

import java.io.IOException;

@JsonDeserialize(as = Brand.class)
public class BrandSerializer extends StdSerializer<Brand> {

    private static final long serialVersionUID = 3118147867934653710L;

    protected BrandSerializer(Class<Brand> t) {
        super(t);
    }

    private BrandSerializer() {
        super(Brand.class);
    }

    @Override
    public void serialize(Brand brand, JsonGenerator jsonGenerator,
        SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("id", brand.getId());
        jsonGenerator.writeStringField("created_at", brand.getCreatedAt().toString());
        jsonGenerator.writeStringField("updated_at", brand.getUpdatedAt().toString());
        jsonGenerator.writeNullField("deleted_at");
        if (brand.getDeletedAt() != null) {
            jsonGenerator.writeStringField("deleted_at", brand.getDeletedAt().toString());
        }
        jsonGenerator.writeStringField("name", brand.getName());
        jsonGenerator.writeEndObject();
    }
}
