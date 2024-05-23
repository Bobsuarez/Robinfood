package com.robinfood.repository.createtransaction;

import com.robinfood.dtos.request.ordertocreatedto.OrderToCreateDTO;
import com.robinfood.network.http.api.OrderCreationAPI;

import java.util.Map;

public class CreateTransactionRepository implements ICreateTransactionRepository {

    private final OrderCreationAPI orderCreationAPI;

    public CreateTransactionRepository(OrderCreationAPI orderCreationAPI) {
        this.orderCreationAPI = orderCreationAPI;
    }

    public CreateTransactionRepository() {
        this.orderCreationAPI = new OrderCreationAPI();
    }

    @Override
    public OrderToCreateDTO createTransaction(
            Map<String, String> messageAttribute,
            OrderToCreateDTO orderToCreateDTO,
            String token
    ) {

        return this.orderCreationAPI.processOrders(messageAttribute, orderToCreateDTO, token);
    }
}
