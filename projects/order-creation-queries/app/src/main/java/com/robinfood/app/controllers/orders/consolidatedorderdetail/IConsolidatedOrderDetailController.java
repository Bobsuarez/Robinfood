package com.robinfood.app.controllers.orders.consolidatedorderdetail;

import com.robinfood.core.dtos.ApiResponseDTO;
import com.robinfood.core.dtos.orderdetailplushistory.OrderDetailPlusHistoryDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

public interface IConsolidatedOrderDetailController {

    /**
     * Get order details plus history
     *
     * @param uuid          identifier the order
     * @return records with detail
     */
    ResponseEntity<ApiResponseDTO<OrderDetailPlusHistoryDTO>> invoke(
            @PathVariable String uuid
    );
}
