package com.robinfood.app.commandhandler.commands;

import com.robinfood.app.usecases.getfinalproducttaxes.IGetFinalProductTaxesUseCase;
import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import org.springframework.stereotype.Component;

@Component
public class CommandGetFinalProductTaxes implements ICommand {

    private final IGetFinalProductTaxesUseCase finalProductTaxesUseCase;

    public CommandGetFinalProductTaxes(IGetFinalProductTaxesUseCase finalProductTaxesUseCase) {
        this.finalProductTaxesUseCase = finalProductTaxesUseCase;
    }

    @Override
    public void execute(String token, TransactionRequestDTO transactionRequestDTO) {
        this.finalProductTaxesUseCase.invoke(token, transactionRequestDTO).join();
    }

    @Override
    public void rollback(String token, TransactionRequestDTO transactionRequestDTO) {

    }
}
