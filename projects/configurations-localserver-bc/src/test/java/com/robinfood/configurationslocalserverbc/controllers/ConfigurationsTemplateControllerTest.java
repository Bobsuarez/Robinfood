package com.robinfood.configurationslocalserverbc.controllers;

import com.robinfood.configurationslocalserverbc.dtos.templateResponse.PrintingTemplateResponseDTO;
import com.robinfood.configurationslocalserverbc.mocks.dtos.PrintingTemplateResponseDTOMock;
import com.robinfood.configurationslocalserverbc.usecase.getreponseprintingtemplates.IGetResponsePrintingTemplatesUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import static com.robinfood.configurationslocalserverbc.configs.constans.APIConstants.CONFIGURATION_GET_TEMPLATE_V1;
import static com.robinfood.configurationslocalserverbc.configs.constans.APIConstants.CONFIGURATION_LOCALSERVER_V1;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
class ConfigurationsTemplateControllerTest {

    private static final String ENCODING = "utf-8";

    @MockBean
    private IGetResponsePrintingTemplatesUseCase getResponsePrintingTemplatesUseCase;

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    @Spy
    private ConfigurationsTemplateController configurationsTemplateController;

    private static final String TOKEN = "eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJpYXQiOjE1MTYyMzkwMjIsImV4cCI6MTgxNjIzOTAyMiwiYXVkIjoiaW50ZXJuYWwiLCJwZXIiOlsicG9zX2NyZWF0ZV9vcmRlciIsIm9yZGVyc19yZWplY3Rfb3JkZXIiXX0.WzKjKJp--J75eAkaPWhLjnuchMTS0MgULNAtB4_lsDOF-T_f4pVPFKPH-mVCVDudCTqPjvIfv8J_VPTEI5AhVQ";
    private static final String BEARER_AUTH = "Bearer ";

    private final Long storeId = 1L;

    final PrintingTemplateResponseDTO printingTemplateResponseDTOMock = new PrintingTemplateResponseDTOMock()
            .printingTemplateResponseDTOMock;

    @Test
    void test_When_Configurations_Template_Controller_Returns_Server_Error() throws Exception {

        String path = CONFIGURATION_LOCALSERVER_V1 + CONFIGURATION_GET_TEMPLATE_V1 + "?storeId=1";
        mockMvc.perform(get(path)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding(ENCODING)
                        .header(HttpHeaders.AUTHORIZATION, BEARER_AUTH + TOKEN + "wwww")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.message").value(
                        "JWT signature does not match locally computed signature. JWT validity cannot be asserted and should not be trusted.: "));
    }

    @Test
    void test_When_Configurations_Template_Controller_Returns_Server_Ok() throws Exception {

        when(getResponsePrintingTemplatesUseCase.invoke(storeId))
                .thenReturn(printingTemplateResponseDTOMock);

        String path = CONFIGURATION_LOCALSERVER_V1 + CONFIGURATION_GET_TEMPLATE_V1 + "?storeId=1";

        mockMvc.perform(get(path)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding(ENCODING)
                        .header(HttpHeaders.AUTHORIZATION, BEARER_AUTH + TOKEN)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.data").isNotEmpty());
    }
}
