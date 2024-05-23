package com.robinfood.localserver.commons.entities.storeconfiguration.treasurydepartment;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class EntityPaymentEntity {

    private Long paymentMethodId;

    private List<ParameterEntity> parameters;
}
