package com.robinfood.ordereports_bc_muyapp.usecases.getordercouponbytransactionid;

import com.robinfood.app.library.util.ObjectMapperSingleton;
import com.robinfood.ordereports_bc_muyapp.dto.OrderCouponDTO;
import com.robinfood.ordereports_bc_muyapp.dto.orderdetail.ResponseCouponsDTO;
import com.robinfood.ordereports_bc_muyapp.models.mapper.OrderDetailOrderMapper;
import com.robinfood.ordereports_bc_muyapp.repository.ordercoupons.IOrderCouponRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@AllArgsConstructor
@Component
public class GetOrderCouponByTransactionIdUseCase implements IGetOrderCouponByTransactionIdUseCase {

    private final IOrderCouponRepository getOrderCouponRepository;

    private final OrderDetailOrderMapper orderDetailOrderMapper;

    @Override
    public CompletableFuture<List<ResponseCouponsDTO>> invoke(Integer transactionId) {
        return CompletableFuture.supplyAsync(() -> getDataOrderCoupon(transactionId));
    }

    private List<ResponseCouponsDTO> getDataOrderCoupon(Integer transactionId) {
        return getOrderCouponRepository.findAllByTransactionId(transactionId)
                .map(orderCouponEntities -> orderCouponEntities
                        .stream()
                        .map(orderCouponEntity -> ObjectMapperSingleton
                                .objectToClassConvertValue(
                                        orderCouponEntity,
                                        OrderCouponDTO.class
                                ))
                        .map(orderDetailOrderMapper::mapFinalProductToResponseCouponDTO)
                        .toList())
                .orElse(Collections.emptyList());
    }
}
