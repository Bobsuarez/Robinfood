package com.robinfood.app.datamocks.dto.input;


import com.robinfood.core.dtos.request.order.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OrderFlagDTODataMock {

    private OrderFlagCorporateDTO orderFlagCorporateDTO = new OrderFlagCorporateDTODataMock()
            .getDataDefault();

    private OrderFlagPitsDTO orderFlagPitsDTO = new OrderFlagPitsDTODataMock()
            .getDataDefault();

    private OrderFlagSubmarineDTO orderFlagSubmarineDTO = new OrderFlagSubmarineDTODataMock()
            .getDataDefault();

    private OrderFlagTogoDTO orderFlagTogoDTO = new OrderFlagTogoDTODataMock()
            .getDataDefault();

    public OrderFlagDTO getDataDefault(){
        return new OrderFlagDTO(
                orderFlagCorporateDTO,
                orderFlagPitsDTO,
                orderFlagSubmarineDTO,
                orderFlagTogoDTO
        );
    }

    public List<OrderFlagDTO> getDataDefaultList(){
        return new ArrayList<>(Arrays.asList(getDataDefault()));
    }

}
