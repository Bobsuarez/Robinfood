package com.robinfood.configurationsposbc.controllers;

import com.robinfood.configurationsposbc.dtos.pos.PosResponseDTO;
import com.robinfood.configurationsposbc.mocks.PosResponseDTOMock;
import com.robinfood.configurationsposbc.usecase.getconfigurationposstoreuser.IGetConfigurationPosStoreUserUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static com.robinfood.configurationsposbc.configs.constans.APIConstants.CONFIGURATION_POS_V1;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
class ConfigurationPosControllerTest {

    private static final String ENCODING = "utf-8";

    @MockBean
    private IGetConfigurationPosStoreUserUseCase getConfigurationPosStoreUserUseCase;

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    @Spy
    private ConfigurationPosController configurationPosController;

    private static final String TOKEN = "eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJpYXQiOjE1MTYyMzkwMjIsImV4cCI6MTgxNjIzOTAyMiwiYXVkIjoiaW50ZXJuYWwiLCJwZXIiOlsicG9zX2NyZWF0ZV9vcmRlciIsIm9yZGVyc19yZWplY3Rfb3JkZXIiXX0.WzKjKJp--J75eAkaPWhLjnuchMTS0MgULNAtB4_lsDOF-T_f4pVPFKPH-mVCVDudCTqPjvIfv8J_VPTEI5AhVQ";
    private static final String BEARER_AUTH = "Bearer ";

    private final Long storeId = 1L;
    private final Long userId = 1L;

    final PosResponseDTO posResponseDTOMock = new PosResponseDTOMock().posResponseDTO;

    @Test
    void test_Configurations_Pos_Controller_Returns_Server_Error() throws Exception {

        mockMvc.perform(get(CONFIGURATION_POS_V1 + "/pos?storeId=1&userId=1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding(ENCODING)
                        .header(HttpHeaders.AUTHORIZATION, BEARER_AUTH + TOKEN + "wwww")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.message").value(
                        "JWT signature does not match locally computed signature. JWT validity cannot be asserted and should not be trusted.: "));
    }

    @Test
    void test_Get_Configuration_PosByStoreAndUser_Returns_Server_Ok() throws Exception {

        when(getConfigurationPosStoreUserUseCase.invoke(storeId, userId))
                .thenReturn(posResponseDTOMock);

        mockMvc.perform(get(CONFIGURATION_POS_V1 + "/pos?storeId=1&userId=1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding(ENCODING)
                        .header(HttpHeaders.AUTHORIZATION, BEARER_AUTH + TOKEN)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.data").isNotEmpty());
    }
}
