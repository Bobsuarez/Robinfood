package com.robinfood.app.datamocks.dto.input;

import com.robinfood.core.dtos.request.order.OrderHistoryDTO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OrderHistoryDTODataMock {

    public OrderHistoryDTO getDefaultData(){
        return new OrderHistoryDTO(
                "Anything",
                1L,
                1L,
                1L
        );
    }
    public OrderHistoryDTO getDefaultDataThree(){
        return new OrderHistoryDTO(
                "Anything",
                1L,
                2L,
                1L
        );
    }
    public OrderHistoryDTO getDefaultDataTwo(){
        return new OrderHistoryDTO(
                "Anything",
                1L,
                3L,
                1L
        );
    }

    public List<OrderHistoryDTO> getDefaultDataList(){
        return new ArrayList<>(Arrays.asList(
                getDefaultData()
        ));
    }
    public List<OrderHistoryDTO> getDefaultDataListTwoValues(){
        return new ArrayList<>(Arrays.asList(
                getDefaultDataTwo(),
                getDefaultDataThree()
        ));
    }
}
