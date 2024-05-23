package com.robinfood.orderorlocalserver.controllers;

import com.robinfood.orderorlocalserver.dtos.printingtemplate.PrintingTemplateBrandGroupsDTO;
import com.robinfood.orderorlocalserver.dtos.printingtemplate.PrintingTemplateDTO;
import com.robinfood.orderorlocalserver.dtos.printingtemplate.PrintingTemplateTypesDTO;
import com.robinfood.orderorlocalserver.dtos.printingtemplate.TemplateDTO;
import com.robinfood.orderorlocalserver.usecases.getprintingtemplate.IGetPrintingTemplateUseCase;
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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

import static com.robinfood.orderorlocalserver.constants.ApiConstants.API_GET_PRINTING_TEMPLATES;
import static com.robinfood.orderorlocalserver.constants.ApiConstants.API_PRINTING_TEMPLATES_V1;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class PrintingTemplateControllerTest {

    private static final String ENCODING = "utf-8";

    @MockBean
    private IGetPrintingTemplateUseCase getPrintingTemplateUseCase;

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    @Spy
    private PrintingTemplateController printingTemplateController;

    private static final String TOKEN = "eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJhbmRyb2lkLnYxIiwianRpIjoiNEEyMDc5MkUtODA4NS00QzAyLTg4RDgtQzc2ODc0RDBENjE3Iiwic3ViIjoiMTIzNDU2NyIsIm5hbWUiOiJKb2huIERvZSIsImlhdCI6MTUxNjIzOTAyMiwiZXhwIjoxODE2MjQ5MDIyLCJhdWQiOiJpbnRlcm5hbCIsIm1vZCI6WyJvcmRlcl9vcl9sb2NhbHNlcnZlciJdLCJwZXIiOlsib3JjaF9tZW51X3NlcnZpY2VzIiwiY2hlY2tVc2VyRGF0YSIsImNoZWNrX3VzZXJfZGF0YSIsInRheGVzIiwic3luY09yZGVyIiwiQ1JFQVRFX1RSQU5TQUNUSU9OIiwiY2hlY2tDb21wYW55RGF0YSIsImNoZWNrX2NvbXBhbnlfZGF0YSIsIlRBWEVTX0JDX1NFUlZJQ0UiLCJUQVhFU19CQ19MSVNUX0lURU1fVEFYIiwibG95YWx0eV90cmFuc2FjdGlvbl9jcmVkaXRzIiwib3JkZXJfc3luYyIsIk9SREVSX1NZTkMiLCJiY19zZ2lfb3JkZXItcmVhZC1maW5hbmNlX2NhdGVnb3JpZXMiXSwidXNlciI6eyJ1c2VyX2lkIjoxMjM0NTY3LCJlbWFpbCI6ImpvaG5kb2VAbXljb21wYW55LmNvbSIsImNvdW50cnlfaWQiOjEsImNvbXBhbnlfaWQiOjEsImZpcnN0X25hbWUiOiJKaG9uIiwibGFzdF9uYW1lIjoiRG9lIiwicGhvbmUiOiI1NTUtNjM4MzAyMiIsIm1ldGFkYXRhIjp7InN0b3JlX2lkIjo1fX19.0VAb2M2yOEX9mpkgw3hv1bqkDgxb00EGgLY3-WeDRVwlpqXI0QUN5mfLEKp3fL_wD4PGlGSwyY3feXaXRr8EgQ";

    private static final String BEARER_AUTH = "Bearer ";

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
                                    .createdAt(LocalDateTime.parse("2022-08-18T15:26:56.318967"))
                                    .updatedAt(LocalDateTime.parse("2022-08-18T15:26:56.318967"))
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
    void test_GetPrintingTemplates_When_StoreHasTemplates_Should_TemplateResponseOK() throws Exception {
        String path = API_PRINTING_TEMPLATES_V1 + API_GET_PRINTING_TEMPLATES + "?storeId=1";

        when(getPrintingTemplateUseCase.invoke(storeId))
                .thenReturn(printingTemplateDTO);

        mockMvc.perform(get(path)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding(ENCODING)
                        .header(HttpHeaders.AUTHORIZATION, BEARER_AUTH + TOKEN )
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.data").isNotEmpty());

    }
}
