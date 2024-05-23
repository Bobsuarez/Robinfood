package com.robinfood.app.commandhandler.commands;

import com.robinfood.app.usecases.validatecoupons.IValidateCouponUseCase;
import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CommandValidateCoupons implements ICommand{

    private final IValidateCouponUseCase validateCouponsUseCase;

    public CommandValidateCoupons(IValidateCouponUseCase validateCouponsUseCase) {
        this.validateCouponsUseCase = validateCouponsUseCase;
    }

    @Override
    public void execute(String token, TransactionRequestDTO transactionRequestDTO) {
        this.validateCouponsUseCase.invoke(token, transactionRequestDTO).join();
    }

    @Override
    public void rollback(String token, TransactionRequestDTO transactionRequestDTO) {}
}
