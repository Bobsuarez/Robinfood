package com.robinfood.storeor.mocks.entity;


import com.robinfood.storeor.entities.TreasureDepartmentPaymentEntity;
import com.robinfood.storeor.entities.TreasurePaymentEntitiesEntity;
import java.util.List;

public class TreasureDepartmentPaymentEntityMock {

    private final TreasurePaymentEntitiesEntityMock entitiesPaymentMock = new TreasurePaymentEntitiesEntityMock();
    private final List<TreasurePaymentEntitiesEntity> entitiesPayment = entitiesPaymentMock.getDataDefaultList();

    public TreasureDepartmentPaymentEntity getDataDefault() {
        return TreasureDepartmentPaymentEntity.builder()
                .id(1L)
                .entities(entitiesPayment)
                .name("Vale Alimentação")
                .build();
    }

    public List<TreasureDepartmentPaymentEntity> getDataDefaultList() {
        return List.of(getDataDefault());
    }
}
