package com.robinfood.taxes.serializers.tax;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.robinfood.taxes.models.ExpressionFormulaVariable;
import java.io.IOException;

public class ExpressionFormulaVariableSerializer extends StdSerializer<ExpressionFormulaVariable> {


    private static final long serialVersionUID = 7826824398249748445L;

    protected ExpressionFormulaVariableSerializer() {
        this(null);
    }

    protected ExpressionFormulaVariableSerializer(Class<ExpressionFormulaVariable> t) {
        super(t);
    }

    @Override
    public void serialize(ExpressionFormulaVariable expressionFormulaVariable,
        JsonGenerator jsonGenerator,
        SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("id", expressionFormulaVariable.getId());
        jsonGenerator
            .writeStringField("created_at", expressionFormulaVariable.getCreatedAt().toString());
        jsonGenerator
            .writeStringField("updated_at", expressionFormulaVariable.getUpdatedAt().toString());

        if (expressionFormulaVariable.getDeletedAt() != null) {
            jsonGenerator.writeStringField("deleted_at",
                expressionFormulaVariable.getDeletedAt().toString());
        } else {
            jsonGenerator.writeNullField("deleted_at");
        }

        jsonGenerator
            .writeNumberField("expression_id", expressionFormulaVariable.getExpression().getId());
        jsonGenerator.writeNumberField("formula_variable_id",
            expressionFormulaVariable.getFormulaVariable().getId());
        jsonGenerator.writeEndObject();
    }

}
