package com.robinfood.configurationslocalserverbc.mocks.dtos;

import com.robinfood.configurationslocalserverbc.dtos.template.PrintingTemplateBrandGroupsDTO;
import com.robinfood.configurationslocalserverbc.dtos.templateResponse.PrintingTemplateResponseDTO;

import java.time.LocalDateTime;
import java.util.Arrays;

public class PrintingTemplateResponseDTOMock {

    public PrintingTemplateResponseDTO printingTemplateResponseDTOMock = PrintingTemplateResponseDTO
            .builder()
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
            .templates(new TemplateDTOMock().templateDTOSResponseControllerMock)
            .build();
}
