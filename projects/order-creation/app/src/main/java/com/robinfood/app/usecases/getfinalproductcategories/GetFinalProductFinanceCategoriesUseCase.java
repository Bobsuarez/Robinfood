package com.robinfood.app.usecases.getfinalproductcategories;

import com.robinfood.core.dtos.productfinancecategorydto.ProductFinanceCategoryResponseDTO;
import com.robinfood.core.dtos.transactionrequestdto.FinalProductDTO;
import com.robinfood.core.dtos.transactionrequestdto.OrderDTO;
import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import com.robinfood.core.enums.TransactionCreationErrors;
import com.robinfood.core.enums.TransactionCreationResult;
import com.robinfood.core.exceptions.TransactionCreationException;
import com.robinfood.core.mappers.CategoryMappers;
import com.robinfood.repository.productfinancecategory.IProductFinanceCategoryRepository;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
@Slf4j
public class GetFinalProductFinanceCategoriesUseCase implements IGetFinalProductFinanceCategoriesUseCase {

    private final IProductFinanceCategoryRepository productFinanceCategoryRepository;

    public GetFinalProductFinanceCategoriesUseCase(
            IProductFinanceCategoryRepository productFinanceCategoryRepository
    ) {
        this.productFinanceCategoryRepository = productFinanceCategoryRepository;
    }

    @Override
    public CompletableFuture<TransactionCreationResult> invoke(
            @NonNull String token,
            @NonNull TransactionRequestDTO transactionRequest
    ) {
        log.info("Starting fot get final product finance categories has started with request: {}", transactionRequest);

        for (final OrderDTO orderDTO : transactionRequest.getOrders()) {

            final List<Long> finalProductsIds = orderDTO.getFinalProducts().stream().map(
                    FinalProductDTO::getId
            ).collect(Collectors.toList());

            // Stopping thread here since it's easier to retrieve the product categories
            // by order and then edit its final products than getting all final products from all
            // orders and then finding which final product belongs to which order to set its categories
            final List<ProductFinanceCategoryResponseDTO> productFinanceCategories = productFinanceCategoryRepository
                    .getProductsFinanceCategories(token, finalProductsIds)
                    .join();

            orderDTO.getFinalProducts().forEach(product ->
                    setProductFinanceCategories(productFinanceCategories, product));
        }
        return CompletableFuture.completedFuture(
                TransactionCreationResult.StepValidationSuccess.INSTANCE
        );
    }

    private void setProductFinanceCategories(List<ProductFinanceCategoryResponseDTO> productFinanceCategories,
                                             FinalProductDTO product) {
        productFinanceCategories.stream()
                .filter(productFinanceCategoryResponseDTO -> product.getId()
                        .equals(productFinanceCategoryResponseDTO.getProductId()))
                .findFirst().ifPresentOrElse(
                productFinanceCategoryResponseDTO -> product.setCategory(
                        CategoryMappers.toFinalProductCategoryDTO(productFinanceCategoryResponseDTO)
                ),
                () -> {
                    throw new TransactionCreationException(
                            HttpStatus.NOT_FOUND,
                            String.format(
                                    "Not finance category found for product with id %s",
                                    product.getId()
                            ),
                            TransactionCreationErrors.PRODUCT_FINANCE_CATEGORY_NOT_FOUND_ERROR
                    );
                }
        );
    }
}
