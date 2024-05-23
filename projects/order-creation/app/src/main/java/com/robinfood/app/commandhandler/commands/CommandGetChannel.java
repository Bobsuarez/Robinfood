package com.robinfood.app.commandhandler.commands;

import com.robinfood.app.usecases.getchannelbyid.IGetChannelByIdUseCase;
import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import org.springframework.stereotype.Component;

@Component
public class CommandGetChannel implements ICommand {

    private final IGetChannelByIdUseCase getChannelByIdUseCase;

    public CommandGetChannel(IGetChannelByIdUseCase getChannelByIdUseCase) {
        this.getChannelByIdUseCase = getChannelByIdUseCase;
    }

    @Override
    public void execute(String token, TransactionRequestDTO transactionRequestDTO) {
        this.getChannelByIdUseCase.invoke(token, transactionRequestDTO).join();
    }

    @Override
    public void rollback(String token, TransactionRequestDTO transactionRequestDTO) {
    }
}
