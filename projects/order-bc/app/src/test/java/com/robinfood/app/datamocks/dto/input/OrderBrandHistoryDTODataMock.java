package com.robinfood.app.datamocks.dto.input;

import com.robinfood.core.dtos.request.order.OrderBrandHistoryDTO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OrderBrandHistoryDTODataMock {
    public OrderBrandHistoryDTO getDefaultData(){
        return new OrderBrandHistoryDTO(
                1L,
                1L,
                1L,
                1L
        );
    }

    public List<OrderBrandHistoryDTO> getDefaultDataList(){
        return new ArrayList<>(Arrays.asList(
                getDefaultData()
        ));
    }
}
