package com.robinfood.app.usecases.createtransaction;

import com.robinfood.app.mocks.OrderResponseDTOMocks;
import com.robinfood.app.mocks.TransactionRequestDTOMock;
import com.robinfood.app.usecases.getbrandsbycountryid.IGetBrandsByCountryIdUseCase;
import com.robinfood.app.usecases.setordersmultibrand.ISetOrdersMultiBrandUseCase;
import com.robinfood.core.dtos.transactionrequestdto.OrderDTO;
import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import com.robinfood.core.dtos.transactionresponsedto.OrderResponseDTO;
import com.robinfood.core.dtos.transactionresponsedto.TransactionCreationResponseDTO;
import com.robinfood.core.dtos.transactionresponsedto.TransactionResponseDTO;
import com.robinfood.core.enums.TransactionCreationResult;
import com.robinfood.repository.transaction.ITransactionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateTransactionUseCaseTest {

    private final TransactionRequestDTOMock transactionRequestDTOMocks = new TransactionRequestDTOMock();

    private final TransactionRequestDTO transactionRequestDTO = transactionRequestDTOMocks.transactionRequestDTO;

    private final List<OrderResponseDTO> orderResponseDTOs = Collections.singletonList(
            OrderResponseDTOMocks.build()
    );

    private final TransactionResponseDTO transactionResponseDTO = new TransactionResponseDTO(
            1L,
            "",
            orderResponseDTOs,
            "uuid"
    );

    private final TransactionCreationResponseDTO transactionCreationResponseDTO = new TransactionCreationResponseDTO(
            transactionResponseDTO
    );

    @Mock
    private IGetBrandsByCountryIdUseCase getBrandsByCountryIdUseCase;

    @Mock
    private ITransactionRepository mockTransactionRepository;

    @Mock
    private ISetOrdersMultiBrandUseCase setOrdersMultiBrandUseCase;

    @InjectMocks
    private CreateTransactionUseCase createTransactionUseCase;

    @Test
    void test_OrderCreation_Happy_Path() throws Exception {

        // Arrange
        var token = "token";

        ArgumentCaptor<TransactionRequestDTO> requestCaptor = ArgumentCaptor.forClass(TransactionRequestDTO.class);

        when(mockTransactionRepository.createTransaction(
                anyString(),
                anyList(),
                any()
        )).thenReturn(CompletableFuture.completedFuture(transactionCreationResponseDTO));

        // Act
        final CompletableFuture<TransactionCreationResult> result =
                createTransactionUseCase.invoke(token, transactionRequestDTO);

        // Assert
        Mockito.verify(mockTransactionRepository).createTransaction(
                anyString(), anyList(), requestCaptor.capture()
        );
        assertTrue(requestCaptor.getValue().getPaid());
        assertTrue(result.get() instanceof TransactionCreationResult.TransactionCreated);
    }

    @Test
    void test_OrderCreation_Happy_Path_With_PayU_Payment_Method() throws Exception {

        // Arrange
        var token = "token";

        ArgumentCaptor<TransactionRequestDTO> requestCaptor = ArgumentCaptor.forClass(TransactionRequestDTO.class);

        when(mockTransactionRepository.createTransaction(
                anyString(),
                anyList(),
                any()
        )).thenReturn(CompletableFuture.completedFuture(transactionCreationResponseDTO));

        // Act
        final CompletableFuture<TransactionCreationResult> result =
                createTransactionUseCase.invoke(token, transactionRequestDTO);

        // Assert
        Mockito.verify(mockTransactionRepository).createTransaction(
                anyString(), anyList(), requestCaptor.capture()
        );
        assertTrue(requestCaptor.getValue().getPaid());
        assertTrue(result.get() instanceof TransactionCreationResult.TransactionCreated);
    }

    @Test
    void test_OrderCreation_Happy_Path_Without_Order_List_And_Without_Showing_Log_Order_Accepted() throws Exception {

        // Arrange
        var token = "token";


        ArgumentCaptor<TransactionRequestDTO> requestCaptor = ArgumentCaptor.forClass(TransactionRequestDTO.class);

        transactionRequestDTO.setId(1L);
        transactionRequestDTO.setOrders(Arrays.asList(OrderDTO.builder().build()));
        when(mockTransactionRepository.createTransaction(
                anyString(),
                anyList(),
                any()
        )).thenReturn(CompletableFuture.completedFuture(transactionCreationResponseDTO));

        // Act
        final CompletableFuture<TransactionCreationResult> result =
                createTransactionUseCase.invoke(token, transactionRequestDTO);

        // Assert
        Mockito.verify(mockTransactionRepository).createTransaction(
                anyString(), anyList(), requestCaptor.capture()
        );
        assertTrue(requestCaptor.getValue().getPaid());
        assertTrue(result.get() instanceof TransactionCreationResult.TransactionCreated);
    }
}
