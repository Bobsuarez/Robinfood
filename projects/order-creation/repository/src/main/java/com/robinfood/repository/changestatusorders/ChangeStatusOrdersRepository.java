package com.robinfood.repository.changestatusorders;

import com.robinfood.core.dtos.changestatusordersrequestdto.ChangeStatusOrdersRequestDTO;
import com.robinfood.core.entities.changestatusordersrequestentities.ChangeStatusOrdersRequestEntity;
import java.util.concurrent.CompletableFuture;
import org.modelmapper.ModelMapper;

/**
 * Implementation of IChangeStatusOrdersRepository
 */

public class ChangeStatusOrdersRepository implements IChangeStatusOrdersRepository {

    private final ModelMapper modelMapper;
    private final IChangeStatusOrdersRemoteDataSource changeStatusOrdersRemoteDataSource;

    public ChangeStatusOrdersRepository(ModelMapper modelMapper,
            IChangeStatusOrdersRemoteDataSource changeStatusOrdersRemoteDataSource) {
        this.modelMapper = modelMapper;
        this.changeStatusOrdersRemoteDataSource = changeStatusOrdersRemoteDataSource;
    }

    @Override
    public CompletableFuture<Boolean> invoke(
            ChangeStatusOrdersRequestDTO changeStatusOrdersRequestDTO, String token) {
        ChangeStatusOrdersRequestEntity changeStatusOrdersRequestEntity = modelMapper.map(
                changeStatusOrdersRequestDTO, ChangeStatusOrdersRequestEntity.class);
        return changeStatusOrdersRemoteDataSource.invoke(changeStatusOrdersRequestEntity, token);
    }
}
