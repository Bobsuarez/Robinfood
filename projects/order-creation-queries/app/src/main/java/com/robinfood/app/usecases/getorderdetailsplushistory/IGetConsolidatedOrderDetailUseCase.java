package com.robinfood.app.usecases.getorderdetailsplushistory;

import com.robinfood.core.dtos.orderdetailplushistory.OrderDetailPlusHistoryDTO;
import com.robinfood.core.enums.Result;

public interface IGetConsolidatedOrderDetailUseCase {

    /**
     * Retrieves the order detail plus history
     * @param orderUuid the order uuid
     * @return the order detail containing the info
     */
    Result<OrderDetailPlusHistoryDTO> invoke(
            String orderUuid
    );
}
