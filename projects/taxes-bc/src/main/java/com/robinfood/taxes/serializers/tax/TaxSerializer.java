package com.robinfood.taxes.serializers.tax;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.robinfood.taxes.models.Tax;
import java.io.IOException;

public class TaxSerializer extends StdSerializer<Tax> {

    private static final long serialVersionUID = 2497814769565866833L;

    protected TaxSerializer(Class<Tax> t) {
        super(t);
    }

    protected TaxSerializer() {
        this(null);
    }

    @Override
    public void serialize(Tax tax, JsonGenerator jsonGenerator,
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
        jsonGenerator.writeNumberField("value", tax.getValue());
        jsonGenerator.writeStringField("description", tax.getDescription());
        jsonGenerator.writeStringField("sap_id", tax.getSapId());
        jsonGenerator.writeBooleanField("apply_rules", tax.getApplyRules());
        jsonGenerator.writeNumberField("status", tax.getStatus());
        jsonGenerator.writeNumberField("family_id", tax.getFamily().getId());
        jsonGenerator.writeNumberField("tax_type_id", tax.getTaxType().getId());
        jsonGenerator.writeNumberField("expression_id", tax.getExpression().getId());
        jsonGenerator.writeNumberField("status", tax.getStatus());
        jsonGenerator.writeEndObject();
    }

}
