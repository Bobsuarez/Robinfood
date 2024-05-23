package com.robinfood.localserver.commons.dtos.orders.billing;

import com.robinfood.localserver.commons.dtos.storeconfiguration.treasurydepartament.ParameterDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class TreasuryCategoryDTO {

    private String name;

    private List<ParameterDTO> parameters;
}
