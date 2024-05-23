package com.robinfood.app.usecases.getorderdetailprint;

import com.robinfood.core.dtos.OrderDetailDTO;
import com.robinfood.core.enums.Result;

import java.util.List;

/**
 * Use case retrieves the order detail for print
 */
public interface IGetOrderDetailPrintUseCase {

    /**
     * Retrieves the order detail based on the following query params
     * @param orderIds the orders to consult
     * @param orderUids the orders uids
     * @param orderUuid the orders uuids
     * @return the order detail containing the info
     */
    Result<List<OrderDetailDTO>> invoke(
            List<Long> orderIds,
            List<String> orderUids,
            List<String> orderUuid
    );
}
