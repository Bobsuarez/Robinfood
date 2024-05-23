package com.robinfood.app.commandhandler.commands;

import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;

public interface ICommand {
    
    void execute(String token, TransactionRequestDTO transactionRequestDTO);

    void rollback(String token, TransactionRequestDTO transactionRequestDTO);
}
