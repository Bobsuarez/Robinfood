package com.robinfood.app.commandhandler.commands;

import com.robinfood.app.usecases.savepickuptime.ISavePickupTimeUseCase;
import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import org.springframework.stereotype.Component;

@Component
public class CommandSavePickupTime implements ICommand {

    private final ISavePickupTimeUseCase savePickupTimeUseCase;

    public CommandSavePickupTime(ISavePickupTimeUseCase savePickupTimeUseCase) {
        this.savePickupTimeUseCase = savePickupTimeUseCase;
    }

    @Override
    public void execute(String token, TransactionRequestDTO transactionRequestDTO) {
        this.savePickupTimeUseCase.invoke(token, transactionRequestDTO).join();
    }

    @Override
    public void rollback(String token, TransactionRequestDTO transactionRequestDTO) {
    }
}
