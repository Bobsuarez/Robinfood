package com.robinfood.app.commandhandler.commands;

import com.robinfood.app.usecases.validatemenu.IValidateMenuUseCase;
import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CommandValidateMenu implements ICommand {

    private final IValidateMenuUseCase validateMenuUseCase;

    public CommandValidateMenu(IValidateMenuUseCase validateMenuUseCase) {
        this.validateMenuUseCase = validateMenuUseCase;
    }

    @Override
    public void execute(String token, TransactionRequestDTO transactionRequestDTO) {
        this.validateMenuUseCase.invoke(token, transactionRequestDTO).join();
    }

    @Override
    public void rollback(String token, TransactionRequestDTO transactionRequestDTO) {}
}
