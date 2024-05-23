package com.robinfood.ordereports_bc_muyapp.usecases.getplatformbyorderid;

import com.robinfood.ordereports_bc_muyapp.models.entities.OrderDeviceEntity;
import com.robinfood.ordereports_bc_muyapp.repository.orderdevice.IOrderDeviceRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import static com.robinfood.ordereports_bc_muyapp.constants.GlobalConstants.PLATFORM_ID_DEFAULT;

@AllArgsConstructor
@Component
@Slf4j
public class GetPlatformByOrderIdUseCase implements IGetPlatformByOrderIdUseCase {

    private final IOrderDeviceRepository orderDeviceRepository;

    @Async
    @Override
    public CompletableFuture<Short> invoke(Integer orderId) {

        OrderDeviceEntity orderDeviceEntity =
                Optional.of(orderDeviceRepository.findFirstByOrderIdOrderByIdDesc(orderId))
                        .orElse(OrderDeviceEntity.builder()
                                        .build());

        if (Objects.nonNull(orderDeviceEntity.getPlatformId())) {
            return CompletableFuture.completedFuture(orderDeviceEntity.getPlatformId());
        }

        return CompletableFuture.completedFuture(PLATFORM_ID_DEFAULT);
    }
}
