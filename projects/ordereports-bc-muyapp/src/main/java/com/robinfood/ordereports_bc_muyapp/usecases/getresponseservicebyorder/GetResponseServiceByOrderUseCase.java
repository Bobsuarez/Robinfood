package com.robinfood.ordereports_bc_muyapp.usecases.getresponseservicebyorder;

import com.robinfood.ordereports_bc_muyapp.dto.orderdetail.ResponseServiceDTO;
import com.robinfood.ordereports_bc_muyapp.models.entities.OrderServicesEntity;
import com.robinfood.ordereports_bc_muyapp.models.mapper.OrderDetailOrderMapper;
import com.robinfood.ordereports_bc_muyapp.repository.service.IServiceRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Implementation of IGetResponseServiceByOrderUseCase
 */
@AllArgsConstructor
@Component
@Slf4j
public class GetResponseServiceByOrderUseCase implements IGetResponseServiceByOrderUseCase {

    private final IServiceRepository serviceRepository;
    private final OrderDetailOrderMapper orderDetailOrderMapper;

    @Override
    public Optional<ResponseServiceDTO> invoke(OrderServicesEntity orderService) {

        return serviceRepository.findById(orderService.getServiceId()
                                                  .longValue())
                .map(service ->
                             orderDetailOrderMapper.mapOrderServiceToResponseDTO(orderService, service)
                );
    }
}
