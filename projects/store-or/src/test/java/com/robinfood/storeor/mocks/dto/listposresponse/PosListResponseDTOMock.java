package com.robinfood.storeor.mocks.dto.listposresponse;

import com.robinfood.storeor.dtos.listposresponse.PosListResponseDTO;
import com.robinfood.storeor.dtos.listposresponse.ResolutionResponseDTO;
import com.robinfood.storeor.dtos.listposresponse.StorePosResponseDTO;

public class PosListResponseDTOMock {

    public static PosListResponseDTO build() {

        return PosListResponseDTO.builder()
                .code("ABC")
                .name("pos")
                .id(1L)
                .status(Boolean.TRUE)
                .store(StorePosResponseDTO.builder().id(1L).name("Store One").build())
                .resolution(
                        ResolutionResponseDTO
                                .builder()
                                .build()
                )
                .build();
    }
}
