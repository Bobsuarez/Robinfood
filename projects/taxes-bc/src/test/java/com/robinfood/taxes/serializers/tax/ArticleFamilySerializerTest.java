package com.robinfood.taxes.serializers.tax;

import static org.junit.jupiter.api.Assertions.assertAll;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.robinfood.taxes.models.ArticleFamily;
import com.robinfood.taxes.models.Family;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ArticleFamilySerializerTest {

    @InjectMocks
    private ArticleFamilySerializer articleFamilySerializer;

    @Mock
    private JsonGenerator jsonGenerator;

    @Mock
    private SerializerProvider serializerProvider;

    @Test
    void test_ArticleFamilySerializer_Should_Serialize_When_ReceiveValidArticleFamily() {
        ArticleFamily articleFamily = getArticleFamily();
        articleFamily.setId(1L);
        articleFamily.setCreatedAt(LocalDateTime.now());
        articleFamily.setUpdatedAt(LocalDateTime.now());
        assertAll(() -> articleFamilySerializer.serialize(articleFamily, jsonGenerator, serializerProvider));
    }

    @Test
    void test_ArticleFamilySerializer_Should_Serialize_When_ReceiveValidArticleFamilyWithDeletedAt() {
        ArticleFamily articleFamily = getArticleFamily();
        articleFamily.setId(1L);
        articleFamily.setCreatedAt(LocalDateTime.now());
        articleFamily.setUpdatedAt(LocalDateTime.now());
        articleFamily.setDeletedAt(LocalDateTime.now());
        assertAll(() -> articleFamilySerializer.serialize(articleFamily, jsonGenerator, serializerProvider));
    }

    private ArticleFamily getArticleFamily() {
        return ArticleFamily.builder()
            .family(new Family(1L))
            .productTypeId(1L)
            .articleId(1L)
            .status(1)
            .build();
    }

}
