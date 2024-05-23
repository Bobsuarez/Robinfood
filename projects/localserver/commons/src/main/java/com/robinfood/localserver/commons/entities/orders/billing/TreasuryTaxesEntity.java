package com.robinfood.localserver.commons.entities.orders.billing;

import com.robinfood.localserver.commons.entities.storeconfiguration.treasurydepartment.ParameterEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class TreasuryTaxesEntity {

    private String name;

    private List<ParameterEntity> parameters;
}
