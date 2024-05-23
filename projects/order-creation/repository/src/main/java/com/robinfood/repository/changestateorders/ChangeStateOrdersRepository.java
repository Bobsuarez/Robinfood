package com.robinfood.repository.changestateorders;

import com.robinfood.core.dtos.staterequestdto.StateChangeRequestDTO;
import com.robinfood.core.entities.changestateorderequestentities.ChangeStateOrderRequestEntity;
import com.robinfood.core.entities.changestateorderequestentities.ChangeStateOrderRespondEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

import java.util.concurrent.CompletableFuture;

@Repository
public class ChangeStateOrdersRepository implements IChangeStateOrdersRepository{

    private final ModelMapper modelMapper;

    private final IChangeStateOrderRemoteDataSource stateOrderRemoteDataSource;

    public ChangeStateOrdersRepository(ModelMapper modelMapper,
                                       IChangeStateOrderRemoteDataSource stateOrderRemoteDataSource) {
        this.modelMapper = modelMapper;
        this.stateOrderRemoteDataSource = stateOrderRemoteDataSource;
    }

    @Override
    public CompletableFuture<ChangeStateOrderRespondEntity> invoke(
            StateChangeRequestDTO stateChangeRequestDTO, String token) {

        ChangeStateOrderRequestEntity changeStateOrderRequestEntity = modelMapper.map(stateChangeRequestDTO,
                ChangeStateOrderRequestEntity.class );

        return stateOrderRemoteDataSource.invoke(changeStateOrderRequestEntity,token);
    }
}
