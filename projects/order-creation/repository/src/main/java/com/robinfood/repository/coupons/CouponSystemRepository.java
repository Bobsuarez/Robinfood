package com.robinfood.repository.coupons;

import com.robinfood.core.dtos.ConfigRedeemCouponDTO;
import com.robinfood.core.dtos.redeemcoupon.PerformCouponRequestDTO;
import com.robinfood.core.dtos.redeemcoupon.PerformCouponResponseDTO;
import com.robinfood.core.dtos.redeemcoupon.RevertCouponRequestDTO;
import com.robinfood.core.entities.redeemcoupon.PerformCouponRequestEntity;
import com.robinfood.core.entities.redeemcoupon.PerformCouponResponseEntity;
import com.robinfood.core.entities.redeemcoupon.RevertCouponRequestEntity;
import com.robinfood.core.mappers.ConfigRedeemCouponMappers;
import com.robinfood.core.mappers.RedeemCouponMappers;
import com.robinfood.core.mappers.RevertCouponMapper;

/**
 * Implementation of IDiscountsRepository
 */

public class CouponSystemRepository implements ICouponSystemRepository {

    private final ICouponSystemLocalDataSource couponSystemLocalDataSource;
    private final ICouponSystemRemoteDataSource couponSystemRemoteDataSource;

    public CouponSystemRepository(ICouponSystemLocalDataSource couponSystemLocalDataSource,
                                  ICouponSystemRemoteDataSource couponSystemRemoteDataSource) {
        this.couponSystemLocalDataSource = couponSystemLocalDataSource;
        this.couponSystemRemoteDataSource = couponSystemRemoteDataSource;
    }

    @Override
    public ConfigRedeemCouponDTO getRedeemCouponRedeemIds() {
        if (couponSystemLocalDataSource.getRedeemCouponRedeemIds() == null) {
            return null;
        }
        return ConfigRedeemCouponMappers
                .toConfigRedeemCouponDTO(couponSystemLocalDataSource.getRedeemCouponRedeemIds());
    }

    @Override
    public PerformCouponResponseDTO performCouponRequest(
            String token,
            PerformCouponRequestDTO performCouponRequestDTO
    ) {
        final PerformCouponRequestEntity performCouponRequestEntity = RedeemCouponMappers
                .toRedeemCouponRequestEntity(performCouponRequestDTO);

        PerformCouponResponseEntity couponResponseEntity = couponSystemRemoteDataSource.performCouponRequest(
            token, performCouponRequestEntity);

        return RedeemCouponMappers.toRedeemCouponResponseDTO(couponResponseEntity);

    }

    @Override
    public Object revertRedeemIds(
            String token,
            RevertCouponRequestDTO revertCouponRequestDTO
    ) {
        final RevertCouponRequestEntity revertCouponRequestEntity = RevertCouponMapper
                .toRevertCouponRequestEntity(revertCouponRequestDTO);
        return couponSystemRemoteDataSource.revertRedeemIds(token, revertCouponRequestEntity);
    }

    @Override
    public void setRedeemCouponRedeemIds(ConfigRedeemCouponDTO configRedeemCouponDTO) {
        couponSystemLocalDataSource
                .setRedeemCouponRedeemIds(ConfigRedeemCouponMappers.toConfigRedeemCouponEntity(configRedeemCouponDTO));
    }
}
