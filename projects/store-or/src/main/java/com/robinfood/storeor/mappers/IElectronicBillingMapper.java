package com.robinfood.storeor.mappers;

import com.robinfood.storeor.dtos.electronicbilling.CreateElectronicBillingRequestDTO;
import com.robinfood.storeor.dtos.electronicbilling.CreateElectronicBillingResponseDTO;
import com.robinfood.storeor.dtos.electronicbilling.ElectronicBillDTO;
import com.robinfood.storeor.entities.electronicbilling.ElectronicBillingEntity;
import com.robinfood.storeor.entities.electronicbilling.ElectronicBillingRequestEntity;
import com.robinfood.storeor.entities.electronicbilling.ElectronicBillingResponseEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IElectronicBillingMapper {

    ElectronicBillingRequestEntity createElectronicBillingRequestDTOToElectronicBillingRequestEntity(
            CreateElectronicBillingRequestDTO createElectronicBillingRequestDTO);

    CreateElectronicBillingResponseDTO electronicBillingResponseEntityToCreateElectronicBillingResponseDTO(
            ElectronicBillingResponseEntity electronicBillingResponseEntity);

    ElectronicBillDTO electronicBillingEntityToElectronicBillDTO(ElectronicBillingEntity electronicBillingEntity);

    List<ElectronicBillDTO>electronicBillingEntityListToElectronicBillDTOList(
            List<ElectronicBillingEntity> electronicBillingEntityList);
}
