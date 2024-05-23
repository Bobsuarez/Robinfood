package com.robinfood.taxes.controllers.v1;

import static com.robinfood.taxes.constants.PermissionsConstants.CREATE_EXPRESSION_FORMULA_VARIABLES;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.robinfood.taxes.dto.v1.create.CreateExpressionFormulaVariableDTO;
import com.robinfood.taxes.models.Expression;
import com.robinfood.taxes.models.ExpressionFormulaVariable;
import com.robinfood.taxes.models.FormulaVariable;
import com.robinfood.taxes.services.ExpressionFormulaVariableService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(controllers = ExpressionFormulaVariableController.class)
@TestPropertySource(properties = {
    "jwt.token.mod=expression_formula_variables"
})
class ExpressionFormulaVariableControllerTest extends BaseTestController {

    @MockBean
    private ExpressionFormulaVariableService expressionFormulaVariableService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @SpyBean
    private ModelMapper modelMapper;

    private static final String MODULE = "expression_formula_variables";

    private static final String AUDIENCE = "internal";

    @Test
    void test_Create_Should_Return201Code_When_ExpressionFormulaVariableIsCreated()
        throws Exception {

        String token = getJWT(MODULE, CREATE_EXPRESSION_FORMULA_VARIABLES, AUDIENCE);

        when(expressionFormulaVariableService.create(any(ExpressionFormulaVariable.class)))
            .thenReturn(getExpressionFormulaVariable());

        this.mockMvc.perform(post("/v1/expression-formula-variables")
            .header(HttpHeaders.AUTHORIZATION, token)
            .contentType(MediaType.APPLICATION_JSON)
            .characterEncoding("utf-8")
            .accept(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(getCreateExpressionFormulaVariableDTO())))
            .andDo(print())
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.code").value(201))
            .andExpect(jsonPath("$.data").isNotEmpty())
            .andExpect(jsonPath("$.message").isNotEmpty())
            .andExpect(jsonPath("$.error").isEmpty());
    }

    @Test
    void test_Create_Should_Return401Code_When_Unauthorized() throws Exception {
        this.mockMvc.perform(post("/v1/expression-formula-variables")
            .contentType(MediaType.APPLICATION_JSON)
            .characterEncoding("utf-8")
            .accept(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(getCreateExpressionFormulaVariableDTO())))
            .andDo(print())
            .andExpect(status().isUnauthorized())
            .andExpect(jsonPath("$.code").value(401))
            .andExpect(jsonPath("$.message").isNotEmpty());
    }

    @Test
    void test_Create_Should_Return403Code_When_Forbidden() throws Exception {
        String token = getJWT(MODULE, "per", AUDIENCE);

        this.mockMvc.perform(post("/v1/expression-formula-variables")
            .header(HttpHeaders.AUTHORIZATION, token)
            .contentType(MediaType.APPLICATION_JSON)
            .characterEncoding("utf-8")
            .accept(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(getCreateExpressionFormulaVariableDTO())))
            .andDo(print())
            .andExpect(status().isForbidden())
            .andExpect(jsonPath("$.code").value(403))
            .andExpect(jsonPath("$.error").isNotEmpty());
    }

    private CreateExpressionFormulaVariableDTO getCreateExpressionFormulaVariableDTO()
        throws JsonProcessingException {
        return CreateExpressionFormulaVariableDTO.builder()
            .expressionId(1L)
            .formulaVariableId(1L)
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

    private FormulaVariable getFormulaVariable() {
        FormulaVariable formulaVariable = FormulaVariable.builder()
            .type("number")
            .name("variable")
            .value("1")
            .description("description")
            .build();
        formulaVariable.setId(1L);
        return formulaVariable;
    }

    private ExpressionFormulaVariable getExpressionFormulaVariable() {
        ExpressionFormulaVariable expressionFormulaVariable = ExpressionFormulaVariable.builder()
            .expression(getExpression())
            .formulaVariable(getFormulaVariable())
            .build();
        expressionFormulaVariable.setId(1L);
        return expressionFormulaVariable;
    }

}
