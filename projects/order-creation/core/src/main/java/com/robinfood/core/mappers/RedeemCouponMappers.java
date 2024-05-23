package com.robinfood.core.mappers;

import static kotlin.collections.CollectionsKt.map;

import com.robinfood.core.dtos.redeemcoupon.PerformCouponEntityDTO;
import com.robinfood.core.dtos.redeemcoupon.PerformCouponOrderDTO;
import com.robinfood.core.dtos.redeemcoupon.PerformCouponOriginDTO;
import com.robinfood.core.dtos.redeemcoupon.PerformCouponPaymentDTO;
import com.robinfood.core.dtos.redeemcoupon.PerformCouponRequestDTO;
import com.robinfood.core.dtos.redeemcoupon.PerformCouponResponseDTO;
import com.robinfood.core.dtos.redeemcoupon.PerformCouponUserDTO;
import com.robinfood.core.dtos.redeemcoupon.RedeemCouponPaymentMethodDTO;
import com.robinfood.core.dtos.redeemcoupon.RedeemCouponPortionDTO;
import com.robinfood.core.dtos.redeemcoupon.RedeemCouponProductDTO;
import com.robinfood.core.entities.redeemcoupon.PerformCouponEntity;
import com.robinfood.core.entities.redeemcoupon.PerformCouponOrderEntity;
import com.robinfood.core.entities.redeemcoupon.PerformCouponOriginEntity;
import com.robinfood.core.entities.redeemcoupon.PerformCouponPaymentEntity;
import com.robinfood.core.entities.redeemcoupon.PerformCouponRequestEntity;
import com.robinfood.core.entities.redeemcoupon.PerformCouponResponseEntity;
import com.robinfood.core.entities.redeemcoupon.PerformCouponUserEntity;
import com.robinfood.core.entities.redeemcoupon.RedeemCouponPaymentMethodEntity;
import com.robinfood.core.entities.redeemcoupon.RedeemCouponPortionEntity;
import com.robinfood.core.entities.redeemcoupon.RedeemCouponProductEntity;
import java.util.List;
import java.util.stream.Collectors;

public final class RedeemCouponMappers {

    private RedeemCouponMappers() {
    }

    public static PerformCouponRequestEntity toRedeemCouponRequestEntity(
            PerformCouponRequestDTO performCouponRequestDTO
    ) {
        return new PerformCouponRequestEntity(
                performCouponRequestDTO.getCode(),
                RedeemCouponMappers.toPerformCouponEntityDTO(performCouponRequestDTO.getEntity()),
                RedeemCouponMappers.toOrderEntity(performCouponRequestDTO.getOrder()),
                RedeemCouponMappers.toOriginEntity(performCouponRequestDTO.getOrigin()),
                RedeemCouponMappers.toPaymentEntity(performCouponRequestDTO.getPayment()),
                RedeemCouponMappers.toUserEntity(performCouponRequestDTO.getUser())
        );
    }

    public static PerformCouponEntity toPerformCouponEntityDTO(PerformCouponEntityDTO performCouponEntityDTO) {
        return PerformCouponEntity.builder()
                .id(performCouponEntityDTO.getId())
                .reference(performCouponEntityDTO.getReference())
                .source(performCouponEntityDTO.getSource())
                .build();
    }

    public static PerformCouponResponseDTO toRedeemCouponResponseDTO(
            PerformCouponResponseEntity performCouponResponseEntity
    ) {
        return new PerformCouponResponseDTO(
                performCouponResponseEntity.getCode(),
                performCouponResponseEntity.getCodeId(),
                performCouponResponseEntity.getCouponType(),
                performCouponResponseEntity.getDiscount(),
                performCouponResponseEntity.getRedeemedId()
        );
    }

    public static PerformCouponOrderEntity toOrderEntity(PerformCouponOrderDTO performCouponOrderDTO) {
        return new PerformCouponOrderEntity(
                performCouponOrderDTO.getFlowId(),
                performCouponOrderDTO.getOrderId(),
                map(
                        performCouponOrderDTO.getProducts(),
                        RedeemCouponMappers::toProductEntity
                )
        );
    }

    public static PerformCouponOriginEntity toOriginEntity(PerformCouponOriginDTO performCouponOriginDTO) {
        return new PerformCouponOriginEntity(
                performCouponOriginDTO.getCompanyId(),
                performCouponOriginDTO.getIp(),
                performCouponOriginDTO.getPlatformId(),
                performCouponOriginDTO.getPlatformVersion(),
                performCouponOriginDTO.getStoreId(),
                performCouponOriginDTO.getTimezone()
        );
    }

    public static PerformCouponPaymentEntity toPaymentEntity(PerformCouponPaymentDTO performCouponPaymentDTO) {
        return new PerformCouponPaymentEntity(
                performCouponPaymentDTO.getPaymentMethodId(),
                map(
                        performCouponPaymentDTO.getPaymentMethods(),
                        RedeemCouponMappers::toPaymentMethodEntity
                ),
                performCouponPaymentDTO.getTotal()
        );
    }

    public static RedeemCouponPaymentMethodEntity toPaymentMethodEntity(
            RedeemCouponPaymentMethodDTO redeemCouponPaymentMethodDTO) {
        return new RedeemCouponPaymentMethodEntity(
                redeemCouponPaymentMethodDTO.getId(),
                redeemCouponPaymentMethodDTO.getValue()
        );
    }

    public static RedeemCouponProductEntity toProductEntity(RedeemCouponProductDTO redeemCouponProductDTO) {
        return new RedeemCouponProductEntity(
                redeemCouponProductDTO.getBrandId(),
                redeemCouponProductDTO.getId(),
                map(
                        redeemCouponProductDTO.getPortions(),
                        RedeemCouponMappers::toPortionEntity
                )
        );
    }

    public static RedeemCouponPortionEntity toPortionEntity(RedeemCouponPortionDTO redeemCouponPortionDTO) {
        return new RedeemCouponPortionEntity(
                redeemCouponPortionDTO.getId()
        );
    }

    public static PerformCouponUserEntity toUserEntity(PerformCouponUserDTO performCouponUserDTO) {

        List<PerformCouponUserEntity.Counter> counters =
                performCouponUserDTO.getCounters().stream().map(
                        RedeemCouponMappers::toPerformCouponUserEntityCounter
                ).collect(Collectors.toList());

        return new PerformCouponUserEntity(
                performCouponUserDTO.getFirstName(),
                performCouponUserDTO.getLastName(),
                performCouponUserDTO.getNumberOrdersCompleted(),
                performCouponUserDTO.getPhoneCode(),
                performCouponUserDTO.getPhoneNumber(),
                performCouponUserDTO.getUserId(),
                performCouponUserDTO.getEmail(),
                performCouponUserDTO.getIsEmployee(),
                counters
        );
    }

    private static PerformCouponUserEntity.Counter toPerformCouponUserEntityCounter(
            PerformCouponUserDTO.Counter userDetailEntityCounter
    ) {
        return PerformCouponUserEntity.Counter.builder()
                .key(userDetailEntityCounter.getKey())
                .reference(userDetailEntityCounter.getReference())
                .value(userDetailEntityCounter.getValue())
                .build();
    }
}
