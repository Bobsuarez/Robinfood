package com.robinfood.localorderbc.repositories.changestate;

import com.robinfood.localorderbc.configs.apis.OrderCreationApi;
import com.robinfood.localorderbc.dtos.request.ChangeStateDTO;
import com.robinfood.localorderbc.entities.changestate.ChangeStateEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
public class ChangeStateRepository implements IChangeStateRepository {

    private OrderCreationApi orderCreationApi;

    public ChangeStateRepository(OrderCreationApi orderCreationApi) {
        this.orderCreationApi = orderCreationApi;
    }

    @Override
    public ChangeStateEntity responseChangeState(String token, ChangeStateDTO changeStateDTO) {

        log.info("Change Status Order repository with data {} and token {} ",
                changeStateDTO, token);

        return orderCreationApi.changeState(token, changeStateDTO).getData();

    }

}
