package com.robinfood.app.usecases.getmenuhallproductdetail;

import com.robinfood.core.dtos.menuhallproductdetail.MenuHallProductDetailDTO;
import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import com.robinfood.core.enums.TransactionCreationResult;
import com.robinfood.core.exceptions.TransactionCreationException;
import com.robinfood.repository.menu.IMenuRepository;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.robinfood.core.mappers.MenuHallProductDetailMapper.setMenuProductDetailInfo;

@Service
@Slf4j
public class GetMenuHallProductDetailUseCase implements IGetMenuHallProductDetailUseCase {

    private final IMenuRepository menuRepository;

    public GetMenuHallProductDetailUseCase(IMenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    @Override
    public CompletableFuture<TransactionCreationResult> invoke(
            @NotNull String token, @NotNull TransactionRequestDTO transactionRequestDTO
    ) {

        log.info("Starting to get Menu hall product detail has started with request: {}", transactionRequestDTO);

        Set<Long> menuHallIds = transactionRequestDTO.getOrders().stream()
                .map(orderDTO -> orderDTO.getFinalProducts().stream()
                        .map(finalProductDTO -> finalProductDTO.getArticle().getMenuHallProductId())
                        .collect(Collectors.toList()))
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());

        try {

            Map<Long, MenuHallProductDetailDTO> menuHallProductsDetail = menuHallIds.stream()
                    .map(id -> CompletableFuture.supplyAsync(() -> menuRepository.getMenuHallProductDetail(token, id)))
                    .map(CompletableFuture::join)
                    .collect(Collectors.toMap(MenuHallProductDetailDTO::getId, Function.identity()));

            setMenuProductDetailInfo(transactionRequestDTO, menuHallProductsDetail);

            return CompletableFuture.completedFuture(
                    TransactionCreationResult.StepValidationSuccess.INSTANCE
            );

        } catch (CancellationException | CompletionException exception) {
            
            log.error(exception.getMessage(), exception);

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
