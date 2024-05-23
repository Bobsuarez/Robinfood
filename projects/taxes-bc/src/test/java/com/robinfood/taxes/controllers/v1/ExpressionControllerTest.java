package com.robinfood.taxes.controllers.v1;

import static com.robinfood.taxes.constants.PermissionsConstants.CREATE_EXPRESSIONS;
import static com.robinfood.taxes.constants.PermissionsConstants.DELETE_EXPRESSIONS;
import static com.robinfood.taxes.constants.PermissionsConstants.LIST_EXPRESSIONS;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.robinfood.taxes.dto.v1.create.CreateExpressionDTO;
import com.robinfood.taxes.models.Expression;
import com.robinfood.taxes.services.ExpressionService;
import java.util.ArrayList;
import java.util.List;
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
@WebMvcTest(controllers = ExpressionController.class)
@TestPropertySource(properties = {
    "jwt.token.mod=expressions"
})
class ExpressionControllerTest extends BaseTestController {

    @MockBean
    private ExpressionService expressionService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @SpyBean
    private ModelMapper modelMapper;

    private static final String MODULE = "expressions";

    private static final String AUDIENCE = "internal";

    @Test
    void test_Create_Should_Return201Code_When_ExpressionIsCreated() throws Exception {
        String token = getJWT(MODULE, CREATE_EXPRESSIONS, AUDIENCE);

        when(expressionService.create(any(Expression.class))).thenReturn(getExpression());

        this.mockMvc.perform(post("/v1/expressions")
            .header(HttpHeaders.AUTHORIZATION, token)
            .contentType(MediaType.APPLICATION_JSON)
            .characterEncoding("utf-8")
            .accept(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(getCreateExpressionDTO())))
            .andDo(print())
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.code").value(201))
            .andExpect(jsonPath("$.data").isNotEmpty())
            .andExpect(jsonPath("$.message").isNotEmpty())
            .andExpect(jsonPath("$.error").isEmpty());
    }

    @Test
    void test_Create_Should_Return401Code_When_Unauthorized() throws Exception {
        this.mockMvc.perform(post("/v1/expressions")
            .contentType(MediaType.APPLICATION_JSON)
            .characterEncoding("utf-8")
            .accept(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(getCreateExpressionDTO())))
            .andDo(print())
            .andExpect(status().isUnauthorized())
            .andExpect(jsonPath("$.code").value(401))
            .andExpect(jsonPath("$.message").isNotEmpty());
    }

    @Test
    void test_Create_Should_Return403Code_When_Forbidden() throws Exception {
        String token = getJWT(MODULE, "per", AUDIENCE);

        this.mockMvc.perform(post("/v1/expressions")
            .header(HttpHeaders.AUTHORIZATION, token)
            .contentType(MediaType.APPLICATION_JSON)
            .characterEncoding("utf-8")
            .accept(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(getCreateExpressionDTO())))
            .andDo(print())
            .andExpect(status().isForbidden())
            .andExpect(jsonPath("$.code").value(403))
            .andExpect(jsonPath("$.error").isNotEmpty());
    }

    @Test
    void test_findAll_Should_ReturnOk_When_GivenValidParameters() throws Exception {
        String token = getJWT(MODULE, LIST_EXPRESSIONS, AUDIENCE);
        List<Expression> expressions = new ArrayList<>();
        expressions.add(getExpression());
        Page<Expression> expressionPage = new PageImpl<>(expressions);
        when(expressionService.findAll(anyBoolean(), any(Pageable.class))).thenReturn(expressionPage);

        this.mockMvc.perform(get("/v1/expressions")
            .header(HttpHeaders.AUTHORIZATION, token)
            .contentType(MediaType.APPLICATION_JSON)
            .characterEncoding("utf-8")
            .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.code").value(200))
            .andExpect(jsonPath("$.data").isNotEmpty())
            .andExpect(jsonPath("$.error").isEmpty());
    }

    @Test
    void test_delete_Should_DeleteExpression_When_GivenAValidId() throws Exception {
        String token = getJWT(MODULE, DELETE_EXPRESSIONS, AUDIENCE);
        doNothing().when(expressionService).delete(anyLong());

        this.mockMvc.perform(delete("/v1/expressions/1")
            .header(HttpHeaders.AUTHORIZATION, token)
            .contentType(MediaType.APPLICATION_JSON)
            .characterEncoding("utf-8")
            .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.code").value(204))
            .andExpect(jsonPath("$.error").isEmpty());
    }

    private CreateExpressionDTO getCreateExpressionDTO() throws JsonProcessingException {
        return CreateExpressionDTO.builder()
            .value("value")
            .description("description")
            .build();
    }

    private Expression getExpression() {
        Expression expression = Expression.builder()
            .value("value")
            .description("description")
            .status(1)
            .build();
        expression.setId(1L);
        return expression;
    }

}
