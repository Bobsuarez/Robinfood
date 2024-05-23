package com.robinfood.app.commandhandler.commands;

import com.robinfood.app.usecases.createtransaction.ICreateTransactionUseCase;
import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import org.springframework.stereotype.Component;

@Component
public class CommandCreationTransaction implements ICommand {

    private final ICreateTransactionUseCase createTransactionUseCase;

    public CommandCreationTransaction(ICreateTransactionUseCase createTransactionUseCase) {
        this.createTransactionUseCase = createTransactionUseCase;
    }

    @Override
    public void execute(String token, TransactionRequestDTO transactionRequestDTO) {
        this.createTransactionUseCase.invoke(token, transactionRequestDTO).join();
    }

    @Override
    public void rollback(String token, TransactionRequestDTO transactionRequestDTO) {}
}
