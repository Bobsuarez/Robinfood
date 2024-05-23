package com.robinfood.repository.exitstransactionuuidorderuid;

import com.robinfood.core.dtos.ExistsTransactionUuidOrderUuidDTO;
import com.robinfood.core.entities.ExistsTransactionUuidOrderUuidEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ExitsTransactionUuidOrderUuidRepository implements IExitsTransactionUuidOrderUuidRepository {

    private final ModelMapper modelMapper;
    private final IExitsTransactionUuidOrderUuidDataSource exitsTransactionUuidOrderUuidDataSource;

    public ExitsTransactionUuidOrderUuidRepository(
            ModelMapper modelMapper,
            IExitsTransactionUuidOrderUuidDataSource exitsTransactionUuidOrderUuidDataSource
    ) {
        this.modelMapper = modelMapper;
        this.exitsTransactionUuidOrderUuidDataSource = exitsTransactionUuidOrderUuidDataSource;
    }

    @Override
    public ExistsTransactionUuidOrderUuidDTO invoke(String token, String transactionUuid, List<String> orderUuids) {

        ExistsTransactionUuidOrderUuidEntity transactionUuidOrderUuidEntity =
                exitsTransactionUuidOrderUuidDataSource.invoke(token, transactionUuid, orderUuids);

        return modelMapper.map(transactionUuidOrderUuidEntity, ExistsTransactionUuidOrderUuidDTO.class);
    }
}
