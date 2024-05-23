package com.robinfood.localserver.commons.mappers.orderbilling;

import com.robinfood.localserver.commons.dtos.orders.billing.TreasuryTaxesDTO;
import com.robinfood.localserver.commons.dtos.storeconfiguration.treasurydepartament.EntityTaxDTO;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ITreasuryTaxesMapper {

    List<TreasuryTaxesDTO> entityDTOToTreasuryTaxesDTO(List<EntityTaxDTO> entityTaxDTO);
}
