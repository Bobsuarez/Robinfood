package com.robinfood.configurationslocalserverbc.mocks.dtos;

import com.robinfood.configurationslocalserverbc.dtos.template.PrintingTemplateGroupsDTO;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class PrintingTemplateGroupsDTOMock {

    public List<PrintingTemplateGroupsDTO> printingTemplateGroupsDTOSResponseMock = Arrays.asList(
            PrintingTemplateGroupsDTO
                    .builder()
                    .id(1L)
                    .groupId(1L)
                    .active(Boolean.TRUE)
                    .printingTemplateId(1L)
                    .createdAt(LocalDateTime.parse("2022-08-18T15:26:56.318967"))
                    .updatedAt(LocalDateTime.parse("2022-08-18T15:26:56.318967"))
                    .build()
    );

}
