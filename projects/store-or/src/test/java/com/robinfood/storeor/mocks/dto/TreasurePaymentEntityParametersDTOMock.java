package com.robinfood.storeor.mocks.dto;

import com.robinfood.storeor.dtos.response.TreasurePaymentEntityParametersDTO;
import java.util.List;

public class TreasurePaymentEntityParametersDTOMock {

    public TreasurePaymentEntityParametersDTO getDataDefault() {
        return TreasurePaymentEntityParametersDTO.builder()
                .name("code")
                .value("5101")
                .build();
    }

    public List<TreasurePaymentEntityParametersDTO> getDataDefaultList() {
        return List.of(getDataDefault());
    }
}
