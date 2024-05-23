package com.robinfood.configurationsposbc.mocks;

import com.robinfood.configurationsposbc.dtos.pos.PosTypesDTO;

public class PosTypesDTOMock {

    public PosTypesDTO posTypesDTO = PosTypesDTO
            .builder()
            .id(1L)
            .name("Pos Type")
            .build();
}
