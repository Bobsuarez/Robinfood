package com.robinfood.app.commandhandler.commands;

import com.robinfood.app.usecases.savetemporarytransactionvalidatingpaymentmethods.ISaveTemporaryTransactionValidatingPaymentMethods;
import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CommandSaveTemporaryTransactionValidatingPaymentMethods implements ICommand {

    private final ISaveTemporaryTransactionValidatingPaymentMethods saveTemporaryTransactionValidatingPaymentMethods;

    public CommandSaveTemporaryTransactionValidatingPaymentMethods(
            ISaveTemporaryTransactionValidatingPaymentMethods saveTemporaryTransactionValidatingPaymentMethods
    ) {
        this.saveTemporaryTransactionValidatingPaymentMethods = saveTemporaryTransactionValidatingPaymentMethods;
    }

    @Override
    public void execute(String token, TransactionRequestDTO transactionRequestDTO) {
        this.saveTemporaryTransactionValidatingPaymentMethods.invoke(transactionRequestDTO);
    }

    @Override
    public void rollback(String token, TransactionRequestDTO transactionRequestDTO) {
    }
}
