package com.robinfood.taxes.serializers.tax;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.robinfood.taxes.models.Expression;
import java.io.IOException;

public class ExpressionSerializer extends StdSerializer<Expression> {

    private static final long serialVersionUID = 775416185240689815L;

    protected ExpressionSerializer() {
        this(null);
    }

    protected ExpressionSerializer(Class<Expression> t) {
        super(t);
    }

    @Override
    public void serialize(Expression expression, JsonGenerator jsonGenerator,
        SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("id", expression.getId());
        jsonGenerator.writeStringField("created_at", expression.getCreatedAt().toString());
        jsonGenerator.writeStringField("updated_at", expression.getUpdatedAt().toString());

        if (expression.getDeletedAt() != null) {
            jsonGenerator.writeStringField("deleted_at", expression.getDeletedAt().toString());
        } else {
            jsonGenerator.writeNullField("deleted_at");
        }

        jsonGenerator.writeStringField("value", expression.getValue());
        jsonGenerator.writeStringField("description", expression.getDescription());
        jsonGenerator.writeNumberField("status", expression.getStatus());
        jsonGenerator.writeEndObject();
    }

}
