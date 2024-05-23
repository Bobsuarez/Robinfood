package com.robinfood.app.datamocks.entity;

import com.robinfood.core.entities.PosResolutionsEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class PosResolutionEntityMock {

    public static PosResolutionsEntity getDataDefault() {
        return PosResolutionsEntity.builder()
                .createdAt(LocalDateTime.now())
                .current(1)
                .dicStatusId(2)
                .endDate(LocalDate.now())
                .id(9L)
                .initialDate(LocalDate.now())
                .invoiceNumberEnd(15340)
                .invoiceNumberInitial(0)
                .invoiceNumberResolutions("18764002771812")
                .invoiceText("Doc Aut DIAN #18764002771812")
                .name("Caja Fisica - Muy Arrecife")
                .orderNumberInitial(0)
                .prefix("P011")
                .posId(1L)
                .resolutionId(80L)
                .storeId(1L)
                .typeDocument("Doc Aut DIAN #18764002771812")
                .updatedAt(LocalDateTime.now()).build();

    }
}
