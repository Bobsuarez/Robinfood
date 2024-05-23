package com.robinfood.paymentmethodsbc.services.impl;

import com.robinfood.paymentmethodsbc.dto.api.creditcards.CreateCreditCardRequestDTO;
import com.robinfood.paymentmethodsbc.dto.steps.CreditCardCreatePipeDTO;
import com.robinfood.paymentmethodsbc.dto.steps.CreditCardUserListPipeDTO;
import com.robinfood.paymentmethodsbc.exceptions.BaseException;
import com.robinfood.paymentmethodsbc.exceptions.EntityNotFoundException;
import com.robinfood.paymentmethodsbc.model.GeneralPaymentMethod;
import com.robinfood.paymentmethodsbc.repositories.GeneralPaymentMethodsRepository;
import com.robinfood.paymentmethodsbc.sample.CreditCardSamples;
import com.robinfood.paymentmethodsbc.sample.GeneralPaymentMethodSample;
import com.robinfood.paymentmethodsbc.security.CipherUtility;
import com.robinfood.paymentmethodsbc.utils.StepsControl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import java.util.Optional;

import static com.robinfood.paymentmethodsbc.sample.CreditCardSamples.getCreditCardList;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreditCardServiceImplTest {
    @Mock
    private GeneralPaymentMethodsRepository generalPaymentMethodsRepository;

    @Mock
    private CipherUtility cipherUtility;

    @InjectMocks
    private CreditCardServiceImpl creditCardService;

    @Test
    public void testFindAllByUserAndCountryShouldBeOk() {
        when(generalPaymentMethodsRepository.findByIdAndStatus(anyLong(), anyBoolean()))
            .thenReturn(Optional.of(GeneralPaymentMethodSample.getGeneralPaymentMethod()));

        try (MockedStatic<StepsControl> stepControlStaticMock = mockStatic(StepsControl.class)) {
            stepControlStaticMock.when(() -> StepsControl.validateSteps(any(), any()))
                .thenAnswer((Answer<Void>) invocation -> null);

            assertAll(
                () -> creditCardService.findAllByUserAndCountryAndPaymentMethodId(
                    1L,
                    1L,
                    1L
                )
            );
        }
    }

    @Test
    public void testFindAllByUserAndCountryShouldBeOkCreditCardListNonNull() {
        when(generalPaymentMethodsRepository.findByIdAndStatus(anyLong(), anyBoolean()))
            .thenReturn(Optional.of(GeneralPaymentMethodSample.getGeneralPaymentMethod()));

        try (MockedStatic<StepsControl> stepControlStaticMock = mockStatic(StepsControl.class)) {
            stepControlStaticMock.when(() -> StepsControl.validateSteps(any(), any()))
                .thenAnswer((Answer<Void>) invocation -> {
                    CreditCardUserListPipeDTO pipe = invocation.getArgument(0);
                    pipe.setCreditCardList(getCreditCardList());
                    return null;
                });

            assertAll(() -> creditCardService.findAllByUserAndCountryAndPaymentMethodId(
                1L,
                1L,
                1L
            ));
        }
    }

    @Test
    public void testFindAllByUserAndCountryShouldExceptionNotFoundException() {
        GeneralPaymentMethod generalPaymentMethod = GeneralPaymentMethodSample.getGeneralPaymentMethod();
        generalPaymentMethod.setPaymentGateway(null);
        when(generalPaymentMethodsRepository.findByIdAndStatus(anyLong(), anyBoolean()))
            .thenReturn(Optional.of(generalPaymentMethod));

        assertThrows(
            BaseException.class,
            () -> creditCardService.findAllByUserAndCountryAndPaymentMethodId(
                1L,
                1L,
                1L
            ),
            "BaseException"
        );
    }

    @Test
    public void testFindAllByUserAndCountryShoulduldEntityNotFoundException() {
        when(generalPaymentMethodsRepository.findByIdAndStatus(anyLong(), anyBoolean()))
            .thenReturn(Optional.ofNullable(null));

        assertThrows(
            EntityNotFoundException.class,
            () -> creditCardService.findAllByUserAndCountryAndPaymentMethodId(
                1L,
                1L,
                1L
            ),
            "EntityNotFoundException"
        );
    }

    @Test
    public void testCreateShouldBeErrorWhenNoCreditCardsTokenized() {
        CreditCardCreatePipeDTO pipe = CreditCardSamples.getCreditCardCreatePipeDTO();
        pipe.setGeneratedTokens(1);
        CreateCreditCardRequestDTO creditCardRequestDTO = CreditCardSamples.getCreditCardRequestDTO();

        when(cipherUtility.decryptTextWithPrivateKey(anyString())).thenReturn("XXXX");

        try (MockedStatic<StepsControl> stepControlStaticMock = mockStatic(StepsControl.class)) {
            stepControlStaticMock.when(() -> StepsControl.validateSteps(any(), any()))
                .thenAnswer((Answer<Void>) invocation -> null);

            assertThrows(
                BaseException.class,
                () -> creditCardService.create(creditCardRequestDTO),
                "BaseException"
            );
        }
    }

    @Test
    public void testCreateShouldBeErrorWhenDecryptionError() {
        CreditCardCreatePipeDTO pipe = CreditCardSamples.getCreditCardCreatePipeDTO();
        pipe.setGeneratedTokens(1);
        CreateCreditCardRequestDTO creditCardRequestDTO = CreditCardSamples.getCreditCardRequestDTO();

        when(cipherUtility.decryptTextWithPrivateKey(anyString())).thenReturn(null);

        assertThrows(
            RuntimeException.class,
            () -> creditCardService.create(creditCardRequestDTO),
            "RuntimeException"
        );
    }

    @Test
    public void testCreateShouldBeErrorWhenUserUserEmailsNull() {
        CreditCardCreatePipeDTO pipe = CreditCardSamples.getCreditCardCreatePipeDTO();

        CreateCreditCardRequestDTO creditCardRequestDTO = pipe.getCreditCardRequestDTO();
        creditCardRequestDTO.setUserEmail("email@example.com");
        when(cipherUtility.decryptTextWithPrivateKey(anyString())).thenReturn(null);

        try (MockedStatic<StepsControl> stepControlStaticMock = mockStatic(StepsControl.class)) {
            stepControlStaticMock.when(() -> StepsControl.validateSteps(any(), any()))
                .thenAnswer((Answer<Void>) invocation -> null);

            assertThrows(
                RuntimeException.class,
                () -> creditCardService.create(creditCardRequestDTO),
                "RuntimeException"
            );
        }
    }

    @Test
    public void testCreateShouldBeOk() {
        CreditCardCreatePipeDTO pipe = CreditCardSamples.getCreditCardCreatePipeDTO();

        CreateCreditCardRequestDTO creditCardRequestDTO = pipe.getCreditCardRequestDTO();
        creditCardRequestDTO.setUserEmail("email@example.com");
        when(cipherUtility.decryptTextWithPrivateKey(anyString())).thenReturn("xxxxxxx");

        try (MockedStatic<StepsControl> stepControlStaticMock = mockStatic(StepsControl.class)) {
            stepControlStaticMock.when(() -> StepsControl.validateSteps(any(), any()))
                .thenAnswer((Answer<Void>) invocation -> {
                    CreditCardCreatePipeDTO inPipe = invocation.getArgument(0);
                    inPipe.setGeneratedTokens(1);
                    return null;
                });

            assertAll(() -> creditCardService.create(creditCardRequestDTO));
        }
    }

    @Test
    public void testCreateShouldBeOkWithParamsBlank() {
        CreditCardCreatePipeDTO pipe = CreditCardSamples.getCreditCardCreatePipeDTO();

        CreateCreditCardRequestDTO creditCardRequestDTO = pipe.getCreditCardRequestDTO();
        creditCardRequestDTO.setUserEmail("email@example.com");
        creditCardRequestDTO.setCardVerificationCode(null);
        creditCardRequestDTO.setUserIdentificationNumber(null);
        creditCardRequestDTO.setUserId(null);
        when(cipherUtility.decryptTextWithPrivateKey(anyString())).thenReturn("xxxxxxx");

        try (MockedStatic<StepsControl> stepControlStaticMock = mockStatic(StepsControl.class)) {
            stepControlStaticMock.when(() -> StepsControl.validateSteps(any(), any()))
                .thenAnswer((Answer<Void>) invocation -> {
                    CreditCardCreatePipeDTO inPipe = invocation.getArgument(0);
                    inPipe.setGeneratedTokens(1);
                    return null;
                });

            assertAll(() -> creditCardService.create(creditCardRequestDTO));
        }
    }

    @Test
    public void testCreateShouldBeOkWithUserIdNull() {
        CreditCardCreatePipeDTO pipe = CreditCardSamples.getCreditCardCreatePipeDTO();

        CreateCreditCardRequestDTO creditCardRequestDTO = pipe.getCreditCardRequestDTO();
        creditCardRequestDTO.setUserEmail("email@example.com");
        creditCardRequestDTO.setCardVerificationCode(null);
        creditCardRequestDTO.setUserIdentificationNumber(null);
        creditCardRequestDTO.setUserId(null);
        when(cipherUtility.decryptTextWithPrivateKey(anyString())).thenReturn("xxxxxxx");

        try (MockedStatic<StepsControl> stepControlStaticMock = mockStatic(StepsControl.class)) {
            stepControlStaticMock.when(() -> StepsControl.validateSteps(any(), any()))
                .thenAnswer((Answer<Void>) invocation -> {
                    CreditCardCreatePipeDTO inPipe = invocation.getArgument(0);
                    inPipe.setGeneratedTokens(1);
                    return null;
                });

            assertAll(() -> creditCardService.create(creditCardRequestDTO));
        }
    }

    @Test
    public void testDeleteShouldBeOk() throws BaseException, EntityNotFoundException {
        try (MockedStatic<StepsControl> stepControlStaticMock = mockStatic(StepsControl.class)) {
            stepControlStaticMock.when(() -> StepsControl.validateSteps(any(), any()))
                .thenAnswer((Answer<Void>) invocation -> null);

            assertAll(() -> creditCardService.delete(1L, 1L));
        }
    }

    @Test
    public void testCreateShouldBeErrorWhenUserIdNumberIsNull() throws BaseException, EntityNotFoundException {
        CreditCardCreatePipeDTO pipe = CreditCardSamples.getCreditCardCreatePipeDTO();

        CreateCreditCardRequestDTO creditCardRequestDTO = pipe.getCreditCardRequestDTO();
        creditCardRequestDTO.setUserIdentificationNumber(null);
        when(cipherUtility.decryptTextWithPrivateKey(anyString())).thenReturn("");

        try (MockedStatic<StepsControl> stepControlStaticMock = mockStatic(StepsControl.class)) {
            stepControlStaticMock.when(() -> StepsControl.validateSteps(any(), any()))
                .thenAnswer((Answer<Void>) invocation -> null);

            assertThrows(
                BaseException.class,
                () -> creditCardService.create(creditCardRequestDTO),
                "BaseException"
            );
        }
    }

    @Test
    public void testUpdateShouldBeOk() throws BaseException, EntityNotFoundException {
        try (MockedStatic<StepsControl> stepControlStaticMock = mockStatic(StepsControl.class)) {
            stepControlStaticMock.when(() -> StepsControl.validateSteps(any(), any()))
                .thenAnswer((Answer<Void>) invocation -> null);

            assertAll(() -> creditCardService.update(
                1L,
                1L ,
                CreditCardSamples.getUpdateCreditCardRequestDTO()
            ));
        }
    }
}
