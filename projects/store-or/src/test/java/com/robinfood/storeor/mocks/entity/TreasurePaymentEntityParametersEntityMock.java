package com.robinfood.storeor.mocks.entity;

import com.robinfood.storeor.entities.TreasurePaymentEntityParametersEntity;
import java.util.List;

public class TreasurePaymentEntityParametersEntityMock {

    public TreasurePaymentEntityParametersEntity getDataDefault() {
        return TreasurePaymentEntityParametersEntity.builder()
                .name("cMP")
                .value("01")
                .build();
    }

    public List<TreasurePaymentEntityParametersEntity> getDataDefaultList() {
        return List.of(getDataDefault());
    }
}
