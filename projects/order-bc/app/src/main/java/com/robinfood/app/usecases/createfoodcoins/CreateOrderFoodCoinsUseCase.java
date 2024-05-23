package com.robinfood.app.usecases.createfoodcoins;

import com.robinfood.app.mappers.OrderFoodCoinMapper;
import com.robinfood.core.dtos.request.transaction.RequestOrderTransactionDTO;
import com.robinfood.repository.orderfoodcoins.IOrderFoodCoinRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

@Component
@Slf4j
public class CreateOrderFoodCoinsUseCase implements ICreateOrderFoodCoinsUseCase{

    private final IOrderFoodCoinRepository orderFoodCoinRepository;

    public CreateOrderFoodCoinsUseCase(IOrderFoodCoinRepository orderFoodCoinRepository) {
        this.orderFoodCoinRepository = orderFoodCoinRepository;
    }

    @Override
    public CompletableFuture<Boolean> invoke(
            RequestOrderTransactionDTO requestOrderTransactionDTO,
             List<Long> orderIds) {

        log.info(
                "Start of the process that save foodcoins of a transaction with ordersids {}",
                orderIds
        );

        for(int i = 0; i < requestOrderTransactionDTO.getOrders().size(); i++) {

            if (Objects.nonNull(requestOrderTransactionDTO.getOrders().get(i).getFoodcoins())) {

                log.info("Save food coin with transactions: {}", requestOrderTransactionDTO);

                orderFoodCoinRepository.save(OrderFoodCoinMapper
                        .orderFoodCoinToOrderFoodCoinEntity(
                                requestOrderTransactionDTO.getOrders().get(i),orderIds.get(i)));
            }
        }

        return CompletableFuture.completedFuture(Boolean.TRUE);
    }
}
