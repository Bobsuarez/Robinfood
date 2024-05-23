package com.robinfood.paymentmethodsbc.services.steps.common;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.nullable;
import static org.mockito.Mockito.when;

import com.robinfood.paymentmethodsbc.dto.steps.CreditCardCreatePipeDTO;
import com.robinfood.paymentmethodsbc.dto.steps.PaymentPipeDTO;
import com.robinfood.paymentmethodsbc.exceptions.EntityNotFoundException;
import com.robinfood.paymentmethodsbc.exceptions.PaymentStepException;
import com.robinfood.paymentmethodsbc.repositories.CreditCardTypesRepository;
import com.robinfood.paymentmethodsbc.repositories.CreditCardsRepository;
import com.robinfood.paymentmethodsbc.sample.CreditCardSamples;
import com.robinfood.paymentmethodsbc.sample.TransactionSamples;
import com.robinfood.paymentmethodsbc.security.CipherUtility;
import java.time.LocalDateTime;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class GetCreditCardStepTest {
    @Mock
    private CipherUtility cipherUtility;

    @Mock
    private CreditCardsRepository creditCardsRepository;

    @Mock
    private CreditCardTypesRepository creditCardTypesRepository;

    @InjectMocks
    private GetCreditCardStep getCreditCardStep;

    @Test
    public void testInvokeForTransactionCreatePipeDTOShouldBeOk() {
        when(
            creditCardsRepository.findByIdAndUserIdAndDeletedAt(
                anyLong(), anyLong(), nullable(LocalDateTime.class)
            )
        ).thenReturn(Optional.of(CreditCardSamples.getCreditCard(false)));

        assertAll(
            () -> getCreditCardStep.invoke(
                TransactionSamples.getTransactionCreatePipeDTO()
            )
        );
    }

    @Test
    public void testInvokeForTransactionCreatePipeDTOShouldBeOkCreditCardUpdated() {
        when(
            creditCardsRepository.findByIdAndUserIdAndDeletedAt(
                anyLong(), anyLong(), nullable(LocalDateTime.class)
            )
        ).thenReturn(Optional.of(CreditCardSamples.getCreditCardUpdated(false)));

        assertAll(
            () -> getCreditCardStep.invoke(
                TransactionSamples.getTransactionCreatePipeDTO()
            )
        );
    }

    @Test
    public void testInvokeForTransactionCreatePipeDTOShouldBeOkCreditCardNotUpdated() {
        when(
            creditCardsRepository.findByIdAndUserIdAndDeletedAt(
                anyLong(), anyLong(), nullable(LocalDateTime.class)
            )
        ).thenReturn(Optional.of(CreditCardSamples.getCreditCardNotUpdated(false)));

        assertAll(
            () -> getCreditCardStep.invoke(
                TransactionSamples.getTransactionCreatePipeDTO()
            )
        );
    }

    @Test
    public void testInvokeForTransactionCreatePipeDTOShouldBeThrowBaseException() {
        PaymentPipeDTO pipe = TransactionSamples.getTransactionCreatePipeDTO();
        pipe.getTransactionRequestDTO().setCountryId(2L);

        when(
            creditCardsRepository.findByIdAndUserIdAndDeletedAt(
                anyLong(), anyLong(), nullable(LocalDateTime.class)
            )
        ).thenReturn(Optional.of(CreditCardSamples.getCreditCard(false)));

        assertThrows(PaymentStepException.class, () -> getCreditCardStep.invoke(pipe), "Exception");
    }

    @Test
    public void testInvokeForTransactionCreatePipeDTOShouldBeThrowException() {
        assertThrows(
            EntityNotFoundException.class,
            () -> getCreditCardStep.invoke(
                TransactionSamples.getTransactionCreatePipeDTO()
            ),
            "EntityNotFoundException"
        );
    }

    @Test
    public void testInvokeForCreditCardCreatePipeDTOShouldBeOk() {
        when(creditCardTypesRepository.findByCode(anyString()))
            .thenReturn(Optional.of(CreditCardSamples.getCreditCardType()));

        assertAll(
            () -> getCreditCardStep.invoke(
                CreditCardSamples.getCreditCardCreatePipeDTO()
            )
        );
    }

    @Test
    public void testInvokeForCreditCardCreatePipeDTOShouldBeOkUpdated() {
        when(creditCardTypesRepository.findByCode(anyString()))
            .thenReturn(Optional.of(CreditCardSamples.getCreditCardType()));

        assertAll(
            () -> getCreditCardStep.invoke(
                CreditCardSamples.getCreditCardCreatePipeDTOUpdated()
            )
        );
    }

    @Test
    public void testInvokeForCreditCardCreatePipeDTOShouldBeOkNotUpdated() {
        when(creditCardTypesRepository.findByCode(anyString()))
            .thenReturn(Optional.of(CreditCardSamples.getCreditCardType()));

        assertAll(
            () -> getCreditCardStep.invoke(
                CreditCardSamples.getCreditCardCreatePipeDTONotUpdated()
            )
        );
    }

    @Test
    public void testInvokeForCreditCardCreatePipeDTOShouldBeThrowExceptionWhenValidateCardVerificationCodeNotNumeric() {
        CreditCardCreatePipeDTO pipe = CreditCardSamples.getCreditCardCreatePipeDTO();
        pipe.getCreditCardRequestDTO().setCardVerificationCode("1223u");

        assertThrows(PaymentStepException.class, () -> getCreditCardStep.invoke(pipe), "PaymentStepException");
    }

    @Test
    public void testInvokeForCreditCardCreatePipeDTOShouldBeExceptionWhenValidateCardVerificationCodeIsBlank() {
        CreditCardCreatePipeDTO pipe = CreditCardSamples.getCreditCardCreatePipeDTO();
        pipe.getCreditCardRequestDTO().setCardVerificationCode("");

        assertThrows(PaymentStepException.class, () -> getCreditCardStep.invoke(pipe), "PaymentStepException");
    }

    @Test
    public void testInvokeForCreditCardCreatePipeDTOShouldBeThrowExceptionWhenvalidateIfNumberExistsByUserIdAndNumberEquals() {
        CreditCardCreatePipeDTO pipe = CreditCardSamples.getCreditCardCreatePipeDTO();

        when(
            creditCardsRepository.findByUserIdAndCountryIdAndDeletedAt(
                anyLong(), anyLong(), nullable(LocalDateTime.class)
            )
        ).thenReturn(CreditCardSamples.getCreditCardList());

        when(cipherUtility.decryptTextWithPrivateKey(anyString())).thenReturn("1111");

        assertThrows(PaymentStepException.class, () -> getCreditCardStep.invoke(pipe), "PaymentStepException");
    }

    @Test
    public void testInvokeForCreditCardCreatePipeDTOShouldBeOkWhenvalidateIfNumberExistsByUserIdAndNumberNotEquals() {
        CreditCardCreatePipeDTO pipe = CreditCardSamples.getCreditCardCreatePipeDTO();

        when(creditCardTypesRepository.findByCode(anyString()))
            .thenReturn(Optional.of(CreditCardSamples.getCreditCardType()));

        when(
            creditCardsRepository.findByUserIdAndCountryIdAndDeletedAt(
                anyLong(), anyLong(), nullable(LocalDateTime.class)
            )
        ).thenReturn(CreditCardSamples.getCreditCardList());

        when(cipherUtility.decryptTextWithPrivateKey(anyString())).thenReturn("1112");

        assertAll(() -> getCreditCardStep.invoke(pipe));
    }

    @Test
    public void testInvokeForCreditCardCreatePipeDTOShouldBeThrowExceptionWhenInvalidNumber() {
        CreditCardCreatePipeDTO pipe = CreditCardSamples.getCreditCardCreatePipeDTO();
        String invalidNumber = "INVALIDNUMBER";
        pipe.getCreditCardRequestDTO().setNumber(invalidNumber);

        assertThrows(PaymentStepException.class, () -> getCreditCardStep.invoke(pipe), "PaymentStepException");
    }

    @Test
    public void testInvokeForCreditCardCreatePipeDTOShouldBeThrowExceptionWhenNumberNotCompanyValid() {
        CreditCardCreatePipeDTO pipe = CreditCardSamples.getCreditCardCreatePipeDTO();
        String numberNotCompany = "1234";
        pipe.getCreditCardRequestDTO().setNumber(numberNotCompany);

        assertThrows(PaymentStepException.class, () -> getCreditCardStep.invoke(pipe), "PaymentStepException");
    }

    @Test
    public void testInvokeForCreditCardCreatePipeDTOShouldBeThrowExceptionWhenInvalidMonth() {
        CreditCardCreatePipeDTO pipe = CreditCardSamples.getCreditCardCreatePipeDTO();
        pipe.getCreditCardRequestDTO().setExpirationMonth("NO");

        assertThrows(PaymentStepException.class, () -> getCreditCardStep.invoke(pipe), "");
    }

    @Test
    public void testInvokeForCreditCardCreatePipeDTOShouldBeThrowExceptionWhenInvalidYear() {
        CreditCardCreatePipeDTO pipe = CreditCardSamples.getCreditCardCreatePipeDTO();
        pipe.getCreditCardRequestDTO().setExpirationYear("NONO");

        assertThrows(PaymentStepException.class, () -> getCreditCardStep.invoke(pipe), "PaymentStepException");
    }

    @Test
    public void testInvokeForCreditCardCreatePipeDTOShouldBeThrowExceptionWhenExpired() {
        CreditCardCreatePipeDTO pipe = CreditCardSamples.getCreditCardCreatePipeDTO();
        pipe.getCreditCardRequestDTO().setExpirationYear("1900");

        assertThrows(PaymentStepException.class, () -> getCreditCardStep.invoke(pipe), "PaymentStepException");
    }

    @Test
    public void testInvokeForCreditCardCreatePipeDTOShouldBeThrowBaseExceptionCompanyCreditCardIsNull() {
        CreditCardCreatePipeDTO pipe = CreditCardSamples.getCreditCardCreatePipeDTO();
        pipe.getCreditCardRequestDTO().setNumber("4111111111111111ADF");

        assertThrows(PaymentStepException.class, () -> getCreditCardStep.invoke(pipe), "PaymentStepException");
    }

    @Test
    public void testInvokeForCreditCardCreatePipeDTOShouldBeThrowExceptionWhenFetCreditCardTypeByNumber() {
        assertThrows(
            EntityNotFoundException.class,
            () -> getCreditCardStep.invoke(CreditCardSamples.getCreditCardCreatePipeDTO()),
            "EntityNotFoundException"
        );
    }

    @Test
    public void testInvokeForCreditCardDeletePipeDTOShouldBeOk() {
        when(
            creditCardsRepository.findByIdAndUserIdAndDeletedAt(
                anyLong(), anyLong(), nullable(LocalDateTime.class)
            )
        ).thenReturn(Optional.of(CreditCardSamples.getCreditCard(false)));

        assertAll(
            () -> getCreditCardStep.invoke(
                CreditCardSamples.getCreditCardDeletePipeDTO(false)
            )
        );
    }

    @Test
    public void testInvokeForCreditCardDeletePipeDTOShouldBeThrowException() {
        assertThrows(
            EntityNotFoundException.class,
            () -> getCreditCardStep.invoke(CreditCardSamples.getCreditCardDeletePipeDTO(false)),
            "EntityNotFoundException"
        );
    }

    @Test
    public void testInvokeNoImplementedPipeShouldBeOk() {
        assertThrows(
            PaymentStepException.class,
            () -> getCreditCardStep.invoke(new Object()),
            "PaymentStepException"
        );
    }

    @Test
    public void testInvokeForTransactionCreatePipeDTOShouldBeFail() {
        PaymentPipeDTO pipe = TransactionSamples.getTransactionCreatePipeDTO();
        pipe.getTransactionRequestDTO().getPaymentMethod().setCreditCardId(null);
        assertThrows(PaymentStepException.class, () -> getCreditCardStep.invoke(pipe), "PaymentStepException");
    }
}
