package com.robinfood.taxes.controllers.v1;

import static com.robinfood.taxes.constants.PermissionsConstants.LIST_FAMILIES;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
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

import com.robinfood.taxes.constants.PermissionsConstants;
import com.robinfood.taxes.exceptions.BusinessRuleException;
import com.robinfood.taxes.models.Family;
import com.robinfood.taxes.models.FamilyType;
import com.robinfood.taxes.services.FamilyService;
import java.util.Collections;
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
@WebMvcTest(controllers = FamilyController.class)
@TestPropertySource(properties = {
    "jwt.token.mod=families"
})
class FamilyControllerTest extends BaseTestController {

    @MockBean
    private FamilyService familyService;

    @Autowired
    private MockMvc mockMvc;

    @SpyBean
    private ModelMapper modelMapper;

    private final String modules = "families";

    private final String audience = "internal";

    @Test
    void test_List_Should_Return200_When_ValidParams() throws Exception {

        String token = getJWT(modules, LIST_FAMILIES, audience);

        Family family = getFamily();

        when(familyService.list(anyLong(), anyLong(), anyInt()))
            .thenReturn(Collections.singletonList(family));

        this.mockMvc.perform(get("/v1/families?country_id=1&family_type_id=1&status=1")
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
    void test_List_Should_Return200_When_OptionalParamNotReceived() throws Exception {

        String token = getJWT(modules, LIST_FAMILIES, audience);

        Family family = getFamily();

        when(familyService.list(anyLong(), anyLong(), eq(null)))
            .thenReturn(Collections.singletonList(family));

        this.mockMvc.perform(get("/v1/families?country_id=1&family_type_id=1")
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
    void test_List_Should_Return400_When_MissingRequiredParam() throws Exception {

        String token = getJWT(modules, LIST_FAMILIES, audience);

        this.mockMvc.perform(get("/v1/families")
            .header(HttpHeaders.AUTHORIZATION, token)
            .contentType(MediaType.APPLICATION_JSON)
            .characterEncoding("utf-8")
            .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.code").value(400))
            .andExpect(jsonPath("$.message").isNotEmpty())
            .andExpect(jsonPath("$.data").isEmpty())
            .andExpect(jsonPath("$.error").isNotEmpty());


    }

    @Test
    void test_List_Should_Return401_When_InvalidToken() throws Exception {

        this.mockMvc.perform(get("/v1/families?country_id=1&family_type_id=1&status=1")
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

        this.mockMvc.perform(get("/v1/families?country_id=1&family_type_id=1&status=1")
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
    void test_Create_Should_Return201Code_When_FamilyIsCreated() throws Exception {
        String token = getJWT(modules, PermissionsConstants.CREATE_FAMILIES, audience);

        when(familyService.create(any(Family.class))).thenReturn(getFamily());

        mockMvc.perform(post("/v1/families")
            .header(HttpHeaders.AUTHORIZATION, token)
            .content(getJsonOnCreate())
            .contentType(MediaType.APPLICATION_JSON)
            .characterEncoding("utf-8")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.code").value(201))
            .andExpect(jsonPath("$.message").value("Family created successfully."))
            .andExpect(jsonPath("$.error").isEmpty())
            .andExpect(jsonPath("$.data").isNotEmpty());
    }

    @Test
    void test_Create_Should_Return400Code_When_FamilyWithCountryAndNameAlreadyExist()
        throws Exception {
        String token = getJWT(modules, PermissionsConstants.CREATE_FAMILIES, audience);

        when(familyService.create(any(Family.class))).thenThrow(new BusinessRuleException(""));

        mockMvc.perform(post("/v1/families")
            .header(HttpHeaders.AUTHORIZATION, token)
            .content(getJsonOnCreate())
            .contentType(MediaType.APPLICATION_JSON)
            .characterEncoding("utf-8")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.code").value(400));
    }

    @Test
    void test_Create_Should_Return401Code_When_Unauthorized() throws Exception {
        mockMvc.perform(post("/v1/families")
            .content(getJsonOnCreate())
            .contentType(MediaType.APPLICATION_JSON)
            .characterEncoding("utf-8")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isUnauthorized())
            .andExpect(jsonPath("$.code").value(401))
            .andExpect(jsonPath("$.message").isNotEmpty());
    }

    @Test
    void test_Create_Should_Return403Code_When_Forbidden() throws Exception {
        String token = getJWT(modules, "", audience);

        mockMvc.perform(post("/v1/families")
            .header(HttpHeaders.AUTHORIZATION, token)
            .content(getJsonOnCreate())
            .contentType(MediaType.APPLICATION_JSON)
            .characterEncoding("utf-8")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isForbidden());
    }

    @Test
    void test_Create_Should_Return404Code_When_FamilyTypeDoesNotExist() throws Exception {
        String token = getJWT(modules, PermissionsConstants.CREATE_FAMILIES, audience);

        when(familyService.create(any(Family.class))).thenThrow(new EntityNotFoundException());

        mockMvc.perform(post("/v1/families")
            .header(HttpHeaders.AUTHORIZATION, token)
            .content(getJsonOnCreate())
            .contentType(MediaType.APPLICATION_JSON)
            .characterEncoding("utf-8")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound())
            .andExpect(jsonPath("$.code").value(404));
    }

    @Test
    void test_UpdateFamily_Should_Return200_When_ValidJsonIsSupplied() throws Exception {
        //Data
        String json = "{\n" +
            "  \"name\": \"test\", \n" +
            "  \"country_id\": 1, \n" +
            "  \"family_type_id\": 1, \n" +
            "  \"status\": 1\n" +
            "}";

        String token = getJWT(modules, PermissionsConstants.UPDATE_FAMILIES, audience);

        Family family = Family.builder()
            .name("test")
            .countryId(1L)
            .familyType(new FamilyType(1L))
            .status(1)
            .build();

        //Mock
        when(familyService.update(anyLong(), any())).thenReturn(family);

        //Call to code to test
        this.mockMvc.perform(put("/v1/families/1")
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
    void test_Update_Should_ReturnFamilyUpdated_When_NullElements() throws Exception {

        String token = getJWT(modules, PermissionsConstants.UPDATE_FAMILIES, audience);

        //Data
        String json = "{\n" +
            "  \"name\": \"test\", \n" +
            "  \"country_id\": 1, \n" +
            "  \"family_type_id\": null, \n" +
            "  \"status\": 1\n" +
            "}";

        //Mock
        when(familyService.update(anyLong(), any())).thenThrow(BusinessRuleException.class);

        //Call to code to test
        this.mockMvc.perform(put("/v1/families/1")
            .header(HttpHeaders.AUTHORIZATION, token)
            .content(json)
            .contentType(MediaType.APPLICATION_JSON)
            .characterEncoding("utf-8")
            .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.code").value(400))
            .andExpect(jsonPath("$.error").isNotEmpty());
    }

    @Test
    void test_UpdateFamily_Should_Return400_When_InvalidInput() throws Exception {
        //Data
        String json = "{}";
        String token = getJWT(modules, PermissionsConstants.UPDATE_FAMILIES, audience);

        //Mock
        when(familyService.update(anyLong(), any())).thenThrow(BusinessRuleException.class);

        //Call to code to test
        this.mockMvc.perform(put("/v1/families/1")
            .header(HttpHeaders.AUTHORIZATION, token)
            .content(json)
            .contentType(MediaType.APPLICATION_JSON)
            .characterEncoding("utf-8")
            .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.code").value(400))
            .andExpect(jsonPath("$.error").isNotEmpty());
    }

    @Test
    void test_updateFamily_Should_Return403Code_When_TokenInvalidPermissions()
        throws Exception {
        // Given
        String token = getJWT(modules, "update", audience);

        String json = "{\n" +
            "  \"name\": \"test\", \n" +
            "  \"country_id\": 1, \n" +
            "  \"family_type_id\": 1, \n" +
            "  \"status\": 1\n" +
            "}";

        // Then
        mockMvc.perform(put("/v1/families/1")
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
    void test_updateFamily_Should_Return401Code_When_TokenNoValid() throws Exception {
        // Given
        String json = "{\n" +
            "  \"name\": \"test\", \n" +
            "  \"country_id\": 1, \n" +
            "  \"family_type_id\": 1, \n" +
            "  \"status\": 1\n" +
            "}";

        // Then
        mockMvc.perform(put("/v1/families/1")
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
    void test_Delete_Should_Return204_When_ExistingFamilyId() throws Exception {

        String token = getJWT(modules, PermissionsConstants.DELETE_FAMILIES, audience);

        doNothing().when(familyService).delete(anyLong());

        this.mockMvc.perform(delete("/v1/families/1")
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

        String token = getJWT(modules, PermissionsConstants.DELETE_FAMILIES, audience);

        doThrow(EntityNotFoundException.class).when(familyService).delete(anyLong());

        this.mockMvc.perform(delete("/v1/families/1")
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
    void test_Delete_Should_Return400_When_FamilyIsActiveForFamilyTaxes() throws Exception {

        String token = getJWT(modules, PermissionsConstants.DELETE_FAMILIES, audience);

        doThrow(BusinessRuleException.class).when(familyService).delete(anyLong());

        this.mockMvc.perform(delete("/v1/families/1")
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

        this.mockMvc.perform(delete("/v1/families/1")
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

        this.mockMvc.perform(delete("/v1/families/1")
            .header(HttpHeaders.AUTHORIZATION, token)
            .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isForbidden())
            .andExpect(jsonPath("$.code").value(403))
            .andExpect(jsonPath("$.message").isNotEmpty())
            .andExpect(jsonPath("$.data").isEmpty())
            .andExpect(jsonPath("$.error").isNotEmpty());

    }

    private Family getFamily() {

        FamilyType familyType = FamilyType.builder()
            .name("Family type")
            .build();
        familyType.setId(1L);

        Family family = Family.builder()
            .countryId(1L)
            .familyType(familyType)
            .name("Family")
            .status(1)
            .build();
        family.setId(1L);

        return family;
    }

    private String getJsonOnCreate() {
        return "{\n" +
            "  \"name\": \"test\", \n" +
            "  \"country_id\": 1, \n" +
            "  \"family_type_id\": 1 \n" +
            "}";
    }

}


