package com.robinfood.app.commandhandler.commands;

import com.robinfood.app.usecases.redeemcoupons.IRedeemCouponsUseCase;
import com.robinfood.app.usecases.rollbackcouponsredeemed.IRollbackCouponsRedeemedUseCase;
import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import org.springframework.stereotype.Component;

@Component
public class CommandRedeemCoupons implements ICommand {

    private final IRedeemCouponsUseCase redeemCouponsUseCase;
    private final IRollbackCouponsRedeemedUseCase rollbackCouponsRedeemedUseCase;

    public CommandRedeemCoupons(
            IRedeemCouponsUseCase redeemCouponsUseCase,
            IRollbackCouponsRedeemedUseCase rollbackCouponsRedeemedUseCase
    ) {
        this.redeemCouponsUseCase = redeemCouponsUseCase;
        this.rollbackCouponsRedeemedUseCase = rollbackCouponsRedeemedUseCase;
    }


    @Override
    public void execute(String token, TransactionRequestDTO transactionRequestDTO) {
        this.redeemCouponsUseCase.invoke(token, transactionRequestDTO).join();
    }

    @Override
    public void rollback(String token, TransactionRequestDTO transactionRequestDTO) {
        this.rollbackCouponsRedeemedUseCase.invoke(token, transactionRequestDTO);
    }
}
