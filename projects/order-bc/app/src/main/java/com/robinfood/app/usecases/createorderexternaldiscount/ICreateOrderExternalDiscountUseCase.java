package com.robinfood.app.usecases.createorderexternaldiscount;

import com.robinfood.core.dtos.request.order.DeliveryDTO;

/**
 * Use case that gets the create order external discount
 */
public interface ICreateOrderExternalDiscountUseCase {

    /**
     *
     * @param deliveryDTO the order external discount for creating an order external discount
     * @param orderId
     */
    void invoke(DeliveryDTO deliveryDTO, Long orderId);
}
