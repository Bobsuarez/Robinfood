package com.robinfood.usecases.updateresolutionsbyposid;

import com.robinfood.mocks.entities.PosResolutionEntityMock;
import com.robinfood.repository.posresolution.IPosResolutionRepository;
import com.robinfood.usecases.updateresolutionsbyresolutionid.UpdateResolutionsByResolutionIdUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

public class UpdateResolutionsByPosIdUseCaseTest {

    @Mock
    private IPosResolutionRepository posResolutionRepository;

    @InjectMocks
    private UpdateResolutionsByPosIdUseCase updateResolutionsByPosIdUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void test_GetInstance_When_Success() {

        UpdateResolutionsByPosIdUseCase useCaseInstanceSecond = UpdateResolutionsByPosIdUseCase.getInstance();
        UpdateResolutionsByPosIdUseCase useCaseInstanceThird = UpdateResolutionsByPosIdUseCase.getInstance();

        assertSame(useCaseInstanceSecond, useCaseInstanceThird);
    }

    @Test
    void test_Invoke_UpdateResolutionsByResolutionId_When_Success() {

        when(posResolutionRepository.findById(anyLong())).thenReturn(PosResolutionEntityMock.build());

        updateResolutionsByPosIdUseCase.invoke(1L, 1L);
    }
}
