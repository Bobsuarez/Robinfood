package com.robinfood.core.constants;

public final class ExceptionConstants {

    public static final String VALIDATE_GIFT_CARD_COUPON_AND_PAYMENT_METHOD_EXCEPTION = "There cannot be a gift " +
            "card payment method with a coupon in the same order";
    public static final String VALIDATE_GIFT_CARD_PAYMENT_METHOD_AND_PRODUCT_CATEGORY_EXCEPTION = "There cannot be a " +
            "gift card payment method with a product category gift card in the same order";

    private ExceptionConstants() {
        throw new IllegalStateException("Utility class");
    }
}
