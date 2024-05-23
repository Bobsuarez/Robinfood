package com.robinfood.app.commandhandler.commands;

import com.robinfood.app.usecases.validateco2.IValidateCO2UseCase;
import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CommandValidateCO2 implements ICommand {

    private final IValidateCO2UseCase validateCO2UseCase;

    public CommandValidateCO2(IValidateCO2UseCase validateCO2UseCase) {
        this.validateCO2UseCase = validateCO2UseCase;
    }

    @Override
    public void execute(String token, TransactionRequestDTO transactionRequestDTO) {
        this.validateCO2UseCase.invoke(token, transactionRequestDTO).join();
    }

    @Override
    public void rollback(String token, TransactionRequestDTO transactionRequestDTO) {}
}
