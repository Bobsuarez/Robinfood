package com.robinfood.app.datamocks.dto.input;

import com.robinfood.core.dtos.request.order.OrderFlagTogoDTO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OrderFlagTogoDTODataMock {

    public OrderFlagTogoDTO getDataDefault(){
        return new OrderFlagTogoDTO(
                1L,
                false,
                1L,
                1L
        );
    }

    public List<OrderFlagTogoDTO> getDataDefaultList(){
        return new ArrayList<>(Arrays.asList(getDataDefault()));
    }
}
