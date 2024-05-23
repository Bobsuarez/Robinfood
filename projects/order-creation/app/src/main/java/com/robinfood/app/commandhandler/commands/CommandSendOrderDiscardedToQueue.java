package com.robinfood.app.commandhandler.commands;

import com.robinfood.app.usecases.sendorderdiscardedtoqueue.ISendOrderDiscardedToQueueUseCase;
import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import org.springframework.stereotype.Component;

@Component
public class CommandSendOrderDiscardedToQueue implements ICommand {

    private final ISendOrderDiscardedToQueueUseCase sendOrderDiscardedToQueueUseCase;

    public CommandSendOrderDiscardedToQueue(ISendOrderDiscardedToQueueUseCase sendOrderDiscardedToQueueUseCase) {
        this.sendOrderDiscardedToQueueUseCase = sendOrderDiscardedToQueueUseCase;
    }

    @Override
    public void execute(String token, TransactionRequestDTO transactionRequestDTO) {
        this.sendOrderDiscardedToQueueUseCase.invoke(transactionRequestDTO);
    }

    @Override
    public void rollback(String token, TransactionRequestDTO transactionRequestDTO) {
    }
}
