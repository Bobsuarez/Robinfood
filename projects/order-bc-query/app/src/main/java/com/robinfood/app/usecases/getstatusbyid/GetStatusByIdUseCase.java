package com.robinfood.app.usecases.getstatusbyid;

import com.robinfood.core.dtos.OrderStatusDTO;
import com.robinfood.core.entities.StatusEntity;
import com.robinfood.repository.status.IStatusRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.webjars.NotFoundException;

/**
 * Implementation of IGetStatusByIdUseCase
 */
@Component
@Slf4j
public class GetStatusByIdUseCase implements IGetStatusByIdUseCase {

    private final IStatusRepository statusRepository;

    public GetStatusByIdUseCase(IStatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    @Override
    public OrderStatusDTO invoke(Long statusId) {

        log.info("Getting status with id: {}", statusId);

        StatusEntity statusEntity = statusRepository.findById(statusId)
            .orElseThrow(() -> new NotFoundException("Status not found id:" + statusId));

        return new OrderStatusDTO(
            statusEntity.getId(),
            statusEntity.getName()
        );
    }
}
