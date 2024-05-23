package com.robinfood.repository.changeorderstatus;

import com.robinfood.dtos.request.RequestChangeOrderStatusDTO;

/**
 * Repository Orchestrate state changes from on-premises to the cloud
 */
public interface IChangeOrderStatusRepository {

    /**
     * services repository change order status
     *
     * @param requestChangeOrderStatusDTO body
     * @param url                         url services
     * @param token                       services token
     * @return ResponseChangeOrderStatusEntity Entity
     */
    void changeOrderStatus(
            RequestChangeOrderStatusDTO requestChangeOrderStatusDTO,
            String url,
            String token
    );
}
