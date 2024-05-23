package com.robinfood.repository.mocks.dtos;

import com.robinfood.core.dtos.redeemcoupon.PerformCouponEntityDTO;
import com.robinfood.core.dtos.redeemcoupon.PerformCouponOrderDTO;
import com.robinfood.core.dtos.redeemcoupon.PerformCouponOriginDTO;
import com.robinfood.core.dtos.redeemcoupon.PerformCouponPaymentDTO;
import com.robinfood.core.dtos.redeemcoupon.PerformCouponRequestDTO;
import com.robinfood.core.dtos.redeemcoupon.PerformCouponUserDTO;
import com.robinfood.core.dtos.redeemcoupon.RedeemCouponPaymentMethodDTO;
import com.robinfood.core.dtos.redeemcoupon.RedeemCouponPortionDTO;
import com.robinfood.core.dtos.redeemcoupon.RedeemCouponProductDTO;

import java.math.BigDecimal;
import java.util.Collections;

public class PerformCouponRequestDTOMocks {

    public PerformCouponRequestDTO getDataDefault() {
        return PerformCouponRequestDTO.builder()
                .code("COUPON")
                .entity(PerformCouponEntityDTO.builder().build())
                .order(new PerformCouponOrderDTO(
                        1L,
                        1L,
                        Collections.singletonList(new RedeemCouponProductDTO(
                                1L,
                                1L,
                                Collections.singletonList(new RedeemCouponPortionDTO(
                                        1L
                                ))
                        ))
                ))
                .origin(new PerformCouponOriginDTO(
                        1L,
                        "192.168.1.1",
                        1L,
                        "v1",
                        1L,
                        "Americas/Bogota"
                ))
                .payment(new PerformCouponPaymentDTO(
                        1L,
                        Collections.singletonList(new RedeemCouponPaymentMethodDTO(
                                1L,
                                BigDecimal.valueOf(7900.0)
                        )),
                        BigDecimal.valueOf(7900.0)
                ))
                .user(new PerformCouponUserDTO(
                        "Pepe",
                        "Pepe",
                        0,
                        "57",
                        "7658764568",
                        1L,
                        "test@test.com",
                        false,
                        Collections.singletonList(
                                PerformCouponUserDTO.Counter.builder()
                                        .key("test")
                                        .reference(1L)
                                        .value(1L)
                                        .build()
                        )))
                .build();
    }
}
