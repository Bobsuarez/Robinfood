package com.robinfood.configurationsposbc.mocks;

import com.robinfood.configurationsposbc.dtos.pos.PosResponseDTO;
import com.robinfood.configurationsposbc.dtos.pos.PosTypesDTO;

public class PosResponseDTOMock {

    public PosResponseDTO posResponseDTO = PosResponseDTO
            .builder()
            .id(1L)
            .code("POSCODE")
            .name("Pos Sale")
            .posTypes(
                    PosTypesDTO
                            .builder()
                            .id(1L)
                            .name("Pos Type")
                            .build()
            )
            .build();

    public PosResponseDTO posResponseWithoutPosType = PosResponseDTO
            .builder()
            .id(1L)
            .code("POSCODE")
            .name("Pos Sale")
            .build();
}
