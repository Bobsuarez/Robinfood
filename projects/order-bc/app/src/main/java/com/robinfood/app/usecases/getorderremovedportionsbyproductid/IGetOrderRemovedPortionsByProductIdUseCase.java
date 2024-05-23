package com.robinfood.app.usecases.getorderremovedportionsbyproductid;

import com.robinfood.core.dtos.OrderDetailRemovedPortionDTO;

import java.util.List;

/**
 * Use case that returns the removed portions of some final products by id
 */
public interface IGetOrderRemovedPortionsByProductIdUseCase {

    /**
     * Retrieves the removed portions of some final products by their ids
     * @param finalProductIds the ids of the final products
     * @return a list containing each removed portion from the final product ids
     */
    List<OrderDetailRemovedPortionDTO> invoke(List<Long> finalProductIds);
}
