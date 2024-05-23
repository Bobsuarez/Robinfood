package com.robinfood.app.commandhandler.commands;

import com.robinfood.app.usecases.validatediscounts.IValidateDiscountsUseCase;
import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import org.springframework.stereotype.Component;

@Component
public class CommandValidateDiscounts implements ICommand {

    private final IValidateDiscountsUseCase validateDiscountsUseCase;

    public CommandValidateDiscounts(IValidateDiscountsUseCase validateDiscountsUseCase) {
        this.validateDiscountsUseCase = validateDiscountsUseCase;
    }

    @Override
    public void execute(String token, TransactionRequestDTO transactionRequestDTO) {
        this.validateDiscountsUseCase.invoke(token, transactionRequestDTO).join();
    }

    @Override
    public void rollback(String token, TransactionRequestDTO transactionRequestDTO) {

    }
}
