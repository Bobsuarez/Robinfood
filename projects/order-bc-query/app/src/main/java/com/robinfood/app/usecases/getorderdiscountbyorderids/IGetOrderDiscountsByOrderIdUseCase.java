package com.robinfood.app.usecases.getorderdiscountbyorderids;

import com.robinfood.core.dtos.OrderDiscountDTO;
import java.util.List;

public interface IGetOrderDiscountsByOrderIdUseCase {

    /**
     * Method that obtains the discounts of an order by its orderId
     *
     * @param orderId   To consult
     * @return {@link List<OrderDiscountDTO>}
     */
    List<OrderDiscountDTO> invoke(Long orderId);

}
