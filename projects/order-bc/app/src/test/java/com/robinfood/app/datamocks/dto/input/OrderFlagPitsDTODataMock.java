package com.robinfood.app.datamocks.dto.input;

import com.robinfood.core.dtos.request.order.OrderFlagPitsDTO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OrderFlagPitsDTODataMock {

    public OrderFlagPitsDTO getDataDefault(){
        return new OrderFlagPitsDTO(
                "Car Plate",
                1L,
                false,
                1L
        );
    }

    public List<OrderFlagPitsDTO> getDataDefaultList(){
        return new ArrayList<>(Arrays.asList(getDataDefault()));
    }
}
