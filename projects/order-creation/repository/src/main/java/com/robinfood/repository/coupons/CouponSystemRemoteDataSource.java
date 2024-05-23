package com.robinfood.repository.coupons;

import static com.robinfood.core.constants.GlobalConstants.DEFAULT_COUPON_ORDER_ID;
import static com.robinfood.core.util.ObjectMapperSingleton.objectToJson;

import com.robinfood.core.entities.ApiResponseEntity;
import com.robinfood.core.entities.redeemcoupon.PerformCouponRequestEntity;
import com.robinfood.core.entities.redeemcoupon.PerformCouponResponseEntity;
import com.robinfood.core.entities.redeemcoupon.RevertCouponRequestEntity;
import com.robinfood.core.enums.Result;
import com.robinfood.core.enums.TransactionCreationErrors;
import com.robinfood.core.exceptions.TransactionCreationException;
import com.robinfood.core.extensions.NetworkExtensionsKt;
import com.robinfood.network.api.CouponSystemAPI;
import lombok.extern.slf4j.Slf4j;

/**
 * Implementation of ICouponSystemRemoteDataSource
 */
@Slf4j
public class CouponSystemRemoteDataSource implements ICouponSystemRemoteDataSource {

    private final CouponSystemAPI couponSystemAPI;

    public CouponSystemRemoteDataSource(CouponSystemAPI couponSystemAPI) {
        this.couponSystemAPI = couponSystemAPI;
    }

    @Override
    public PerformCouponResponseEntity performCouponRequest(
            String token,
            PerformCouponRequestEntity performCouponRequestEntity
    ) {
        final Result<ApiResponseEntity<PerformCouponResponseEntity>> actualRedeem;
        if (performCouponRequestEntity.getOrder().getOrderId() == DEFAULT_COUPON_ORDER_ID) {
            log.info("Going out to validate coupon with data: {}", objectToJson(performCouponRequestEntity));

            actualRedeem = NetworkExtensionsKt
                    .safeAPICall(couponSystemAPI.validateCoupon(token, performCouponRequestEntity));
        } else {
            log.info("Going out to redeem coupon with data: {}", objectToJson(performCouponRequestEntity));

            actualRedeem = NetworkExtensionsKt
                    .safeAPICall(couponSystemAPI.redeemCoupon(token, performCouponRequestEntity));
        }
        if (actualRedeem instanceof Result.Error) {
            final Result.Error error = (Result.Error) actualRedeem;
            throw new TransactionCreationException(
                    error.getHttpStatus(),
                    error.getException().getLocalizedMessage(),
                    TransactionCreationErrors.COUPON_SYSTEM_API_ERROR
            );
        }
        return
                ((Result.Success<ApiResponseEntity<PerformCouponResponseEntity>>) actualRedeem)
                        .getData()
                        .getData()
        ;
    }

    @Override
    public Object revertRedeemIds(
            String token,
            RevertCouponRequestEntity revertCouponRequestEntity
    ) {
        final Result<ApiResponseEntity<Object>> actualRevert = NetworkExtensionsKt
                .safeAPICall(couponSystemAPI.revertCoupon(token, revertCouponRequestEntity));

        if (actualRevert instanceof Result.Error) {
            final Result.Error error = (Result.Error) actualRevert;

            throw new TransactionCreationException(
                    error.getHttpStatus(),
                    error.getException().getLocalizedMessage(),
                    TransactionCreationErrors.COUPON_SYSTEM_API_ERROR
            );
        }

        return ((Result.Success<ApiResponseEntity<Object>>) actualRevert)
                .getData()
                .getData();
    }
}
