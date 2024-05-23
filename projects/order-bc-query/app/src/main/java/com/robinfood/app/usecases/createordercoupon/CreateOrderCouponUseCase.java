package com.robinfood.app.usecases.createordercoupon;

import com.robinfood.app.mappers.OrderCouponMappersKt;
import com.robinfood.core.dtos.OrderCouponDTO;
import com.robinfood.core.dtos.request.transaction.RequestOrderTransactionDTO;
import com.robinfood.core.entities.OrderCouponEntity;
import com.robinfood.repository.ordercoupons.IOrderCouponRepository;
import kotlin.collections.CollectionsKt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

/**
 * Implementation of  {@link  ICreateOrderCouponUseCase}
 */
@Service
@Slf4j
public class CreateOrderCouponUseCase implements ICreateOrderCouponUseCase {

    private final IOrderCouponRepository orderCouponRepository;

    public CreateOrderCouponUseCase(IOrderCouponRepository orderCouponRepository) {
        this.orderCouponRepository = orderCouponRepository;
    }

    @Override
    @Transactional
    public CompletableFuture<Boolean> invoke(RequestOrderTransactionDTO requestOrderTransactionDTO) {

        log.info("Initializer create order coupon with data {}", requestOrderTransactionDTO);

        List<OrderCouponDTO> listOrderCouponDTO = requestOrderTransactionDTO.getCouponResponseEntities();

        List<OrderCouponEntity> listOrderCouponEntity = CollectionsKt.map(
                listOrderCouponDTO, OrderCouponMappersKt::toResponseOrderDiscountEntity
        );

        if (Objects.nonNull(requestOrderTransactionDTO.getId())) {
            listOrderCouponEntity.forEach(
                    orderCouponEntity -> orderCouponEntity.setTransactionId(requestOrderTransactionDTO.getId())
            );
        }

        orderCouponRepository.saveAll(listOrderCouponEntity);
        return CompletableFuture.completedFuture(Boolean.TRUE);

    }
}

