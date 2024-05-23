package com.robinfood.app.usecases.getresponseservicebyorder;

import com.robinfood.app.mappers.OrderDetailOrderMapper;
import com.robinfood.core.dtos.response.orderhistory.ResponseServiceDTO;
import com.robinfood.core.entities.OrderServicesEntity;
import com.robinfood.repository.service.IServiceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Implementation of IGetResponseServiceByOrderUseCase
 */
@Component
@Slf4j
public class GetResponseServiceByOrderUseCase implements IGetResponseServiceByOrderUseCase {

    private final IServiceRepository serviceRepository;

    public GetResponseServiceByOrderUseCase(IServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    @Override
    public Optional<ResponseServiceDTO> invoke(OrderServicesEntity orderService) {
        log.info("Start of the process that obtains an payment method with {}", orderService.getServiceId());

        return serviceRepository.findById(orderService.getServiceId())
                .map(service ->
                        OrderDetailOrderMapper.mapOrderServiceToResponseDTO(orderService, service)
                );
    }
}
