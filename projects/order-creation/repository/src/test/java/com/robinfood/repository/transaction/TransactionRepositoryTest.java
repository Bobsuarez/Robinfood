package com.robinfood.repository.transaction;

import com.robinfood.core.dtos.ConfigTransactionResponseDTO;
import com.robinfood.core.dtos.transactionrequestdto.FinalProductCategoryDTO;
import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import com.robinfood.core.dtos.transactionresponsedto.OrderResponseDTO;
import com.robinfood.core.dtos.transactionresponsedto.TransactionCreationResponseDTO;
import com.robinfood.core.dtos.transactionresponsedto.TransactionResponseDTO;
import com.robinfood.core.entities.ConfigTransactionResponseEntity;
import com.robinfood.core.entities.transactionrequestentities.TransactionRequestEntity;
import com.robinfood.core.entities.transactionresponseentities.OrderResponseEntity;
import com.robinfood.core.entities.transactionresponseentities.TransactionCreationResponseEntity;
import com.robinfood.core.entities.transactionresponseentities.TransactionResponseEntity;
import com.robinfood.core.models.domain.menu.Brand;
import com.robinfood.core.models.retrofit.order.pickuptime.PickupTimeModelResponseRest;
import com.robinfood.core.models.retrofit.order.pickuptime.PickupTimeModelRest;
import com.robinfood.repository.mocks.TransactionRequestDTOMocks;
import com.robinfood.repository.mocks.domain.PickupTimeMock;
import com.robinfood.repository.mocks.dtos.OrderResponseDTOMocks;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TransactionRepositoryTest {

    @Mock
    private TransactionLocalDataSource transactionLocalDataSource;

    @Mock
    private TransactionRemoteDataSource transactionRemoteDataSource;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private TransactionRepository transactionRepository;

    private final TransactionRequestDTOMocks transactionRequestDTOMocks = new TransactionRequestDTOMocks();

    private final TransactionRequestDTO transactionRequestDTO = transactionRequestDTOMocks.transactionRequestDTO;

    private final FinalProductCategoryDTO categoryDTO = transactionRequestDTOMocks.categoryDTO;

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

    private final List<OrderResponseEntity> orderResponseEntities = Collections.singletonList(
            new OrderResponseEntity(
                    BigDecimal.ZERO,
                    Collections.emptyList(),
                    1L,
                    "000-111",
                    "1501",
                    null,
                    BigDecimal.valueOf(10000.0),
                    BigDecimal.ZERO,
                    BigDecimal.valueOf(10000.0),
                    "uid",
                    "0ee82497-834d-4943-9f4c-5c61eb87f4fc"
            )
    );

    private final TransactionResponseEntity transactionResponseEntity = new TransactionResponseEntity(
            1L,
            "",
            orderResponseEntities,
            "uuid"
    );

    private final TransactionCreationResponseEntity transactionCreationResponseEntity = new TransactionCreationResponseEntity(
            transactionResponseEntity
    );

    private final ConfigTransactionResponseEntity configTransactionResponseEntity = new ConfigTransactionResponseEntity(
            TransactionResponseEntity.builder()
                    .id(1L)
                    .orders(Arrays.asList(
                            OrderResponseEntity.builder()
                                    .id(1L)
                                    .build(),
                            OrderResponseEntity.builder()
                                    .id(2L)
                                    .build()
                    ))
                    .uuid("1234")
                    .build()
    );

    private final ConfigTransactionResponseDTO configTransactionResponseDTO = new ConfigTransactionResponseDTO(
            TransactionCreationResponseDTO.builder()
                    .transaction(TransactionResponseDTO.builder()
                            .id(1L)
                            .orders(Arrays.asList(
                                    OrderResponseDTO.builder()
                                            .id(1L)
                                            .build(),
                                    OrderResponseDTO.builder()
                                            .id(2L)
                                            .build()
                            ))
                            .build())
                    .build()
    );

    @Test
    void test_createTransaction_Should_CompletableFutureTransactionCreationOrder_When_ReceivedValidParams() {
        String token = "token";

        transactionRequestDTO.getOrders().get(0).getFinalProducts().get(0).setCategory(categoryDTO);

        when(transactionRemoteDataSource.createTransaction(anyString(),
                any(TransactionRequestEntity.class))).thenReturn(
                CompletableFuture.completedFuture(transactionCreationResponseEntity));

        when(modelMapper.map(any(), any())).thenReturn(transactionCreationResponseDTO);

        CompletableFuture<TransactionCreationResponseDTO> result =
                transactionRepository.createTransaction(
                        token,
                        Collections.singletonList(Brand.builder()
                                .id(1L)
                                .franchiseId(1L)
                                .build()),
                        transactionRequestDTO
                );

        assertThat(result).isNotNull();
        verify(transactionRemoteDataSource, times(1)).createTransaction(anyString(),
                any(TransactionRequestEntity.class));
        verify(modelMapper, times(1)).map(any(), any());
    }

    @Test
    void test_bran_order_id_nine() {
        String token = "token";

        transactionRequestDTO.getOrders().get(0).getFinalProducts().get(0).setCategory(categoryDTO);
        transactionRequestDTO.getOrders().get(0).getBrand().setId(9L);

        when(transactionRemoteDataSource.createTransaction(anyString(),
                any(TransactionRequestEntity.class))).thenReturn(
                CompletableFuture.completedFuture(transactionCreationResponseEntity));

        when(modelMapper.map(any(), any())).thenReturn(transactionCreationResponseDTO);

        CompletableFuture<TransactionCreationResponseDTO> result =
                transactionRepository.createTransaction(
                        token,
                        Collections.singletonList(Brand.builder()
                                .id(9L)
                                .franchiseId(1L)
                                .build()),
                        transactionRequestDTO
                );

        assertThat(result).isNotNull();
        verify(transactionRemoteDataSource, times(1)).createTransaction(anyString(),
                any(TransactionRequestEntity.class));
        verify(modelMapper, times(1)).map(any(), any());


    }

    @Test
    void test_getTransactionOrderIds_Should_ConfigTransactionResponse_When_ProcessedCorrectly() {

        when(transactionLocalDataSource.getTransactionResponse())
                .thenReturn(configTransactionResponseEntity);

        when(modelMapper.map(any(), any())).thenReturn(transactionResponseDTO);

        ConfigTransactionResponseDTO result = transactionRepository.getTransactionResponseDTO();

        assertEquals(transactionResponseDTO.getOrders().get(0).getId(), result.getOrderIds().get(0));
    }

    @Test
    void test_setTransactionOrderIds_Should_Void_When_ReceivedValidParams() {

        doNothing().when(transactionLocalDataSource).setTransactionResponse(any());

        when(modelMapper.map(any(), any())).thenReturn(configTransactionResponseEntity);

        transactionRepository.setTransactionResponseDTO(configTransactionResponseDTO);

        verify(transactionLocalDataSource, times(1)).setTransactionResponse(any());
        verify(modelMapper, times(1)).map(any(), any());
    }

    @Test
    void give_token_and_pickup_time_then_return_pickup_time_saved() {
        // Arrange
        var token = "token";
        var pickupTime = PickupTimeMock.build();
        var transactionId = 1L;

        when(modelMapper.map(pickupTime, PickupTimeModelRest.class)).thenReturn(
                PickupTimeModelRest.builder().build());

        when(transactionRemoteDataSource.savePickupTime(anyString(), any())).thenReturn(
                Collections.singletonList(
                        PickupTimeModelResponseRest.builder()
                                .id(1L)
                                .build()
                )
        );

        // Act
        var response = transactionRepository.savePickupTime(
                token,
                pickupTime,
                transactionId
        );

        // Assert
        assertNotNull(response);
        assertEquals(transactionId, response.get(0));
    }
}
