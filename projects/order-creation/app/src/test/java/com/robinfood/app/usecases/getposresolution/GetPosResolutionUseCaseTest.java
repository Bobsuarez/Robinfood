package com.robinfood.app.usecases.getposresolution;

import com.robinfood.app.mocks.InformationPosResolutionDTOMocks;
import com.robinfood.app.mocks.TransactionRequestDTOMock;
import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import com.robinfood.core.exceptions.TransactionCreationException;
import com.robinfood.repository.resolutions.IPosResolutionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetPosResolutionUseCaseTest {

    @Mock
    private IPosResolutionRepository posResolutionRepository;

    @InjectMocks
    private GetPosResolutionUseCase getPosResolutionUseCase;

    private final TransactionRequestDTO transactionRequest = new TransactionRequestDTOMock().transactionRequestDTO;

    @Test
    void test_GetPosResolution_successfully() {

        String token = "token";

        when(posResolutionRepository.getInformationPosResolution(any(), any()))
                .thenReturn(InformationPosResolutionDTOMocks.getDataDefault());

        getPosResolutionUseCase.invoke(token, transactionRequest);

        verify(posResolutionRepository, times(1))
                .getInformationPosResolution(anyString(), anyLong());

        Assertions.assertNotNull(transactionRequest.getOrders().get(0).getPosResolution());
    }


    @Test
    void test_GetPosResolution_When_StoreNotFound_Should_ReturnTransactionException() {

        String token = "token";

        transactionRequest.getOrders()
                .get(0)
                .setStore(null);

        Assertions.assertThrows(TransactionCreationException.class, () ->
                getPosResolutionUseCase.invoke(token, transactionRequest)
        );
    }


    @Test
    void test_GetPosResolution_When_PosIdNotFound_Should_ReturnTransactionException() {

        String token = "token";

        transactionRequest.getOrders()
                .get(0)
                .getStore().setPosId(null);

        Assertions.assertThrows(TransactionCreationException.class, () ->
                getPosResolutionUseCase.invoke(token, transactionRequest)
        );
    }

    @Test
    void test_GetPosResolution_When_TokenNull_Should_ReturnException() {

        String token = null;

        transactionRequest.getOrders()
                .get(0)
                .getStore().setPosId(null);

        Assertions.assertThrows(NullPointerException.class, () ->
                getPosResolutionUseCase.invoke(token, transactionRequest)
        );
    }
    @Test
    void test_GetPosResolution_When_TransactionNull_Should_ReturnException() {

        String token = "token";
        TransactionRequestDTO transactionRequestNull = null;

        transactionRequest.getOrders()
                .get(0)
                .getStore().setPosId(null);

        Assertions.assertThrows(NullPointerException.class, () ->
                getPosResolutionUseCase.invoke(token, transactionRequestNull)
        );
    }
}