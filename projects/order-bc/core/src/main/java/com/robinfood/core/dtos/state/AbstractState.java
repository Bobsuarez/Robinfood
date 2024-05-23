package com.robinfood.core.dtos.state;
import com.robinfood.core.dtos.OrderStateDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

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
        if(order.compareTo(nextState.getOrder()) > 0) {
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
