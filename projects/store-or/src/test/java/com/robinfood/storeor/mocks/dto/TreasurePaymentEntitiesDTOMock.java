package com.robinfood.storeor.mocks.dto;

import com.robinfood.storeor.dtos.response.TreasurePaymentEntitiesDTO;
import com.robinfood.storeor.dtos.response.TreasurePaymentEntityParametersDTO;
import java.util.List;

public class TreasurePaymentEntitiesDTOMock {

    private final TreasurePaymentEntityParametersDTOMock parametersEntityMock =
            new TreasurePaymentEntityParametersDTOMock();
    private final List<TreasurePaymentEntityParametersDTO> parametersPayment =
            parametersEntityMock.getDataDefaultList();

    public TreasurePaymentEntitiesDTO getDataDefault() {
        return TreasurePaymentEntitiesDTO.builder()
                .paymentMethodId(1L)
                .parameters(parametersPayment)
                .build();
    }

    public List<TreasurePaymentEntitiesDTO> getDataDefaultList() {
        return List.of(getDataDefault());
    }
}
