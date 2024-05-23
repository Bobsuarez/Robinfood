package com.robinfood.ordereports_bc_muyapp.datamock.entities;

import com.robinfood.ordereports_bc_muyapp.models.entities.ServiceEntity;

import java.time.LocalDateTime;

public class ServicesEntityMock {

    public static ServiceEntity getDataDefault() {
        return ServiceEntity.builder()
                .companyId((short) 1)
                .createdAt(LocalDateTime.now())
                .id(1001L)
                .maxValueCovered(1000.0)
                .name("Premium Service")
                .price(150.0)
                .tax(15.0)
                .typeId((short) 2)
                .typeServiceId((short) 3)
                .updatedAt(LocalDateTime.now())
                .build();
    }

}
