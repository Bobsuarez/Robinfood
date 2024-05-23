package com.robinfood.app.usecases.inputrequestvalidation.services;

import com.robinfood.app.mocks.PaymentMethodsDTOMocks;
import com.robinfood.app.mocks.TransactionRequestDTOMock;
import com.robinfood.core.dtos.transactionrequestdto.PaymentMethodDTO;
import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import com.robinfood.core.exceptions.ValidationRequestException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class ValidateGiftCardsServiceTest {

    @InjectMocks
    public ValidateGiftCardsService validateGiftCardsService;

    private final Long paymentMethodIdGiftCard = 26L;
    private final Long productCategoryIdGiftCard = 3L;
    final Map<Long, List<Long>> giftCardPaymentMethodsIds = new HashMap<>();

    @BeforeEach
    void setup() {

        giftCardPaymentMethodsIds.put(1L, List.of(paymentMethodIdGiftCard));

        ReflectionTestUtils.setField(validateGiftCardsService, "giftCardPaymentMethodsIds", giftCardPaymentMethodsIds);
        ReflectionTestUtils.setField(validateGiftCardsService, "giftCardProductCategoriesIds", productCategoryIdGiftCard);
    }

    @Test
    void test_CouponAndPaymentMethod_When_Exception() {

        final TransactionRequestDTOMock transactionRequestDTOMocks = new TransactionRequestDTOMock();
        final TransactionRequestDTO transactionRequestDTO = transactionRequestDTOMocks.transactionRequestWithCouponsDTO;
        final PaymentMethodDTO paymentMethodDTO = PaymentMethodsDTOMocks.build();

        paymentMethodDTO.setId(paymentMethodIdGiftCard);

        transactionRequestDTO.setPaymentMethods(List.of(paymentMethodDTO));

        assertThrows(ValidationRequestException.class, () -> {
            validateGiftCardsService.couponAndPaymentMethod(transactionRequestDTO);
        });
    }

    @Test
    void test_PaymentMethodAndProductsWithCategory_When_Exception() {

        final TransactionRequestDTOMock transactionRequestDTOMocks = new TransactionRequestDTOMock();
        final TransactionRequestDTO transactionRequestDTO = transactionRequestDTOMocks.transactionRequestWithCouponsDTO;
        final PaymentMethodDTO paymentMethodDTO = PaymentMethodsDTOMocks.build();

        transactionRequestDTO.getOrders().get(0).getFinalProducts().get(0).getCategory().setId(productCategoryIdGiftCard);
        paymentMethodDTO.setId(paymentMethodIdGiftCard);

        transactionRequestDTO.setPaymentMethods(List.of(paymentMethodDTO));

        assertThrows(ValidationRequestException.class, () -> {
            validateGiftCardsService.paymentMethodAndProductsWithCategory(transactionRequestDTO);
        });
    }

    @Test
    void test_PaymentMethodAndProductsWithCategory_When_Orders_Is_Null() {

        final TransactionRequestDTOMock transactionRequestDTOMocks = new TransactionRequestDTOMock();
        final TransactionRequestDTO transactionRequestDTO = transactionRequestDTOMocks.transactionRequestWithCouponsDTO;

        transactionRequestDTO.setOrders(null);

        validateGiftCardsService.paymentMethodAndProductsWithCategory(transactionRequestDTO);
    }

    @Test
    void test_PaymentMethodAndProductsWithCategory_When_Not_Exception() {

        final TransactionRequestDTOMock transactionRequestDTOMocks = new TransactionRequestDTOMock();
        final TransactionRequestDTO transactionRequestDTO = transactionRequestDTOMocks.transactionRequestWithCouponsDTO;

        validateGiftCardsService.paymentMethodAndProductsWithCategory(transactionRequestDTO);
    }

    @Test
    void test_PaymentMethodAndProductsWithCategory_Without_Coupons() {

        final TransactionRequestDTOMock transactionRequestDTOMocks = new TransactionRequestDTOMock();
        final TransactionRequestDTO transactionRequestDTO = transactionRequestDTOMocks.transactionRequestWithCouponsDTO;

        transactionRequestDTO.setCoupons(List.of());

        validateGiftCardsService.paymentMethodAndProductsWithCategory(transactionRequestDTO);
    }

    @Test
    void test_PaymentMethodAndProductsWithCategory_Without_PaymentMethods() {

        final TransactionRequestDTOMock transactionRequestDTOMocks = new TransactionRequestDTOMock();
        final TransactionRequestDTO transactionRequestDTO = transactionRequestDTOMocks.transactionRequestWithCouponsDTO;

        transactionRequestDTO.setPaymentMethods(List.of());

        validateGiftCardsService.paymentMethodAndProductsWithCategory(transactionRequestDTO);
    }

    @Test
    void test_PaymentMethodAndProductsWithCategory_Without_Coupons_And_PaymentMethods() {

        final TransactionRequestDTOMock transactionRequestDTOMocks = new TransactionRequestDTOMock();
        final TransactionRequestDTO transactionRequestDTO = transactionRequestDTOMocks.transactionRequestWithCouponsDTO;

        transactionRequestDTO.setCoupons(List.of());
        transactionRequestDTO.setPaymentMethods(List.of());

        validateGiftCardsService.paymentMethodAndProductsWithCategory(transactionRequestDTO);
    }

    @Test
    void test_CouponAndPaymentMethod_Without_PaymentMethods() {

        final TransactionRequestDTOMock transactionRequestDTOMocks = new TransactionRequestDTOMock();
        final TransactionRequestDTO transactionRequestDTO = transactionRequestDTOMocks.transactionRequestWithCouponsDTO;

        transactionRequestDTO.setPaymentMethods(List.of());

        validateGiftCardsService.couponAndPaymentMethod(transactionRequestDTO);
    }

    @Test
    void test_CouponAndPaymentMethod_Without_Coupons() {

        final TransactionRequestDTOMock transactionRequestDTOMocks = new TransactionRequestDTOMock();
        final TransactionRequestDTO transactionRequestDTO = transactionRequestDTOMocks.transactionRequestWithCouponsDTO;

        transactionRequestDTO.setCoupons(List.of());

        validateGiftCardsService.couponAndPaymentMethod(transactionRequestDTO);
    }

    @Test
    void test_CouponAndPaymentMethod_Without_PaymentMethods_And_Coupons() {

        final TransactionRequestDTOMock transactionRequestDTOMocks = new TransactionRequestDTOMock();
        final TransactionRequestDTO transactionRequestDTO = transactionRequestDTOMocks.transactionRequestWithCouponsDTO;

        transactionRequestDTO.setCoupons(List.of());
        transactionRequestDTO.setPaymentMethods(List.of());

        validateGiftCardsService.couponAndPaymentMethod(transactionRequestDTO);
    }

    @Test
    void test_CouponAndPaymentMethod_Without_PaymentMethods_By_CompanyId() {

        final TransactionRequestDTOMock transactionRequestDTOMocks = new TransactionRequestDTOMock();
        final TransactionRequestDTO transactionRequestDTO = transactionRequestDTOMocks.transactionRequestWithCouponsDTO;

        transactionRequestDTO.getCompany().setId(5L);
        transactionRequestDTO.setCoupons(List.of());
        transactionRequestDTO.setPaymentMethods(List.of());

        validateGiftCardsService.couponAndPaymentMethod(transactionRequestDTO);
    }
}
