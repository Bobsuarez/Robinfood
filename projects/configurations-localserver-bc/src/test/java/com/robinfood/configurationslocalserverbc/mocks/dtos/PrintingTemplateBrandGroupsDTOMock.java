package com.robinfood.configurationslocalserverbc.mocks.dtos;

import com.robinfood.configurationslocalserverbc.dtos.template.PrintingTemplateBrandGroupsDTO;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class PrintingTemplateBrandGroupsDTOMock {

    public List<PrintingTemplateBrandGroupsDTO> printingTemplateBrandGroupsDTOSDefault = Arrays.asList(
            PrintingTemplateBrandGroupsDTO
                    .builder()
                    .active(Boolean.TRUE)
                    .brandId(1L)
                    .printingTemplateId(1L)
                    .groupId(1L)
                    .id(1L)
                    .createdAt(LocalDateTime.parse("2022-08-18T15:26:56.318967"))
                    .updatedAt(LocalDateTime.parse("2022-08-18T15:26:56.318967"))
                    .build(),
            PrintingTemplateBrandGroupsDTO
                    .builder()
                    .active(Boolean.TRUE)
                    .brandId(2L)
                    .printingTemplateId(1L)
                    .groupId(1L)
                    .id(2L)
                    .createdAt(LocalDateTime.parse("2022-08-18T15:26:56.318967"))
                    .updatedAt(LocalDateTime.parse("2022-08-18T15:26:56.318967"))
                    .build()
    );
}
