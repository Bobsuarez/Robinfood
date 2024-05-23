package com.robinfood.changestatusbc.factories.state;

import com.robinfood.changestatusbc.dtos.AbstractState;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

@Slf4j
public class DefaultState extends AbstractState {

    public DefaultState(BigDecimal order, String code) {
        super(order, code);
    }

    @Override
    public Boolean action(Long idOrder) {
        return Boolean.TRUE;
    }
}
