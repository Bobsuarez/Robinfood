package com.robinfood.changestatusor.usecases.changeorderstatus;

import com.robinfood.changestatusor.dtos.ChangeOrderStatusDTO;

/**
 * Orchestrate state changes from on-premises to the cloud use case
 */
public interface IChangeOrderStatusUseCase {

    /**
     * use cases change order status
     *
     * @param changeOrderStatusDTO body
     * @return ChangeOrderStatusDTO DTO
     */
    ChangeOrderStatusDTO invoke(
            ChangeOrderStatusDTO changeOrderStatusDTO
    );
}
