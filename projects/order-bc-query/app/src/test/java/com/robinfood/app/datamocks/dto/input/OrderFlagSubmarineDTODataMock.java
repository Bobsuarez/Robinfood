package com.robinfood.app.datamocks.dto.input;

import com.robinfood.core.dtos.request.order.OrderFlagSubmarineDTO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OrderFlagSubmarineDTODataMock {

    public OrderFlagSubmarineDTO getDataDefault(){
        return new OrderFlagSubmarineDTO(
                1L,
                true,
                1L
        );
    }

    public List<OrderFlagSubmarineDTO> getDataDefaultList(){
        return new ArrayList<>(Arrays.asList(getDataDefault()));
    }
}
