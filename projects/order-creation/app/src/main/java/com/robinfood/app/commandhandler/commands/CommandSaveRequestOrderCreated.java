package com.robinfood.app.commandhandler.commands;

import com.robinfood.app.usecases.saverequestordercreated.ISaveRequestOrderCreatedUseCase;
import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import org.springframework.stereotype.Component;

@Component
public class CommandSaveRequestOrderCreated implements ICommand {

    private final ISaveRequestOrderCreatedUseCase saveRequestOrderCreatedUseCase;

    public CommandSaveRequestOrderCreated(ISaveRequestOrderCreatedUseCase saveRequestOrderCreatedUseCase) {
        this.saveRequestOrderCreatedUseCase = saveRequestOrderCreatedUseCase;
    }

    @Override
    public void execute(String token, TransactionRequestDTO transactionRequestDTO) {
        this.saveRequestOrderCreatedUseCase.invoke(transactionRequestDTO);
    }

    @Override
    public void rollback(String token, TransactionRequestDTO transactionRequestDTO) {
    }
}
