package com.robinfood.app.usecases.validateservice;

import com.robinfood.core.dtos.transactionrequestdto.OrderDTO;
import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import com.robinfood.core.enums.TransactionCreationResult;
import com.robinfood.repository.services.IServicesRepository;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import static com.robinfood.core.util.ObjectMapperSingleton.objectToJson;

@Service
@Slf4j
public class ValidateServiceUseCase implements IValidateServiceUseCase {

    private final IServicesRepository servicesRepository;

    public ValidateServiceUseCase(IServicesRepository servicesRepository) {
        this.servicesRepository = servicesRepository;
    }

    @Override
    public CompletableFuture<TransactionCreationResult> invoke(
            @NonNull String token,
            @NonNull TransactionRequestDTO transactionRequest
    ) {

        for (OrderDTO order : transactionRequest.getOrders()) {

            if (!order.getServices().isEmpty()) {

                log.info("Service validation has started with request: {}", objectToJson(transactionRequest));

                servicesRepository.validateService(token, order.getServices(), transactionRequest);
            }
        }

        return CompletableFuture.completedFuture(
                TransactionCreationResult.StepValidationSuccess.INSTANCE
        );
    }
}
