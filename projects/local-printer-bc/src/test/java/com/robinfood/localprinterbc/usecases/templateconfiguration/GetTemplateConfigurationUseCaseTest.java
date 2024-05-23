package com.robinfood.localprinterbc.usecases.templateconfiguration;

import com.robinfood.localprinterbc.dtos.printingtemplate.PrintingTemplateDTO;
import com.robinfood.localprinterbc.entities.PrintingTemplateBrandGroupsEntity;
import com.robinfood.localprinterbc.entities.PrintingTemplateEntity;
import com.robinfood.localprinterbc.entities.PrintingTemplateTypeEntity;
import com.robinfood.localprinterbc.entities.TemplateEntity;
import com.robinfood.localprinterbc.mappers.printingtemplate.IPrintingTemplateMapper;
import com.robinfood.localprinterbc.repositories.templateconfigurationrepository.ITemplateConfigurationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetTemplateConfigurationUseCaseTest {

    @Mock
    private ITemplateConfigurationRepository templateConfigurationRepository;
    @Mock
    private IPrintingTemplateMapper printingTemplateMapper;
    @InjectMocks
    private GetTemplateConfigurationUseCase getTemplateConfigurationUseCase;
    private static final String BEARER_AUTH = "Bearer ";
    private static final String TOKEN = "eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJhbmRyb2lkLnYxIiwianRpIjoiNEEyMDc5MkUtODA4NS00QzAyLTg4RDgtQzc2ODc0RDBENjE3Iiwic3ViIjoiMTIzNDU2NyIsIm5hbWUiOiJKb2huIERvZSIsImlhdCI6MTUxNjIzOTAyMiwiZXhwIjoxNzE2MjM5MDIyLCJhdWQiOiJpbnRlcm5hbCIsIm1vZCI6WyJvcmRlcl9vcl9sb2NhbHNlcnZlciJdLCJwZXIiOlsib3JjaF9tZW51X3NlcnZpY2VzIiwiY2hlY2tVc2VyRGF0YSIsImNoZWNrX3VzZXJfZGF0YSIsInRheGVzIiwic3luY09yZGVyIiwiQ1JFQVRFX1RSQU5TQUNUSU9OIiwiY2hlY2tDb21wYW55RGF0YSIsImNoZWNrX2NvbXBhbnlfZGF0YSIsIlRBWEVTX0JDX1NFUlZJQ0UiLCJUQVhFU19CQ19MSVNUX0lURU1fVEFYIiwibG95YWx0eV90cmFuc2FjdGlvbl9jcmVkaXRzIiwib3JkZXJfc3luYyIsIk9SREVSX1NZTkMiLCJiY19zZ2lfb3JkZXItcmVhZC1maW5hbmNlX2NhdGVnb3JpZXMiXSwidXNlciI6eyJ1c2VyX2lkIjoxMjM0NTY3LCJlbWFpbCI6ImpvaG5kb2VAbXljb21wYW55LmNvbSIsImNvdW50cnlfaWQiOjEsImNvbXBhbnlfaWQiOjEsImZpcnN0X25hbWUiOiJKaG9uIiwibGFzdF9uYW1lIjoiRG9lIiwicGhvbmUiOiI1NTUtNjM4MzAyMiIsIm1ldGFkYXRhIjp7InN0b3JlX2lkIjo1fX19.aZbT_gnMfCz6XlSJtkY0W0PLuyPFFmVaS8Y8phMhVe6wDDdLsxwYM1v0_UOK6Bg5UUuPPVnGD0Sj1y3aH-uSmg";
    private final Long storeId = 1L;

    final PrintingTemplateEntity printingTemplateEntity = PrintingTemplateEntity.builder()
            .printingTemplateBrands(
                    Arrays.asList(
                            PrintingTemplateBrandGroupsEntity
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
                            TemplateEntity
                                    .builder()
                                    .templateId(1L)
                                    .groupId(1L)
                                    .groupName("Group")
                                    .templateString("<template>test</template>")
                                    .rules(new ArrayList<>())
                                    .templateType(PrintingTemplateTypeEntity.builder().build())
                                    .build(),
                            TemplateEntity
                                    .builder()
                                    .templateId(2L)
                                    .groupId(1L)
                                    .groupName("Group")
                                    .templateString("<template>test two</template>")
                                    .rules(new ArrayList<>())
                                    .templateType(PrintingTemplateTypeEntity.builder().build())
                                    .build()
                    )
            )
            .build();

    @Test
    void invoke() {
        String token = BEARER_AUTH+TOKEN;
        when(templateConfigurationRepository.findTemplateByIdStore(token, storeId)).thenReturn(printingTemplateEntity);
        final PrintingTemplateDTO printingTemplateDTO =  getTemplateConfigurationUseCase.invoke(token, storeId);
        PrintingTemplateDTO printingTemplate = this.printingTemplateMapper.entityToDto(printingTemplateEntity);
        assertEquals(printingTemplateDTO, printingTemplate);
    }
}