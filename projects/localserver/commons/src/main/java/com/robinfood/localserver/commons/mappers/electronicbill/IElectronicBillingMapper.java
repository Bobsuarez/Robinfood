package com.robinfood.localserver.commons.mappers.electronicbill;

import com.robinfood.localserver.commons.dtos.electronicbill.RequestElectronicBillingDTO;
import com.robinfood.localserver.commons.dtos.electronicbill.ResponseElectronicBillingDTO;
import com.robinfood.localserver.commons.entities.electronicbill.ElectronicBillingRequestEntity;
import com.robinfood.localserver.commons.entities.electronicbill.ElectronicBillingResponseEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IElectronicBillingMapper {

    ElectronicBillingRequestEntity createElectronicBillingRequestDTOToElectronicBillingRequestEntity(
            RequestElectronicBillingDTO createElectronicBillingRequestDTO);

    ResponseElectronicBillingDTO electronicBillingResponseEntityToCreateElectronicBillingResponseDTO(
            ElectronicBillingResponseEntity electronicBillingResponseEntity);

    RequestElectronicBillingDTO electronicBillingRequestEntityToRequestElectronicBillingDTO(
            ElectronicBillingRequestEntity electronicBillingRequestEntity);
}

