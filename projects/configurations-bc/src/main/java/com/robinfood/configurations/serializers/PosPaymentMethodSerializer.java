package com.robinfood.configurations.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.robinfood.configurations.models.PosPaymentMethod;

import java.io.IOException;

@JsonDeserialize(as = PosPaymentMethod.class)
public class PosPaymentMethodSerializer extends StdSerializer<PosPaymentMethod> {

    private static final long serialVersionUID = 8378143667934653733L;

    protected PosPaymentMethodSerializer(Class<PosPaymentMethod> t) {
        super(t);
    }

    private PosPaymentMethodSerializer() {
        super(PosPaymentMethod.class);
    }

    @Override
    public void serialize(PosPaymentMethod posPaymentMethod, JsonGenerator jsonGenerator,
        SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("id", posPaymentMethod.getId());
        jsonGenerator.writeStringField("created_at", posPaymentMethod.getCreatedAt().toString());
        jsonGenerator.writeStringField("updated_at", posPaymentMethod.getUpdatedAt().toString());
        jsonGenerator.writeNullField("deleted_at");
        if (posPaymentMethod.getDeletedAt() != null) {
            jsonGenerator.writeStringField("deleted_at",
                posPaymentMethod.getDeletedAt().toString());
        }
        jsonGenerator.writeNumberField("payment_method_id", posPaymentMethod.getPaymentMethodId());
        jsonGenerator.writeEndObject();
    }
}
