package com.robinfood.taxes.controllers.v1;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.robinfood.taxes.constants.PermissionsConstants;
import com.robinfood.taxes.exceptions.BusinessRuleException;
import com.robinfood.taxes.models.Expression;
import com.robinfood.taxes.models.Family;
import com.robinfood.taxes.models.Tax;
import com.robinfood.taxes.models.TaxType;
import com.robinfood.taxes.services.TaxService;
import java.math.BigDecimal;
import java.util.ArrayList;
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
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(controllers = TaxController.class)
@TestPropertySource(properties = {
    "jwt.token.mod=taxes"
})
class TaxControllerTest extends BaseTestController {

    @MockBean
    private TaxService taxService;

    @Autowired
    private MockMvc mockMvc;

    @SpyBean
    private ModelMapper modelMapper;

    @SpyBean
    private ObjectMapper objectMapper;

    private final String modules = "taxes";

    private final String audience = "internal";


    @Test
    void test_List_Should_Return200_When_InvokeWithFamilyId() throws Exception {

        String token = getJWT(modules, PermissionsConstants.LIST_TAXES, audience);

        List<Tax> taxList = new ArrayList<>();
        taxList.add(getTax());

        //Mock
        when(taxService.list(anyLong()))
            .thenReturn(taxList);
        this.mockMvc.perform(get("/v1/taxes?family_id=1")
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
    void test_List_Should_Return401_When_InvalidToken() throws Exception {

        this.mockMvc.perform(get("/v1/taxes?family_id=1")
            .header(HttpHeaders.AUTHORIZATION, "token")
            .contentType(MediaType.APPLICATION_JSON)
            .characterEncoding("utf-8")
            .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isUnauthorized())
            .andExpect(jsonPath("$.code").value(401))
            .andExpect(jsonPath("$.message").isNotEmpty())
            .andExpect(jsonPath("$.data").isEmpty())
            .andExpect(jsonPath("$.error").isEmpty());

    }

    @Test
    void test_List_Should_Return403_When_InvalidPermissions() throws Exception {

        String token = getJWT(modules, "somePermission", audience);

        this.mockMvc.perform(get("/v1/taxes?family_id=1")
            .header(HttpHeaders.AUTHORIZATION, token)
            .contentType(MediaType.APPLICATION_JSON)
            .characterEncoding("utf-8")
            .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isForbidden())
            .andExpect(jsonPath("$.code").value(403))
            .andExpect(jsonPath("$.message").isNotEmpty())
            .andExpect(jsonPath("$.data").isEmpty())
            .andExpect(jsonPath("$.error").isNotEmpty());
    }

    @Test
    void test_Create_Should_Return200_When_ReceiveValidDTO_And_RecordInserted() throws Exception {

        String token = getJWT(modules, PermissionsConstants.CREATE_TAXES, audience);
        Tax tax = getTax();

        String json = "{\n"
            + "  \"value\": 10,\n"
            + "  \"name\": \"tax name\",\n"
            + "  \"description\": \"tax description\",\n"
            + "  \"apply_rules\": true,\n"
            + "  \"sap_id\": \"123\",\n"
            + "  \"family_id\": 1,\n"
            + "  \"tax_type_id\": 1,\n"
            + "  \"expression_id\": 1\n"
            + "}";

        when(taxService.create(any(Tax.class))).thenReturn(tax);

        //Call to code to test
        this.mockMvc.perform(post("/v1/taxes")
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
    void test_Create_Should_Return400_When_InvalidDTO() throws Exception {

        String token = getJWT(modules, PermissionsConstants.CREATE_TAXES, audience);

        Tax tax = getTax();

        String json = "{\n"
            + "  \"name\": \"Test tax\"\n"
            + "}";

        //Call to code to test
        this.mockMvc.perform(post("/v1/taxes")
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
    void test_Create_Should_Return400_When_ErrorOnService() throws Exception {

        String token = getJWT(modules, PermissionsConstants.CREATE_TAXES, audience);

        String json = "{\n"
            + "  \"value\": 10,\n"
            + "  \"name\": \"tax name\",\n"
            + "  \"description\": \"tax description\",\n"
            + "  \"apply_rules\": true,\n"
            + "  \"sap_id\": \"123\",\n"
            + "  \"family_id\": 1,\n"
            + "  \"tax_type_id\": 1,\n"
            + "  \"expression_id\": 1\n"
            + "}";

        when(taxService.create(any(Tax.class))).thenThrow(new BusinessRuleException("Error"));

        //Call to code to test
        this.mockMvc.perform(post("/v1/taxes")
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

        //Call to code to test
        this.mockMvc.perform(post("/v1/taxes")
            .header(HttpHeaders.AUTHORIZATION, "token")
            .content("{}")
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
    void test_Create_Should_Return403_When_InvalidPermissionToken() throws Exception {

        String token = getJWT(modules, "SomePermission", audience);

        String json = "{\n"
            + "  \"value\": 10,\n"
            + "  \"name\": \"tax name\",\n"
            + "  \"description\": \"tax description\",\n"
            + "  \"apply_rules\": true,\n"
            + "  \"sap_id\": \"123\",\n"
            + "  \"family_id\": 1,\n"
            + "  \"tax_type_id\": 1,\n"
            + "  \"expression_id\": 1\n"
            + "}";

        //Call to code to test
        this.mockMvc.perform(post("/v1/taxes")
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
    void test_update_Should_Return200_When_ValidJsonIsSupplied() throws Exception {
        //Data
        String json = "{\n"
            + "  \"value\": 10,\n"
            + "  \"name\": \"tax name\",\n"
            + "  \"description\": \"tax description\",\n"
            + "  \"apply_rules\": true,\n"
            + "  \"sap_id\": \"123\",\n"
            + "  \"family_id\": 1,\n"
            + "  \"tax_type_id\": 1,\n"
            + "  \"expression_id\": 1,\n"
            + "  \"status\": 1\n"
            + "}";

        String token = getJWT(modules, PermissionsConstants.UPDATE_TAXES, audience);

        //Mock
        when(taxService.update(anyLong(), any())).thenReturn(getTax());

        //Call to code to test
        this.mockMvc.perform(put("/v1/taxes/1")
            .header(HttpHeaders.AUTHORIZATION, token)
            .content(json)
            .contentType(MediaType.APPLICATION_JSON)
            .characterEncoding("utf-8")
            .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.code").value(200))
            .andExpect(jsonPath("$.error").isEmpty());
    }

    @Test
    void test_update_Should_Return200_When_ChangeStatus() throws Exception {
        //Data
        String json = "{\n"
            + "  \"status\": 1\n"
            + "}";

        String token = getJWT(modules, PermissionsConstants.UPDATE_TAXES, audience);

        //Mock
        when(taxService.update(anyLong(), any())).thenReturn(getTax());

        //Call to code to test
        this.mockMvc.perform(put("/v1/taxes/1")
            .header(HttpHeaders.AUTHORIZATION, token)
            .content(json)
            .contentType(MediaType.APPLICATION_JSON)
            .characterEncoding("utf-8")
            .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.code").value(200))
            .andExpect(jsonPath("$.error").isEmpty());
    }

    @Test
    void test_update_Should_Return403Code_When_TokenInvalidPermissions()
        throws Exception {
        // Given
        String token = getJWT(modules, "test", audience);

        String json = "{}";

        // Then
        mockMvc.perform(put("/v1/taxes/1")
            .header(HttpHeaders.AUTHORIZATION, token)
            .content(json)
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
    void test_update_Should_Return401Code_When_TokenNoValid() throws Exception {
        // Given
        String json = "{}";

        // Then
        mockMvc.perform(put("/v1/taxes/1")
            .header(HttpHeaders.AUTHORIZATION, "")
            .content(json)
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
    void test_Delete_Should_Return204_When_TaxExists() throws Exception {

        String token = getJWT(modules, PermissionsConstants.DELETE_TAXES, audience);

        doNothing().when(taxService).delete(anyLong());

        this.mockMvc.perform(delete("/v1/taxes/1")
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
    void test_Delete_Should_Return404_When_TaxNotFound() throws Exception {

        String token = getJWT(modules, PermissionsConstants.DELETE_TAXES, audience);

        doThrow(EntityNotFoundException.class).when(taxService).delete(anyLong());

        this.mockMvc.perform(delete("/v1/taxes/1")
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
    void test_Delete_Should_Return400_When_TaxIsActiveForTaxRules() throws Exception {

        String token = getJWT(modules, PermissionsConstants.DELETE_TAXES, audience);

        doThrow(BusinessRuleException.class).when(taxService).delete(anyLong());

        this.mockMvc.perform(delete("/v1/taxes/1")
            .header(HttpHeaders.AUTHORIZATION, token)
            .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.code").value(400))
            .andExpect(jsonPath("$.message").isNotEmpty())
            .andExpect(jsonPath("$.data").isEmpty())
            .andExpect(jsonPath("$.error").isNotEmpty());
    }

    @Test
    void test_Delete_Should_Return401_When_InvalidToken() throws Exception {

        this.mockMvc.perform(delete("/v1/taxes/1")
            .header(HttpHeaders.AUTHORIZATION, "token")
            .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isUnauthorized())
            .andExpect(jsonPath("$.code").value(401))
            .andExpect(jsonPath("$.message").isNotEmpty())
            .andExpect(jsonPath("$.data").isEmpty())
            .andExpect(jsonPath("$.error").isEmpty());
    }

    @Test
    void test_Delete_Should_Return403_When_InvalidPermission() throws Exception {

        String token = getJWT(modules, "SomePermission", audience);

        this.mockMvc.perform(delete("/v1/taxes/1")
            .header(HttpHeaders.AUTHORIZATION, token)
            .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isForbidden())
            .andExpect(jsonPath("$.code").value(403))
            .andExpect(jsonPath("$.message").isNotEmpty())
            .andExpect(jsonPath("$.data").isEmpty())
            .andExpect(jsonPath("$.error").isNotEmpty());
    }

    private Tax getTax() {
        return Tax.builder()
            .value(BigDecimal.valueOf(10))
            .name("test")
            .description("test")
            .applyRules(true)
            .sapId("123")
            .family(new Family(1L))
            .taxType(new TaxType(1L))
            .expression(new Expression(1L))
            .build();
    }


}
