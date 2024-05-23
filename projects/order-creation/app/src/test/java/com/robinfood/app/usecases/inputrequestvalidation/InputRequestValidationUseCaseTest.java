package com.robinfood.app.usecases.inputrequestvalidation;

import com.robinfood.app.mocks.TransactionRequestDTOMock;
import com.robinfood.app.usecases.inputrequestvalidation.services.ValidateGiftCardsService;
import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import com.robinfood.core.enums.TransactionCreationErrors;
import com.robinfood.core.exceptions.TransactionCreationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static com.robinfood.core.constants.GlobalConstants.DEFAULT_INTEGER_VALUE;
import static com.robinfood.core.constants.GlobalConstants.NUMBER_DECIMAL_PLACES;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class InputRequestValidationUseCaseTest {

    @InjectMocks
    private InputRequestValidationUseCase inputRequestValidationUseCase;

    @Mock
    ValidateGiftCardsService validateGiftCardsService;

    @BeforeEach
    void setup() {
        ReflectionTestUtils.setField(inputRequestValidationUseCase, "paymentMethodsIdsAllowedToValidateDetail", List.of(4L, 6L));
    }

    @Test
    void given_transaction_then_is_ok() {

        // Arrange
        TransactionRequestDTO transaction = new TransactionRequestDTOMock().transactionRequestDTOWithCouponsWithOutServices;
        transaction.getOrders()
                .get(DEFAULT_INTEGER_VALUE).getFinalProducts()
                .get(DEFAULT_INTEGER_VALUE).setDiscounts(Collections.emptyList());

        // Act - Assert
        assertDoesNotThrow(() -> inputRequestValidationUseCase.invoke(transaction));
    }

    @Test
    void given_transaction_then_is_ok_and_transaction_zero() {

        // Arrange
        final TransactionRequestDTO transaction = new TransactionRequestDTOMock()
                .transactionRequestDTOWithCouponsWithOutServices;

        transaction.setTotal(BigDecimal.valueOf(7900));
        transaction.getOrders().get(0).getCoupons().get(0).setValue(BigDecimal.valueOf(7900));
        transaction.setPaymentMethods(Collections.emptyList());
        transaction.getOrders()
                .get(DEFAULT_INTEGER_VALUE).getFinalProducts()
                .get(DEFAULT_INTEGER_VALUE).setDiscounts(Collections.emptyList());

        // Act - Assert
        assertDoesNotThrow(() -> inputRequestValidationUseCase.invoke(transaction));
    }

    @Test
    void given_transaction_then_return_exception_by_total_order() {

        // Arrange
        final TransactionRequestDTO transaction = new TransactionRequestDTOMock().transactionRequestDTOWithOtherTotal;

        // Act - Assert
        assertThrows(TransactionCreationException.class,
                () -> inputRequestValidationUseCase.invoke(transaction));
    }

    @Test
    void given_transaction_then_return_exception_by_price_products() {

        // Arrange
        final TransactionRequestDTO transaction = new TransactionRequestDTOMock().transactionRequestDTOWithTotalOrderOther;

        // Act - Assert
        assertThrows(TransactionCreationException.class,
                () -> inputRequestValidationUseCase.invoke(transaction));
    }

    @Test
    void given_transaction_then_return_exception_by_price_payment_methods() {

        // Arrange
        final TransactionRequestDTO transaction =
                new TransactionRequestDTOMock().transactionRequestDTOWithPricePaymentsError;

        // Act - Assert
        assertThrows(TransactionCreationException.class,
                () -> inputRequestValidationUseCase.invoke(transaction));
    }

    @Test
    void given_transaction_then_return_exception_by_not_contain_detail_in_payment_method() {

        // Arrange
        TransactionRequestDTO transaction = new TransactionRequestDTOMock().transactionRequestDTO;

        // Act - Assert
        assertThrows(TransactionCreationException.class,
                () -> inputRequestValidationUseCase.invoke(transaction));
    }

    @Test
    void given_transaction_then_return_exception_by_not_matching_payments_with_total() {

        // Arrange
        TransactionRequestDTO transaction =
                new TransactionRequestDTOMock().transactionRequestDTOWithNoMatchingPayments;

        // Act - Assert
        assertThrows(TransactionCreationException.class,
                () -> inputRequestValidationUseCase.invoke(transaction));
    }

    @Test
    void test_when_delivery_type_id_is_four_and_delivery_are_null_throw_transaction_creation_exception() {

        try {

            TransactionRequestDTO transaction = new TransactionRequestDTOMock().transactionRequestDTO;
            transaction.getOrders().get(DEFAULT_INTEGER_VALUE)
                    .getFinalProducts().get(DEFAULT_INTEGER_VALUE)
                    .setDiscounts(Collections.emptyList());
            transaction.getOrders().forEach(orderDTO -> orderDTO.setDeliveryTypeId(4L));

            inputRequestValidationUseCase.invoke(transaction);

        } catch (TransactionCreationException exception) {
            assertEquals(HttpStatus.PRECONDITION_FAILED, exception.getStatus());
        }
    }

    @Test
    void test_when_total_transaction_and_total_price_order_are_different_throw_transaction_creation_exception() {

        try {
            // Arrange
            TransactionRequestDTO transaction = new TransactionRequestDTOMock().transactionRequestDTO;
            transaction.getOrders().get(DEFAULT_INTEGER_VALUE)
                    .getFinalProducts().get(DEFAULT_INTEGER_VALUE)
                    .setDiscounts(Collections.emptyList());
            transaction.setTotal(BigDecimal.ZERO);

            // Act - Assert
            inputRequestValidationUseCase.invoke(transaction);

        } catch (TransactionCreationException exception) {
            assertEquals(HttpStatus.PRECONDITION_FAILED, exception.getStatus());
        }
    }

    @Test
    void test_when_total_transaction_and_total_price_payments_are_different_throw_transaction_creation_exception() {

        try {
            TransactionRequestDTO transaction = new TransactionRequestDTOMock().transactionRequestDTO;
            transaction.getOrders().get(DEFAULT_INTEGER_VALUE)
                    .getFinalProducts().get(DEFAULT_INTEGER_VALUE)
                    .setDiscounts(Collections.emptyList());
            transaction.getPaymentMethods().get(DEFAULT_INTEGER_VALUE).setValue(BigDecimal.ZERO);

            inputRequestValidationUseCase.invoke(transaction);

        } catch (TransactionCreationException exception) {
            assertEquals(HttpStatus.PRECONDITION_FAILED, exception.getStatus());
        }
    }

    @Test
    void test_when_allowed_payment_methods_contains_payment_methods_throw_transaction_creation_exception() {

        try {

            // Arrange
            TransactionRequestDTO transaction = new TransactionRequestDTOMock().transactionRequestDTO;
            transaction.getOrders().get(DEFAULT_INTEGER_VALUE)
                    .getFinalProducts().get(DEFAULT_INTEGER_VALUE)
                    .setDiscounts(Collections.emptyList());
            transaction.getPaymentMethods().forEach(paymentMethodDTO -> paymentMethodDTO.setId(10L));

            // Act - Assert
            inputRequestValidationUseCase.invoke(transaction);

        } catch (TransactionCreationException exception) {
            assertEquals(HttpStatus.PRECONDITION_FAILED, exception.getStatus());
        }
    }

    @Test
    void given_transaction_then_is_not_paid() {

        // Arrange
        TransactionRequestDTO transaction = new TransactionRequestDTOMock().transactionRequestDTO;
        transaction.setPaid(false);
        transaction.getOrders()
                .get(DEFAULT_INTEGER_VALUE).getFinalProducts()
                .get(DEFAULT_INTEGER_VALUE).setDiscounts(Collections.emptyList());

        // Act - Assert
        assertDoesNotThrow(() -> inputRequestValidationUseCase.invoke(transaction));
    }

    @Test
    void given_transaction_then_payment_method_with_not_detail() {

        final Long paymentMethodSelfManagement = 4L;

        // Arrange
        TransactionRequestDTO transaction = new TransactionRequestDTOMock().transactionRequestDTOWithCouponsWithOutServices;
        transaction.getOrders()
                .get(DEFAULT_INTEGER_VALUE).getFinalProducts()
                .get(DEFAULT_INTEGER_VALUE).setDiscounts(Collections.emptyList());

        transaction.getPaymentMethods().get(0).setId(paymentMethodSelfManagement);

        // Act - Assert
        final TransactionCreationException result = assertThrows(
                TransactionCreationException.class,
                () -> inputRequestValidationUseCase.invoke(transaction)
        );

        assertEquals(HttpStatus.PRECONDITION_FAILED, result.getStatus());
        assertEquals(TransactionCreationErrors.PAYMENT_METHOD_WITH_NO_DETAIL, result.getTransactionCreationError());
    }

    @Test
    void given_transaction_then_transaction_prices_error() {

        // Arrange
        TransactionRequestDTO transaction = new TransactionRequestDTOMock().transactionRequestDTOWithCouponsWithOutServices;
        transaction.getOrders()
                .get(DEFAULT_INTEGER_VALUE).getFinalProducts()
                .get(DEFAULT_INTEGER_VALUE).setDiscounts(Collections.emptyList());
        transaction.setCo2Total(BigDecimal.ONE);

        // Act - Assert
        final TransactionCreationException result = assertThrows(
                TransactionCreationException.class,
                () -> inputRequestValidationUseCase.invoke(transaction)
        );

        assertEquals(HttpStatus.PRECONDITION_FAILED, result.getStatus());
        assertEquals(TransactionCreationErrors.TRANSACTION_PRICES_ERROR, result.getTransactionCreationError());
    }

    @Test
    void test_when_final_product_have_additions() {

        // Arrange
        TransactionRequestDTO transaction = new TransactionRequestDTOMock().transactionRequestWithAdditionDTO;
        transaction.getOrders()
                .get(DEFAULT_INTEGER_VALUE).getFinalProducts()
                .get(DEFAULT_INTEGER_VALUE).setDiscounts(Collections.emptyList());
        transaction.setTotal(BigDecimal.valueOf(18900));
        // Act - Assert
        assertDoesNotThrow(() -> inputRequestValidationUseCase.invoke(transaction));
    }

    @Test
    void test_Transaction_Then_Return_Exception_By_Co2() {

        // Arrange
        final TransactionRequestDTO transactionRequestDTO = new TransactionRequestDTOMock().transactionRequestWithAdditionDTO;

        transactionRequestDTO.setCo2Total(BigDecimal.valueOf(100));
        transactionRequestDTO.getOrders().get(0).setCo2Total(BigDecimal.valueOf(50));

        // Act - Assert
        assertThrows(TransactionCreationException.class,
                () -> inputRequestValidationUseCase.invoke(transactionRequestDTO));
    }


    @Test
    void test_Transaction_Then_Is_Delivery() {

        // Arrange
        final TransactionRequestDTO transactionRequestDTO = new TransactionRequestDTOMock().transactionRequestDTOWithCouponsWithOutServices;
        transactionRequestDTO.setDelivery(null);

        transactionRequestDTO.getOrders()
                .get(DEFAULT_INTEGER_VALUE).getFinalProducts()
                .get(DEFAULT_INTEGER_VALUE).setDiscounts(Collections.emptyList());

        // Act - Assert
        assertDoesNotThrow(() -> inputRequestValidationUseCase.invoke(transactionRequestDTO));
    }

    @Test
    void test_Transaction_Then_Is_Ok_When_ValidateIsProductDiscount() {

        // Arrange
        final TransactionRequestDTO transactionRequestDTO = new TransactionRequestDTOMock()
                .transactionRequestDTOWithCouponsWithOutServices;

        transactionRequestDTO.setDelivery(null);

        transactionRequestDTO.getOrders()
                .get(DEFAULT_INTEGER_VALUE).getFinalProducts()
                .get(DEFAULT_INTEGER_VALUE).getDiscounts().get(0).setIsProductDiscount(Boolean.FALSE);

        transactionRequestDTO.getOrders()
                .get(DEFAULT_INTEGER_VALUE).setTotal(BigDecimal.valueOf(4900).setScale(NUMBER_DECIMAL_PLACES));

        transactionRequestDTO.getPaymentMethods()
                .get(DEFAULT_INTEGER_VALUE).setValue(BigDecimal.valueOf(0));

        // Act - Assert
        assertDoesNotThrow(() -> inputRequestValidationUseCase.invoke(transactionRequestDTO));
    }

    @Test
    void test_Transaction_Then_Is_Ok_When_ValidateIsProductDiscount_List() {

        // Arrange
        final TransactionRequestDTO transactionRequestDTO = new TransactionRequestDTOMock()
                .transactionRequestWithDiscounts;

        transactionRequestDTO.setDelivery(null);

        transactionRequestDTO.getOrders()
                .get(DEFAULT_INTEGER_VALUE).getFinalProducts()
                .get(DEFAULT_INTEGER_VALUE).getDiscounts().get(0).setIsProductDiscount(Boolean.FALSE);

        transactionRequestDTO.getOrders()
                .get(DEFAULT_INTEGER_VALUE).setTotal(BigDecimal.valueOf(12900).setScale(NUMBER_DECIMAL_PLACES));

        transactionRequestDTO.getPaymentMethods()
                .get(DEFAULT_INTEGER_VALUE).setValue(BigDecimal.valueOf(9900));
        transactionRequestDTO.getPaymentMethods()
                .get(DEFAULT_INTEGER_VALUE).setId(4L);
        transactionRequestDTO.getPaymentMethods().get(DEFAULT_INTEGER_VALUE).setDetail(
                new TransactionRequestDTOMock().paymentMethodDetailDTO);

        // Act - Assert
        assertDoesNotThrow(() -> inputRequestValidationUseCase.invoke(transactionRequestDTO));
    }
}
