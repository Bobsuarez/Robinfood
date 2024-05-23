package com.robinfood.app.usecases.convertidstransactiontonull;

import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import org.springframework.stereotype.Service;

@Service
public class ConvertIdsTransactionToNullUseCase implements IConvertIdsTransactionToNullUseCase {

    @Override
    public void invoke(TransactionRequestDTO transactionRequestDTO) {
        transactionRequestDTO.getOrders().forEach(orderDTO -> orderDTO.setId(null));
    }
}
