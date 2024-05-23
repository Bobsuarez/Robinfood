package com.robinfood.usecases.createorder;

import com.robinfood.dtos.request.ordertocreatedto.OrderToCreateDTO;
import com.robinfood.repository.createtransaction.CreateTransactionRepository;
import com.robinfood.repository.createtransaction.ICreateTransactionRepository;

import java.util.Map;

public class CreateOrderUseCase implements ICreateOrderUseCase {

    private final ICreateTransactionRepository createTransactionRepository;

    public CreateOrderUseCase(ICreateTransactionRepository createTransactionRepository) {
        this.createTransactionRepository = createTransactionRepository;
    }

    public CreateOrderUseCase() {
        this.createTransactionRepository = new CreateTransactionRepository();
    }

    @Override
    public OrderToCreateDTO invoke(OrderToCreateDTO transaction, String token, Map<String, String> messageAttribute) {

        return createTransactionRepository.createTransaction(messageAttribute, transaction, token);
    }
}
