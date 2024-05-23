package com.robinfood.app.commandhandler.commands;

import com.robinfood.app.usecases.convertidstransactiontonull.IConvertIdsTransactionToNullUseCase;
import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import org.springframework.stereotype.Component;

@Component
public class CommandConvertIdsTransactionToNull implements ICommand {

    private final IConvertIdsTransactionToNullUseCase convertIdsTransactionToNullUseCase;

    public CommandConvertIdsTransactionToNull(IConvertIdsTransactionToNullUseCase convertIdsTransactionToNullUseCase) {
        this.convertIdsTransactionToNullUseCase = convertIdsTransactionToNullUseCase;
    }

    @Override
    public void execute(String token, TransactionRequestDTO transactionRequestDTO) {
        this.convertIdsTransactionToNullUseCase.invoke(transactionRequestDTO);
    }

    @Override
    public void rollback(String token, TransactionRequestDTO transactionRequestDTO) {
    }
}
