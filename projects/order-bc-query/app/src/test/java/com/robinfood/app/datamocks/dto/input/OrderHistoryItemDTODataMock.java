package com.robinfood.app.datamocks.dto.input;

import com.robinfood.core.dtos.OrderHistoryItemDTO;

import java.util.Arrays;
import java.util.List;

public class OrderHistoryItemDTODataMock {
    
    public OrderHistoryItemDTO getDataDefault() {
        return OrderHistoryItemDTO.builder()
                .build();
    }

    public List<OrderHistoryItemDTO> getDataDefaultList() {
        return Arrays.asList(getDataDefault());
    }
}
