package com.robinfood.app.usecases.getorderbyid;

import com.robinfood.core.dtos.OrderDTO;

public interface IGetOrderByIdUseCase {

    /**
     * Method that obtains an order by its orderId
     *
     * @param orderId   To consult
     * @return {@link OrderDTO}
     */
    OrderDTO invoke(Long orderId);

}
