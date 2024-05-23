package com.robinfood.repository.coupons;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import com.robinfood.core.entities.ApiResponseEntity;
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
import com.robinfood.core.extensions.ObjectExtensions;
import com.robinfood.network.api.CouponSystemAPI;
import java.math.BigDecimal;
import java.util.Collections;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import retrofit2.Call;
import retrofit2.Response;

@ExtendWith(MockitoExtension.class)
class CouponSystemRemoteDataSourceTest {

    @Mock
    private CouponSystemAPI mockCouponSystemAPI;

    @Mock
    private Call<ApiResponseEntity<PerformCouponResponseEntity>> mockCouponSystem;

    @Mock
    private Call<ApiResponseEntity<Object>> mockCouponSystemRevert;

    @InjectMocks
    private CouponSystemRemoteDataSource couponSystemRemoteDataSource;

    private final String token = "token";

    private final PerformCouponRequestEntity requestEntity = new PerformCouponRequestEntity(
            "CUPONOK",
            PerformCouponEntity.builder().build(),
            new PerformCouponOrderEntity(
                    1L,
                    2L,
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

    private final PerformCouponRequestEntity requestEntityOrderFirst = new PerformCouponRequestEntity(
            "CUPONOK",
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

    final ApiResponseEntity<PerformCouponResponseEntity> apiResponseEntityRedeemCouponSuccess = ApiResponseEntity.<PerformCouponResponseEntity>builder()
            .code(200)
            .data(            new PerformCouponResponseEntity(
                    "CUPONOK",
                    1L,
                    1L,
                    BigDecimal.valueOf(2000.0),
                    "1"
            )
            )
            .locale("CO")
            .message("We deduct $10 from your order")
            .build();

    final ApiResponseEntity<Object> apiErrorResponseEntityMenu = ApiResponseEntity.builder()
            .code(400)
            .error("Error")
            .message("Coupon not exists")
            .build();

    final ApiResponseEntity<Object> apiErrorResponseEntityRevert = ApiResponseEntity.builder()
            .code(400)
            .error("Error")
            .locale("CO")
            .message("redeem id not exists")
            .build();

    final RevertCouponRequestEntity requestRevertCouponEntity = new RevertCouponRequestEntity(
            Collections.singletonList(1L)
    );

    final ApiResponseEntity<Object> apiResponseEntityRevertCouponSuccess = ApiResponseEntity.builder()
            .code(200)
            .error("Error")
            .locale("CO")
            .message("We deduct $10 from your order")
            .build();

    @Test
    void test_RedeemCoupon_Returns_Correctly() throws Exception {

        when(mockCouponSystemAPI.redeemCoupon(token, requestEntity))
                .thenReturn(mockCouponSystem);

        when(mockCouponSystem.execute()).thenReturn(Response.success(apiResponseEntityRedeemCouponSuccess));

        final PerformCouponResponseEntity result = couponSystemRemoteDataSource.performCouponRequest(token, requestEntity);

        assertEquals(apiResponseEntityRedeemCouponSuccess.getData(), result);
    }

    @Test
    void test_RedeemCoupon_Returns_Correctly_OrderFirst() throws Exception {

        when(mockCouponSystemAPI.validateCoupon(token, requestEntityOrderFirst))
                .thenReturn(mockCouponSystem);

        when(mockCouponSystem.execute()).thenReturn(Response.success(apiResponseEntityRedeemCouponSuccess));

        final PerformCouponResponseEntity result = couponSystemRemoteDataSource.performCouponRequest(token, requestEntityOrderFirst);

        assertEquals(apiResponseEntityRedeemCouponSuccess.getData(), result);
    }

    @Test
    void test_RedeemCoupon_Returns_Error() throws Exception {
        final String responseJSON = ObjectExtensions.toJson(apiErrorResponseEntityMenu);

        when(mockCouponSystemAPI.redeemCoupon(token, requestEntity))
                .thenReturn(mockCouponSystem);

        when(mockCouponSystem.execute()).thenReturn(Response.error(500, ResponseBody.create(
                MediaType.parse("application/json"),
                responseJSON
        )));

        try {
            couponSystemRemoteDataSource.performCouponRequest(token, requestEntity);
        } catch (Exception exception) {
            assertTrue(exception.getLocalizedMessage().contains(apiErrorResponseEntityMenu.getMessage()));
        }
    }

    @Test
    void test_RevertCoupon_Returns_Correctly() throws Exception {
        when(mockCouponSystemAPI.revertCoupon(token, requestRevertCouponEntity))
                .thenReturn(mockCouponSystemRevert);

        when(mockCouponSystemRevert.execute()).thenReturn(Response.success(apiResponseEntityRevertCouponSuccess));

        final Object result = couponSystemRemoteDataSource.revertRedeemIds(token, requestRevertCouponEntity);

        assertEquals(apiResponseEntityRevertCouponSuccess.getData(), result);
    }


    @Test
    void test_RevertCoupon_Returns_Error() throws Exception {
        final String responseJSON = ObjectExtensions.toJson(apiErrorResponseEntityRevert);

        when(mockCouponSystemAPI.revertCoupon(token, requestRevertCouponEntity))
                .thenReturn(mockCouponSystemRevert);

        when(mockCouponSystemRevert.execute()).thenReturn(Response.error(500, ResponseBody.create(
                MediaType.parse("application/json"),
                responseJSON
        )));

        try {
            couponSystemRemoteDataSource.revertRedeemIds(token, requestRevertCouponEntity);
        } catch (Exception exception) {
            assertTrue(exception.getLocalizedMessage().contains(apiErrorResponseEntityRevert.getMessage()));
        }
    }
}
