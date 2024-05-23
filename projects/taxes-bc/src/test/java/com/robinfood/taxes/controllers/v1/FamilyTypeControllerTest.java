package com.robinfood.taxes.controllers.v1;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.robinfood.taxes.constants.PermissionsConstants;
import com.robinfood.taxes.models.FamilyType;
import com.robinfood.taxes.services.FamilyTypeService;
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
@WebMvcTest(controllers = FamilyTypeController.class)
@TestPropertySource(properties = {
    "jwt.token.mod=taxesBc"
})
class FamilyTypeControllerTest extends BaseTestController {

    @MockBean
    private FamilyTypeService familyTypeService;

    @Autowired
    private MockMvc mockMvc;

    @SpyBean
    private ModelMapper modelMapper;

    private final String modules = "taxesBc";

    private final String audience = "internal";

    @Test
    void test_findAll_Should_Return200_When_ValidData() throws Exception {

        String token = getJWT(modules, PermissionsConstants.LIST_FAMILY_TYPES, audience);

        List<FamilyType> familyTypesList = new ArrayList<>();
        FamilyType familyType = new FamilyType(1l);
        familyType.setName("test");
        familyTypesList.add(familyType);

        //Mock
        when(familyTypeService.findAll())
            .thenReturn(familyTypesList);
        //Call to code to test
        this.mockMvc.perform(get("/v1/family-types")
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
    void test_findAll_Should_Return403Code_When_TokenInvalidPermissions() throws Exception {
        // Given
        String token = getJWT(modules, "test", audience);

        // Then
        this.mockMvc.perform(get("/v1/family-types")
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
    void test_findAll_Should_Return401Code_When_TokenNoValid() throws Exception {

        // Then
        this.mockMvc.perform(get("/v1/family-types")
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

}
