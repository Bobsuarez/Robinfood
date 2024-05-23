package com.robinfood.app.usecases.getorderdetailpaymentmethodbyorderids;

import com.robinfood.core.dtos.OrderPaymentDTO;
import com.robinfood.core.dtos.GetOrderDetailPaymentMethodDTO;

import java.util.List;
import java.util.Map;

/**
 * Use case retrieves the list of detail orders DTO
 */
public interface IGroupOrderDetailPaymentMethodsByOrderIds {

    /**
     * Retrieves the list of detail orders DTO
     *
     * @param orderPaymentDTOS the list order payment DTO
     * @return List <GetOrderDetailPaymentMethodDTO> the list of order detail order DTO
     */
    Map<Long, List<GetOrderDetailPaymentMethodDTO>> invoke(List<OrderPaymentDTO> orderPaymentDTOS);
}
