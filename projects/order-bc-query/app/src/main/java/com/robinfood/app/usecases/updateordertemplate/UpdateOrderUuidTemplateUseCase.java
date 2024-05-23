package com.robinfood.app.usecases.updateordertemplate;

import static com.robinfood.core.constants.GlobalConstants.UUID_DEFAULT_TEMPLATE_NAME;

import com.robinfood.core.entities.OrderEntity;
import com.robinfood.core.exceptions.ResourceNotFoundException;
import com.robinfood.repository.orders.IOrdersRepository;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UpdateOrderUuidTemplateUseCase implements IUpdateOrderUuidTemplateUseCase {

    private final IOrdersRepository ordersRepository;

    public UpdateOrderUuidTemplateUseCase(IOrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

    @Override
    public String invoke(String uuid) throws ResourceNotFoundException {

        log.info("Starting process to update an order per Uuid, Uuid: {}", uuid);
        final Optional<OrderEntity> findOrderEntity = ordersRepository.findByUuid(uuid);

        return findOrderEntity.map(this::saveChanges).orElseThrow(() ->
                new ResourceNotFoundException("Order not found with the Uuid: " + uuid)
        );
    }


    private String saveChanges(OrderEntity orderEntity) {

        final String uuidTemplate = orderEntity.getUuid().concat(UUID_DEFAULT_TEMPLATE_NAME);
        orderEntity.setUuid(uuidTemplate);
        ordersRepository.saveAndFlush(orderEntity);

        return uuidTemplate;
    }
}
