package com.robinfood.app.datamocks.dto.input;

import com.robinfood.core.dtos.request.order.OrderFlagCorporateDTO;
import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
public class OrderFlagCorporateDTODataMock {

    public OrderFlagCorporateDTO getDataDefault(){
        return new OrderFlagCorporateDTO(
               1L,
               false,
               1L
        );
    }

    public List<OrderFlagCorporateDTO> getDataDefaultList(){
        return new ArrayList<>(Arrays.asList(getDataDefault()));
    }
}
