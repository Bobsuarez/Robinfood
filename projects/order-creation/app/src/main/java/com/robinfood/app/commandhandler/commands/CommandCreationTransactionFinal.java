package com.robinfood.app.commandhandler.commands;

import com.robinfood.app.usecases.createtransaction.ICreateTransactionUseCase;
import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CommandCreationTransactionFinal implements ICommand {

    private final ICreateTransactionUseCase createTransactionUseCase;

    public CommandCreationTransactionFinal(ICreateTransactionUseCase createTransactionUseCase) {
        this.createTransactionUseCase = createTransactionUseCase;
    }

    @Override
    public void execute(String token, TransactionRequestDTO transactionRequestDTO) {
        this.createTransactionUseCase.invoke(token, transactionRequestDTO).join();
    }

    @Override
    public void rollback(String token, TransactionRequestDTO transactionRequestDTO) {

    }
}
