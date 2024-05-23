package com.robinfood.changestatusbc.factories.state;

import com.robinfood.changestatusbc.dtos.AbstractState;
import com.robinfood.changestatusbc.usecases.changepaidstate.IChangePaidStateUseCase;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o){

        if (o == null || getClass() != o.getClass()){
            return false;
        }
        if (!super.equals(o)){
            return false;
        }
        ApprovedState that = (ApprovedState) o;
        return Objects.equals(changePaidStateUseCase, that.changePaidStateUseCase);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), changePaidStateUseCase);
    }
}
