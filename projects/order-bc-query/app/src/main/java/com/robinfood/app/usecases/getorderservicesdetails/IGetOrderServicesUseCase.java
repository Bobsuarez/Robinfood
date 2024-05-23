package com.robinfood.app.usecases.getorderservicesdetails;

import com.robinfood.core.dtos.response.orderhistory.GetOrderServicesDetailsDTO;
import com.robinfood.core.exceptions.ResourceNotFoundException;

import java.util.List;

/**
 * Use case that returns the order services detail by uid
 */
public interface IGetOrderServicesUseCase {

    /**
     * Gets the order services detail by uid
     * @param orderId order identification
     * @return the order services detail by uid
     */
    List<GetOrderServicesDetailsDTO> invoke(Long orderId);
}
