package com.robinfood.localserver.commons.dtos.orders.billing;

import com.robinfood.localserver.commons.dtos.storeconfiguration.treasurydepartament.ParameterDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class TreasuryPaymentsDTO {

    private Long id;

    private String name;

    private List<ParameterDTO> parameters;

    private BigDecimal value;

}
