package com.robinfood.taxes.serializers.tax;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.robinfood.taxes.models.ArticleFamily;
import java.io.IOException;

public class ArticleFamilySerializer extends StdSerializer<ArticleFamily> {

    private static final long serialVersionUID = 4472009795325703058L;

    protected ArticleFamilySerializer(Class<ArticleFamily> t) {
        super(t);
    }

    protected ArticleFamilySerializer() {
        this(null);
    }

    @Override
    public void serialize(ArticleFamily articleFamily, JsonGenerator jsonGenerator,
        SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("id", articleFamily.getId());
        jsonGenerator.writeStringField("created_at", articleFamily.getCreatedAt().toString());
        jsonGenerator.writeStringField("updated_at", articleFamily.getUpdatedAt().toString());

        if (articleFamily.getDeletedAt() != null) {
            jsonGenerator.writeStringField("deleted_at", articleFamily.getDeletedAt().toString());
        } else {
            jsonGenerator.writeNullField("deleted_at");
        }

        jsonGenerator.writeNumberField("family_id", articleFamily.getFamily().getId());
        jsonGenerator.writeNumberField("product_type_id", articleFamily.getProductTypeId());
        jsonGenerator.writeNumberField("article_id", articleFamily.getArticleId());
        jsonGenerator.writeNumberField("status", articleFamily.getStatus());
        jsonGenerator.writeEndObject();
    }


}
