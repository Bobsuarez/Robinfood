package com.robinfood.app.usecases.gettemporarytransaction;

import static com.robinfood.core.enums.TransactionCreationErrors.TEMPORARY_TRANSACTION_NOT_FOUND;
import static com.robinfood.core.enums.TransactionCreationErrors.TRANSACTION_ORDERS_UUID_EXITS;

import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import com.robinfood.core.exceptions.TransactionCreationException;
import com.robinfood.core.mappers.TransactionCreatedMapper;
import com.robinfood.repositories.dynamodb.transactioncreated.ITransactionCreatedRepository;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class GetTemporaryTransactionUseCase implements IGetTemporaryTransactionUseCase {

    private final ITransactionCreatedRepository transactionCreatedRepository;

    public GetTemporaryTransactionUseCase(ITransactionCreatedRepository transactionCreatedRepository) {
        this.transactionCreatedRepository = transactionCreatedRepository;
    }

    @Override
    @SneakyThrows
    public TransactionRequestDTO invoke(String transactionUuid) {

        return transactionCreatedRepository.findById(transactionUuid)
                .map(TransactionCreatedMapper::buildTransactionCreatedDTO)
                .orElseThrow(() -> new TransactionCreationException(HttpStatus.NOT_FOUND,
                    "Temporary transaction not found " + transactionUuid, TEMPORARY_TRANSACTION_NOT_FOUND));
    }
}
