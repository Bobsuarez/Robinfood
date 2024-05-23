package com.robinfood.repository.ordersstore;

import com.robinfood.core.dtos.ordersstore.DataOrderStoreRequestDTO;
import com.robinfood.core.dtos.ordersstore.OrderStoreDTO;
import com.robinfood.core.enums.Result;

import java.util.List;

public interface IOrdersStoreRepository {

    /**
     * get the list of orders details by store
     *
     * @param dataOrderStoreRequestDTO information for the different filters
     * @param token token auth service
     * @return data of orders store
     */
    Result<List<OrderStoreDTO>> getDataOrderStore(
            DataOrderStoreRequestDTO dataOrderStoreRequestDTO,
            String token
    );
}
