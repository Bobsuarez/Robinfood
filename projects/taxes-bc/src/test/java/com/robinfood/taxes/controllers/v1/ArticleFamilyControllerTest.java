package com.robinfood.taxes.controllers.v1;

import static com.robinfood.taxes.constants.PermissionsConstants.CREATE_ARTICLE_FAMILIES;
import static com.robinfood.taxes.constants.PermissionsConstants.LIST_ARTICLE_FAMILIES;
import static com.robinfood.taxes.constants.PermissionsConstants.LIST_ARTICLE_FAMILIES_TAX;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static com.robinfood.taxes.constants.PermissionsConstants.DELETE_ARTICLE_FAMILIES;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.robinfood.taxes.domain.ArticleTaxDetail;
import com.robinfood.taxes.dto.v1.create.CreateArticleFamilyDTO;
import com.robinfood.taxes.exceptions.BusinessRuleException;
import com.robinfood.taxes.models.ArticleFamily;
import com.robinfood.taxes.models.Family;
import com.robinfood.taxes.services.ArticleFamilyService;
import java.util.Collections;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(controllers = ArticleFamilyController.class)
@TestPropertySource(properties = {
    "jwt.token.mod=article-families"
})
class ArticleFamilyControllerTest extends BaseTestController {

    @MockBean
    private ArticleFamilyService articleFamilyService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @SpyBean
    private ModelMapper modelMapper;

    private static final String MODULE = "article-families";

    private static final String AUDIENCE = "internal";

    @Test
    void test_List_Should_Return200Code_When_ReceiveNoQueryParams() throws Exception {

        String token = getJWT(MODULE, LIST_ARTICLE_FAMILIES, AUDIENCE);

        Page<ArticleFamily> articleFamilies = new PageImpl<>(
            Collections.singletonList(getArticleFamily()));

        when(articleFamilyService.list(eq(null), eq(null), eq(null), any(
            Pageable.class))).thenReturn(articleFamilies);

        this.mockMvc.perform(get("/v1/article-families")
            .header(HttpHeaders.AUTHORIZATION, token)
            .contentType(MediaType.APPLICATION_JSON)
            .characterEncoding("utf-8"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.code").value(200))
            .andExpect(jsonPath("$.message").isNotEmpty())
            .andExpect(jsonPath("$.data").isNotEmpty())
            .andExpect(jsonPath("$.error").isEmpty());
    }

    @Test
    void test_List_Should_Return200Code_When_ReceiveQueryParams() throws Exception {

        String token = getJWT(MODULE, LIST_ARTICLE_FAMILIES, AUDIENCE);

        Page<ArticleFamily> articleFamilies = new PageImpl<>(
            Collections.singletonList(getArticleFamily()));

        when(articleFamilyService.list(anyLong(), anyLong(), anyInt(), any(
            Pageable.class))).thenReturn(articleFamilies);

        this.mockMvc.perform(
            get("/v1/article-families?product_type_id=1&article_id=1&status=1&page=1&size=1")
                .header(HttpHeaders.AUTHORIZATION, token)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.code").value(200))
            .andExpect(jsonPath("$.message").isNotEmpty())
            .andExpect(jsonPath("$.data").isNotEmpty())
            .andExpect(jsonPath("$.error").isEmpty());
    }

    @Test
    void test_List_Should_Return401Code_When_Unauthorized() throws Exception {
        this.mockMvc.perform(get("/v1/article-families")
            .contentType(MediaType.APPLICATION_JSON)
            .characterEncoding("utf-8"))
            .andDo(print())
            .andExpect(status().isUnauthorized())
            .andExpect(jsonPath("$.code").value(401))
            .andExpect(jsonPath("$.message").isNotEmpty())
            .andExpect(jsonPath("$.data").isEmpty())
            .andExpect(jsonPath("$.error").isEmpty());
    }

    @Test
    void test_List_Should_Return403Code_When_InvalidPermission() throws Exception {

        String token = getJWT(MODULE, "Some permissions", AUDIENCE);

        this.mockMvc.perform(get("/v1/article-families")
            .header(HttpHeaders.AUTHORIZATION, token)
            .contentType(MediaType.APPLICATION_JSON)
            .characterEncoding("utf-8"))
            .andDo(print())
            .andExpect(status().isForbidden())
            .andExpect(jsonPath("$.code").value(403))
            .andExpect(jsonPath("$.message").isNotEmpty())
            .andExpect(jsonPath("$.data").isEmpty())
            .andExpect(jsonPath("$.error").isNotEmpty());
    }

    @Test
    void test_ListDetail_Should_Return200Code_When_ReceiveQueryParams() throws Exception {

        String token = getJWT(MODULE, LIST_ARTICLE_FAMILIES_TAX, AUDIENCE);

        List<ArticleTaxDetail> articleFamilies = List.of(getArticleDetail());

        when(articleFamilyService.findDetail(anyLong(), anyLong())).thenReturn(articleFamilies);

        this.mockMvc.perform(get("/v1/article-families/detail?product_type_id=1&article_id=1")
            .header(HttpHeaders.AUTHORIZATION, token)
            .contentType(MediaType.APPLICATION_JSON)
            .characterEncoding("utf-8"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.code").value(200))
            .andExpect(jsonPath("$.message").isNotEmpty())
            .andExpect(jsonPath("$.data").isNotEmpty())
            .andExpect(jsonPath("$.error").isEmpty());
    }


    @Test
    void test_ListDetail_Should_Return401Code_When_Unauthorized() throws Exception {
        this.mockMvc.perform(get("/v1/article-families/detail?product_type_id=1&article_id=1")
            .contentType(MediaType.APPLICATION_JSON)
            .characterEncoding("utf-8"))
            .andDo(print())
            .andExpect(status().isUnauthorized())
            .andExpect(jsonPath("$.code").value(401))
            .andExpect(jsonPath("$.message").isNotEmpty())
            .andExpect(jsonPath("$.data").isEmpty())
            .andExpect(jsonPath("$.error").isEmpty());
    }

    @Test
    void test_ListDetail_Should_Return403Code_When_InvalidPermission() throws Exception {

        String token = getJWT(MODULE, "Some permissions", AUDIENCE);

        this.mockMvc.perform(get("/v1/article-families/detail?product_type_id=1&article_id=1")
            .header(HttpHeaders.AUTHORIZATION, token)
            .contentType(MediaType.APPLICATION_JSON)
            .characterEncoding("utf-8"))
            .andDo(print())
            .andExpect(status().isForbidden())
            .andExpect(jsonPath("$.code").value(403))
            .andExpect(jsonPath("$.message").isNotEmpty())
            .andExpect(jsonPath("$.data").isEmpty())
            .andExpect(jsonPath("$.error").isNotEmpty());
    }

    @Test
    void test_Create_Should_Return401Code_When_Unauthorized() throws Exception {
        this.mockMvc.perform(post("/v1/article-families")
            .contentType(MediaType.APPLICATION_JSON)
            .characterEncoding("utf-8")
            .accept(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(getCreateArticleFamilyDTO())))
            .andDo(print())
            .andExpect(status().isUnauthorized())
            .andExpect(jsonPath("$.code").value(401))
            .andExpect(jsonPath("$.message").isNotEmpty())
            .andExpect(jsonPath("$.data").isEmpty())
            .andExpect(jsonPath("$.error").isEmpty());
    }

    @Test
    void test_Create_Should_Return403Code_When_InvalidPermission() throws Exception {

        String token = getJWT(MODULE, "Some permissions", AUDIENCE);

        this.mockMvc.perform(post("/v1/article-families")
            .header(HttpHeaders.AUTHORIZATION, token)
            .contentType(MediaType.APPLICATION_JSON)
            .characterEncoding("utf-8")
            .accept(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(getCreateArticleFamilyDTO())))
            .andDo(print())
            .andExpect(status().isForbidden())
            .andExpect(jsonPath("$.code").value(403))
            .andExpect(jsonPath("$.message").isNotEmpty())
            .andExpect(jsonPath("$.data").isEmpty())
            .andExpect(jsonPath("$.error").isNotEmpty());
    }

    @Test
    void test_Create_Should_Return404Code_When_FamilyNotFound() throws Exception {

        String token = getJWT(MODULE, CREATE_ARTICLE_FAMILIES, AUDIENCE);

        when(articleFamilyService.create(any(ArticleFamily.class)))
            .thenThrow(EntityNotFoundException.class);

        this.mockMvc.perform(post("/v1/article-families")
            .header(HttpHeaders.AUTHORIZATION, token)
            .contentType(MediaType.APPLICATION_JSON)
            .characterEncoding("utf-8")
            .accept(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(getCreateArticleFamilyDTO())))
            .andDo(print())
            .andExpect(status().isNotFound())
            .andExpect(jsonPath("$.code").value(404))
            .andExpect(jsonPath("$.message").isNotEmpty())
            .andExpect(jsonPath("$.data").isEmpty())
            .andExpect(jsonPath("$.error").isNotEmpty());
    }

    @Test
    void test_Create_Should_Return400Code_When_AlreadyExisting() throws Exception {

        String token = getJWT(MODULE, CREATE_ARTICLE_FAMILIES, AUDIENCE);

        when(articleFamilyService.create(any(ArticleFamily.class)))
            .thenThrow(BusinessRuleException.class);

        this.mockMvc.perform(post("/v1/article-families")
            .header(HttpHeaders.AUTHORIZATION, token)
            .contentType(MediaType.APPLICATION_JSON)
            .characterEncoding("utf-8")
            .accept(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(getCreateArticleFamilyDTO())))
            .andDo(print())
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.code").value(400))
            .andExpect(jsonPath("$.message").isNotEmpty())
            .andExpect(jsonPath("$.data").isEmpty())
            .andExpect(jsonPath("$.error").isNotEmpty());
    }

    @Test
    void test_Create_Should_Return200Code_When_ReceiveValidInput() throws Exception {

        String token = getJWT(MODULE, CREATE_ARTICLE_FAMILIES, AUDIENCE);

        when(articleFamilyService.create(any(ArticleFamily.class)))
            .thenReturn(getArticleFamily());

        this.mockMvc.perform(post("/v1/article-families")
            .header(HttpHeaders.AUTHORIZATION, token)
            .contentType(MediaType.APPLICATION_JSON)
            .characterEncoding("utf-8")
            .accept(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(getCreateArticleFamilyDTO())))
            .andDo(print())
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.code").value(201))
            .andExpect(jsonPath("$.message").isNotEmpty())
            .andExpect(jsonPath("$.data").isNotEmpty())
            .andExpect(jsonPath("$.error").isEmpty());
    }

    @Test
    void test_Delete_Should_Return204Code_When_ArticleFamilyIsDeleted() throws Exception {
        String token = getJWT(MODULE, DELETE_ARTICLE_FAMILIES, AUDIENCE);

        doNothing().when(articleFamilyService).delete(any());

        this.mockMvc.perform(delete("/v1/article-families/1")
            .header(HttpHeaders.AUTHORIZATION, token)
            .contentType(MediaType.APPLICATION_JSON)
            .characterEncoding("utf-8")
            .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.code").value(204))
            .andExpect(jsonPath("$.data").isEmpty())
            .andExpect(jsonPath("$.message").isNotEmpty())
            .andExpect(jsonPath("$.error").isEmpty());
    }

    @Test
    void test_Delete_Should_Return401Code_When_Unauthorized() throws Exception {
        this.mockMvc.perform(delete("/v1/article-families/1")
            .contentType(MediaType.APPLICATION_JSON)
            .characterEncoding("utf-8")
            .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isUnauthorized())
            .andExpect(jsonPath("$.code").value(401))
            .andExpect(jsonPath("$.message").isNotEmpty());
    }

    @Test
    void test_Delete_Should_Return403Code_When_Forbidden() throws Exception {
        String token = getJWT(MODULE, "per", AUDIENCE);

        this.mockMvc.perform(delete("/v1/article-families/1")
            .header(HttpHeaders.AUTHORIZATION, token)
            .contentType(MediaType.APPLICATION_JSON)
            .characterEncoding("utf-8")
            .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isForbidden())
            .andExpect(jsonPath("$.code").value(403))
            .andExpect(jsonPath("$.error").isNotEmpty());
    }

    @Test
    void test_Delete_Should_Return404Code_When_ArticleFamilyDoesNotExist() throws Exception {
        String token = getJWT(MODULE, DELETE_ARTICLE_FAMILIES, AUDIENCE);

        doThrow(new EntityNotFoundException()).when(articleFamilyService).delete(anyLong());

        this.mockMvc.perform(delete("/v1/article-families/1")
            .header(HttpHeaders.AUTHORIZATION, token)
            .contentType(MediaType.APPLICATION_JSON)
            .characterEncoding("utf-8")
            .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isNotFound())
            .andExpect(jsonPath("$.code").value(404))
            .andExpect(jsonPath("$.message").isNotEmpty());
    }

    private CreateArticleFamilyDTO getCreateArticleFamilyDTO() {
        return CreateArticleFamilyDTO.builder()
            .articleId(1L)
            .familyId(1L)
            .productTypeId(1L)
            .build();
    }

    private ArticleFamily getArticleFamily() {
        return ArticleFamily.builder()
            .articleId(1L)
            .family(new Family(1L))
            .productTypeId(1L)
            .build();
    }

    private ArticleTaxDetail getArticleDetail() {
        return ArticleTaxDetail.builder()
            .articleId(1L)
            .productTypeId(1L)
            .familyTaxTypeId(1L)
            .dicTaxId(1L)
            .value(8.00)
            .build();
    }

}
