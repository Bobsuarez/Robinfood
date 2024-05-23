package com.robinfood.app.usecases.getinformationbystore;

import com.robinfood.core.constants.GlobalConstants;
import com.robinfood.core.dtos.transactionrequestdto.OrderDTO;
import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import com.robinfood.core.enums.TransactionCreationResult;
import com.robinfood.core.models.domain.configuration.StoreInformation;
import com.robinfood.repository.configurationsbc.IConfigurationRepository;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;

import static com.robinfood.core.constants.GlobalConstants.DEFAULT_INTEGER_VALUE;
import static com.robinfood.core.util.ObjectMapperSingleton.objectToJson;

@Service
@Slf4j
public class GetInformationByStoreUseCase implements IGetInformationByStoreUseCase {

    private final IConfigurationRepository configurationRepository;

    public GetInformationByStoreUseCase(IConfigurationRepository configurationRepository) {
        this.configurationRepository = configurationRepository;
    }

    @Override
    public CompletableFuture<TransactionCreationResult> invoke(
            @NonNull String token,
            @NonNull TransactionRequestDTO transactionRequest
    ) {
        log.info(
                "Executing use case that obtains the info of a store with request: {}",
                transactionRequest
        );

        final StoreInformation store = configurationRepository.getStoreConfiguration(
                token,
                transactionRequest.getOrders().get(DEFAULT_INTEGER_VALUE).getStore().getId()
        );

        for (OrderDTO order : transactionRequest.getOrders()) {

            if (Objects.nonNull(store.getFlowTax())) {
                order.getTaxCriteria().put(GlobalConstants.CRITERIA_FLOW, store.getFlowTax().getValue());
            }
            order.getTaxCriteria().put(GlobalConstants.CRITERIA_LOCATION, store.getState().getId());

            transactionRequest.getDevice().setTimezone(store.getTimezone());
        }

        log.info("Store found: {}", objectToJson(store));

        transactionRequest.setStoreInfo(store);

        return CompletableFuture.completedFuture(TransactionCreationResult.StepValidationSuccess.INSTANCE);
    }
}
