package com.robinfood.taxes.serializers.tax;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.robinfood.taxes.models.RuleVariable;
import java.io.IOException;

public class RuleVariableSerializer extends StdSerializer<RuleVariable>  {

    private static final long serialVersionUID = 326665705819767631L;

    protected RuleVariableSerializer(Class<RuleVariable> t) {
        super(t);
    }

    protected RuleVariableSerializer() {
        this(null);
    }

    @Override
    public void serialize(RuleVariable ruleVariable, JsonGenerator jsonGenerator,
        SerializerProvider serializerProvider) throws IOException {

        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("id", ruleVariable.getId());
        jsonGenerator.writeStringField("created_at", ruleVariable.getCreatedAt().toString());
        jsonGenerator.writeStringField("updated_at", ruleVariable.getUpdatedAt().toString());
        if (ruleVariable.getDeletedAt() != null) {
            jsonGenerator.writeStringField("deleted_at", ruleVariable.getDeletedAt().toString());
        } else {
            jsonGenerator.writeNullField("deleted_at");
        }
        jsonGenerator.writeStringField("name", ruleVariable.getName());

        jsonGenerator.writeStringField("description", ruleVariable.getDescription());
        jsonGenerator.writeStringField("type", ruleVariable.getType());
        jsonGenerator.writeStringField("path", ruleVariable.getPath());
        jsonGenerator.writeStringField("value", ruleVariable.getValue());

        jsonGenerator.writeEndObject();
    }

}
