package com.robinfood.core.dtos.request.order;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class OrderFlagDTO {

    private OrderFlagCorporateDTO corporate;

    private OrderFlagPitsDTO pits;

    private OrderFlagSubmarineDTO submarine;

    private OrderFlagTogoDTO togo;

}
