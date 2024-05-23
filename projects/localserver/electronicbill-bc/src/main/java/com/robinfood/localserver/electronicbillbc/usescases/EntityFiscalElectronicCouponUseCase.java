package com.robinfood.localserver.electronicbillbc.usescases;

import com.robinfood.localserver.commons.dtos.storeconfiguration.treasurydepartament.EntityTreasuryDepartmentDTO;
import com.robinfood.localserver.commons.dtos.storeconfiguration.treasurydepartament.ParameterDTO;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class EntityFiscalElectronicCouponUseCase implements IEntityFiscalElectronicCouponUseCase {

    @SneakyThrows
    @Override
    public Map<String, String>
    invoke(List<EntityTreasuryDepartmentDTO> listTreasuryEntitiesDTO, String property) {

        return listTreasuryEntitiesDTO.stream().filter(
                        treasuryEntityDTO ->
                                treasuryEntityDTO.getName().equals(property)).findAny()
                .orElse(new EntityTreasuryDepartmentDTO())
                .getParameters().stream().collect(
                        Collectors.toMap(
                                ParameterDTO::getName, ParameterDTO::getValue
                        )
                );
    }
}
