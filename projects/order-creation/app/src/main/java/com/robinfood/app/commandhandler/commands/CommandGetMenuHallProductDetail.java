package com.robinfood.app.commandhandler.commands;

import com.robinfood.app.usecases.getmenuhallproductdetail.IGetMenuHallProductDetailUseCase;
import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import org.springframework.stereotype.Component;

@Component
public class CommandGetMenuHallProductDetail implements ICommand {

    private final IGetMenuHallProductDetailUseCase getMenuHallProductDetailUseCase;

    public CommandGetMenuHallProductDetail(IGetMenuHallProductDetailUseCase getMenuHallProductDetailUseCase) {
        this.getMenuHallProductDetailUseCase = getMenuHallProductDetailUseCase;
    }

    @Override
    public void execute(String token, TransactionRequestDTO transactionRequestDTO) {
        this.getMenuHallProductDetailUseCase.invoke(token, transactionRequestDTO).join();
    }

    @Override
    public void rollback(String token, TransactionRequestDTO transactionRequestDTO) {
    }
}
