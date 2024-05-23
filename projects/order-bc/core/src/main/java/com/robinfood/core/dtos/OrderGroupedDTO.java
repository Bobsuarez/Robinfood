package com.robinfood.core.dtos;

import lombok.Data;

import java.util.List;

@Data
public class OrderGroupedDTO {

    private List<OrderIntegrationDTO> orderIntegrationDTOList;

    private List<OrderFlagSubmarineDTO> orderFlagSubmarineDTOList;
}
