package com.robinfood.app.usecases.distributediscounts;

import com.robinfood.app.util.ServicesUtil;
import com.robinfood.core.dtos.transactionrequestdto.FinalProductDTO;
import com.robinfood.core.dtos.transactionrequestdto.FinalProductDiscountDTO;
import com.robinfood.core.dtos.transactionrequestdto.OrderDTO;
import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import com.robinfood.core.util.ObjectMapperSingleton;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import static com.robinfood.core.constants.GlobalConstants.NUMBER_DECIMAL_PLACES;

/**
 * Implementation of IDistributeDiscountsUseCase
 */
@Slf4j
@Component("distributeDiscountsUseCase")
public class DistributeDiscountsUseCase implements IDistributeDiscountsUseCase {

    @Override
    public void invoke(
            OrderDTO orderDTO,
            BigDecimal discountsTotal,
            Long discountId,
            TransactionRequestDTO transactionRequestDTO
    ) {

        log.info("invoke [DistributeDiscountsUseCase] - orderDTO: {}," +
                        " discountsTotal: {}, discountId: {}, transactionRequestDTO: {}",
                ObjectMapperSingleton.objectToJson(orderDTO),
                discountsTotal,
                discountId,
                ObjectMapperSingleton.objectToJson(transactionRequestDTO)
        );

        final BigDecimal totalService = ServicesUtil.getNetValueByOrderDto(orderDTO);

        distributeDiscounts(
                orderDTO,
                orderDTO.getFinalProducts(),
                orderDTO.getTotal().subtract(totalService),
                discountsTotal
        );
    }

    /**
     * Distribute the value of a coupon to all products
     *
     * @param orderDTO      the order DTO that will be distributed
     * @param valueDiscount Value of a coupon
     * @param total         subtotal of all orders
     * @param products      products
     */
    private void distributeDiscounts(
            OrderDTO orderDTO,
            List<FinalProductDTO> products,
            BigDecimal total,
            BigDecimal valueDiscount
    ) {

        BigDecimal finalProductsTotal = products.stream()
                .map((FinalProductDTO finalProductDTO) ->
                        processTotalProducts(finalProductDTO, total, valueDiscount))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        if (!orderDTO.getFinalProducts().isEmpty()) {

            final BigDecimal discountsDifference = valueDiscount.subtract(finalProductsTotal);

            final List<FinalProductDiscountDTO> firstFinalProductDiscounts = orderDTO
                    .getFinalProducts()
                    .get(0)
                    .getDiscounts();

            final FinalProductDiscountDTO lastDiscountOfFirstFinalProduct = firstFinalProductDiscounts
                    .get(firstFinalProductDiscounts.size() - 1);

            lastDiscountOfFirstFinalProduct
                    .setValue(lastDiscountOfFirstFinalProduct.getValue().add(discountsDifference));
        }
    }

    private BigDecimal processTotalProducts(
            FinalProductDTO finalProductDTO, BigDecimal total,
            BigDecimal valueDiscount
    ) {

        BigDecimal totalDiscounts = finalProductDTO.getTotalDiscounts();

        BigDecimal calculateDiscount = totalDiscounts
                .multiply(valueDiscount)
                .divide(total, NUMBER_DECIMAL_PLACES, RoundingMode.HALF_DOWN)
                .setScale(NUMBER_DECIMAL_PLACES, RoundingMode.HALF_DOWN);

        BigDecimal calculateDiscountForUnit =
                calculateDiscount.divide(BigDecimal.valueOf(finalProductDTO.getQuantity()),
                                NUMBER_DECIMAL_PLACES, RoundingMode.HALF_DOWN)
                        .setScale(NUMBER_DECIMAL_PLACES, RoundingMode.CEILING);

        finalProductDTO.getDiscounts().clear();

        finalProductDTO.getDiscounts().add(
                builderFinalProductDiscountDTO(calculateDiscountForUnit, finalProductDTO.getSku())
        );
        return calculateDiscount;
    }

    private FinalProductDiscountDTO builderFinalProductDiscountDTO(
            BigDecimal calculateDiscount,
            String sku
    ) {
        return FinalProductDiscountDTO.builder()
                .isProductDiscount(Boolean.FALSE)
                .value(calculateDiscount)
                .SKU(sku)
                .build();
    }
}
