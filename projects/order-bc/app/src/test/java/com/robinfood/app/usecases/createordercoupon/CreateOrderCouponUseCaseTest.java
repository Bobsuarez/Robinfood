package com.robinfood.app.usecases.createordercoupon;

import com.robinfood.core.dtos.OrderCouponDTO;
import com.robinfood.core.dtos.request.transaction.RequestOrderTransactionDTO;
import com.robinfood.core.entities.OrderCouponEntity;
import com.robinfood.repository.ordercoupons.IOrderCouponRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class CreateOrderCouponUseCaseTest {

    @Mock
    private IOrderCouponRepository orderCouponRepository;

    @InjectMocks
    private CreateOrderCouponUseCase createOrderCouponUseCase;


    private final OrderCouponEntity orderCouponEntity = OrderCouponEntity.builder()
            .couponType(1L)
            .code("CUPONOK")
            .value(BigDecimal.valueOf(1000L))
            .redeemedId("1234")
            .transactionId(1L)
            .build();

    private final OrderCouponDTO orderCouponDTO = OrderCouponDTO.builder()
            .couponType(1L)
            .code("CUPONOK")
            .value(BigDecimal.valueOf(1000L))
            .redeemedId("1234")
            .transactionId(1L)
            .build();

    private final RequestOrderTransactionDTO requestOrderTransactionDTO = RequestOrderTransactionDTO.builder()
            .id(1L)
            .couponResponseEntities(List.of(orderCouponDTO))
            .build();


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void test_When_Order_Coupon_Is_Ok_Then_Saved() {
        assertDoesNotThrow(() -> createOrderCouponUseCase.invoke(requestOrderTransactionDTO));
    }

    @Test
    void test_When_Order_Coupon_Is_Null(){
        RequestOrderTransactionDTO requestOrderTransactionDTO = RequestOrderTransactionDTO.builder()
            .id(null)
            .couponResponseEntities(List.of(orderCouponDTO))
            .build();

        assertDoesNotThrow(() -> createOrderCouponUseCase.invoke(requestOrderTransactionDTO));

    }

}