package com.robinfood.app.datamocks.dto.output;

import com.robinfood.core.dtos.posresolution.GetPosResolutionsDTO;

import java.time.LocalDate;

public class PosResolutionDTOMock {

    public static GetPosResolutionsDTO getDataDefault() {

        var localDateStart = LocalDate.parse("2023-01-18");
        var localDateEnd = LocalDate.parse("2023-01-18");
        return GetPosResolutionsDTO.builder()
                .cancelledInvoices(1L)
                .current(1)
                .dicStatusId(2L)
                .effectiveInvoices(1L)
                .endDate(localDateEnd)
                .endNumber(0)
                .id(9L)
                .initialDate(localDateStart)
                .name("Caja Fisica - Muy Arrecife")
                .posId(1L)
                .prefix("P011")
                .storeId(1L)
                .startNumber(0)
                .typeDocument("Doc Aut DIAN #18764002771812")
                .build();
    }
}
