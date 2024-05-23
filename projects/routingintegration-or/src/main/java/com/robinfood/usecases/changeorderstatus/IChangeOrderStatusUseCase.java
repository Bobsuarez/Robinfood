package com.robinfood.usecases.changeorderstatus;

import com.robinfood.dtos.request.RequestChangeOrderStatusDTO;

/**
 * Orchestrate state changes from on-premises to the cloud use case
 */
public interface IChangeOrderStatusUseCase {

    /**
     * use cases change order status
     *
     * @param requestChangeOrderStatusDTO body
     * @param urlStackChannelOu           url services
     * @param token                       services token
     * @return ChangeOrderStatusDTO DTO
     */
    void invoke(
            RequestChangeOrderStatusDTO requestChangeOrderStatusDTO,
            String urlStackChannelOu,
            String token
    );

}
