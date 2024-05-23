package com.robinfood.app.commandhandler.commands;

import com.robinfood.app.usecases.validatefoodcoins.IValidateFoodCoinsUseCase;
import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CommandValidateFoodCoins implements ICommand{

    private final IValidateFoodCoinsUseCase validateFoodCoinsUseCase;

    public CommandValidateFoodCoins(IValidateFoodCoinsUseCase validateFoodCoinsUseCase) {
        this.validateFoodCoinsUseCase = validateFoodCoinsUseCase;
    }

    @Override
    public void execute(String token, TransactionRequestDTO transactionRequestDTO) {
        this.validateFoodCoinsUseCase.invoke(token, transactionRequestDTO).join();
    }

    @Override
    public void rollback(String token, TransactionRequestDTO transactionRequestDTO) {}
}
