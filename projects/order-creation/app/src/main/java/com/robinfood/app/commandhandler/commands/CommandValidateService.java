package com.robinfood.app.commandhandler.commands;

import com.robinfood.app.usecases.validateservice.IValidateServiceUseCase;
import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CommandValidateService implements ICommand {

    private final IValidateServiceUseCase validateServiceUseCase;

    public CommandValidateService(IValidateServiceUseCase validateServiceUseCase) {
        this.validateServiceUseCase = validateServiceUseCase;
    }

    @Override
    public void execute(String token, TransactionRequestDTO transactionRequestDTO) {
        this.validateServiceUseCase.invoke(token, transactionRequestDTO).join();
    }

    @Override
    public void rollback(String token, TransactionRequestDTO transactionRequestDTO) {}
}
