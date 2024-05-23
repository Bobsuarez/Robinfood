package com.robinfood.app.commandhandler.commands;

import com.robinfood.app.usecases.getfinalproductcategories.IGetFinalProductFinanceCategoriesUseCase;
import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import org.springframework.stereotype.Component;

@Component
public class CommandGetFinalProductFinanceCategories implements ICommand {

    private final IGetFinalProductFinanceCategoriesUseCase getFinalProductsFinanceCategoriesUseCase;

    public CommandGetFinalProductFinanceCategories(
            IGetFinalProductFinanceCategoriesUseCase getFinalProductsFinanceCategoriesUseCase
    ) {
        this.getFinalProductsFinanceCategoriesUseCase = getFinalProductsFinanceCategoriesUseCase;
    }

    @Override
    public void execute(String token, TransactionRequestDTO transactionRequestDTO) {
        this.getFinalProductsFinanceCategoriesUseCase.invoke(token, transactionRequestDTO).join();
    }

    @Override
    public void rollback(String token, TransactionRequestDTO transactionRequestDTO) {
    }
}
