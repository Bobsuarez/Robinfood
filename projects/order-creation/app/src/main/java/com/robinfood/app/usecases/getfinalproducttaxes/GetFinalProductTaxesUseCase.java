package com.robinfood.app.usecases.getfinalproducttaxes;

import com.robinfood.app.usecases.configuretaxinfo.IConfigureTaxInfoUseCase;
import com.robinfood.core.constants.GlobalConstants;
import com.robinfood.core.dtos.DeductionDTO;
import com.robinfood.core.dtos.ValidateTaxItemRequestDTO;
import com.robinfood.core.dtos.ValidateTaxItemResponseDTO;
import com.robinfood.core.dtos.ValidateTaxRequestDTO;
import com.robinfood.core.dtos.ValidateTaxResponseDTO;
import com.robinfood.core.dtos.transactionrequestdto.FinalProductDTO;
import com.robinfood.core.dtos.transactionrequestdto.FinalProductDiscountDTO;
import com.robinfood.core.dtos.transactionrequestdto.FinalProductTaxDTO;
import com.robinfood.core.dtos.transactionrequestdto.OrderDTO;
import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import com.robinfood.core.enums.TransactionCreationResult;
import com.robinfood.core.util.ObjectMapperSingleton;
import com.robinfood.repository.tax.ITaxRepository;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.robinfood.core.constants.GlobalConstants.DEFAULT_INTEGER_VALUE;
import static com.robinfood.core.constants.GlobalConstants.TAX_PERCENTAGE_ID;

@Service
@Slf4j
public class GetFinalProductTaxesUseCase implements IGetFinalProductTaxesUseCase {

    private final ITaxRepository taxRepository;

    private final IConfigureTaxInfoUseCase configureTaxInfoUseCase;

    public GetFinalProductTaxesUseCase(ITaxRepository taxRepository, IConfigureTaxInfoUseCase configureTaxInfoUseCase) {
        this.taxRepository = taxRepository;
        this.configureTaxInfoUseCase = configureTaxInfoUseCase;
    }

    @Override
    public CompletableFuture<TransactionCreationResult> invoke(
            @NonNull String token,
            @NonNull TransactionRequestDTO transactionRequest
    ) {
        log.info("Stating for get final product taxes has started with request: {}",
                ObjectMapperSingleton.objectToJson(transactionRequest));

        transactionRequest.getOrders().forEach((OrderDTO orderDTO) -> {

            List<ValidateTaxItemRequestDTO> validateItemDTOs = builderListValidateItemDTO(orderDTO);

            ValidateTaxRequestDTO validateTaxRequestDTO = ValidateTaxRequestDTO.builder()
                    .criteria(orderDTO.getTaxCriteria())
                    .items(validateItemDTOs)
                    .build();

            builderTaxForEachFinalProduct(token, orderDTO, validateTaxRequestDTO);
        });

        configureTaxInfoUseCase.invoke(transactionRequest);

        return CompletableFuture.completedFuture(
                TransactionCreationResult.StepValidationSuccess.INSTANCE
        );

    }

    private List<ValidateTaxItemRequestDTO> builderListValidateItemDTO(OrderDTO orderDTO) {

        return orderDTO.getFinalProducts().stream()
                .map((FinalProductDTO finalProductDTO) -> {

                    final BigDecimal discountCoupon = getValueCouponTheProduct(finalProductDTO);

                    BigDecimal totalDiscountProduct = getTotalDiscountProduct(finalProductDTO);

                    BigDecimal totalDiscountForEachProduct = discountCoupon.add(totalDiscountProduct);

                    return builderValidateTaxItemRequestDTO(finalProductDTO, totalDiscountForEachProduct);

                }).collect(Collectors.toList());
    }

    private void builderTaxForEachFinalProduct(
            String token,
            OrderDTO orderDTO,
            ValidateTaxRequestDTO validateTaxRequestDTO
    ) {
        // Stopping thread here since it's easier to retrieve the taxes order
        // by order and then edit its final products than getting all final products from all
        // orders and then finding which final product belongs to which order to set its taxes
        List<ValidateTaxResponseDTO> finalProductTaxes = taxRepository
                .getFinalProductsTaxes(token, validateTaxRequestDTO)
                .join();

        IntStream.range(DEFAULT_INTEGER_VALUE, finalProductTaxes.size())
                .forEach((int index) -> {
                    ValidateTaxResponseDTO currentFinalProductTax = finalProductTaxes.get(index);
                    FinalProductDTO currentFinalProduct = orderDTO.getFinalProducts().get(index);
                    applyTaxesToProducts(currentFinalProduct, currentFinalProductTax);
                });
    }

    private ValidateTaxItemRequestDTO builderValidateTaxItemRequestDTO(
            FinalProductDTO finalProductDTO, BigDecimal totalDiscountForEachProduct
    ) {
        return ValidateTaxItemRequestDTO.builder()
                .articleId(finalProductDTO.getArticle().getId())
                .articleTypeId(finalProductDTO.getArticle().getTypeId())
                .discount(totalDiscountForEachProduct)
                .price(finalProductDTO.getTotalPrice())
                .quantity(finalProductDTO.getQuantity())
                .build();
    }

    private BigDecimal getValueCouponTheProduct(FinalProductDTO finalProductDTO) {

        return finalProductDTO.getDiscounts()
                .stream()
                .filter(data ->
                        Optional.ofNullable(data.getIsCoupons())
                                .orElse(Boolean.FALSE).equals(Boolean.TRUE))
                .map(FinalProductDiscountDTO::getValue)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private void applyTaxesToProducts(
            FinalProductDTO currentFinalProduct,
            ValidateTaxResponseDTO currentFinalProductTax
    ) {

        currentFinalProductTax.getTaxes()
                .forEach((ValidateTaxItemResponseDTO validateTaxItemResponseDTO) ->
                        addTaxFinalProduct(
                                currentFinalProduct,
                                currentFinalProductTax,
                                validateTaxItemResponseDTO)
                );
    }

    private void addTaxFinalProduct(
            FinalProductDTO currentFinalProduct,
            ValidateTaxResponseDTO currentFinalProductTax,
            ValidateTaxItemResponseDTO validateTaxItemResponseDTO
    ) {
        FinalProductTaxDTO finalProductTaxDTO =
                builderFinalProductTaxDTO(currentFinalProductTax, validateTaxItemResponseDTO);

        if (currentFinalProduct.getTaxes().isEmpty()) {
            currentFinalProduct.getTaxes().add(finalProductTaxDTO);
            return;
        }

        currentFinalProduct.getTaxes()
                .stream()
                .filter(finalProductTaxDTOFilter ->
                        finalProductTaxDTOFilter.getArticleId()
                                .equals(currentFinalProductTax.getArticleId())
                                && finalProductTaxDTOFilter.getFamilyTaxTypeId()
                                .equals(finalProductTaxDTO.getFamilyTaxTypeId())
                ).findFirst()
                .ifPresentOrElse(
                        tax -> tax.setTaxPrice(finalProductTaxDTO.getTaxPrice()),
                        () -> currentFinalProduct.getTaxes().add(finalProductTaxDTO)
                );
    }

    private FinalProductTaxDTO builderFinalProductTaxDTO(
            ValidateTaxResponseDTO currentFinalProductTax,
            ValidateTaxItemResponseDTO validateTaxItemResponseDTO
    ) {

        return FinalProductTaxDTO.builder()
                .articleId(currentFinalProductTax.getArticleId())
                .articleTypeId(currentFinalProductTax.getArticleTypeId())
                .dicTaxId(TAX_PERCENTAGE_ID)
                .familyId(validateTaxItemResponseDTO.getFamilyId())
                .familyTaxTypeId(validateTaxItemResponseDTO.getId())
                .taxTypeName(validateTaxItemResponseDTO.getTaxTypeName())
                .taxPrice(validateTaxItemResponseDTO.getValue())
                .taxId(validateTaxItemResponseDTO.getTaxId())
                .taxTypeId(validateTaxItemResponseDTO.getTaxTypeId())
                .taxValue(validateTaxItemResponseDTO.getRate())
                .build();
    }


    BigDecimal getTotalDiscountProduct(FinalProductDTO product) {

        BigDecimal addToDiscount = BigDecimal.ZERO;
        BigDecimal addDeductions = BigDecimal.ZERO;

        for (FinalProductDiscountDTO discount : product.getDiscounts()) {

            if (discount.getIsProductDiscount().equals(Boolean.TRUE)) {
                addToDiscount = discount.getValue()
                        .multiply(BigDecimal.valueOf(product.getQuantity()));
                continue;
            }
            discount.setValue(discount.getValue().divide(
                    BigDecimal.valueOf(
                            product.getQuantity()),
                    GlobalConstants.NUMERIC_DECIMAL_DIVISION, RoundingMode.FLOOR));
        }

        if (Objects.nonNull(product.getDeduction())) {
            addDeductions = product
                    .getDeduction()
                    .stream()
                    .map(DeductionDTO::getValue)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
        }
        return addDeductions.add(addToDiscount);
    }
}
