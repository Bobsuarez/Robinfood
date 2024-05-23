package com.robinfood.changestatusor.repository.changestateorders;

import com.robinfood.changestatusor.config.network.api.ChangeStatusBCAPI;
import com.robinfood.changestatusor.dtos.ChangeOrderStatusDTO;
import com.robinfood.changestatusor.entities.changestateorderequestentities.ChangeStateOrderRespondEntity;
import com.robinfood.changestatusor.mappers.ChangeStateOrderRespondEntityMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ChangeSateOrderRepository implements IChangeSateOrderRepository{

    private final ChangeStatusBCAPI changeStatusBCAPI;

    public ChangeSateOrderRepository(ChangeStatusBCAPI changeStatusBCAPI){
        this.changeStatusBCAPI = changeStatusBCAPI;
    }

    @Override
    public ChangeStateOrderRespondEntity changeOrderStatus(ChangeOrderStatusDTO changeOrderStatusDTO, String token) {

        log.info("Init changeOrderStatus changeOrderStatusDTO: {}  and token: {}", changeOrderStatusDTO, token);

        return changeStatusBCAPI.changeState(
                        ChangeStateOrderRespondEntityMapper
                                .fromChangeOrderStatusDTOToChangeStateOrderRespondEntity(changeOrderStatusDTO),
                        token).getData();
    }
}
