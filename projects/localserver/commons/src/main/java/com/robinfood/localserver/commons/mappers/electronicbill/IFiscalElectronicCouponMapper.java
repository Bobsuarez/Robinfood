package com.robinfood.localserver.commons.mappers.electronicbill;

import com.robinfood.localserver.commons.dtos.electronicbill.FiscalElectronicCouponDTO;
import com.robinfood.localserver.commons.entities.electronicbill.FiscalElectronicCouponEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IFiscalElectronicCouponMapper {

    FiscalElectronicCouponEntity toCouponFiscalElectronicEntity(FiscalElectronicCouponDTO fiscalElectronicCouponDTO);

    FiscalElectronicCouponDTO toCouponFiscalElectronicCouponDTO(FiscalElectronicCouponEntity fiscalElectronicCouponEntity);
}
