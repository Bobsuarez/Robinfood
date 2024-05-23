package com.robinfood.localserver.app.configurations;

import com.robinfood.localserver.localprocessorderor.dtos.printingtemplate.PrintingTemplateBrandGroupsDTO;
import com.robinfood.localserver.localprocessorderor.dtos.printingtemplate.PrintingTemplateDTO;
import com.robinfood.localserver.localprocessorderor.dtos.printingtemplate.PrintingTemplateTypesDTO;
import com.robinfood.localserver.localprocessorderor.dtos.printingtemplate.TemplateDTO;
import com.robinfood.localserver.localprocessorderor.usecase.getprintingtemplateconfigurationusecase.IGetPrintingTemplateConfigurationUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PrintingTemplateConfigurationTest {

    @InjectMocks
    private PrintingTemplateConfiguration printingTemplateConfiguration;

    @Mock
    private IGetPrintingTemplateConfigurationUseCase getPrintingTemplateConfigurationUseCase;


    private final PrintingTemplateDTO printingTemplateDTOMock = PrintingTemplateDTO.builder()
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
    void Given_ServerStarting_When_SendTokenToLocalPrinterBc_Then_ReturnPrintingTemplateDTO(){

        when(getPrintingTemplateConfigurationUseCase.invoke())
                .thenReturn(printingTemplateDTOMock);

        PrintingTemplateDTO result = printingTemplateConfiguration.consumeLocalPrinterBcToGetPrintingTemplates();

        Assertions.assertNotNull(result);
        Assertions.assertSame(printingTemplateDTOMock, result);
        Assertions.assertEquals(printingTemplateDTOMock.getTemplates().size(), result.getTemplates().size() );
        Mockito.verify(getPrintingTemplateConfigurationUseCase, Mockito.times(1)).invoke();

    }

    @Test
    public void Given_ServerStartingFailure_When_SendTokenToLocalPrinterBc_Then_CatchException() {

        when(getPrintingTemplateConfigurationUseCase.invoke()).thenThrow(new RuntimeException("Error"));

        PrintingTemplateDTO result = printingTemplateConfiguration.consumeLocalPrinterBcToGetPrintingTemplates();

        Assertions.assertNull(result);
        Mockito.verify(getPrintingTemplateConfigurationUseCase, Mockito.times(1)).invoke();
    }
}
