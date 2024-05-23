package com.robinfood.app.commandhandler.commands;

import com.robinfood.app.usecases.getposinformation.IGetPosInformationUseCase;
import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import org.springframework.stereotype.Component;

@Component
public class CommandPosInformation implements ICommand {

    private final IGetPosInformationUseCase getPosInformationUseCase;

    public CommandPosInformation(IGetPosInformationUseCase getPosInformationUseCase) {
        this.getPosInformationUseCase = getPosInformationUseCase;
    }

    @Override
    public void execute(String token, TransactionRequestDTO transactionRequestDTO) {
        this.getPosInformationUseCase.invoke(token, transactionRequestDTO).join();
    }

    @Override
    public void rollback(String token, TransactionRequestDTO transactionRequestDTO) {}
}
