package com.robinfood.app.usecases.inputrequestvalidation.services;

import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import com.robinfood.core.exceptions.ValidationRequestException;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import static com.robinfood.core.constants.ExceptionConstants.VALIDATE_GIFT_CARD_COUPON_AND_PAYMENT_METHOD_EXCEPTION;
import static com.robinfood.core.constants.ExceptionConstants.VALIDATE_GIFT_CARD_PAYMENT_METHOD_AND_PRODUCT_CATEGORY_EXCEPTION;
import static com.robinfood.core.enums.logs.OrderErrorLogEnum.ERROR_VALIDATING_GIFT_CARD_PAYMENT_METHODS_BY_COMPANY_ID;
import static com.robinfood.core.enums.logs.OrderErrorLogEnum.ERROR_VALIDATING_GIFT_CARD_PAYMENT_METHODS_WITH_COUPONS;
import static com.robinfood.core.enums.logs.OrderErrorLogEnum.ERROR_VALIDATING_GIFT_CARD_PAYMENT_METHODS_WITH_PRODUCT_CATEGORY_GIFT_CARD;

@Service
@Slf4j
public class ValidateGiftCardsService {

    @Value("#{${orders.gift-card-payment-methods-ids}}")
    private Map<Long, List<Long>> giftCardPaymentMethodsIds;

    @Value("${orders.gift-card-product-categories-ids}")
    private Long giftCardProductCategoriesIds;

    public ValidateGiftCardsService() {
        //Constructor empty
    }

    @SneakyThrows
    public void couponAndPaymentMethod(TransactionRequestDTO transactionRequestDTO) {

        final Boolean isPaymentMethod = isPaymentMethodGiftCard(transactionRequestDTO);
        final Boolean isCoupons = isCouponsNotEmpty(transactionRequestDTO);

        if (isPaymentMethod && isCoupons) {
            log.error(ERROR_VALIDATING_GIFT_CARD_PAYMENT_METHODS_WITH_COUPONS.getMessage());
            throw new ValidationRequestException(VALIDATE_GIFT_CARD_COUPON_AND_PAYMENT_METHOD_EXCEPTION);
        }
    }

    @SneakyThrows
    public void paymentMethodAndProductsWithCategory(TransactionRequestDTO transactionRequestDTO) {

        final Boolean isPaymentMethod = isProductsWithCategory(transactionRequestDTO);
        final Boolean isCoupons = isPaymentMethodGiftCard(transactionRequestDTO);

        if (isPaymentMethod && isCoupons) {
            log.error(ERROR_VALIDATING_GIFT_CARD_PAYMENT_METHODS_WITH_PRODUCT_CATEGORY_GIFT_CARD.getMessage());
            throw new ValidationRequestException(VALIDATE_GIFT_CARD_PAYMENT_METHOD_AND_PRODUCT_CATEGORY_EXCEPTION);
        }
    }

    private Boolean isCouponsNotEmpty(TransactionRequestDTO transactionRequestDTO) {
        return !transactionRequestDTO.getCoupons().isEmpty();
    }

    private Boolean isPaymentMethodGiftCard(TransactionRequestDTO transactionRequestDTO) {

        Long companyId = transactionRequestDTO.getCompany().getId();

        if (!giftCardPaymentMethodsIds.containsKey(companyId)) {
            log.warn(ERROR_VALIDATING_GIFT_CARD_PAYMENT_METHODS_BY_COMPANY_ID.getMessage(), companyId);
            return Boolean.FALSE;
        }

        return transactionRequestDTO.getPaymentMethods().stream().anyMatch(
                paymentMethodDTO -> giftCardPaymentMethodsIds.get(companyId)
                        .contains(paymentMethodDTO.getId())
        );
    }

    private Boolean isProductsWithCategory(TransactionRequestDTO transactionRequestDTO) {

        if (Objects.isNull(transactionRequestDTO.getOrders())) {
            return Boolean.FALSE;
        }

        return transactionRequestDTO.getOrders().stream().anyMatch(
                orderDTO -> orderDTO.getFinalProducts().stream().anyMatch(
                        finalProductDTO -> finalProductDTO.getCategory().getId().equals(giftCardProductCategoriesIds)
                )
        );
    }
}
