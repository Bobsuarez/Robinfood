package com.robinfood.app.usecases.validatemenu;

import com.robinfood.core.dtos.MenuValidationDTO;
import com.robinfood.core.dtos.transactionrequestdto.OrderDTO;
import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import com.robinfood.core.enums.TransactionCreationResult;
import com.robinfood.core.exceptions.TransactionCreationException;
import com.robinfood.repository.menu.IMenuRepository;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.stream.Collectors;

import static kotlin.collections.CollectionsKt.map;
import static com.robinfood.core.util.ObjectMapperSingleton.objectToJson;

@Service
@Slf4j
public class ValidateMenuUseCase implements IValidateMenuUseCase {

    private final IMenuRepository menuRepository;

    public ValidateMenuUseCase(IMenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    @Override
    public CompletableFuture<TransactionCreationResult> invoke(
            @NotNull String token,
            @NotNull TransactionRequestDTO transactionRequestDTO
    ) {
        log.info("Menu validation has started with request: {}", objectToJson(transactionRequestDTO));

        try {

            final Long countryId = transactionRequestDTO.getCompany().getId();
            final Long platformId = transactionRequestDTO.getDevice().getPlatform();

            map(
                    transactionRequestDTO.getOrders(),
                    (OrderDTO order) -> {

                        final MenuValidationDTO menuValidationDTO = MenuValidationDTO.builder()
                                .countryId(countryId)
                                .flowId(transactionRequestDTO.getFlowId())
                                .order(order)
                                .platformId(platformId)
                                .timezone(transactionRequestDTO.getStoreInfo().getTimezone())
                                .build();

                        return menuRepository.validateMenu(token, menuValidationDTO);
                    }
            )
                    .stream()
                    .map(CompletableFuture::join)
                    .collect(Collectors.toList());

            return CompletableFuture.completedFuture(
                    TransactionCreationResult.StepValidationSuccess.INSTANCE
            );

        } catch (CancellationException | CompletionException exception) {

            if (exception.getCause() instanceof TransactionCreationException) {
                throw exception;
            }

            throw new TransactionCreationException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    exception.getLocalizedMessage()
            );
        }
    }
}
