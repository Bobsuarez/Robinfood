package com.robinfood.datamock;

import com.robinfood.dtos.TypeDTO;

public class TypeDTOMock {

    public static TypeDTO getDefault() {

        return TypeDTO.builder()
                .description("HTTP")
                .id(1L)
                .name("HTTP")
                .build();
    }
}
