package com.robinfood.taxes.serializers.tax;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.robinfood.taxes.models.Family;
import java.io.IOException;

public class FamilySerializer extends StdSerializer<Family> {

    private static final long serialVersionUID = 5520090232742730888L;

    protected FamilySerializer(Class<Family> t) {
        super(t);
    }

    protected FamilySerializer() {
        this(null);
    }

    @Override
    public void serialize(Family family, JsonGenerator jsonGenerator,
        SerializerProvider serializerProvider) throws IOException {

        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("id", family.getId());
        jsonGenerator.writeStringField("created_at", family.getCreatedAt().toString());
        jsonGenerator.writeStringField("updated_at", family.getUpdatedAt().toString());
        if (family.getDeletedAt() != null) {
            jsonGenerator.writeStringField("deleted_at", family.getDeletedAt().toString());
        } else {
            jsonGenerator.writeNullField("deleted_at");
        }
        jsonGenerator.writeStringField("name", family.getName());
        jsonGenerator.writeNumberField("country_id", family.getCountryId());
        jsonGenerator.writeNumberField("country_id", family.getFamilyType().getId());
        jsonGenerator.writeNumberField("status", family.getStatus());
        jsonGenerator.writeEndObject();
    }

}
