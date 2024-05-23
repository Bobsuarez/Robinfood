package com.robinfood.app.factory.state;

import com.robinfood.app.usecases.changepaidstate.IChangePaidStateUseCase;
import com.robinfood.core.dtos.state.AbstractState;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

@Slf4j
public class ApprovedState extends AbstractState {

    private final IChangePaidStateUseCase changePaidStateUseCase;

    public ApprovedState(
        BigDecimal order,
        String code,
        IChangePaidStateUseCase changPaidUseCase) {
        super(order, code);
        this.changePaidStateUseCase = changPaidUseCase;
    }

    @Override
    public Boolean action(Long idOrder) {

        log.info("Executing action for ApprovedState");

        return changePaidStateUseCase.changePaid(idOrder);
    }

}
