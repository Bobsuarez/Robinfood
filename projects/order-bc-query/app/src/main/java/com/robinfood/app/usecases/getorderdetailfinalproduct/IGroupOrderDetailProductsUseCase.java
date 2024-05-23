package com.robinfood.app.usecases.getorderdetailfinalproduct;

import com.robinfood.core.dtos.GetOrderDetailFinalProductDTO;
import com.robinfood.core.dtos.OrderDeductionFinalProductDTO;
import com.robinfood.core.dtos.OrderDiscountDTO;
import com.robinfood.core.dtos.OrderProductTaxDTO;
import java.util.List;
import java.util.Map;

/**
 * Use case that groups all order detail final product dtos items by their id
 */
public interface IGroupOrderDetailProductsUseCase {

    /**
     * Retrieves the list of order final product DTO by order id
     *
     * @param orderIds the list orders id's
     * @param orderFinalProductIds the list products id's
     * @param orderDiscountDTOS the list discounts id's
     * @param orderProductTaxDTOS the list taxes id's
     * @return the grouped elements of order detail final product DTO
     */
    Map<Long, List<GetOrderDetailFinalProductDTO>> invoke(
            List<Long> orderIds,
            List<Long> orderFinalProductIds,
            List<OrderDiscountDTO> orderDiscountDTOS,
            List<OrderProductTaxDTO> orderProductTaxDTOS,
            List<OrderDeductionFinalProductDTO> deductionDTOS
    );
}
