package com.robinfood.repository.resolutions;

import com.robinfood.core.entities.InformationPosResolutionsResponseEntity;
import com.robinfood.repository.mocks.dtos.InformationPosResolutionDTOMocks;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.concurrent.CompletableFuture;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PosResolutionRepositoryTest {

    @Mock
    private PosResolutionDataSource posResolutionDataSource;

    @InjectMocks
    private PosResolutionRepository posResolutionRepository;

    @Test
    void test_GetPosResolution_Ok() {

        String token = "token";

        Short posId = 164;

        when(posResolutionDataSource.getInformationPosResolution(anyString(), anyLong()))
                .thenReturn(CompletableFuture.completedFuture(InformationPosResolutionDTOMocks.getDataDefault()));

        InformationPosResolutionsResponseEntity informationPosResolution =
                posResolutionRepository.getInformationPosResolution(
                        token,
                        Long.valueOf(posId)
                );

        verify(posResolutionDataSource, times(1))
                .getInformationPosResolution(anyString(), anyLong());

        Assertions.assertEquals(informationPosResolution.getPosId(), posId);
    }
}
