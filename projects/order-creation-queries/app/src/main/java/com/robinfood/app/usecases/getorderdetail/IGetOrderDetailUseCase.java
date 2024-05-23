package com.robinfood.app.usecases.getorderdetail;

import com.robinfood.core.dtos.OrderDetailDTO;
import com.robinfood.core.enums.Result;

import java.util.List;

/**
 * Use case that retrieves the details of the orders
 */
public interface IGetOrderDetailUseCase {

    /**
     * Retrieves the order detail based on the following query params
     * @param countryId the country to consult
     * @param flowId the flow to consult
     * @param orderIds the orders to consult
     * @param orderUids the orders to consult
     * @param orderUuid the orders uuids
     * @return  the order detail containing the info
     */
    Result<List<OrderDetailDTO>> invoke(
            Long countryId,
            Long flowId,
            List<Long> orderIds,
            List<String> orderUids,
            List<String> orderUuid
    );
}
