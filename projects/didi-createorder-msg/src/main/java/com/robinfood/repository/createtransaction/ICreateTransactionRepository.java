package com.robinfood.repository.createtransaction;

import com.robinfood.dtos.request.ordertocreatedto.OrderToCreateDTO;

import java.util.Map;

public interface ICreateTransactionRepository {

    OrderToCreateDTO createTransaction(
            Map<String, String> messageAttribute,
            OrderToCreateDTO orderToCreateDTO,
            String token
    );
}
