package com.robinfood.taxes.controllers.v1;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.robinfood.taxes.constants.PermissionsConstants;
import com.robinfood.taxes.dto.v1.update.UpdateTaxTypeDTO;
import com.robinfood.taxes.exceptions.BusinessRuleException;
import com.robinfood.taxes.models.TaxType;
import com.robinfood.taxes.services.TaxTypeService;
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
import javax.persistence.EntityNotFoundException;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(controllers = TaxTypeController.class)
@TestPropertySource(properties = {
    "jwt.token.mod=taxes"
})
class TaxTypeControllerTest extends BaseTestController {

    @MockBean
    private TaxTypeService taxTypeService;

    @Autowired
    private MockMvc mockMvc;

    @SpyBean
    private ModelMapper modelMapper;

    @SpyBean
    private ObjectMapper objectMapper;

    private final String modules = "taxes";

    private final String audience = "internal";

    @Test
    void test_list_Should_Return200_When_NoStatusFilter() throws Exception {

        String token = getJWT(modules, PermissionsConstants.LIST_TAX_TYPE, "internal");

        List<TaxType> taxTypeList = new ArrayList<>();
        TaxType taxType = new TaxType(1L);
        taxType.setCountryId(1L);
        taxType.setStatus(2);
        taxTypeList.add(taxType);

        //Mock
        when(taxTypeService.list(anyLong(), eq(null)))
            .thenReturn(taxTypeList);
        //Call to code to test
        this.mockMvc.perform(get("/v1/tax-types?country_id=1")
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
    void test_list_Should_Return200_When_StatusFilter() throws Exception {

        String token = getJWT(modules, PermissionsConstants.LIST_TAX_TYPE, "internal");

        List<TaxType> taxTypeList = new ArrayList<>();
        TaxType taxType = new TaxType(1L);
        taxType.setCountryId(1L);
        taxType.setStatus(2);
        taxTypeList.add(taxType);

        //Mock
        when(taxTypeService.list(anyLong(), any()))
            .thenReturn(taxTypeList);
        //Call to code to test
        this.mockMvc.perform(get("/v1/tax-types?country_id=1&status=1")
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
    void test_Create_Should_Return200_When_ReceiveValidDTO_And_RecordInserted() throws Exception {

        String token = getJWT(modules, PermissionsConstants.CREATE_TAX_TYPE, audience);
        TaxType taxType = getTax();

        String json = "{\n"
            + "  \"name\": \"Test tax type\",\n"
            + "  \"country_id\": 1\n"
            + "}";

        when(taxTypeService.create(any(TaxType.class))).thenReturn(taxType);

        //Call to code to test
        this.mockMvc.perform(post("/v1/tax-types")
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

        String token = getJWT(modules, PermissionsConstants.CREATE_TAX_TYPE, audience);

        TaxType taxType = getTax();

        String json = "{\n"
            + "  \"name\": \"Test tax type\",\n"
            + "  \"country_id\": 0\n"
            + "}";

        when(taxTypeService.create(any(TaxType.class))).thenReturn(taxType);

        //Call to code to test
        this.mockMvc.perform(post("/v1/tax-types")
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

        String token = getJWT(modules, PermissionsConstants.CREATE_TAX_TYPE, audience);

        String json = "{\n"
            + "  \"name\": \"Test tax type\",\n"
            + "  \"country_id\": 1\n"
            + "}";

        when(taxTypeService.create(any(TaxType.class))).thenThrow(new BusinessRuleException("Error"));

        //Call to code to test
        this.mockMvc.perform(post("/v1/tax-types")
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

        TaxType taxType = getTax();

        String json = "{\n"
            + "  \"name\": \"Test tax\",\n"
            + "  \"country_id\": 1\n"
            + "}";

        when(taxTypeService.create(any(TaxType.class))).thenReturn(taxType);

        //Call to code to test
        this.mockMvc.perform(post("/v1/tax-types")
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
    void test_Create_Should_Return403_When_InvalidPermissionToken() throws Exception {

        String token = getJWT(modules, "SomePermission", audience);

        TaxType taxType = getTax();

        String json = "{\n"
            + "  \"name\": \"Test tax\",\n"
            + "  \"country_id\": 1\n"
            + "}";

        when(taxTypeService.create(any(TaxType.class))).thenReturn(taxType);

        //Call to code to test
        this.mockMvc.perform(post("/v1/tax-types")
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
    void test_Update_Should_Return400Code_When_InvalidBody() throws Exception {

        String token = getJWT(modules, PermissionsConstants.UPDATE_TAX_TYPE, audience);

        UpdateTaxTypeDTO updateTaxDTO = new UpdateTaxTypeDTO();
        updateTaxDTO.setName("newNameTest");
        updateTaxDTO.setStatus(0);

        //Call to code to test
        this.mockMvc.perform(put("/v1/tax-types/1")
            .header(HttpHeaders.AUTHORIZATION, token)
            .content(objectMapper.writeValueAsString(updateTaxDTO))
            .contentType(MediaType.APPLICATION_JSON)
            .characterEncoding("utf-8")
            .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.code").value(400))
            .andExpect(jsonPath("$.message").value("Bad Request"))
            .andExpect(
                jsonPath("$.data").value("status: Status value does not meet minimum length."))
            .andExpect(jsonPath("$.error").value("Invalid type for parameter"));
    }

    @Test
    void test_Update_Should_Return401Code_When_InvalidToken() throws Exception {

        //Call to code to test
        this.mockMvc.perform(put("/v1/tax-types/1")
            .header(HttpHeaders.AUTHORIZATION, "token")
            .content(objectMapper.writeValueAsString(getTaxTypeDTO()))
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
    void test_Update_Should_Return403Code_When_InvalidPermission() throws Exception {

        String token = getJWT(modules, PermissionsConstants.CREATE_TAX_TYPE, audience);

        //Call to code to test
        this.mockMvc.perform(put("/v1/tax-types/1")
            .header(HttpHeaders.AUTHORIZATION, token)
            .content(objectMapper.writeValueAsString(getTaxTypeDTO()))
            .contentType(MediaType.APPLICATION_JSON)
            .characterEncoding("utf-8")
            .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isForbidden())
            .andExpect(jsonPath("$.code").value(403))
            .andExpect(jsonPath("$.message").value("Forbidden"))
            .andExpect(jsonPath("$.data").isEmpty())
            .andExpect(jsonPath("$.error").value("Access is denied"));

    }

    @Test
    void test_Update_should_ReturnTax_When_UpdatedSuccessfully() throws Exception {

        String token = getJWT(modules, PermissionsConstants.UPDATE_TAX_TYPE, audience);

        when(taxTypeService.update(anyLong(),any(TaxType.class))).thenReturn(getTax());

        //Call to code to test
        this.mockMvc.perform(put("/v1/tax-types/1")
            .header(HttpHeaders.AUTHORIZATION, token)
            .content(objectMapper.writeValueAsString(getTaxTypeDTO()))
            .contentType(MediaType.APPLICATION_JSON)
            .characterEncoding("utf-8")
            .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.code").value(200))
            .andExpect(jsonPath("$.message").value("Tax type updated successfully"))
            .andExpect(jsonPath("$.data").isNotEmpty())
            .andExpect(jsonPath("$.error").isEmpty());
    }

    @Test
    void test_Delete_Should_Return204_When_ExistingTaxTypeId() throws Exception {

        String token = getJWT(modules, PermissionsConstants.DELETE_TAX_TYPE, audience);

        doNothing().when(taxTypeService).delete(anyLong());

        this.mockMvc.perform(delete("/v1/tax-types/1")
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
    void test_Delete_Should_Return404_When_TaxTypeIdNotFound() throws Exception {

        String token = getJWT(modules, PermissionsConstants.DELETE_TAX_TYPE, audience);

        doThrow(EntityNotFoundException.class).when(taxTypeService).delete(anyLong());

        this.mockMvc.perform(delete("/v1/tax-types/1")
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
    void test_Delete_Should_Return400_When_TaxTypeIsActiveForTaxes() throws Exception {

        String token = getJWT(modules, PermissionsConstants.DELETE_TAX_TYPE, audience);

        doThrow(BusinessRuleException.class).when(taxTypeService).delete(anyLong());

        this.mockMvc.perform(delete("/v1/tax-types/1")
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

        this.mockMvc.perform(delete("/v1/tax-types/1")
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

        this.mockMvc.perform(delete("/v1/tax-types/1")
                .header(HttpHeaders.AUTHORIZATION, token)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.code").value(403))
                .andExpect(jsonPath("$.message").isNotEmpty())
                .andExpect(jsonPath("$.data").isEmpty())
                .andExpect(jsonPath("$.error").isNotEmpty());

    }

    private TaxType getTax() {
        return TaxType.builder()
            .name("test")
            .countryId(1L)
            .status(1)
            .build();
    }

    private UpdateTaxTypeDTO getTaxTypeDTO() {
        UpdateTaxTypeDTO updateTaxTypeDTO = new UpdateTaxTypeDTO();
        updateTaxTypeDTO.setName("newNameTest");
        updateTaxTypeDTO.setStatus(2);
        return updateTaxTypeDTO;
    }

}
