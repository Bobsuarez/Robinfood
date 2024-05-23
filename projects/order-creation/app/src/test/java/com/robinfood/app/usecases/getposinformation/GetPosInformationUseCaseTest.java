package com.robinfood.app.usecases.getposinformation;

import com.robinfood.app.mocks.TransactionRequestDTOMock;
import com.robinfood.app.mocks.domain.configuration.StoreMock;
import com.robinfood.app.usecases.inputrequestvalidation.services.ValidateGiftCardsService;
import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import com.robinfood.core.enums.TransactionCreationResult;
import com.robinfood.core.models.domain.configuration.Store;
import com.robinfood.repository.configurationsbc.IConfigurationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetPosInformationUseCaseTest {

    final String token = "token";

    @Mock
    private IConfigurationRepository configurationRepository;

    @Mock
    private ValidateGiftCardsService validateGiftCardsService;

    @InjectMocks
    private GetPosInformationUseCase getPosInformationUseCase;

    @Test
    void given_request_then_return_pos_id_with_pos_type_two() {

        // Arrange
        TransactionRequestDTO transactionRequestDTO = new TransactionRequestDTOMock().transactionRequestDTO;
        transactionRequestDTO.getOrders().get(0).getStore().setPosId(null);

        ArgumentCaptor<Store> storeArgumentCaptor = ArgumentCaptor.forClass(Store.class);

        when(
                configurationRepository.getPosIdByStoreIdAndPaymentMethodIds(anyString(), any())
        ).thenReturn(StoreMock.build());

        // Act
        getPosInformationUseCase.invoke(token, transactionRequestDTO);

        // Assert
        verify(configurationRepository).getPosIdByStoreIdAndPaymentMethodIds(anyString(), storeArgumentCaptor.capture());

        assertEquals(2L, storeArgumentCaptor.getValue().getPosTypeId());
    }

    @Test
    void given_request_then_return_pos_id_with_pos_type_one() {
        // Arrange
        TransactionRequestDTO transactionRequestDTO = new TransactionRequestDTOMock().transactionRequestDTO;
        transactionRequestDTO.getOrders().get(0).getStore().setPosId(null);
        transactionRequestDTO.getOrders().get(0).setInvoiceNumber("123213132");

        ArgumentCaptor<Store> storeArgumentCaptor = ArgumentCaptor.forClass(Store.class);

        when(configurationRepository.getPosIdByStoreIdAndPaymentMethodIds(anyString(), any())
        ).thenReturn(StoreMock.build());

        // Act
        getPosInformationUseCase.invoke(token, transactionRequestDTO);

        // Assert
        verify(configurationRepository)
                .getPosIdByStoreIdAndPaymentMethodIds(anyString(), storeArgumentCaptor.capture());
        assertEquals(1L, storeArgumentCaptor.getValue().getPosTypeId());
    }

    @Test
    void given_request_with_order_then_it_do_nothing() {

        // Arrange
        TransactionRequestDTO transactionRequestDTO = new TransactionRequestDTOMock().transactionRequestDTO;

        // Act
        getPosInformationUseCase.invoke(token, transactionRequestDTO);

        // Assert
        verify(configurationRepository, times(0)).getPosIdByStoreIdAndPaymentMethodIds(anyString(), any());
    }

    @Test
    void given_request_not_paid() {

        // Arrange
        TransactionRequestDTO transactionRequestDTO = new TransactionRequestDTOMock().transactionRequestDTO;
        transactionRequestDTO.setPaid(Boolean.FALSE);

        // Act
        TransactionCreationResult result = getPosInformationUseCase.invoke(token, transactionRequestDTO).join();

        // Assert
        assertEquals(TransactionCreationResult.StepValidationSuccess.INSTANCE, result);
    }

    @Test
    void test_Params_Is_Null() {
        assertThrows(
                NullPointerException.class,
                () -> getPosInformationUseCase.invoke(null, null)
        );
    }
}
