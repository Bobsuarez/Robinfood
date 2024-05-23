package com.robinfood.taxes.serializers.tax;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.robinfood.taxes.models.FormulaVariable;
import java.io.IOException;

public class FormulaVariableSerializer extends StdSerializer<FormulaVariable> {

    private static final long serialVersionUID = -1086108570936786981L;

    protected FormulaVariableSerializer(Class<FormulaVariable> t) {
        super(t);
    }

    protected FormulaVariableSerializer() {
        this(null);
    }

    @Override
    public void serialize(FormulaVariable formulaVariable, JsonGenerator jsonGenerator,
        SerializerProvider serializerProvider) throws IOException {

        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("id", formulaVariable.getId());
        jsonGenerator.writeStringField("created_at", formulaVariable.getCreatedAt().toString());
        jsonGenerator.writeStringField("updated_at", formulaVariable.getUpdatedAt().toString());
        if (formulaVariable.getDeletedAt() != null) {
            jsonGenerator.writeStringField("deleted_at", formulaVariable.getDeletedAt().toString());
        } else {
            jsonGenerator.writeNullField("deleted_at");
        }
        jsonGenerator.writeStringField("name", formulaVariable.getName());
        jsonGenerator.writeStringField("value", formulaVariable.getValue());
        jsonGenerator.writeStringField("description", formulaVariable.getDescription());
        jsonGenerator.writeStringField("type", formulaVariable.getType());
        jsonGenerator.writeEndObject();
    }

}
