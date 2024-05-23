package com.robinfood.repository.coupons;

import com.robinfood.core.dtos.ConfigRedeemCouponDTO;
import com.robinfood.core.dtos.redeemcoupon.PerformCouponResponseDTO;
import com.robinfood.core.dtos.redeemcoupon.RevertCouponRequestDTO;
import com.robinfood.core.entities.ConfigRedeemCouponEntity;
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
import com.robinfood.core.entities.redeemcoupon.RevertCouponRequestEntity;
import com.robinfood.core.entities.redeemcoupon.RevertCouponResponseEntity;
import com.robinfood.repository.mocks.dtos.PerformCouponRequestDTOMocks;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CouponSystemRepositoryTest {

    @Mock
    private ICouponSystemRemoteDataSource couponSystemRemoteDataSource;

    @Mock
    private ICouponSystemLocalDataSource couponSystemLocalDataSource;

    @InjectMocks
    private CouponSystemRepository couponSystemRepository;

    private final String token = "token";

    private final PerformCouponRequestEntity requestEntity = new PerformCouponRequestEntity(
            "COUPON",
            PerformCouponEntity.builder().build(),
            new PerformCouponOrderEntity(
                    1L,
                    1L,
                    Collections.singletonList(new RedeemCouponProductEntity(
                            1L,
                            1L,
                            Collections.singletonList(new RedeemCouponPortionEntity(
                                    1L
                            ))
                    ))
            ),
            new PerformCouponOriginEntity(
                    1L,
                    "192.168.1.1",
                    1L,
                    "v1",
                    1L,
                    "Americas/Bogota"
            ),
            new PerformCouponPaymentEntity(
                    1L,
                    Collections.singletonList(new RedeemCouponPaymentMethodEntity(
                            1L,
                            BigDecimal.valueOf(7900.0)
                    )),
                    BigDecimal.valueOf(7900.0)
            ),
            new PerformCouponUserEntity(
                    "Pepe",
                    "Pepe",
                    0,
                    "57",
                    "7658764568",
                    1L,
                    "test@test.com",
                    false,
                    Collections.singletonList(
                            PerformCouponUserEntity
                                    .Counter.builder()
                                    .key("test")
                                    .reference(1L)
                                    .value(1L)
                                    .build()
                    )
            )
    );

    private final PerformCouponResponseEntity response = new PerformCouponResponseEntity(
            "CUPONOK",
            1L,
            1L,
            BigDecimal.valueOf(2000.0),
            "1"
    );

    private final PerformCouponResponseDTO responseDTO = new PerformCouponResponseDTO(
            "CUPONOK",
            1L,
            1L,
            BigDecimal.valueOf(2000.0),
            "1"
    );

    private final PerformCouponRequestDTOMocks performCouponRequestDTO = new PerformCouponRequestDTOMocks();

    final RevertCouponRequestEntity requestRevertCouponEntity = new RevertCouponRequestEntity(
            Collections.singletonList(1L)
    );

    final RevertCouponRequestDTO requestRevertCouponDTO = new RevertCouponRequestDTO(
            Collections.singletonList(1L)
    );

    @Test
    void test_Redeem_Coupon_Responds_Correctly() {
        when(couponSystemRemoteDataSource.performCouponRequest(token, requestEntity))
                .thenReturn((response));

        final PerformCouponResponseDTO result = couponSystemRepository.performCouponRequest(token, performCouponRequestDTO.getDataDefault());

        assertEquals(responseDTO, result);
    }


    @Test
    void test_Revert_Coupon_Responds_Correctly() {
        when(couponSystemRemoteDataSource.revertRedeemIds(token, requestRevertCouponEntity))
                .thenReturn(new RevertCouponResponseEntity());

        final Object result = couponSystemRepository.revertRedeemIds(token, requestRevertCouponDTO);

        assertEquals(new RevertCouponResponseEntity(), result);
    }

    @Test
    void test_getRedeemCouponRedeemIds_LocalDataSource_Null() {

        when(couponSystemLocalDataSource.getRedeemCouponRedeemIds())
                .thenReturn(null);

        final ConfigRedeemCouponDTO result = couponSystemRepository.getRedeemCouponRedeemIds();

        assertNull(result);
    }

    @Test
    void test_getRedeemCouponRedeemIds_LocalDataSource_List() {

        List<Long> ids = List.of(10L, 11L);

        when(couponSystemLocalDataSource.getRedeemCouponRedeemIds())
                .thenReturn(new ConfigRedeemCouponEntity(ids));

        final ConfigRedeemCouponDTO result = couponSystemRepository.getRedeemCouponRedeemIds();

        assertEquals(ids, result.getRedeemIds());
    }

    @Test
    void test__setRedeemCouponRedeemIds() {

        List<Long> ids = List.of(10L, 11L);

        doNothing().when(couponSystemLocalDataSource).setRedeemCouponRedeemIds(any(ConfigRedeemCouponEntity.class));

        couponSystemRepository.setRedeemCouponRedeemIds(new ConfigRedeemCouponDTO(ids));

        verify(couponSystemLocalDataSource).setRedeemCouponRedeemIds(any(ConfigRedeemCouponEntity.class));
    }
}
