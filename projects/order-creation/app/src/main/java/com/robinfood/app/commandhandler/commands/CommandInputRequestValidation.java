package com.robinfood.app.commandhandler.commands;

import com.robinfood.app.usecases.inputrequestvalidation.IInputRequestValidationUseCase;
import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import org.springframework.stereotype.Component;

@Component
public class CommandInputRequestValidation implements ICommand {

    private final IInputRequestValidationUseCase inputRequestValidationUseCase;

    public CommandInputRequestValidation(IInputRequestValidationUseCase inputRequestValidationUseCase) {
        this.inputRequestValidationUseCase = inputRequestValidationUseCase;
    }

    @Override
    public void execute(String token, TransactionRequestDTO transactionRequestDTO) {
        this.inputRequestValidationUseCase.invoke(transactionRequestDTO);
    }

    @Override
    public void rollback(String token, TransactionRequestDTO transactionRequestDTO) {

    }
}
