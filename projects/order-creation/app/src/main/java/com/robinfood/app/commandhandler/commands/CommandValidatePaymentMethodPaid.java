package com.robinfood.app.commandhandler.commands;

import com.robinfood.app.usecases.validatepaymentmethodpaid.IValidatePaymentMethodPaidUseCase;
import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import org.springframework.stereotype.Component;

@Component
public class CommandValidatePaymentMethodPaid implements ICommand {

    private final IValidatePaymentMethodPaidUseCase validatePaymentMethodPaidUseCase;

    public CommandValidatePaymentMethodPaid(IValidatePaymentMethodPaidUseCase validatePaymentMethodPaidUseCase) {
        this.validatePaymentMethodPaidUseCase = validatePaymentMethodPaidUseCase;
    }

    @Override
    public void execute(String token, TransactionRequestDTO transactionRequestDTO) {
        this.validatePaymentMethodPaidUseCase.invoke(token, transactionRequestDTO);
    }

    @Override
    public void rollback(String token, TransactionRequestDTO transactionRequestDTO) {
    }
}
