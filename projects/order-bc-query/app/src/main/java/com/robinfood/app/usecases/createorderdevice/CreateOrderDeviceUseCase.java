package com.robinfood.app.usecases.createorderdevice;

import com.robinfood.core.dtos.request.transaction.RequestDeviceDTO;
import com.robinfood.core.entities.OrderDeviceEntity;
import com.robinfood.repository.orderdevice.IOrderDeviceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Implementation of ICreateOrderDevice
 */
@Component
@Slf4j
public class CreateOrderDeviceUseCase implements ICreateOrderDeviceUseCase {

    private final IOrderDeviceRepository orderDeviceRepository;

    public CreateOrderDeviceUseCase(IOrderDeviceRepository orderDeviceRepository) {
        this.orderDeviceRepository = orderDeviceRepository;
    }

    @Override
    public CompletableFuture<Void> invoke(RequestDeviceDTO requestDeviceDTO, List<Long> orderIds) {

        log.info("Starting process to save order device with data: {}, and order ids: [{}]",
            requestDeviceDTO, orderIds);

        List<OrderDeviceEntity> orderDeviceEntities = new ArrayList<>();

        for (Long orderId : orderIds) {
            orderDeviceEntities.add(
                new OrderDeviceEntity(
                    null,
                    null,
                    requestDeviceDTO.getIp(),
                    orderId,
                    requestDeviceDTO.getPlatform(),
                    requestDeviceDTO.getVersion()
                )
            );
        }

        orderDeviceRepository.saveAll(orderDeviceEntities);

        return CompletableFuture.completedFuture(null);
    }
}
