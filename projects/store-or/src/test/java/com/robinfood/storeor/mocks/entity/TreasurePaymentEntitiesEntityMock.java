package com.robinfood.storeor.mocks.entity;

import com.robinfood.storeor.entities.TreasurePaymentEntitiesEntity;
import com.robinfood.storeor.entities.TreasurePaymentEntityParametersEntity;
import java.util.List;

public class TreasurePaymentEntitiesEntityMock {

    private final TreasurePaymentEntityParametersEntityMock parametersEntityMock =
            new TreasurePaymentEntityParametersEntityMock();
    private final List<TreasurePaymentEntityParametersEntity> parametersPayment =
            parametersEntityMock.getDataDefaultList();

    public TreasurePaymentEntitiesEntity getDataDefault() {
        return TreasurePaymentEntitiesEntity.builder()
                .paymentMethodId(1L)
                .parameters(parametersPayment)
                .build();
    }

    public List<TreasurePaymentEntitiesEntity> getDataDefaultList() {
        return List.of(getDataDefault());
    }
}
