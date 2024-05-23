package com.robinfood.taxes.controllers.v1;

import static com.robinfood.taxes.constants.PermissionsConstants.DELETE_RULE_VARIABLES;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.robinfood.taxes.constants.PermissionsConstants;
import com.robinfood.taxes.dto.v1.create.CreateRuleVariableDTO;
import com.robinfood.taxes.exceptions.BusinessRuleException;
import com.robinfood.taxes.models.RuleVariable;
import com.robinfood.taxes.services.RuleVariableService;
import java.time.LocalDateTime;
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
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(controllers = RuleVariableController.class)
@TestPropertySource(properties = {
    "jwt.token.mod=rule-variables"
})
class RuleVariableControllerTest extends BaseTestController {

    @MockBean
    private RuleVariableService ruleVariableService;

    @Autowired
    private MockMvc mockMvc;

    @SpyBean
    private ModelMapper modelMapper;

    private final String modules = "rule-variables";

    private final String audience = "internal";

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void test_FindAll_Should_Return200_When_ValidData() throws Exception {

        String token = getJWT(modules, PermissionsConstants.LIST_RULE_VARIABLES, audience);

        List<RuleVariable> ruleVariableList = new ArrayList<>();
        RuleVariable ruleVariable = new RuleVariable();
        ruleVariable.setId(1L);
        ruleVariable.setName("Test Name Rule Variable");
        ruleVariable.setDescription("Test Description Rule Variable");
        ruleVariable.setType("ArrayNumber");
        ruleVariable.setPath("Test path");
        ruleVariable.setCreatedAt(LocalDateTime.now());
        ruleVariable.setUpdatedAt(LocalDateTime.now());
        ruleVariableList.add(ruleVariable);

        //Mock
        when(ruleVariableService.findAll()).thenReturn(ruleVariableList);

        //Call to code to test
        this.mockMvc.perform(get("/v1/rule-variables")
            .header(HttpHeaders.AUTHORIZATION, token)
            .contentType(MediaType.APPLICATION_JSON)
            .characterEncoding("utf-8")
            .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.code").value(200))
            .andExpect(jsonPath("$.error").isEmpty());
    }

    @Test
    void test_FindAll_Should_Return403Code_When_TokenInvalidPermissions() throws Exception {
        // Given
        String token = getJWT(modules, "test", audience);

        // Then
        this.mockMvc.perform(get("/v1/rule-variables")
            .header(HttpHeaders.AUTHORIZATION, token)
            .contentType(MediaType.APPLICATION_JSON)
            .characterEncoding("utf-8")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isForbidden())
            .andExpect(jsonPath("$.code").value(403))
            .andExpect(jsonPath("$.message").value("Forbidden"))
            .andExpect(jsonPath("$.error").value("Access is denied"))
            .andExpect(jsonPath("$.data").isEmpty());
    }

    @Test
    void test_FindAll_Should_Return401Code_When_TokenNoValid() throws Exception {

        // Then
        this.mockMvc.perform(get("/v1/rule-variables")
            .header(HttpHeaders.AUTHORIZATION, "")
            .contentType(MediaType.APPLICATION_JSON)
            .characterEncoding("utf-8")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isUnauthorized())
            .andExpect(jsonPath("$.code").value(401))
            .andExpect(jsonPath("$.message").value("Unauthorized request."))
            .andExpect(jsonPath("$.error").isEmpty())
            .andExpect(jsonPath("$.data").isEmpty());
    }


    @Test
    void test_Create_Should_Return201Code_When_RuleVariableIsCreated_WithPath() throws Exception {
        String token = getJWT(modules, PermissionsConstants.CREATE_RULE_VARIABLES, audience);

        var createRuleVariableDTO = getRuleService("path");

        String ruleVariableJson = objectMapper.writeValueAsString(createRuleVariableDTO);

        when(ruleVariableService.create(any(RuleVariable.class))).thenReturn(any(RuleVariable.class));

        mockMvc.perform(post("/v1/rule-variables")
            .header(HttpHeaders.AUTHORIZATION, token)
            .content(ruleVariableJson)
            .contentType(MediaType.APPLICATION_JSON)
            .characterEncoding("utf-8")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.code").value(201))
            .andExpect(jsonPath("$.message").value("Rule variable created successfully."))
            .andExpect(jsonPath("$.error").isEmpty())
            .andExpect(jsonPath("$.data").isNotEmpty());
    }

    @Test
    void test_Create_Should_Return201Code_When_RuleVariableIsCreated_WithValue() throws Exception {
        String token = getJWT(modules, PermissionsConstants.CREATE_RULE_VARIABLES, audience);

        var createRuleVariableDTO = getRuleService("value");
        String ruleVariableJson = objectMapper.writeValueAsString(createRuleVariableDTO);

        when(ruleVariableService.create(any(RuleVariable.class))).thenReturn(any(RuleVariable.class));

        mockMvc.perform(post("/v1/rule-variables")
            .header(HttpHeaders.AUTHORIZATION, token)
            .content(ruleVariableJson)
            .contentType(MediaType.APPLICATION_JSON)
            .characterEncoding("utf-8")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.code").value(201))
            .andExpect(jsonPath("$.message").value("Rule variable created successfully."))
            .andExpect(jsonPath("$.error").isEmpty())
            .andExpect(jsonPath("$.data").isNotEmpty());
    }

    @Test
    void test_Create_Should_Return400Code_When_ValidateDoesNotWorks()
        throws Exception {
        String token = getJWT(modules, PermissionsConstants.CREATE_RULE_VARIABLES, audience);

        var createRuleVariableDTO = getRuleService("");
        String ruleVariableJson = objectMapper.writeValueAsString(createRuleVariableDTO);

        when(ruleVariableService.create(any(RuleVariable.class))).thenThrow(new BusinessRuleException(""));

        mockMvc.perform(post("/v1/rule-variables")
            .header(HttpHeaders.AUTHORIZATION, token)
            .content(ruleVariableJson)
            .contentType(MediaType.APPLICATION_JSON)
            .characterEncoding("utf-8")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.code").value(400));
    }

    @Test
    void test_Create_Should_Return403Code_When_TokenInvalidPermissions() throws Exception {
        // Given
        String token = getJWT(modules, "", audience);

        var createRuleVariableDTO = getRuleService("value");
        String ruleVariableJson = objectMapper.writeValueAsString(createRuleVariableDTO);


        // Then
        mockMvc.perform(post("/v1/rule-variables")
            .header(HttpHeaders.AUTHORIZATION, token)
            .content(ruleVariableJson)
            .contentType(MediaType.APPLICATION_JSON)
            .characterEncoding("utf-8")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isForbidden())
            .andExpect(jsonPath("$.code").value(403))
            .andExpect(jsonPath("$.message").value("Forbidden"))
            .andExpect(jsonPath("$.error").value("Access is denied"))
            .andExpect(jsonPath("$.data").isEmpty());
    }

    @Test
    void test_Create_Should_Return401Code_When_Unauthorized() throws Exception {

        var createRuleVariableDTO = getRuleService("value");
        String ruleVariableJson = objectMapper.writeValueAsString(createRuleVariableDTO);

        // Then
        mockMvc.perform(post("/v1/rule-variables")
            .content(ruleVariableJson)
            .contentType(MediaType.APPLICATION_JSON)
            .characterEncoding("utf-8")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isUnauthorized())
            .andExpect(jsonPath("$.code").value(401))
            .andExpect(jsonPath("$.message").isNotEmpty());
    }

    @Test
    void test_Delete_Should_Return204_When_ExistingIdReceived() throws Exception {

        String token = getJWT(modules, DELETE_RULE_VARIABLES, audience);

        doNothing().when(ruleVariableService).delete(anyLong());

        this.mockMvc.perform(delete("/v1/rule-variables/1")
            .header(HttpHeaders.AUTHORIZATION, token))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.code").value(204))
            .andExpect(jsonPath("$.error").isEmpty())
            .andExpect(jsonPath("$.data").isEmpty())
            .andExpect(jsonPath("$.message").isNotEmpty());
    }

    @Test
    void test_Delete_Should_Return401_When_InvalidToken() throws Exception {

        this.mockMvc.perform(delete("/v1/rule-variables/1")
            .header(HttpHeaders.AUTHORIZATION, "token"))
            .andDo(print())
            .andExpect(status().isUnauthorized())
            .andExpect(jsonPath("$.code").value(401))
            .andExpect(jsonPath("$.error").isEmpty())
            .andExpect(jsonPath("$.data").isEmpty())
            .andExpect(jsonPath("$.message").isNotEmpty());
    }

    @Test
    void test_Delete_Should_Return403_When_InvalidPermission() throws Exception {

        String token = getJWT(modules, "SomePermission", audience);

        doNothing().when(ruleVariableService).delete(anyLong());

        this.mockMvc.perform(delete("/v1/rule-variables/1")
            .header(HttpHeaders.AUTHORIZATION, token))
            .andDo(print())
            .andExpect(status().isForbidden())
            .andExpect(jsonPath("$.code").value(403))
            .andExpect(jsonPath("$.error").isNotEmpty())
            .andExpect(jsonPath("$.data").isEmpty())
            .andExpect(jsonPath("$.message").isNotEmpty());
    }

    private CreateRuleVariableDTO getRuleService(String pathOrValue) {
        CreateRuleVariableDTO createRuleVariableDTO = null;
        if (pathOrValue.equals("path")) {
            createRuleVariableDTO = CreateRuleVariableDTO.builder()
                .name("Test Name Rule Variable")
                .description("Test Description Rule Variable")
                .type("ArrayString")
                .path("Test path")
                .value(null)
                .build();
        } else if (pathOrValue.equals("value")) {
            createRuleVariableDTO = CreateRuleVariableDTO.builder()
                .name("Test Name Rule Variable")
                .description("Test Description Rule Variable")
                .type("ArrayString")
                .path(null)
                .value("Test null")
                .build();
        }

        return createRuleVariableDTO;
    }

}
