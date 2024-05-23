package com.robinfood.app.usecases.inputrequestvalidation;

import com.robinfood.app.usecases.inputrequestvalidation.services.ValidateGiftCardsService;
import com.robinfood.app.util.ServicesUtil;
import com.robinfood.core.dtos.transactionrequestdto.CouponDTO;
import com.robinfood.core.dtos.transactionrequestdto.FinalProductDTO;
import com.robinfood.core.dtos.transactionrequestdto.FinalProductDiscountDTO;
import com.robinfood.core.dtos.transactionrequestdto.GroupDTO;
import com.robinfood.core.dtos.transactionrequestdto.OrderDTO;
import com.robinfood.core.dtos.transactionrequestdto.PaymentMethodDTO;
import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import com.robinfood.core.enums.TransactionCreationErrors;
import com.robinfood.core.enums.logs.OrderErrorLogEnum;
import com.robinfood.core.exceptions.TransactionCreationException;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.robinfood.core.constants.GlobalConstants.DEFAULT_INTEGER_VALUE;
import static com.robinfood.core.constants.GlobalConstants.NUMBER_DECIMAL_PLACES;
import static com.robinfood.core.util.ObjectMapperSingleton.objectToJson;

@RefreshScope
@Service
@Slf4j
public class InputRequestValidationUseCase implements IInputRequestValidationUseCase {

    private static final Long DELIVERY_TYPE_EXTERNAL_ID = 4L;

    private static final Long EFFECTIVE_PAYMENT_METHOD_ID = 1L;

    private final ValidateGiftCardsService validateGiftCardsService;

    @Value("${payment-methods-ids-allowed-to-validate-detail}")
    private List<Long> paymentMethodsIdsAllowedToValidateDetail;

    public InputRequestValidationUseCase(ValidateGiftCardsService validateGiftCardsService) {
        this.validateGiftCardsService = validateGiftCardsService;
    }

    public void invoke(@NotNull TransactionRequestDTO transactionRequestDTO) {

        validateGiftCardsService.couponAndPaymentMethod(transactionRequestDTO);

        validateDeliveryType(transactionRequestDTO);
        validateCo2Transaction(transactionRequestDTO);

        if (Boolean.TRUE.equals(transactionRequestDTO.getPaid())) {

            validateTotalPrices(transactionRequestDTO);
            validatePaymentMethodDetail(transactionRequestDTO);
        }
    }

    public void validateDeliveryType(TransactionRequestDTO transactionRequest) {

        log.info("Validating delivery type {}", objectToJson(transactionRequest));

        final Boolean deliveryTypeFound = transactionRequest.getOrders().stream()
                .anyMatch(
                        orderDTO -> DELIVERY_TYPE_EXTERNAL_ID.equals(orderDTO.getDeliveryTypeId())
                );

        if (
                Boolean.TRUE.equals(deliveryTypeFound)
                        && Objects.isNull(transactionRequest.getDelivery())
        ) {

            throw new TransactionCreationException(
                    HttpStatus.PRECONDITION_FAILED,
                    String.format(
                            OrderErrorLogEnum.ERROR_ORDER_TRANSACTION_DELIVERY_IS_REQUIRED.getMessage(),
                            DELIVERY_TYPE_EXTERNAL_ID
                    ),
                    TransactionCreationErrors.DELIVERY_TYPE_NOT_FOUND
            );
        }
    }

    private void validateTotalPrices(TransactionRequestDTO transactionRequest) {

        log.info("Validating the total prices of the transaction: {}", objectToJson(transactionRequest));

        // The value of the total in the transaction
        BigDecimal transactionTotal = transactionRequest.getTotal();

        // The value of the sum of orders totals with discounts and coupons
        BigDecimal totalPriceOrders = validateAndGetTotalPriceOrders(
                transactionRequest.getOrders()
        );

        log.info("totalPriceOrders : -> {}", totalPriceOrders);

        // The value of the payment methods
        BigDecimal totalPricePaymentMethods = getTotalPricePaymentMethods(
                transactionRequest.getPaymentMethods()
        );

        // The total of subtotal dof product without discounts and coupons
        BigDecimal subtotalOrder = calculateSubtotalProducts(transactionRequest.getOrders());

        log.info("subtotal calculated from products: -> {}", subtotalOrder);

        // The total of the coupons and discounts
        BigDecimal totalDiscounts = transactionTotal.subtract(totalPriceOrders);

        log.info("Validating the totalDiscounts of the transaction: -> {}", totalDiscounts);

        // The total of the transaction with co2
        BigDecimal finalTotalTransaction = validateSubtotalNegative(subtotalOrder, totalDiscounts)
                .add(transactionRequest.getCo2Total());

        // The total of the user payments
        BigDecimal finalTotalPayments = totalPricePaymentMethods
                .add(transactionRequest.getTotalPaidPayments());

        log.info("difference between customer payment -> {} and calculated payment -> {}",
                finalTotalPayments, finalTotalTransaction
        );

        if (!finalTotalTransaction.equals(finalTotalPayments)) {

            throw new TransactionCreationException(
                    HttpStatus.PRECONDITION_FAILED,
                    String.format(
                            OrderErrorLogEnum.ERROR_PRICE_TRANSACTION_DIFFERENT_TOTAL_PAYMENTS_ORDER.getMessage(),
                            finalTotalTransaction,
                            finalTotalPayments
                    ),
                    TransactionCreationErrors.TRANSACTION_PRICES_ERROR
            );
        }
        transactionRequest.setTotal(totalPriceOrders.add(totalDiscounts));
    }

    private BigDecimal validateSubtotalNegative(BigDecimal subtotalOrder, BigDecimal totalDiscounts) {
        BigDecimal subtracted = (subtotalOrder.subtract(totalDiscounts));

        if (subtracted.signum() < DEFAULT_INTEGER_VALUE) {
            return BigDecimal.ZERO.setScale(NUMBER_DECIMAL_PLACES, RoundingMode.UNNECESSARY);
        }
        return subtracted;
    }

    private BigDecimal getTotalPricePaymentMethods(List<PaymentMethodDTO> paymentMethodDTOS) {
        return paymentMethodDTOS.stream()
                .map(PaymentMethodDTO::getValue)
                .collect(Collectors.toList())
                .stream()
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .setScale(NUMBER_DECIMAL_PLACES, RoundingMode.UNNECESSARY);
    }

    private BigDecimal validateAndGetTotalPriceOrders(List<OrderDTO> orderDTOS) {
        return orderDTOS.stream()
                .map(this::validateAndGetTotalPriceOrder)
                .collect(Collectors.toList())
                .stream()
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .setScale(NUMBER_DECIMAL_PLACES, RoundingMode.UNNECESSARY);
    }

    private BigDecimal validateAndGetTotalPriceOrder(OrderDTO orderDTO) {

        BigDecimal totalPriceProducts = getTotalPriceProducts(orderDTO.getFinalProducts());

        BigDecimal totalServiceAndProduct = ServicesUtil.getNetValueByOrderDto(orderDTO)
                .add(totalPriceProducts);

        BigDecimal totalOrder = orderDTO.getTotal();

        // Validate sum of products with total of order
        if (!totalOrder.equals(totalServiceAndProduct)) {

            throw new TransactionCreationException(
                    HttpStatus.PRECONDITION_FAILED,
                    String.format(
                            OrderErrorLogEnum.ERROR_PRICE_PRODUCTS_DIFFERENT_TOTAL_ORDER.getMessage(),
                            totalServiceAndProduct,
                            totalOrder),
                    TransactionCreationErrors.TRANSACTION_PRICES_ERROR
            );
        }

        BigDecimal totalOrderCoupons = getValueCoupons(orderDTO.getCoupons());
        BigDecimal totalOrderDiscounts = getValueDiscounts(orderDTO.getFinalProducts());

        // Return value of products subtract the sum of discounts and coupons
        return totalServiceAndProduct.subtract(totalOrderCoupons.add(totalOrderDiscounts));
    }

    private BigDecimal getTotalPriceProducts(List<FinalProductDTO> finalProductDTOS) {
        return finalProductDTOS.stream()
                .map(this::getTotalPriceProduct)
                .collect(Collectors.toList())
                .stream()
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .setScale(NUMBER_DECIMAL_PLACES, RoundingMode.UNNECESSARY);
    }

    private BigDecimal getTotalPriceProduct(FinalProductDTO finalProductDTO) {

        BigDecimal totalPriceProduct = getSubtotalProduct(finalProductDTO);

        return getDiscountsFinalProduct(finalProductDTO)
                .map(discount -> totalPriceProduct
                        .subtract(discount.multiply(BigDecimal.valueOf(finalProductDTO.getQuantity()))))
                .orElseGet(finalProductDTO::getTotalPrice);
    }

    private BigDecimal calculateSubtotalProducts(List<OrderDTO> orderDTOS) {

        return orderDTOS.stream()
                .map((OrderDTO orderDTO) -> {
                    BigDecimal subtotalProduct = orderDTO.getFinalProducts()
                            .stream()
                            .map(finalProductDTO -> getSubtotalProduct(finalProductDTO)
                                    .subtract(calculateDiscountToProduct(finalProductDTO)))
                            .reduce(BigDecimal.ZERO, BigDecimal::add);
                    return subtotalProduct.add(ServicesUtil.getNetValueByOrderDto(orderDTO)
                            .setScale(NUMBER_DECIMAL_PLACES, RoundingMode.UNNECESSARY));
                })
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .setScale(NUMBER_DECIMAL_PLACES, RoundingMode.UNNECESSARY);
    }

    private BigDecimal calculateDiscountToProduct(FinalProductDTO finalProductDTO) {

        BigDecimal finalProductDiscount = finalProductDTO.getDiscounts()
                .stream()
                .filter(dat -> Optional
                        .ofNullable(dat.getIsProductDiscount())
                        .orElse(Boolean.FALSE).equals(Boolean.FALSE) && Optional.ofNullable(dat.getIsCoupons())
                        .orElse(Boolean.FALSE).equals(Boolean.FALSE))
                .map(FinalProductDiscountDTO::getValue)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .setScale(NUMBER_DECIMAL_PLACES, RoundingMode.UNNECESSARY);

        return finalProductDiscount
                .multiply(BigDecimal.valueOf(finalProductDTO.getQuantity()));
    }


    private Optional<BigDecimal> getDiscountsFinalProduct(FinalProductDTO finalProductDTO) {
        return Optional.of(
                finalProductDTO.getDiscounts().stream()
                        .map(FinalProductDiscountDTO::getValue)
                        .collect(Collectors.toList())
                        .stream()
                        .reduce(BigDecimal.ZERO, BigDecimal::add)
                        .setScale(NUMBER_DECIMAL_PLACES, RoundingMode.UNNECESSARY)
        );
    }

    private BigDecimal getSubtotalProduct(FinalProductDTO finalProductDTO) {

        BigDecimal additionTotalValue = finalProductDTO.getGroups().stream()
                .map(GroupDTO::getPortions)
                .flatMap(Collection::stream)
                .filter(item -> item.getPrice().compareTo(BigDecimal.ZERO) > DEFAULT_INTEGER_VALUE)
                .map(item -> item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .setScale(NUMBER_DECIMAL_PLACES, RoundingMode.UNNECESSARY);

        return finalProductDTO.getPrice()
                .add(additionTotalValue)
                .multiply(BigDecimal.valueOf(finalProductDTO.getQuantity()));
    }

    private void validatePaymentMethodDetail(
            TransactionRequestDTO transactionRequest
    ) {

        log.info("Validating payment methods {} to verify detail",
                objectToJson(transactionRequest.getPaymentMethods())
        );

        if (transactionRequest.getPaymentMethods().isEmpty()) {
            transactionRequest.setPaymentMethods(Collections.singletonList(
                    PaymentMethodDTO
                            .builder()
                            .id(EFFECTIVE_PAYMENT_METHOD_ID)
                            .originId(transactionRequest.getOrigin().getId())
                            .value(BigDecimal.ZERO)
                            .build()
            ));
        } else {
            transactionRequest.getPaymentMethods()
                    .forEach(this::verifyPaymentMethodAllowed);
        }
    }

    private void verifyPaymentMethodAllowed(PaymentMethodDTO paymentMethod) {

        final boolean isPaymentMethodValidate = paymentMethodsIdsAllowedToValidateDetail.contains(
                paymentMethod.getId()
        );

        if (isPaymentMethodValidate && Objects.isNull(paymentMethod.getDetail())) {

            throw new TransactionCreationException(
                    HttpStatus.PRECONDITION_FAILED,
                    String.format(
                            OrderErrorLogEnum.ERROR_METHOD_PAYMENT_NOT_CONTAIN_DETAIL.getMessage(),
                            paymentMethod.getId()
                    ),
                    TransactionCreationErrors.PAYMENT_METHOD_WITH_NO_DETAIL
            );
        }
    }

    private BigDecimal getValueCoupons(List<CouponDTO> coupons) {
        return coupons.stream()
                .map(CouponDTO::getValue)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private BigDecimal getValueDiscounts(List<FinalProductDTO> finalProductDTOS) {
        return finalProductDTOS
                .stream()
                .map(data -> getDiscountsFinalProduct(data)
                        .map(discount -> discount.multiply(BigDecimal.valueOf(data.getQuantity())))
                        .orElseGet(() -> BigDecimal.ZERO))
                .collect(Collectors.toList())
                .stream()
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private void validateCo2Transaction(TransactionRequestDTO transactionRequest) {

        BigDecimal co2TotalOrders = transactionRequest.getOrders().stream()
                .map(OrderDTO::getCo2Total)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal co2TotalProducts = transactionRequest.getOrders()
                .stream().map(OrderDTO::getFinalProducts)
                .flatMap(Collection::stream)
                .map(finalProductDTO -> finalProductDTO.getCo2Total()
                        .multiply(BigDecimal.valueOf(finalProductDTO.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        if (!co2TotalProducts.equals(co2TotalOrders)) {

            throw new TransactionCreationException(
                    HttpStatus.PRECONDITION_FAILED,
                    String.format(
                            "the sum of the co2 of the products %s does not match the total co2 of the order %s",
                            co2TotalProducts,
                            co2TotalOrders),
                    TransactionCreationErrors.CO2_VALIDATION_ERROR
            );
        }
    }
}
