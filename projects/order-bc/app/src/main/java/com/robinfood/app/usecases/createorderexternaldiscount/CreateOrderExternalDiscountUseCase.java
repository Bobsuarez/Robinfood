package com.robinfood.app.usecases.createorderexternaldiscount;

import com.robinfood.core.dtos.request.order.DeliveryDTO;
import com.robinfood.core.entities.OrderExternalDiscountEntity;
import com.robinfood.repository.orderexternaldiscount.IOrderExternalDiscountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Implementation of ICreateOrderExternalDiscountUseCase
 */
@Component
@Slf4j
public class CreateOrderExternalDiscountUseCase implements ICreateOrderExternalDiscountUseCase {

    private final IOrderExternalDiscountRepository orderExternalDiscountRepository;

    public CreateOrderExternalDiscountUseCase(IOrderExternalDiscountRepository orderExternalDiscountRepository) {
        this.orderExternalDiscountRepository = orderExternalDiscountRepository;
    }

    @Override
    public void invoke(DeliveryDTO deliveryDTO, Long orderId) {

        log.info(
                "Starting process to save order external discount with delivery: [{}], and order id: [{}]",
                deliveryDTO,
                orderId
        );

        final OrderExternalDiscountEntity orderExternalDiscountEntity = new OrderExternalDiscountEntity();
        orderExternalDiscountEntity.setOrderId(orderId);
        orderExternalDiscountEntity.setType(deliveryDTO.getType());
        orderExternalDiscountEntity.setValue(deliveryDTO.getTotalDiscount());

        orderExternalDiscountRepository.save(orderExternalDiscountEntity);
    }
}
