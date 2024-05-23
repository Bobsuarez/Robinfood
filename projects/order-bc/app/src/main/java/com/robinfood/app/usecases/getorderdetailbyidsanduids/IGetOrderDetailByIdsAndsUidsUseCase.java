package com.robinfood.app.usecases.getorderdetailbyidsanduids;

import com.robinfood.core.dtos.GetOrderDetailDTO;

import java.util.List;

/**
 * Use case get detail Order's by id and uids
 */
public interface IGetOrderDetailByIdsAndsUidsUseCase {

    /**
     * Get Detail Order's by id and uids
     * @param orderIds
     * @param orderUids
     * @param orderUuid
     * @return Order detail List
     */
    List<GetOrderDetailDTO> invoke(List<Long> orderIds, List<String> orderUids, List<String> orderUuid);
}
