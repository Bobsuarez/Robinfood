package com.robinfood.app.usecases.changestatusorders;

import com.robinfood.core.dtos.changestatusordersrequestdto.ChangeStatusOrdersRequestDTO;
import org.jetbrains.annotations.NotNull;

/**
 * Use case that updates the orders status
 */
public interface IChangeOrdersStatusUseCase {

    /**
     * Updates orders status
     *
     * @param changeStatusOrdersRequestDTO orders change status request
     * @return true if order status was changed successfully
     */
    Boolean invoke(
        @NotNull ChangeStatusOrdersRequestDTO changeStatusOrdersRequestDTO
    );
}
