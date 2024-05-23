package com.robinfood.changestatusbc.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

import static com.robinfood.changestatusbc.configs.constants.GlobalConstants.DEFAULT_INTEGER_VALUE;

@Data
@AllArgsConstructor
public abstract class AbstractState {

    private BigDecimal order;
    private String code;

    /**
     * Validate if is posible to get to this state
     *
     * @param nextState that the order wants to be
     * @return True if is posible to move to this State otherwise return false
     */
    public Boolean nextState(OrderStateDTO nextState) {

        if(order.compareTo(nextState.getOrder()) > DEFAULT_INTEGER_VALUE) {
            return Boolean.FALSE;
        }

        return Boolean.TRUE;
    }

    /**
     * Execute and actions unique to this state
     *
     * @return True if is posible to  execute the action
     */
    public abstract Boolean action(Long idOrder);
}
