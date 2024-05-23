package com.robinfood.app.usecases.savepickuptime;

import com.robinfood.app.usecases.getpickuptime.IGetPickupTimeUseCase;
import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import com.robinfood.core.dtos.transactionresponsedto.TransactionCreationResponseDTO;
import com.robinfood.core.enums.TransactionCreationResult;
import com.robinfood.core.models.domain.pickuptime.PickupTime;
import com.robinfood.repository.transaction.ITransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import static com.robinfood.core.util.ObjectMapperSingleton.objectToJson;

@Service
@Slf4j
public class SavePickupTimeUseCase implements ISavePickupTimeUseCase {

    private final ITransactionRepository transactionRepository;
    private final IGetPickupTimeUseCase pickupTimeUseCase;

    public SavePickupTimeUseCase(
            ITransactionRepository transactionRepository,
            IGetPickupTimeUseCase pickupTimeUseCase
    ) {
        this.transactionRepository = transactionRepository;
        this.pickupTimeUseCase = pickupTimeUseCase;
    }

    @Override
    public CompletableFuture<TransactionCreationResult> invoke(
            @NotNull String token,
            @NotNull TransactionRequestDTO transactionRequest
    ) {
        log.info("Process start to save pickup-time with data: {}", objectToJson(transactionRequest));

        if (Boolean.TRUE.equals(transactionRequest.getPickupTimeConsumption())) {

            final PickupTime pickupTime = pickupTimeUseCase.invoke(token, transactionRequest);
            final TransactionCreationResponseDTO transactionCreationResponseDTO = transactionRepository
                    .getTransactionResponseDTO().getTransactionCreationResponse();

            transactionRepository.savePickupTime(
                    token,
                    pickupTime,
                    transactionCreationResponseDTO.getTransaction().getId()
            );

            return CompletableFuture.completedFuture(
                    new TransactionCreationResult.TransactionCreated(transactionCreationResponseDTO)
            );
        }

        return CompletableFuture.completedFuture(
                TransactionCreationResult.StepValidationSuccess.INSTANCE
        );
    }
}
