package com.robinfood.core.mappers;

import com.robinfood.core.dtos.ConfigRedeemCouponDTO;
import com.robinfood.core.entities.ConfigRedeemCouponEntity;

public final class ConfigRedeemCouponMappers {
    
    private ConfigRedeemCouponMappers() {
    }

    public static ConfigRedeemCouponEntity toConfigRedeemCouponEntity(
            ConfigRedeemCouponDTO configRedeemCouponDTO
    ) {
        return new ConfigRedeemCouponEntity(
                configRedeemCouponDTO.getRedeemIds()
        );
    }

    public static ConfigRedeemCouponDTO toConfigRedeemCouponDTO(
            ConfigRedeemCouponEntity configRedeemCouponEntity
    ) {
        return new ConfigRedeemCouponDTO(
                configRedeemCouponEntity.getRedeemIds()
        );
    }
}
