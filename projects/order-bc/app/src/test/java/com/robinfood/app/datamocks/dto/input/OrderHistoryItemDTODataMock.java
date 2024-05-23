package com.robinfood.app.datamocks.dto.input;

import com.robinfood.core.dtos.OrderHistoryItemDTO;

import java.util.Arrays;
import java.util.List;

public class OrderHistoryItemDTODataMock {
    
    public OrderHistoryItemDTO getDataDefault() {
        return new OrderHistoryItemDTO(
                "MUY",
                "2021-05-24",
                1L,
                1L,
                null,
                "Cajas V2",
                1L,
                8900.0
        );
    }

    public List<OrderHistoryItemDTO> getDataDefaultList() {
        return Arrays.asList(getDataDefault());
    }
}
