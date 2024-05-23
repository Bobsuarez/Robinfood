package com.robinfood.configurations.controllers.v1;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.robinfood.configurations.constants.PermissionsConstants;
import com.robinfood.configurations.dto.v1.ActivateOrDeactivateDTO;
import com.robinfood.configurations.dto.v1.ActivateOrDeactivateDTOMock;
import com.robinfood.configurations.dto.v1.ResponseResolutionsWithPosDTOMock;
import com.robinfood.configurations.dto.v1.StoreResolutionDTO;
import com.robinfood.configurations.dto.v1.StoreResolutionDTOMock;
import com.robinfood.configurations.services.StoreResolutionService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(controllers = StoreResolutionController.class)
@TestPropertySource(properties = {
        "jwt.token.mod=configurations_bc"
})
class StoreResolutionControllerTest extends BaseControllerTest {

    private static final String PATH = "/v1/stores";
    private static final String MODULES = "configurations_bc";
    private final ObjectMapper objectMapper = new ObjectMapper();
    @MockBean
    private StoreResolutionService storeResolutionService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void test_CreateResolution_Should_Return_When_Success() throws Exception {

        objectMapper.registerModule(new JavaTimeModule());

        StoreResolutionDTO storeResolutionDTO = StoreResolutionDTOMock.build();

        when(storeResolutionService.create(List.of(storeResolutionDTO))).thenReturn(List.of(ResponseResolutionsWithPosDTOMock.build()));

        String token = getJWT(MODULES, PermissionsConstants.SERVICE, "service");
        mockMvc.perform(post(PATH + "/resolutions")
                        .header(HttpHeaders.AUTHORIZATION, token)
                        .content(objectMapper.writeValueAsString(List.of(storeResolutionDTO)))
                        .characterEncoding("utf-8")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.code").value(201))
                .andExpect(content().string(Matchers.containsString("id")))
                .andExpect(jsonPath("$.error").isEmpty())
                .andExpect(jsonPath("$.data").isNotEmpty());
    }

    @Test
    void test_UpdateResolution_Should_Return_When_Success() throws Exception {

        objectMapper.registerModule(new JavaTimeModule());

        StoreResolutionDTO storeResolutionDTO = StoreResolutionDTOMock.build();

        when(storeResolutionService.update(any(), anyLong())).thenReturn("{}");

        String token = getJWT(MODULES, PermissionsConstants.SERVICE, "service");
        mockMvc.perform(put(PATH + "/resolutions/10")
                        .header(HttpHeaders.AUTHORIZATION, token)
                        .content(objectMapper.writeValueAsString(storeResolutionDTO))
                        .characterEncoding("utf-8")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.code").value(202))
                .andExpect(jsonPath("$.data").isNotEmpty());
    }

    @Test
    void test_ActivateOrDeactivate_Should_Return_When_Success_Should_Return_Accepted() throws Exception {

        objectMapper.registerModule(new JavaTimeModule());

        final Long resolutionId = 1L;

        ActivateOrDeactivateDTO activateOrDeactivateDTO = ActivateOrDeactivateDTOMock.build();

        when(storeResolutionService.isActivateOrDeactivateByResolutionId(
                activateOrDeactivateDTO,
                resolutionId
        )).thenReturn(Boolean.TRUE);

        String token = getJWT(MODULES, PermissionsConstants.SERVICE, "service");
        mockMvc.perform(patch(PATH + "/resolutions/1/active")
                .header(HttpHeaders.AUTHORIZATION, token)
                .content(objectMapper.writeValueAsString(activateOrDeactivateDTO))
                .characterEncoding("utf-8")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(APPLICATION_JSON))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.code").value(202))
                .andExpect(jsonPath("$.error").isEmpty())
                .andExpect(jsonPath("$.data").isEmpty());
    }
}
