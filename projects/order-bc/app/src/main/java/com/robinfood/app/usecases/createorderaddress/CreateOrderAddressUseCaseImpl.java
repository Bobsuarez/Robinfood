package com.robinfood.app.usecases.createorderaddress;

import com.robinfood.app.mappers.OrderAddressMappersKt;
import com.robinfood.core.dtos.OrderAddressDTO;
import com.robinfood.core.entities.OrderAddressEntity;
import com.robinfood.repository.orderaddress.IOrderAddressRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class CreateOrderAddressUseCaseImpl implements ICreateOrderAddressUseCase {

    private final IOrderAddressRepository orderAddressRepository;

    public CreateOrderAddressUseCaseImpl(IOrderAddressRepository orderAddressRepository) {
        this.orderAddressRepository = orderAddressRepository;
    }

    @Override
    @Transactional
    public CompletableFuture<Boolean> invoke(OrderAddressDTO orderAddressDTO) {

        log.info("Initializer create order address service with data: {}", orderAddressDTO);
        OrderAddressEntity orderAddressEntity = OrderAddressMappersKt.toOrderAddressEntity(orderAddressDTO);

        try {
            orderAddressRepository.save(orderAddressEntity);
            return CompletableFuture.completedFuture(true);
        } catch (RuntimeException exception) {
            log.error("Error in createOrderAddressUseCase: {} {}", exception.getMessage(), exception);
            return CompletableFuture.failedFuture(exception);
        }
    }
}
