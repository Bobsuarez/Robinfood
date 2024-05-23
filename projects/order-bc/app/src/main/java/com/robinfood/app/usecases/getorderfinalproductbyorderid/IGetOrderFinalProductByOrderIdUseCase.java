package com.robinfood.app.usecases.getorderfinalproductbyorderid;

import com.robinfood.core.dtos.OrderFinalProductDTO;
import java.util.List;

public interface IGetOrderFinalProductByOrderIdUseCase {

    /**
     * Method that searches for the products of an order by their orderId
     *
     * @param orderId   To consult
     * @return {@link List<OrderFinalProductDTO>}
     */
    List<OrderFinalProductDTO> invoke(Long orderId);

}
