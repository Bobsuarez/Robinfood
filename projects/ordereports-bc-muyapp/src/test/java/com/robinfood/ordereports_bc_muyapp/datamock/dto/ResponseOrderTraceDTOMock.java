package com.robinfood.ordereports_bc_muyapp.datamock.dto;

import com.robinfood.ordereports_bc_muyapp.dto.orderdetail.ResponseOrderTraceDTO;

import java.time.LocalDateTime;

public class ResponseOrderTraceDTOMock {

    public static ResponseOrderTraceDTO getDataDefault() {

        return ResponseOrderTraceDTO.builder()
                .description("Trace description")
                .datetime(LocalDateTime.now())
                .id((short) 1)
                .name("Trace Name")
                .build();
    }
}
