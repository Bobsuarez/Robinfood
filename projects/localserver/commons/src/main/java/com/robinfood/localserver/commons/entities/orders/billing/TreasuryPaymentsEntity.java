package com.robinfood.localserver.commons.entities.orders.billing;

import com.robinfood.localserver.commons.entities.storeconfiguration.treasurydepartment.ParameterEntity;
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
public class TreasuryPaymentsEntity {

    private Long id;

    private String name;

    private List<ParameterEntity> parameters;

    private BigDecimal value;

}
