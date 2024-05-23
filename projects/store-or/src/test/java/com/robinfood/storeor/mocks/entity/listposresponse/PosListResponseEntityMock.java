package com.robinfood.storeor.mocks.entity.listposresponse;

import com.robinfood.storeor.entities.listposresponse.PosListResponseEntity;
import com.robinfood.storeor.entities.listposresponse.ResolutionResponseEntity;
import com.robinfood.storeor.entities.listposresponse.StorePosResponseEntity;

public class PosListResponseEntityMock {

    public static PosListResponseEntity build() {

        return PosListResponseEntity.builder()
                .code("ABC")
                .name("pos")
                .id(1L)
                .status(Boolean.TRUE)
                .store(StorePosResponseEntity.builder().id(1L).name("Store One").build())
                .resolution(
                        ResolutionResponseEntity
                                .builder()
                                .build()
                )
                .build();
    }
}
