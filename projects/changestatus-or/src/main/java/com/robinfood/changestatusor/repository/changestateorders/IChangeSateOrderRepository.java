package com.robinfood.changestatusor.repository.changestateorders;

import com.robinfood.changestatusor.dtos.ChangeOrderStatusDTO;
import com.robinfood.changestatusor.entities.changestateorderequestentities.ChangeStateOrderRespondEntity;

/**
 * Repository Orchestrate state changes from on-premises to the cloud
 */
public interface IChangeSateOrderRepository {

    /**
     * services repository change order status
     *
     * @param changeOrderStatusDTO body
     * @param token  services token
     * @return ResponseChangeOrderStatusEntity Entity
     */
    ChangeStateOrderRespondEntity changeOrderStatus(
            ChangeOrderStatusDTO changeOrderStatusDTO,
            String token
    );
}
