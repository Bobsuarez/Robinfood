package com.robinfood.app.usecases.getorderdetailfinalproducttax;

import com.robinfood.core.dtos.OrderProductTaxDTO;
import com.robinfood.core.dtos.GetOrderDetailFinalProductTaxDTO;

import java.util.List;
import java.util.Map;

public interface IGetOrderDetailFinalProductTaxUseCase {

    /**
     * Retrieves the list of order final product taxes DTO by product id
     *
     * @param orderProductTaxDTOS the list order final product id's
     * @return Map<Long, List <GetOrderDetailFinalProductTaxDTO>
     * Long the order final product id
     * List<GetOrderDetailFinalProductTaxDTO> the list of order final product taxes DTO
     */
    Map<Long, List<GetOrderDetailFinalProductTaxDTO>> invoke(List<OrderProductTaxDTO> orderProductTaxDTOS);
}
