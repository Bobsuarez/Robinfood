package com.robinfood.app.commandhandler.commands;

import com.robinfood.app.usecases.getinformationbystore.IGetInformationByStoreUseCase;
import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CommandInformationByStore implements ICommand {

    private final IGetInformationByStoreUseCase getInformationByStoreUseCase;

    public CommandInformationByStore(IGetInformationByStoreUseCase getInformationByStore) {
        this.getInformationByStoreUseCase = getInformationByStore;
    }

    @Override
    public void execute(String token, TransactionRequestDTO transactionRequestDTO) {
        this.getInformationByStoreUseCase.invoke(token, transactionRequestDTO).join();
    }

    @Override
    public void rollback(String token, TransactionRequestDTO transactionRequestDTO) {}
}

