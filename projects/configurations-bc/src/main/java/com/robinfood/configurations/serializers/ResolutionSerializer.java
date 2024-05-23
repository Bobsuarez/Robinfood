package com.robinfood.configurations.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.robinfood.configurations.models.Resolution;

import java.io.IOException;

@JsonDeserialize(as = Resolution.class)
public class ResolutionSerializer extends StdSerializer<Resolution> {

    private static final long serialVersionUID = 8318198017934653734L;

    protected ResolutionSerializer(Class<Resolution> t) {
        super(t);
    }

    private ResolutionSerializer() {
        super(Resolution.class);
    }

    @Override
    public void serialize(Resolution resolution, JsonGenerator jsonGenerator,
        SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("id", resolution.getId());
        jsonGenerator.writeStringField("created_at", resolution.getCreatedAt().toString());
        jsonGenerator.writeStringField("updated_at", resolution.getUpdatedAt().toString());
        jsonGenerator.writeNullField("deleted_at");
        if (resolution.getDeletedAt() != null) {
            jsonGenerator.writeStringField("deleted_at", resolution.getDeletedAt().toString());
        }
        jsonGenerator.writeNumberField("status", resolution.getStatus());
        jsonGenerator.writeStringField("starting_number", resolution.getStartingNumber());
        jsonGenerator.writeStringField("final_number", resolution.getFinalNumber());
        jsonGenerator.writeStringField("name", resolution.getName());
        jsonGenerator.writeNullField("start_date");
        if (resolution.getStartDate() != null) {
            jsonGenerator.writeStringField("start_date", resolution.getStartDate().toString());
        }
        jsonGenerator.writeNullField("end_date");
        if (resolution.getEndDate() != null) {
            jsonGenerator.writeStringField("end_date", resolution.getEndDate().toString());
        }
        jsonGenerator.writeStringField("prefix", resolution.getPrefix());
        jsonGenerator.writeStringField("invoice_text", resolution.getInvoiceText());
        jsonGenerator.writeStringField("serial", resolution.getSerial());
        jsonGenerator.writeStringField("invoice_number_resolutions",
            resolution.getInvoiceNumberResolutions());
        jsonGenerator.writeStringField("document", resolution.getDocument());
        jsonGenerator.writeNumberField("pos_id", resolution.getPos().getId());
        jsonGenerator.writeEndObject();
    }
}
