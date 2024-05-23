package com.robinfood.app.usecases.getposinformation;

import com.robinfood.app.usecases.inputrequestvalidation.services.ValidateGiftCardsService;
import com.robinfood.core.dtos.transactionrequestdto.OrderDTO;
import com.robinfood.core.dtos.transactionrequestdto.StoreDTO;
import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import com.robinfood.core.enums.TransactionCreationResult;
import com.robinfood.core.models.domain.configuration.Store;
import com.robinfood.core.models.domain.configuration.StoreInformation;
import com.robinfood.repository.configurationsbc.IConfigurationRepository;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

import static com.robinfood.core.constants.GlobalConstants.POS_TYPE_OFFLINE;
import static com.robinfood.core.constants.GlobalConstants.POS_TYPE_ONLINE;
import static com.robinfood.core.util.ObjectMapperSingleton.objectToJson;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Service
@Slf4j
public class GetPosInformationUseCase implements IGetPosInformationUseCase {

    private final IConfigurationRepository configurationRepository;
    private final ValidateGiftCardsService validateGiftCardsService;

    public GetPosInformationUseCase(
            IConfigurationRepository configurationRepository,
            ValidateGiftCardsService validateGiftCardsService
    ) {
        this.configurationRepository = configurationRepository;
        this.validateGiftCardsService = validateGiftCardsService;
    }

    @Override
    public CompletableFuture<TransactionCreationResult> invoke(
            @NonNull String token,
            @NonNull TransactionRequestDTO transactionRequest
    ) {
        log.info("Starting process to get the posId with data: {}", objectToJson(transactionRequest));

        validateGiftCardsService.paymentMethodAndProductsWithCategory(transactionRequest);

        transactionRequest.getOrders().forEach((OrderDTO orderDTO) -> {

            Store store = buildStore(orderDTO);

            if (isNull(store.getPosId())) {

                store = configurationRepository.getPosIdByStoreIdAndPaymentMethodIds(token, store);
                log.info("PosId obtained from configurations-bc: {}", objectToJson(store));

                setPosIdInOrder(orderDTO, store);
            }

            builderCodeAndPostalCode(orderDTO, transactionRequest.getStoreInfo());
        });

        return CompletableFuture.completedFuture(
                TransactionCreationResult.StepValidationSuccess.INSTANCE
        );
    }

    private void setPosIdInOrder(
            OrderDTO orderDTO,
            Store store
    ) {
        StoreDTO storeDTO = orderDTO.getStore();
        storeDTO.setPosId(store.getPosId());
    }

    private void builderCodeAndPostalCode(
            OrderDTO orderDTO,
            StoreInformation storeInformation
    ) {
        StoreDTO storeDTO = orderDTO.getStore();
        storeDTO.setCode(storeInformation.getCity().getCode());
        storeDTO.setPostalCode(storeInformation.getPostalCode());
    }

    private Store buildStore(OrderDTO orderDTO) {

        Long posType = POS_TYPE_ONLINE;

        if (nonNull(orderDTO.getInvoiceNumber())) {
            posType = POS_TYPE_OFFLINE;
        }

        log.info("Assigned PostType: {}", objectToJson(posType));

        return Store.builder()
                .id(orderDTO.getStore().getId())
                .posId(orderDTO.getStore().getPosId())
                .posTypeId(posType)
                .build();
    }
}
