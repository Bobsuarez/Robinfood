package com.robinfood.taxes.controllers.v1;

import static com.robinfood.taxes.constants.PermissionsConstants.CREATE_TAX_RULES;
import static com.robinfood.taxes.constants.PermissionsConstants.LIST_TAX_RULES;
import static com.robinfood.taxes.constants.PermissionsConstants.UPDATE_TAX_RULES;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static com.robinfood.taxes.constants.PermissionsConstants.DELETE_TAX_RULES;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.robinfood.taxes.dto.v1.create.CreateTaxRuleDTO;
import com.robinfood.taxes.dto.v1.update.UpdateTaxRuleDTO;
import com.robinfood.taxes.exceptions.BusinessRuleException;
import com.robinfood.taxes.models.RuleType;
import com.robinfood.taxes.models.RuleVariable;
import com.robinfood.taxes.models.Tax;
import com.robinfood.taxes.models.TaxRule;
import com.robinfood.taxes.services.TaxRuleService;
import java.util.Collections;
import javax.persistence.EntityNotFoundException;
import org.hamcrest.Matchers;
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
@WebMvcTest(controllers = TaxRuleController.class)
@TestPropertySource(properties = {
    "jwt.token.mod=tax-rules"
})
class TaxRuleControllerTest extends BaseTestController {

    @MockBean
    private TaxRuleService taxRuleService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @SpyBean
    private ModelMapper modelMapper;

    private static final String MODULE = "tax-rules";

    private static final String AUDIENCE = "internal";

    @Test
    void test_List_Should_Return200_When_MethodIsCalled() throws Exception {

        Page<TaxRule> taxRules = new PageImpl<>(
            Collections.singletonList(getTaxRule()));

        String token = getJWT(MODULE, LIST_TAX_RULES, AUDIENCE);

        when(taxRuleService.list(any(), any(), any(Pageable.class)))
            .thenReturn(taxRules);

        //Call
        this.mockMvc.perform(get("/v1/tax-rules")
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
    void test_List_Should_Return401_When_InvalidToken() throws Exception {

        //Call to code to test
        this.mockMvc.perform(get("/v1/tax-rules")
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
    void test_List_Should_Return403_When_MethodIsCalled() throws Exception {

        String token = getJWT(MODULE, "SomePermission", AUDIENCE);

        //Call to code to test
        this.mockMvc.perform(get("/v1/tax-rules")
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
    void test_Create_Should_Return201Code_When_TaxRuleIsCreated() throws Exception {
        String token = getJWT(MODULE, CREATE_TAX_RULES, AUDIENCE);

        when(taxRuleService.create(any(TaxRule.class))).thenReturn(getTaxRule());

        this.mockMvc.perform(post("/v1/tax-rules")
            .header(HttpHeaders.AUTHORIZATION, token)
            .contentType(MediaType.APPLICATION_JSON)
            .characterEncoding("utf-8")
            .accept(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(getCreateTaxRuleDTO())))
            .andDo(print())
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.code").value(201))
            .andExpect(jsonPath("$.data").isNotEmpty())
            .andExpect(jsonPath("$.message").isNotEmpty())
            .andExpect(jsonPath("$.error").isEmpty());
    }

    @Test
    void test_Create_Should_Return400Code_When_TaxRuleWithTaxAndRuleTypeAndLeftAndRightExist()
        throws Exception {
        String token = getJWT(MODULE, CREATE_TAX_RULES, AUDIENCE);

        when(taxRuleService.create(any(TaxRule.class))).thenThrow(new BusinessRuleException(
            "There already exist a Tax Rule with the same tax, rule type, "
                + "left variable, and right variable."));

        this.mockMvc.perform(post("/v1/tax-rules")
            .header(HttpHeaders.AUTHORIZATION, token)
            .contentType(MediaType.APPLICATION_JSON)
            .characterEncoding("utf-8")
            .accept(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(getCreateTaxRuleDTO())))
            .andDo(print())
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.code").value(400))
            .andExpect(jsonPath("$.error").isNotEmpty());
    }

    @Test
    void test_Create_Should_Return400Code_When_LeftAndRightAreTheSame()
        throws Exception {
        String token = getJWT(MODULE, CREATE_TAX_RULES, AUDIENCE);

        when(taxRuleService.create(any(TaxRule.class))).thenThrow(new BusinessRuleException(
            "Left and right variables must not be the same."));

        CreateTaxRuleDTO createTaxRuleDTO = getCreateTaxRuleDTO();
        createTaxRuleDTO.setLeftVariableId(1L);
        createTaxRuleDTO.setRightVariableId(1L);

        this.mockMvc.perform(post("/v1/tax-rules")
            .header(HttpHeaders.AUTHORIZATION, token)
            .contentType(MediaType.APPLICATION_JSON)
            .characterEncoding("utf-8")
            .accept(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(getCreateTaxRuleDTO())))
            .andDo(print())
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.code").value(400))
            .andExpect(jsonPath("$.error").isNotEmpty());
    }

    @Test
    void test_Create_Should_Return401Code_When_Unauthorized() throws Exception {
        this.mockMvc.perform(post("/v1/tax-rules")
            .contentType(MediaType.APPLICATION_JSON)
            .characterEncoding("utf-8")
            .accept(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(getCreateTaxRuleDTO())))
            .andDo(print())
            .andExpect(status().isUnauthorized())
            .andExpect(jsonPath("$.code").value(401))
            .andExpect(jsonPath("$.message").isNotEmpty());
    }

    @Test
    void test_Create_Should_Return403Code_When_Forbidden() throws Exception {
        String token = getJWT(MODULE, "per", AUDIENCE);

        this.mockMvc.perform(post("/v1/tax-rules")
            .header(HttpHeaders.AUTHORIZATION, token)
            .contentType(MediaType.APPLICATION_JSON)
            .characterEncoding("utf-8")
            .accept(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(getCreateTaxRuleDTO())))
            .andDo(print())
            .andExpect(status().isForbidden())
            .andExpect(jsonPath("$.code").value(403))
            .andExpect(jsonPath("$.error").isNotEmpty());
    }

    @Test
    void test_Create_Should_Return404Code_When_TaxDoesNotExist()
        throws Exception {
        String token = getJWT(MODULE, CREATE_TAX_RULES, AUDIENCE);

        when(taxRuleService.create(any(TaxRule.class))).thenThrow(new EntityNotFoundException(
            "Tax with ID 1 not found."));

        this.mockMvc.perform(post("/v1/tax-rules")
            .header(HttpHeaders.AUTHORIZATION, token)
            .contentType(MediaType.APPLICATION_JSON)
            .characterEncoding("utf-8")
            .accept(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(getCreateTaxRuleDTO())))
            .andDo(print())
            .andExpect(status().isNotFound())
            .andExpect(jsonPath("$.code").value(404))
            .andExpect(jsonPath("$.message").isNotEmpty());
    }

    @Test
    void test_Create_Should_Return404Code_When_RuleTypeDoesNotExist()
        throws Exception {
        String token = getJWT(MODULE, CREATE_TAX_RULES, AUDIENCE);

        when(taxRuleService.create(any(TaxRule.class))).thenThrow(new EntityNotFoundException(
            "Rule Type with ID 1 not found."));

        this.mockMvc.perform(post("/v1/tax-rules")
            .header(HttpHeaders.AUTHORIZATION, token)
            .contentType(MediaType.APPLICATION_JSON)
            .characterEncoding("utf-8")
            .accept(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(getCreateTaxRuleDTO())))
            .andDo(print())
            .andExpect(status().isNotFound())
            .andExpect(jsonPath("$.code").value(404))
            .andExpect(jsonPath("$.message").isNotEmpty());
    }

    @Test
    void test_Create_Should_Return404Code_When_RuleVariableDoesNotExist()
        throws Exception {
        String token = getJWT(MODULE, CREATE_TAX_RULES, AUDIENCE);

        when(taxRuleService.create(any(TaxRule.class))).thenThrow(new EntityNotFoundException(
            "Rule Variable with ID 1 not found."));

        this.mockMvc.perform(post("/v1/tax-rules")
            .header(HttpHeaders.AUTHORIZATION, token)
            .contentType(MediaType.APPLICATION_JSON)
            .characterEncoding("utf-8")
            .accept(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(getCreateTaxRuleDTO())))
            .andDo(print())
            .andExpect(status().isNotFound())
            .andExpect(jsonPath("$.code").value(404))
            .andExpect(jsonPath("$.message").isNotEmpty());
    }

    @Test
    void test_Update_Should_Return200Code_When_TaxRuleIsUpdated() throws Exception {
        String token = getJWT(MODULE, UPDATE_TAX_RULES, AUDIENCE);

        when(taxRuleService.update(anyLong(), any(TaxRule.class))).thenReturn(getTaxRule());

        this.mockMvc.perform(put("/v1/tax-rules/1")
            .header(HttpHeaders.AUTHORIZATION, token)
            .contentType(MediaType.APPLICATION_JSON)
            .characterEncoding("utf-8")
            .accept(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(getUpdateTaxRuleDTO())))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.code").value(200))
            .andExpect(jsonPath("$.data").isNotEmpty())
            .andExpect(jsonPath("$.message").isNotEmpty())
            .andExpect(jsonPath("$.error").isEmpty());
    }

    @Test
    void test_Update_Should_Return200Code_When_TaxRuleFieldIsUpdated() throws Exception {
        String token = getJWT(MODULE, UPDATE_TAX_RULES, AUDIENCE);

        UpdateTaxRuleDTO updateTaxRuleDTO = UpdateTaxRuleDTO.builder()
            .taxId(1L)
            .build();

        when(taxRuleService.update(anyLong(), any(TaxRule.class))).thenReturn(getTaxRule());

        this.mockMvc.perform(put("/v1/tax-rules/1")
            .header(HttpHeaders.AUTHORIZATION, token)
            .contentType(MediaType.APPLICATION_JSON)
            .characterEncoding("utf-8")
            .accept(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(updateTaxRuleDTO)))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.code").value(200))
            .andExpect(jsonPath("$.data").isNotEmpty())
            .andExpect(jsonPath("$.message").isNotEmpty())
            .andExpect(jsonPath("$.error").isEmpty());
    }

    @Test
    void test_Update_Should_Return401Code_When_Unauthorized() throws Exception {
        this.mockMvc.perform(put("/v1/tax-rules/1")
            .contentType(MediaType.APPLICATION_JSON)
            .characterEncoding("utf-8")
            .accept(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(getCreateTaxRuleDTO())))
            .andDo(print())
            .andExpect(status().isUnauthorized())
            .andExpect(jsonPath("$.code").value(401))
            .andExpect(jsonPath("$.message").isNotEmpty());
    }

    @Test
    void test_Update_Should_Return403Code_When_Forbidden() throws Exception {
        String token = getJWT(MODULE, "per", AUDIENCE);

        this.mockMvc.perform(put("/v1/tax-rules/1")
            .header(HttpHeaders.AUTHORIZATION, token)
            .contentType(MediaType.APPLICATION_JSON)
            .characterEncoding("utf-8")
            .accept(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(getUpdateTaxRuleDTO())))
            .andDo(print())
            .andExpect(status().isForbidden())
            .andExpect(jsonPath("$.code").value(403))
            .andExpect(jsonPath("$.error").isNotEmpty());
    }

    @Test
    void test_Update_Should_Return404Code_When_EntityDoesNotExist()
        throws Exception {
        String token = getJWT(MODULE, UPDATE_TAX_RULES, AUDIENCE);

        when(taxRuleService.update(anyLong(), any(TaxRule.class)))
            .thenThrow(EntityNotFoundException.class);

        this.mockMvc.perform(put("/v1/tax-rules/1")
            .header(HttpHeaders.AUTHORIZATION, token)
            .contentType(MediaType.APPLICATION_JSON)
            .characterEncoding("utf-8")
            .accept(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(getUpdateTaxRuleDTO())))
            .andDo(print())
            .andExpect(status().isNotFound())
            .andExpect(jsonPath("$.code").value(404))
            .andExpect(jsonPath("$.message").isNotEmpty());
    }

    @Test
    void test_Update_Should_Return400Code_When_BusinessRuleException()
        throws Exception {
        String token = getJWT(MODULE, UPDATE_TAX_RULES, AUDIENCE);

        when(taxRuleService.update(anyLong(),any(TaxRule.class)))
            .thenThrow(BusinessRuleException.class);

        this.mockMvc.perform(put("/v1/tax-rules/1")
            .header(HttpHeaders.AUTHORIZATION, token)
            .contentType(MediaType.APPLICATION_JSON)
            .characterEncoding("utf-8")
            .accept(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(getUpdateTaxRuleDTO())))
            .andDo(print())
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.code").value(400))
            .andExpect(jsonPath("$.error").isNotEmpty());
    }

    @Test
    void test_Delete_Should_Return204_When_ExistingTaxRule() throws Exception {

        String token = getJWT(MODULE, DELETE_TAX_RULES, AUDIENCE);

        doNothing().when(taxRuleService).delete(anyLong());

        this.mockMvc.perform(delete("/v1/tax-rules/1")
            .header(HttpHeaders.AUTHORIZATION, token)
            .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.code").value(204))
            .andExpect(jsonPath("$.message").isNotEmpty())
            .andExpect(jsonPath("$.data").isEmpty())
            .andExpect(jsonPath("$.error").isEmpty());

    }

    @Test
    void test_Delete_Should_Return404_When_FamilyIdNotFound() throws Exception {

        String token = getJWT(MODULE, DELETE_TAX_RULES, AUDIENCE);

        doThrow(EntityNotFoundException.class).when(taxRuleService).delete(anyLong());

        this.mockMvc.perform(delete("/v1/tax-rules/1")
            .header(HttpHeaders.AUTHORIZATION, token)
            .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isNotFound())
            .andExpect(jsonPath("$.code").value(404))
            .andExpect(jsonPath("$.message").isNotEmpty())
            .andExpect(jsonPath("$.data").isEmpty())
            .andExpect(jsonPath("$.error").isNotEmpty());

    }

    @Test
    void test_Delete_Should_Return401_When_InvalidToken() throws Exception {

        this.mockMvc.perform(delete("/v1/tax-rules/1")
            .header(HttpHeaders.AUTHORIZATION, "token")
            .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isUnauthorized())
            .andExpect(jsonPath("$.code").value(401))
            .andExpect(jsonPath("$.message").isNotEmpty())
            .andExpect(jsonPath("$.data").isEmpty())
            .andExpect(jsonPath("$.error").isEmpty());

    }

    private CreateTaxRuleDTO getCreateTaxRuleDTO() throws JsonProcessingException {
        return CreateTaxRuleDTO.builder()
            .taxId(1L)
            .ruleTypeId(1L)
            .leftVariableId(1L)
            .rightVariableId(2L)
            .build();
    }

    private UpdateTaxRuleDTO getUpdateTaxRuleDTO() {
        return UpdateTaxRuleDTO.builder()
            .taxId(1L)
            .ruleTypeId(1L)
            .leftVariableId(1L)
            .rightVariableId(2L)
            .build();
    }

    private TaxRule getTaxRule() {
        TaxRule taxRule = TaxRule.builder()
            .tax(new Tax(1L))
            .ruleType(new RuleType(1L))
            .leftVariable(new RuleVariable(1L))
            .rightVariable(new RuleVariable(2L))
            .status(1)
            .build();
        taxRule.setId(1L);
        return taxRule;
    }

}
