package com.robinfood.app.mocks;

import com.robinfood.core.dtos.posresolution.GetPosResolutionsDTO;

import java.time.LocalDate;

public final class PosResolutionDTOMock {

    public static GetPosResolutionsDTO getDataDefault() {

        return GetPosResolutionsDTO.builder()
                .cancelledInvoices(0L)
                .current(1)
                .dicStatusId(2L)
                .effectiveInvoices(0L)
                .endDate("2023-01-18")
                .endNumber(null)
                .id(9L)
                .initialDate("2023-01-18")
                .name("Caja Fisica - Muy Arrecife")
                .posId(1L)
                .prefix("P011")
                .storeId(1L)
                .startNumber(null)
                .typeDocument("Doc Aut DIAN #18764002771812")
                .build();
    }
}
