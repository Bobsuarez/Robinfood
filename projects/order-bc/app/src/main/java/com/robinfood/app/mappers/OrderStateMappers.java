package com.robinfood.app.mappers;

import com.robinfood.core.dtos.OrderStateDTO;
import com.robinfood.core.entities.StatusEntity;
import org.springframework.stereotype.Component;

@Component
public class OrderStateMappers {

    public OrderStateDTO buildOrderState(StatusEntity status) {

        OrderStateDTO orderStateDTO = new OrderStateDTO();
        orderStateDTO.setName(status.getName());
        orderStateDTO.setId(status.getId());
        orderStateDTO.setCode(status.getCode());
        orderStateDTO.setOrder(status.getOrder());
        return orderStateDTO;

    }

    public OrderStateDTO buildOrderState(StatusEntity status, StatusEntity parent) {

        OrderStateDTO orderState = new OrderStateDTO();
        OrderStateDTO subState = new OrderStateDTO();

        orderState.setName(parent.getName());
        orderState.setId(parent.getId());
        orderState.setCode(parent.getCode());
        orderState.setOrder(parent.getOrder());
        subState.setId(status.getId());
        subState.setName(status.getName());
        subState.setCode(status.getCode());
        subState.setOrder(status.getOrder());
        orderState.setSubState(subState);

        return orderState;
    }
}
