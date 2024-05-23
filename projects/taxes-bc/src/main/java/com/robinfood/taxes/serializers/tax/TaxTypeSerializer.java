package com.robinfood.taxes.serializers.tax;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.robinfood.taxes.models.TaxType;
import java.io.IOException;

public class TaxTypeSerializer extends StdSerializer<TaxType> {

    private static final long serialVersionUID = 2497814769565866833L;

    protected TaxTypeSerializer(Class<TaxType> t) {
        super(t);
    }

    protected TaxTypeSerializer() {
        this(null);
    }

    @Override
    public void serialize(TaxType tax, JsonGenerator jsonGenerator,
        SerializerProvider serializerProvider) throws IOException {

        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("id", tax.getId());
        jsonGenerator.writeStringField("created_at", tax.getCreatedAt().toString());
        jsonGenerator.writeStringField("updated_at", tax.getUpdatedAt().toString());
        if (tax.getDeletedAt() != null) {
            jsonGenerator.writeStringField("deleted_at", tax.getDeletedAt().toString());
        } else {
            jsonGenerator.writeNullField("deleted_at");
        }
        jsonGenerator.writeStringField("name", tax.getName());
        jsonGenerator.writeNumberField("country_id", tax.getCountryId());
        jsonGenerator.writeNumberField("status", tax.getStatus());
        jsonGenerator.writeEndObject();
    }

}
