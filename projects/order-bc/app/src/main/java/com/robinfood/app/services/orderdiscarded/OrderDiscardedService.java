package com.robinfood.app.services.orderdiscarded;

import com.robinfood.app.usecases.updateordertemplate.IUpdateOrderUuidTemplateUseCase;
import com.robinfood.core.dtos.OrderDiscardedInfoDTO;
import com.robinfood.core.entities.OrderEntity;
import com.robinfood.core.exceptions.GenericOrderBcException;
import com.robinfood.repository.orders.IOrdersRepository;
import java.util.Optional;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderDiscardedService implements IOrderDiscardedService {

    private final IUpdateOrderUuidTemplateUseCase updateOrderUuidTemplateUseCase;

    private final IOrdersRepository ordersRepository;

    public OrderDiscardedService(IUpdateOrderUuidTemplateUseCase updateOrderUuidTemplateUseCase,
            IOrdersRepository ordersRepository) {
        this.updateOrderUuidTemplateUseCase = updateOrderUuidTemplateUseCase;
        this.ordersRepository = ordersRepository;
    }

    @SneakyThrows
    public OrderDiscardedInfoDTO validateAndUpdate(String uuid) {

        log.info("Starting process to order discarded service per Uuid: {}", uuid);

        Optional<OrderEntity> order = ordersRepository.findByUuid(uuid);
        if (order.isEmpty()) {
            return OrderDiscardedInfoDTO.builder()
                    .discard(false)
                    .build();
        }

        boolean isPaid = order.get().getPaid();

        if (isPaid) {
            throw new GenericOrderBcException("Order with uuid" + uuid + " is already taken");
        }

        final String updatedUuid = updateOrderUuidTemplateUseCase.invoke(uuid);

        return OrderDiscardedInfoDTO.builder()
                .discard(true)
                .uuid(updatedUuid)
                .build();
    }
}
