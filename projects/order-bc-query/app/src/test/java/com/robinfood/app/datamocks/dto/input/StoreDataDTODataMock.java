package com.robinfood.app.datamocks.dto.input;

import com.robinfood.core.dtos.request.order.StoreDTO;
import lombok.Data;

@Data
public class StoreDataDTODataMock {
    public StoreDTO getDataDefault() {
        return new StoreDTO( 1L, "MUY 79", 1L);
    }
}
