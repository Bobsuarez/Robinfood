package com.robinfood.repository.mocks.dtos;

import com.robinfood.core.entities.InformationPosResolutionsResponseEntity;

public class InformationPosResolutionDTOMocks {

    public static InformationPosResolutionsResponseEntity getDataDefault() {

        return InformationPosResolutionsResponseEntity.builder()
                .cancelledInvoices("0")
                .current((short) 1)
                .dicStatusId((byte) 1)
                .effectiveInvoices("29")
                .endDate("2024-04-30")
                .endNumber("419080")
                .id(1603)
                .initialDate("2024-04-10")
                .name("Caja 1 - Muy Calle 83 164")
                .posId((short) 164)
                .prefix("AR001")
                .startNumber("41952")
                .storeId((short) 27)
                .typeDocument("Doc Aut DIAN")
                .build();
    }
}
