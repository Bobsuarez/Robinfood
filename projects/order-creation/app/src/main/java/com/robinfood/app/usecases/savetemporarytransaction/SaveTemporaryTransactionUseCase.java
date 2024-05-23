package com.robinfood.app.usecases.savetemporarytransaction;

import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import com.robinfood.core.entities.template.TransactionCreatedEntity;
import com.robinfood.core.util.ObjectMapperSingleton;
import com.robinfood.repositories.dynamodb.transactioncreated.ITransactionCreatedRepository;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;

import static com.robinfood.core.constants.GlobalConstants.EXPIRED_TIME_REGISTER;

@Service
@Slf4j
public class SaveTemporaryTransactionUseCase implements ISaveTemporaryTransactionUseCase {

    private final ITransactionCreatedRepository transactionCreatedRepository;

    public SaveTemporaryTransactionUseCase(
            ITransactionCreatedRepository transactionCreatedRepository
    ) {
        this.transactionCreatedRepository = transactionCreatedRepository;
    }

    @Override
    @SneakyThrows
    public void invoke(TransactionRequestDTO transactionRequestDTO) {

        final long now = Instant.now().getEpochSecond();

        TransactionCreatedEntity transactionCreatedEntity = TransactionCreatedEntity
                .builder()
                .transactionUuid(transactionRequestDTO.getUuid().toString())
                .requestTransaction(ObjectMapperSingleton.objectToJson(transactionRequestDTO))
                .ttl(EXPIRED_TIME_REGISTER + now)
                .build();

        log.info(
                "Save temporary json from order: {}, in DynamoDB",
                ObjectMapperSingleton.objectToJson(transactionCreatedEntity)
        );

        transactionCreatedRepository.save(transactionCreatedEntity);
    }
}
