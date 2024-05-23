package com.robinfood.ordereports_bc_muyapp.usecases.getstatusbyid;

import com.robinfood.ordereports_bc_muyapp.dto.OrderStatusDTO;
import com.robinfood.ordereports_bc_muyapp.repository.status.IStatusRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CompletableFuture;


/**
 * Implementation of IGetStatusByIdUseCase
 */
@AllArgsConstructor
@Component
@Slf4j
public class GetStatusByIdUseCase implements IGetStatusByIdUseCase {

    private final IStatusRepository statusRepository;

    @Async
    @Override
    public CompletableFuture<List<OrderStatusDTO>> invoke(List<Short> statusIds) {

        return CompletableFuture.supplyAsync(() -> getDataOrderStatusDTOList(statusIds));
    }

    private List<OrderStatusDTO> getDataOrderStatusDTOList(List<Short> statusIds) {
        return statusRepository.findByIdIn(statusIds)
                .stream()
                .map(data -> OrderStatusDTO.builder()
                        .id(data.getId())
                        .name(data.getName())
                        .build())
                .toList();
    }
}
