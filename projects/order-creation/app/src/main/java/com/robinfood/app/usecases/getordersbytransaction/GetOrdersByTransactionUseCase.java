package com.robinfood.app.usecases.getordersbytransaction;

import com.robinfood.core.dtos.getordersbytransaction.OrdersByTransactionResponseDTO;
import com.robinfood.repository.getordersbytransaction.IGetOrdersByTransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class GetOrdersByTransactionUseCase implements IGetOrdersByTransactionUseCase {

    private final IGetOrdersByTransactionRepository ordersByTransactionRepository;

    public GetOrdersByTransactionUseCase(IGetOrdersByTransactionRepository ordersByTransactionRepository) {
        this.ordersByTransactionRepository = ordersByTransactionRepository;
    }

    @Override
    public List<OrdersByTransactionResponseDTO> invoke(String token, Long transactionId) {

        return ordersByTransactionRepository.invoke(transactionId, token);
    }
}
