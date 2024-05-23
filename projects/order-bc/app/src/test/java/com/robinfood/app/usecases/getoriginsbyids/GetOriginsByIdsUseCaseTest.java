package com.robinfood.app.usecases.getoriginsbyids;

import com.robinfood.app.datamocks.entity.OriginEntityMock;
import com.robinfood.core.entities.OriginEntity;
import com.robinfood.repository.origin.IOriginRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyList;

@ExtendWith(MockitoExtension.class)
class GetOriginsByIdsUseCaseTest {

    @Mock
    private IOriginRepository originRepository;

    @InjectMocks
    private GetOriginsByIdsUseCase getOriginsByIdsUseCase;

    private final OriginEntity originEntity = OriginEntityMock.build();

    @Test
    void Test_Get_Origins_By_Ids_Use_Case_Success() {
        Mockito.when(originRepository.findAllById(anyList()))
                .thenReturn(List.of(originEntity));

        getOriginsByIdsUseCase.invoke(List.of(1L));

        Mockito.verify(originRepository).findAllById(anyList());
    }
}