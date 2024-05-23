package com.robinfood.storeor.mappers;

import com.robinfood.storeor.dtos.response.TreasureDepartmentsDTO;
import com.robinfood.storeor.entities.TreasuryDepartmentEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ITreasuryDepartmentMapper {

    TreasureDepartmentsDTO treasureDepartmentsEntityToTreasureDepartmentsDto(
        TreasuryDepartmentEntity treasuryDepartment);



}
