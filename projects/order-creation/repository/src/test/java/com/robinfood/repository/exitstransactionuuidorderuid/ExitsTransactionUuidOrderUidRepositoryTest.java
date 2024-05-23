package com.robinfood.repository.exitstransactionuuidorderuid;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.robinfood.core.dtos.ExistsTransactionUuidOrderUuidDTO;
import com.robinfood.core.entities.ExistsTransactionUuidOrderUuidEntity;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;


@ExtendWith(MockitoExtension.class)
class ExitsTransactionUuidOrderUidRepositoryTest {


    @Mock
    private IExitsTransactionUuidOrderUuidDataSource exitsTransactionUuidOrderUidDataSource;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private ExitsTransactionUuidOrderUuidRepository exitsTransactionUuidOrderUidRepository;

    @Test
    void test_ExitsTransactionUuidOrderUidRepository_happy_path() {

        when(exitsTransactionUuidOrderUidDataSource.invoke(anyString(), anyString(), anyList())).thenReturn(
                ExistsTransactionUuidOrderUuidEntity.builder().build()
        );

        when(modelMapper.map(any(), any())).thenReturn(
                ExistsTransactionUuidOrderUuidDTO.builder()
                        .message("Exits uuid")
                        .exits(true)
                        .build());

        ExistsTransactionUuidOrderUuidDTO existsTransactionUuidOrderUuidDTOResult =
                exitsTransactionUuidOrderUidRepository.invoke("token", UUID.randomUUID().toString(),
                        List.of("yyydd1", "eeeddd2"));

        assertNotNull(existsTransactionUuidOrderUuidDTOResult);
    }
}
