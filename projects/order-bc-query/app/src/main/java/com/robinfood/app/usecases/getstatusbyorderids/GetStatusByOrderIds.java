package com.robinfood.app.usecases.getstatusbyorderids;

import com.robinfood.app.mappers.StatusMappers;
import com.robinfood.core.dtos.OrderStatusDTO;
import com.robinfood.repository.status.IStatusRepository;
import kotlin.collections.CollectionsKt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Implementation of IGetStatusByOrderIds
 */
@Component
@Slf4j
public class GetStatusByOrderIds implements IGetStatusByOrderIds {

    private final IStatusRepository statusDataSource;

    public GetStatusByOrderIds(IStatusRepository statusDataSource) {
        this.statusDataSource = statusDataSource;
    }

    @Override
    public List<OrderStatusDTO> invoke(List<Long> statusIds) {
        log.info("Starting process to get status by [{}] ids", statusIds);

        return CollectionsKt.map(
                statusDataSource.findAllByIdIn(statusIds),
                StatusMappers::toStatusDTO
        );
    }
}
