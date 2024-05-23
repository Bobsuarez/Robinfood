package com.robinfood.taxes.serializers.tax;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.robinfood.taxes.models.TaxRule;
import java.io.IOException;

public class TaxRuleSerializer extends StdSerializer<TaxRule> {

    private static final long serialVersionUID = -2996434766724378310L;

    protected TaxRuleSerializer() {
        this(null);
    }

    protected TaxRuleSerializer(Class<TaxRule> t) {
        super(t);
    }

    @Override
    public void serialize(TaxRule taxRule, JsonGenerator jsonGenerator,
        SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("id", taxRule.getId());
        jsonGenerator.writeStringField("created_at", taxRule.getCreatedAt().toString());
        jsonGenerator.writeStringField("updated_at", taxRule.getUpdatedAt().toString());

        if (taxRule.getDeletedAt() != null) {
            jsonGenerator.writeStringField("deleted_at", taxRule.getDeletedAt().toString());
        } else {
            jsonGenerator.writeNullField("deleted_at");
        }

        jsonGenerator.writeNumberField("tax_id", taxRule.getTax().getId());
        jsonGenerator.writeNumberField("rule_type", taxRule.getRuleType().getId());
        jsonGenerator.writeNumberField("left_variable", taxRule.getLeftVariable().getId());
        jsonGenerator.writeNumberField("right_variable", taxRule.getRightVariable().getId());
        jsonGenerator.writeNumberField("status", taxRule.getStatus());
        jsonGenerator.writeEndObject();
    }

}
