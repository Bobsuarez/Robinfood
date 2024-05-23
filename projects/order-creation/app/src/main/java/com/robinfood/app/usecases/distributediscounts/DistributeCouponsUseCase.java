package com.robinfood.app.usecases.distributediscounts;

import com.robinfood.app.util.ServicesUtil;
import com.robinfood.core.constants.GlobalConstants;
import com.robinfood.core.dtos.transactionrequestdto.DiscountDTO;
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

import static com.robinfood.core.constants.GlobalConstants.DEFAULT_INTEGER_VALUE;
import static com.robinfood.core.constants.GlobalConstants.DEFAULT_STRING_VALUE;
import static com.robinfood.core.constants.GlobalConstants.END_MARGIN_ERROR;
import static com.robinfood.core.constants.GlobalConstants.INITIAL_MARGIN_ERROR;
import static com.robinfood.core.constants.GlobalConstants.NUMBER_DECIMAL_PLACES;
import static com.robinfood.core.constants.GlobalConstants.NUMBER_DECIMAL_PLACE_MAX;

/**
 * Implementation of IDistributeDiscountsUseCase
 */
@Slf4j
@Component("distributeCouponsUseCase")
public class DistributeCouponsUseCase implements IDistributeDiscountsUseCase {

    @Override
    public void invoke(
            OrderDTO orderDTO,
            BigDecimal couponsTotal,
            Long discountId,
            TransactionRequestDTO transactionRequestDTO
    ) {

        log.info("invoke [DistributeCouponsUseCase] - orderDTO {} " +
                        "couponsTotal {} discountId {} transactionRequestDTO {}",
                ObjectMapperSingleton.objectToJson(orderDTO),
                couponsTotal,
                discountId,
                ObjectMapperSingleton.objectToJson(transactionRequestDTO)
        );

        distributeDiscountsCoupons(orderDTO, couponsTotal,
                orderDTO.getFinalProducts(), discountId, transactionRequestDTO);
    }

    /**
     * Distribute the value of a coupon to all products
     *
     * @param orderDTO      value of orders
     * @param valueDiscount Value of a coupon
     * @param discountId    Discount I'd
     */
    private void distributeDiscountsCoupons(
            OrderDTO orderDTO, BigDecimal valueDiscount,
            List<FinalProductDTO> products, Long discountId,
            TransactionRequestDTO transactionRequestDTO
    ) {

        BigDecimal finalDiscountProducts = BigDecimal.ZERO;

        BigDecimal totalService = ServicesUtil.getNetValueByOrderDto(orderDTO);

        for (final FinalProductDTO finalProductDTO : products) {

            var totalDiscount = finalProductDTO.getTotalDiscounts();

            BigDecimal calculateDiscount = totalDiscount
                    .divide(transactionRequestDTO
                                    .getTotalMinusCoupons().subtract(totalService),
                            NUMBER_DECIMAL_PLACE_MAX, RoundingMode.HALF_DOWN)
                    .multiply(valueDiscount)
                    .setScale(NUMBER_DECIMAL_PLACES, RoundingMode.HALF_DOWN);

            // takes the calculated value and adjusts the remaining or missing decimal places to equal
            // the value of the coupons
            BigDecimal calculateDiscountAdjusted =
                    adjustDifferences(valueDiscount, calculateDiscount, transactionRequestDTO, finalProductDTO);

            finalDiscountProducts = finalDiscountProducts
                    .add(calculateDiscountAdjusted);

            finalProductDTO.getDiscounts().add(
                    new FinalProductDiscountDTO(
                            calculateDiscountAdjusted,
                            Boolean.FALSE,
                            Boolean.TRUE,
                            finalProductDTO.getSku()
                    )
            );

        }

        orderDTO.getDiscounts().add(
                new DiscountDTO(
                        discountId,
                        Boolean.FALSE,
                        Boolean.FALSE,
                        1L,
                        finalDiscountProducts,
                        1L,
                        GlobalConstants.COUPON_TYPE_DEFAULT,
                        DEFAULT_STRING_VALUE
                )
        );

    }

    /***
     * AAdjust margin of error in rounding and be the exact value to the coupon
     * @param valueTotalCoupons
     * @param calculateDiscount
     * @param transactionRequestDTO
     * @return
     */
    private BigDecimal adjustDifferences(
            BigDecimal valueTotalCoupons,
            BigDecimal calculateDiscount,
            TransactionRequestDTO transactionRequestDTO,
            FinalProductDTO finalProduct
    ) {

        BigDecimal discountTotalByCouponsByProductTotal = BigDecimal.ZERO;
        BigDecimal calculateDiscountFinal = calculateDiscount;

        Boolean exit = Boolean.FALSE;

        for (OrderDTO order : transactionRequestDTO.getOrders()) {

            BigDecimal discountTotalByCouponsByProduct;

            for (FinalProductDTO finalProductDTO : order.getFinalProducts()) {

                if (Boolean.TRUE.equals(exit)) {
                    break;
                }

                discountTotalByCouponsByProduct = finalProductDTO.getDiscounts().stream()
                        .filter(typeDiscount -> typeDiscount.getIsProductDiscount().equals(Boolean.TRUE))
                        .map(FinalProductDiscountDTO::getValue)
                        .reduce(BigDecimal.ZERO, BigDecimal::add);

                if (discountTotalByCouponsByProduct.equals(BigDecimal.ZERO)) {
                    exit = Boolean.TRUE;
                }

                discountTotalByCouponsByProductTotal = discountTotalByCouponsByProduct
                        .multiply(new BigDecimal(finalProductDTO.getQuantity())).add(
                                discountTotalByCouponsByProductTotal
                        );
            }

        }

        discountTotalByCouponsByProductTotal = calculateDiscount
                .multiply(new BigDecimal(finalProduct.getQuantity())).add(
                        discountTotalByCouponsByProductTotal
                );

        final BigDecimal difference = valueTotalCoupons.subtract(discountTotalByCouponsByProductTotal);

        final Boolean rangeError = differenceValueWithinRange(difference);

        if (Boolean.TRUE.equals(rangeError)) {
            log.error("DistributeCouponsUseCase - 171 {} ", ObjectMapperSingleton.objectToJson(rangeError));
        }

        return calculateDiscountFinal;
    }


    /**
     * Validates if there is a value left within the range of 0.0001 to 0.0005
     * in order to adjust the value, so that it is exact to the coupon.
     *
     * @param errorMarge
     * @return
     */
    private Boolean differenceValueWithinRange(BigDecimal errorMarge) {
        return (errorMarge.abs().compareTo(INITIAL_MARGIN_ERROR) >= DEFAULT_INTEGER_VALUE
                && errorMarge.abs().compareTo(END_MARGIN_ERROR) <= DEFAULT_INTEGER_VALUE);
    }
}
