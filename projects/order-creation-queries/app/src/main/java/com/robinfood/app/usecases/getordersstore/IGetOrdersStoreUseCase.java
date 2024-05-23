package com.robinfood.app.usecases.getordersstore;

import com.robinfood.core.dtos.ordersstore.DataOrderStoreRequestDTO;
import com.robinfood.core.dtos.ordersstore.OrderStoreDTO;
import com.robinfood.core.enums.Result;

import java.util.List;

/**
 * Use case that returns the list of orders by store
 */
public interface IGetOrdersStoreUseCase {

    /**
     * retrieve the orders according to the entered criteria
     *
     * @param dataOrderStoreRequestDTO Dto that contains the data entered
     * @return object with the data pos resolution
     */
    Result<List<OrderStoreDTO>> invoke(DataOrderStoreRequestDTO dataOrderStoreRequestDTO);
}
