package com.robinfood.repository.changestatusorders;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.robinfood.core.dtos.changestatusordersrequestdto.ChangeStatusOrdersRequestDTO;
import com.robinfood.core.entities.changestatusordersrequestentities.ChangeStatusOrdersRequestEntity;
import com.robinfood.repository.mocks.ChangeStatusOrdersRequestDTOMocks;
import com.robinfood.repository.mocks.ChangeStatusOrdersRequestEntityMocks;
import java.util.concurrent.CompletableFuture;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

@ExtendWith(MockitoExtension.class)
class ChangeStatusOrdersRepositoryTest {

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private IChangeStatusOrdersRemoteDataSource mockChangeStatusOrdersRemoteDataSource;

    @InjectMocks
    private ChangeStatusOrdersRepository changeStatusOrdersRepository;

    final String token = "token";

    final ChangeStatusOrdersRequestEntity changeStatusOrdersRequestEntity = new ChangeStatusOrdersRequestEntityMocks()
            .changeStatusOrdersRequestEntity;

    final ChangeStatusOrdersRequestDTO changeStatusOrdersRequestDTO = new ChangeStatusOrdersRequestDTOMocks()
            .changeStatusOrdersRequestDTO;

    @Test
    void test_ChangeStatus_Orders() {

        when(mockChangeStatusOrdersRemoteDataSource.invoke(changeStatusOrdersRequestEntity, token))
                .thenReturn(CompletableFuture.completedFuture(true));

        when(modelMapper.map(any(),any())).thenReturn(changeStatusOrdersRequestEntity);

        final Boolean result = changeStatusOrdersRepository.invoke(changeStatusOrdersRequestDTO, token).join();

        assertTrue(result);
    }
}
