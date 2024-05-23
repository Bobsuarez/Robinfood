package com.robinfood.taxes.controllers.v1;

import static com.robinfood.taxes.constants.PermissionsConstants.DELETE_FORMULA_VARIABLES;
import static com.robinfood.taxes.constants.PermissionsConstants.LIST_FORMULA_VARIABLES;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.robinfood.taxes.constants.PermissionsConstants;
import com.robinfood.taxes.exceptions.BusinessRuleException;
import com.robinfood.taxes.models.FormulaVariable;
import com.robinfood.taxes.services.FormulaVariableService;
import java.util.Collections;
import javax.persistence.EntityNotFoundException;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = FormulaVariableController.class)
@TestPropertySource(properties = {
    "jwt.token.mod=taxes_bc_formula_variables"
})
class FormulaVariableControllerTest extends BaseTestController {

    @MockBean
    private FormulaVariableService formulaVariableService;

    @Autowired
    private MockMvc mockMvc;

    @SpyBean
    private ModelMapper modelMapper;

    private String modules = "taxes_bc_formula_variables";

    private String audience = "internal";

    @Test
    void test_FindAll_Should_Return200_When_MethodIsCalled() throws Exception {

        Page<FormulaVariable> formulaVariables = new PageImpl<>(
            Collections.singletonList(getFormulaVariable()));

        String token = getJWT(modules, LIST_FORMULA_VARIABLES, audience);

        when(formulaVariableService.findAll(any(Pageable.class)))
            .thenReturn(formulaVariables);

        //Call to code to test
        this.mockMvc.perform(get("/v1/formula-variables")
            .header(HttpHeaders.AUTHORIZATION, token)
            .contentType(MediaType.APPLICATION_JSON)
            .characterEncoding("utf-8")
            .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.data").isNotEmpty())
            .andExpect(jsonPath("$.code").value(200))
            .andExpect(jsonPath("$.error").isEmpty())
            .andExpect(content().string(Matchers.containsString("number")))
            .andExpect(content().string(Matchers.containsString("size")));

    }

    @Test
    void test_FindAll_Should_Return401_When_InvalidToken() throws Exception {

        //Call to code to test
        this.mockMvc.perform(get("/v1/formula-variables")
            .header(HttpHeaders.AUTHORIZATION, "token")
            .contentType(MediaType.APPLICATION_JSON)
            .characterEncoding("utf-8")
            .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isUnauthorized())
            .andExpect(jsonPath("$.code").value(401))
            .andExpect(jsonPath("$.data").isEmpty())
            .andExpect(jsonPath("$.message").isNotEmpty())
            .andExpect(jsonPath("$.error").isEmpty());

    }

    @Test
    void test_FindAll_Should_Return403_When_MethodIsCalled() throws Exception {

        String token = getJWT(modules, "SomePermission", audience);

        //Call to code to test
        this.mockMvc.perform(get("/v1/formula-variables")
            .header(HttpHeaders.AUTHORIZATION, token)
            .contentType(MediaType.APPLICATION_JSON)
            .characterEncoding("utf-8")
            .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isForbidden())
            .andExpect(jsonPath("$.code").value(403))
            .andExpect(jsonPath("$.data").isEmpty())
            .andExpect(jsonPath("$.message").isNotEmpty())
            .andExpect(jsonPath("$.error").isNotEmpty());

    }

    @Test
    void test_Create_Should_Return201_When_validDTOAndRecordInserted()
        throws Exception {

        //Data
        String token = getJWT(modules, PermissionsConstants.CREATE_FORMULA_VARIABLES, audience);

        String json = "{\n"
            + "  \"value\": \"1\",\n"
            + "  \"name\": \"name\",\n"
            + "  \"description\": \"description\",\n"
            + "  \"type\": \"Number\" \n"
            + "}";

        //Mock
        when(formulaVariableService.create(any(FormulaVariable.class)))
            .thenReturn(getFormulaVariable());

        //Call
        this.mockMvc.perform(post("/v1/formula-variables")
            .header(HttpHeaders.AUTHORIZATION, token)
            .content(json)
            .contentType(MediaType.APPLICATION_JSON)
            .characterEncoding("utf-8")
            .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.code").value(201))
            .andExpect(jsonPath("$.data").isNotEmpty())
            .andExpect(jsonPath("$.error").isEmpty());
    }

    @Test
    void test_Create_Should_Return400_When_InvalidDTO()
        throws Exception {

        //Data
        String token = getJWT(modules, PermissionsConstants.CREATE_FORMULA_VARIABLES, audience);

        String json = "{\n"
            + "  \"value\": \"\",\n"
            + "  \"name\": \"name\",\n"
            + "  \"description\": \"description\",\n"
            + "  \"type\": \"Number\" \n"
            + "}";

        //Mock
        when(formulaVariableService.create(any(FormulaVariable.class)))
            .thenReturn(getFormulaVariable());

        //Call
        this.mockMvc.perform(post("/v1/formula-variables")
            .header(HttpHeaders.AUTHORIZATION, token)
            .content(json)
            .contentType(MediaType.APPLICATION_JSON)
            .characterEncoding("utf-8")
            .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.code").value(400))
            .andExpect(jsonPath("$.message").value("Bad Request"))
            .andExpect(jsonPath("$.data").isNotEmpty())
            .andExpect(jsonPath("$.error").isNotEmpty());
    }

    @Test
    void test_Create_Should_Return400_When_ErrorOnService()
        throws Exception {

        //Data
        String token = getJWT(modules, PermissionsConstants.CREATE_FORMULA_VARIABLES, audience);

        String json = "{\n"
            + "  \"value\": \"1\",\n"
            + "  \"name\": \"name\",\n"
            + "  \"description\": \"description\",\n"
            + "  \"type\": \"Number\" \n"
            + "}";

        //Mock
        when(formulaVariableService.create(any(FormulaVariable.class)))
            .thenThrow(new BusinessRuleException("Error"));

        //Call
        this.mockMvc.perform(post("/v1/formula-variables")
            .header(HttpHeaders.AUTHORIZATION, token)
            .content(json)
            .contentType(MediaType.APPLICATION_JSON)
            .characterEncoding("utf-8")
            .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.code").value(400))
            .andExpect(jsonPath("$.message").value("Bad Request"))
            .andExpect(jsonPath("$.data").isEmpty())
            .andExpect(jsonPath("$.error").isNotEmpty());

    }

    @Test
    void test_Create_Should_Return401_When_InvalidToken()
        throws Exception {

        //Data
        String json = "{\n"
            + "  \"value\": \"1\",\n"
            + "  \"name\": \"name\",\n"
            + "  \"description\": \"description\",\n"
            + "  \"type\": \"Number\" \n"
            + "}";

        //Call
        this.mockMvc.perform(post("/v1/formula-variables")
            .header(HttpHeaders.AUTHORIZATION, "token")
            .content(json)
            .contentType(MediaType.APPLICATION_JSON)
            .characterEncoding("utf-8")
            .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isUnauthorized())
            .andExpect(jsonPath("$.code").value(401))
            .andExpect(jsonPath("$.message").value("Unauthorized request."))
            .andExpect(jsonPath("$.data").isEmpty())
            .andExpect(jsonPath("$.error").isEmpty());
    }

    @Test
    void test_Create_Should_Return401_When_InvalidPermissionToken()
        throws Exception {

        //Data
        String token = getJWT(modules, "", audience);

        String json = "{\n"
            + "  \"value\": \"1\",\n"
            + "  \"name\": \"name\",\n"
            + "  \"description\": \"description\",\n"
            + "  \"type\": \"Number\" \n"
            + "}";

        //Call
        this.mockMvc.perform(post("/v1/formula-variables")
            .header(HttpHeaders.AUTHORIZATION, token)
            .content(json)
            .contentType(MediaType.APPLICATION_JSON)
            .characterEncoding("utf-8")
            .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isForbidden())
            .andExpect(jsonPath("$.code").value(403))
            .andExpect(jsonPath("$.message").value("Forbidden"))
            .andExpect(jsonPath("$.data").isEmpty())
            .andExpect(jsonPath("$.error").isNotEmpty());
    }

    @Test
    void test_Delete_Should_Return204Code_When_FormulaVariableIsDeleted() throws Exception {
        String token = getJWT(modules, DELETE_FORMULA_VARIABLES, audience);

        doNothing().when(formulaVariableService).delete(any());

        this.mockMvc.perform(delete("/v1/formula-variables/1")
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
    void test_Delete_Should_Return400Code_When_FormulaVariableIsAssociatedToExpressionFormulaVariable() throws Exception {
        String token = getJWT(modules, DELETE_FORMULA_VARIABLES, audience);

        doThrow(new BusinessRuleException("")).when(formulaVariableService).delete(anyLong());

        this.mockMvc.perform(delete("/v1/formula-variables/1")
            .header(HttpHeaders.AUTHORIZATION, token)
            .contentType(MediaType.APPLICATION_JSON)
            .characterEncoding("utf-8")
            .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.code").value(400))
            .andExpect(jsonPath("$.message").isNotEmpty());
    }

    @Test
    void test_Delete_Should_Return401Code_When_Unauthorized() throws Exception {
        this.mockMvc.perform(delete("/v1/formula-variables/1")
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
        String token = getJWT(modules, "per", audience);

        this.mockMvc.perform(delete("/v1/formula-variables/1")
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
    void test_Delete_Should_Return404Code_When_FormulaVariableDoesNotExist() throws Exception {
        String token = getJWT(modules, DELETE_FORMULA_VARIABLES, audience);

        doThrow(new EntityNotFoundException()).when(formulaVariableService).delete(anyLong());

        this.mockMvc.perform(delete("/v1/formula-variables/1")
            .header(HttpHeaders.AUTHORIZATION, token)
            .contentType(MediaType.APPLICATION_JSON)
            .characterEncoding("utf-8")
            .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isNotFound())
            .andExpect(jsonPath("$.code").value(404))
            .andExpect(jsonPath("$.message").isNotEmpty());
    }

    private FormulaVariable getFormulaVariable() {
        return FormulaVariable.builder()
            .name("Name")
            .description("Desc")
            .type("Type")
            .value("Val")
            .build();
    }

}
