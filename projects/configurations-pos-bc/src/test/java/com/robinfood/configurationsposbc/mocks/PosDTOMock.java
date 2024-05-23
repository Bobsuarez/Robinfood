package com.robinfood.configurationsposbc.mocks;

import com.robinfood.configurationsposbc.dtos.pos.PosDTO;

public class PosDTOMock {

    public PosDTO posDTO = PosDTO
            .builder()
            .id(1L)
            .code("POSCODE")
            .name("Pos Sale")
            .posTypeId(1L)
            .status(Boolean.TRUE)
            .build();
}
