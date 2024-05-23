package com.robinfood.localserver.electronicbillbc.usescases;

import com.robinfood.localserver.commons.dtos.storeconfiguration.treasurydepartament.EntityTreasuryDepartmentDTO;

import java.util.List;
import java.util.Map;

public interface IEntityFiscalElectronicCouponUseCase {

    Map<String, String> invoke(List<EntityTreasuryDepartmentDTO> listTreasuryEntitiesDTO, String property);

}
