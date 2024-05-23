package com.robinfood.storeor.mocks.dto;

import com.robinfood.storeor.dtos.response.TreasureDepartmentPaymentDTO;
import com.robinfood.storeor.dtos.response.TreasurePaymentEntitiesDTO;
import java.util.List;

public class TreasureDepartmentPaymentDTOMock {

    private final TreasurePaymentEntitiesDTOMock entitiesPaymentMock = new TreasurePaymentEntitiesDTOMock();
    private final List<TreasurePaymentEntitiesDTO> entitiesPayment = entitiesPaymentMock.getDataDefaultList();

    public TreasureDepartmentPaymentDTO getDataDefault() {
        return TreasureDepartmentPaymentDTO.builder()
                .id(1L)
                .entities(entitiesPayment)
                .name("Vale Alimentação")
                .build();
    }

    public List<TreasureDepartmentPaymentDTO> getDataDefaultList() {
        return List.of(getDataDefault());
    }
}
