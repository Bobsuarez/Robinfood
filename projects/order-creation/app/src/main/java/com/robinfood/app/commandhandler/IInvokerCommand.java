package com.robinfood.app.commandhandler;

import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;

public interface IInvokerCommand {

    void group(String commandGroup, TransactionRequestDTO transactionRequestDTO);

    void single(String command, TransactionRequestDTO transactionRequestDTO);

    void rollback();
}
