package com.robinfood.app.usecases.getstatusbylistid;

import com.robinfood.app.mappers.StatusMappers;
import com.robinfood.core.dtos.OrderStatusDTO;
import com.robinfood.core.entities.StatusEntity;
import com.robinfood.repository.status.IStatusRepository;
import kotlin.collections.CollectionsKt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.webjars.NotFoundException;

import java.util.List;

/**
 * Implementation of IGetStatusByIdUseCase
 */
@Component
@Slf4j
public class GetStatusByListIdUseCase implements IGetStatusByListIdUseCase {

    private final IStatusRepository statusRepository;

    public GetStatusByListIdUseCase(IStatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    @Override
    public List<OrderStatusDTO> invoke(List<Long> statusIds) {
        log.info("Starting process to get status by ids: {}", statusIds);

        List<StatusEntity> statusEntity = statusRepository.findAllByIdIn(statusIds);

        if (statusEntity.isEmpty()) {
            log.error("Status not found for ids [{}]", statusIds);
            throw new NotFoundException("Status not found for ids: " + statusIds);
        }

        return CollectionsKt.map(
            statusEntity,
            StatusMappers::toStatusDTO
        );
    }
}
