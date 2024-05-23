package com.robinfood.localserver.commons.mappers.electronicbill;

import com.robinfood.localserver.commons.dtos.electronicbill.cancelsale.CancelElectronicCouponDTO;
import com.robinfood.localserver.commons.entities.electronicbill.cancelsale.CancelElectronicCouponEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ICancelElectronicCouponMapper {

    CancelElectronicCouponEntity toCancelElectronicCouponEntity(CancelElectronicCouponDTO cancelElectronicCouponDTO);

    CancelElectronicCouponDTO toCancelElectronicCouponDTO(CancelElectronicCouponEntity cancelElectronicCouponEntity);
}
