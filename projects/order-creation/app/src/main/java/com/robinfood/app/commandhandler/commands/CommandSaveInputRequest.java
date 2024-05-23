package com.robinfood.app.commandhandler.commands;

import com.robinfood.app.usecases.saveinputrequest.ISaveInputRequestUseCase;
import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import org.springframework.stereotype.Component;

@Component
public class CommandSaveInputRequest implements ICommand {

    private final ISaveInputRequestUseCase saveInputRequestUseCase;

    public CommandSaveInputRequest(ISaveInputRequestUseCase saveInputRequestUseCase) {
        this.saveInputRequestUseCase = saveInputRequestUseCase;
    }

    @Override
    public void execute(String token, TransactionRequestDTO transactionRequestDTO) {
        this.saveInputRequestUseCase.invoke(transactionRequestDTO);
    }

    @Override
    public void rollback(String token, TransactionRequestDTO transactionRequestDTO) {
    }
}
