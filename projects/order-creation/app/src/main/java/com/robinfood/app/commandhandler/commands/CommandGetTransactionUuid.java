package com.robinfood.app.commandhandler.commands;

import com.robinfood.app.usecases.gettransactionuuid.IGetTransactionUuidUseCase;
import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CommandGetTransactionUuid implements ICommand {

    private final IGetTransactionUuidUseCase getTransactionUuidUseCase;

    public CommandGetTransactionUuid(IGetTransactionUuidUseCase getTransactionUuidUseCase) {
        this.getTransactionUuidUseCase = getTransactionUuidUseCase;
    }

    @Override
    public void execute(String token, TransactionRequestDTO transactionRequestDTO) {
        this.getTransactionUuidUseCase.invoke(token, transactionRequestDTO);
    }

    @Override
    public void rollback(String token, TransactionRequestDTO transactionRequestDTO) {}
}
