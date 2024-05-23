package com.robinfood.app.usecases.roundvaluestransaction;

import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.RoundingMode;

import static com.robinfood.core.constants.GlobalConstants.NUMBER_DECIMAL_PLACES;

@Service
@Slf4j
public class RoundValuesTransactionUseCase implements IRoundValuesTransactionUseCase {

    public RoundValuesTransactionUseCase() {
        //Constructor empty
    }

    public TransactionRequestDTO invoke(TransactionRequestDTO transactionRequest) {

        transactionRequest.getOrders().forEach(orderDTO ->
                orderDTO.setTotal(
                        orderDTO.getTotal().setScale(NUMBER_DECIMAL_PLACES, RoundingMode.UNNECESSARY)
                )
        );

        transactionRequest.setTotal(
                transactionRequest.getTotal().setScale(NUMBER_DECIMAL_PLACES, RoundingMode.UNNECESSARY)
        );

        transactionRequest.setTotalPaidPayments(
                transactionRequest.getTotalPaidPayments().setScale(NUMBER_DECIMAL_PLACES, RoundingMode.UNNECESSARY)
        );

        return transactionRequest;
    }
}
