package com.robinfood.app.usecases.saveorderservices;

import com.robinfood.app.mappers.OrderServiceMapper;
import com.robinfood.core.dtos.request.transaction.RequestOrderTransactionDTO;
import com.robinfood.repository.orderservices.IOrderServiceRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import com.robinfood.core.dtos.request.order.OrderDTO;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

@AllArgsConstructor
@Component
@Slf4j
public class SaveOrderServiceUseCase implements ISaveOrderServiceUseCase {

    private final IOrderServiceRepository orderServiceRepository;

    @Override
    public CompletableFuture<Boolean> invoke(
            RequestOrderTransactionDTO requestOrderTransactionDTO,
            List<Long> orderIds) {

        log.info("Start of the process that save service  of a transaction with ordersids {}", orderIds);

        for (int indexOfAnOrder = 0; indexOfAnOrder < requestOrderTransactionDTO.getOrders().size(); indexOfAnOrder++) {

            OrderDTO orderDTO = requestOrderTransactionDTO.getOrders().get(indexOfAnOrder);

            if (Objects.nonNull(orderDTO.getServices()) && !orderDTO.getServices().isEmpty()) {

                log.info("Save service with orders: {}", orderDTO);

                for (int indexOfAnService = 0; indexOfAnService < orderDTO.getServices().size(); indexOfAnService++) {

                    orderServiceRepository.save(
                            OrderServiceMapper.orderServiceDTOToEntity(orderDTO.getServices().get(indexOfAnService),
                                    orderIds.get(indexOfAnOrder))
                    );
                }
            }
        }
        return CompletableFuture.completedFuture(Boolean.TRUE);
    }
}
