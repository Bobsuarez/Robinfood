package com.robinfood.app.commandhandler.commands;

import com.robinfood.app.usecases.roundvaluestransaction.IRoundValuesTransactionUseCase;
import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import org.springframework.stereotype.Component;

@Component
public class CommandRoundValuesTransaction implements ICommand {

    private final IRoundValuesTransactionUseCase roundValuesTransactionUseCase;

    public CommandRoundValuesTransaction(IRoundValuesTransactionUseCase roundValuesTransactionUseCase) {
        this.roundValuesTransactionUseCase = roundValuesTransactionUseCase;
    }

    @Override
    public void execute(String token, TransactionRequestDTO transactionRequestDTO) {
        roundValuesTransactionUseCase.invoke(transactionRequestDTO);
    }

    @Override
    public void rollback(String token, TransactionRequestDTO transactionRequestDTO) {
    }
}
