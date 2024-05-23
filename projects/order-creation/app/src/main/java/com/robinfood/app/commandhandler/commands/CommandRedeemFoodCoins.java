package com.robinfood.app.commandhandler.commands;

import com.robinfood.app.usecases.redeemfoodcoins.IRedeemFoodCoinsUseCase;
import com.robinfood.app.usecases.rollbackfoodcoins.IRollbackFoodCoinsUseCase;
import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import org.springframework.stereotype.Component;

@Component
public class CommandRedeemFoodCoins implements ICommand {

    private final IRedeemFoodCoinsUseCase redeemFoodCoinsUseCase;
    private final IRollbackFoodCoinsUseCase rollbackFoodCoinsUseCase;

    public CommandRedeemFoodCoins(
            IRedeemFoodCoinsUseCase redeemFoodCoinsUseCase,
            IRollbackFoodCoinsUseCase rollbackFoodCoinsUseCase
    ) {
        this.redeemFoodCoinsUseCase = redeemFoodCoinsUseCase;
        this.rollbackFoodCoinsUseCase = rollbackFoodCoinsUseCase;
    }

    @Override
    public void execute(String token, TransactionRequestDTO transactionRequestDTO) {
        this.redeemFoodCoinsUseCase.invoke(token, transactionRequestDTO).join();
    }

    @Override
    public void rollback(String token, TransactionRequestDTO transactionRequestDTO) {
        this.rollbackFoodCoinsUseCase.invoke(token, transactionRequestDTO);
    }
}
