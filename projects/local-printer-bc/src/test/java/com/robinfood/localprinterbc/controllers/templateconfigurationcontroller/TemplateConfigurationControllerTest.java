package com.robinfood.localprinterbc.controllers.templateconfigurationcontroller;

import com.robinfood.localprinterbc.dtos.printingtemplate.PrintingTemplateBrandGroupsDTO;
import com.robinfood.localprinterbc.dtos.printingtemplate.PrintingTemplateDTO;
import com.robinfood.localprinterbc.dtos.printingtemplate.PrintingTemplateTypesDTO;
import com.robinfood.localprinterbc.dtos.printingtemplate.TemplateDTO;
import com.robinfood.localprinterbc.usecases.templateconfiguration.IGetTemplateConfigurationUseCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

import static com.robinfood.localprinterbc.configs.constants.APIConstants.TEMPLATE;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ITemplateConfigurationController.class)
class TemplateConfigurationControllerTest {

    private static final String TOKEN = "eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJhbmRyb2lkLnYxIiwianRpIjoiNEEyMDc5MkUtODA4NS00QzAyLTg4RDgtQzc2ODc0RDBENjE3Iiwic3ViIjoiMTIzNDU2NyIsIm5hbWUiOiJKb2huIERvZSIsImlhdCI6MTUxNjIzOTAyMiwiZXhwIjoxNzE2MjM5MDIyLCJhdWQiOiJpbnRlcm5hbCIsIm1vZCI6WyJvcmRlcl9vcl9sb2NhbHNlcnZlciJdLCJwZXIiOlsib3JjaF9tZW51X3NlcnZpY2VzIiwiY2hlY2tVc2VyRGF0YSIsImNoZWNrX3VzZXJfZGF0YSIsInRheGVzIiwic3luY09yZGVyIiwiQ1JFQVRFX1RSQU5TQUNUSU9OIiwiY2hlY2tDb21wYW55RGF0YSIsImNoZWNrX2NvbXBhbnlfZGF0YSIsIlRBWEVTX0JDX1NFUlZJQ0UiLCJUQVhFU19CQ19MSVNUX0lURU1fVEFYIiwibG95YWx0eV90cmFuc2FjdGlvbl9jcmVkaXRzIiwib3JkZXJfc3luYyIsIk9SREVSX1NZTkMiLCJiY19zZ2lfb3JkZXItcmVhZC1maW5hbmNlX2NhdGVnb3JpZXMiXSwidXNlciI6eyJ1c2VyX2lkIjoxMjM0NTY3LCJlbWFpbCI6ImpvaG5kb2VAbXljb21wYW55LmNvbSIsImNvdW50cnlfaWQiOjEsImNvbXBhbnlfaWQiOjEsImZpcnN0X25hbWUiOiJKaG9uIiwibGFzdF9uYW1lIjoiRG9lIiwicGhvbmUiOiI1NTUtNjM4MzAyMiIsIm1ldGFkYXRhIjp7InN0b3JlX2lkIjo1fX19.aZbT_gnMfCz6XlSJtkY0W0PLuyPFFmVaS8Y8phMhVe6wDDdLsxwYM1v0_UOK6Bg5UUuPPVnGD0Sj1y3aH-uSmg";
    private static final String BEARER_AUTH = "Bearer ";
    private static final String ENCODING = "utf-8";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IGetTemplateConfigurationUseCase getTemplateConfigurationUseCase;

    private final Long storeId = 1L;

    final PrintingTemplateDTO printingTemplateDTO = PrintingTemplateDTO.builder()
            .printingTemplateBrands(
                    Arrays.asList(
                            PrintingTemplateBrandGroupsDTO
                                    .builder()
                                    .id(1L)
                                    .groupId(1L)
                                    .active(Boolean.TRUE)
                                    .printingTemplateId(1L)
                                    .createdAt(LocalDateTime.parse("2023-03-08T14:00:01.318967"))
                                    .updatedAt(LocalDateTime.parse("2023-03-08T14:00:01.318967"))
                                    .build()
                    )
            )
            .templates(
                    Arrays.asList(
                            TemplateDTO
                                    .builder()
                                    .templateId(1L)
                                    .groupId(1L)
                                    .groupName("Group")
                                    .templateString("<template>test</template>")
                                    .rules(new ArrayList<>())
                                    .templateType(PrintingTemplateTypesDTO.builder().build())
                                    .build(),
                            TemplateDTO
                                    .builder()
                                    .templateId(2L)
                                    .groupId(1L)
                                    .groupName("Group")
                                    .templateString("<template>test two</template>")
                                    .rules(new ArrayList<>())
                                    .templateType(PrintingTemplateTypesDTO.builder().build())
                                    .build()
                    )
            )
            .build();

    @Test
    void test_getTemplateConfiguration_Is_Ok() throws Exception {
        String path = TEMPLATE  + "?storeId=1";
        when(getTemplateConfigurationUseCase.invoke(BEARER_AUTH+TOKEN, storeId))
                .thenReturn(printingTemplateDTO);

        mockMvc.perform(get(path)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding(ENCODING)
                        .header(HttpHeaders.AUTHORIZATION, BEARER_AUTH + TOKEN )
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200));
    }
}