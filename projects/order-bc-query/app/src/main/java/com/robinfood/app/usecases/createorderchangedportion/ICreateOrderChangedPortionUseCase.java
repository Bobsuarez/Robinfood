package com.robinfood.app.usecases.createorderchangedportion;

import com.robinfood.core.dtos.OrderChangedPortionDTO;
import com.robinfood.core.dtos.request.order.FinalProductPortionDTO;

import java.util.List;

/**
 * Use case that stores the changed portions of the order
 */
public interface ICreateOrderChangedPortionUseCase {

    /**
     * Store and retrieves the created changed portions of the order
     * @param replacedPortionsDTO replacedPortions of the order
     * @return the recently created changed portion
     */
    List<OrderChangedPortionDTO> invoke(List<FinalProductPortionDTO> replacedPortionsDTO);
}
