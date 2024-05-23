package com.robinfood.app.datamocks.dto.input;

import com.robinfood.core.dtos.request.order.BrandDTO;
import lombok.Data;

@Data
public class BrandDTODataMock {
    public BrandDTO getDataDefault() {
        return new BrandDTO(
            1L,
            1L,
            "Muy"
        );
    }
}
