package com.robinfood.app.commandhandler.commands;

import com.robinfood.app.usecases.issueelectronicbilling.IIssueElectronicBillingUseCase;
import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import org.springframework.stereotype.Component;

@Component
public class CommandIssueElectronicBilling implements ICommand {

    private final IIssueElectronicBillingUseCase issueElectronicBillingUseCase;

    public CommandIssueElectronicBilling(IIssueElectronicBillingUseCase issueElectronicBillingUseCase) {
        this.issueElectronicBillingUseCase = issueElectronicBillingUseCase;
    }

    @Override
    public void execute(String token, TransactionRequestDTO transactionRequestDTO) {
        this.issueElectronicBillingUseCase.invoke(token, transactionRequestDTO);
    }

    @Override
    public void rollback(String token, TransactionRequestDTO transactionRequestDTO) {

    }
}
