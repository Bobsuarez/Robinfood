package com.robinfood.repository.getordersbytransaction;

import com.robinfood.core.dtos.getordersbytransaction.OrdersByTransactionResponseDTO;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;

@Slf4j
public class GetOrdersByTransactionRepository implements IGetOrdersByTransactionRepository {

    private final ModelMapper modelMapper;
    private final IGetOrdersByTransactionRemoteDataSource getOrdersByTransactionRemoteDataSource;

    public GetOrdersByTransactionRepository(ModelMapper modelMapper,
            IGetOrdersByTransactionRemoteDataSource getOrdersByTransactionRemoteDataSource) {
        this.modelMapper = modelMapper;
        this.getOrdersByTransactionRemoteDataSource = getOrdersByTransactionRemoteDataSource;
    }

    /**
     * Returns the orders associated with a transaction
     *
     * @param transactionId transaction id associated with orders
     * @param token         the authorization token
     *
     * @return a future that contains the result of orders associated with a transaction
     */
    @Override
    public List<OrdersByTransactionResponseDTO> invoke(Long transactionId, String token) {
        log.info("Going out to get orders by transaction id {}", transactionId);
        return getOrdersByTransactionRemoteDataSource.invoke(transactionId, token).stream()
                .map(response -> modelMapper.map(response, OrdersByTransactionResponseDTO.class))
                .collect(Collectors.toList());
    }
}
