package com.robinfood.usecases.updateresolutionsbyresolutionid;

import com.robinfood.dtos.v1.request.ResolutionUpdateDTO;
import com.robinfood.entities.db.mysql.PosResolutionEntity;
import com.robinfood.mocks.dtos.v1.request.ResolutionDTOMock;
import com.robinfood.mocks.entities.PosResolutionEntityMock;
import com.robinfood.repository.posresolution.IPosResolutionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.when;

public class UpdateResolutionsByResolutionIdUseCaseTest {

    @Mock
    private IPosResolutionRepository posResolutionRepository;

    @InjectMocks
    private UpdateResolutionsByResolutionIdUseCase updateResolutionsByResolutionIdUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void test_GetInstance_When_Success() {

        UpdateResolutionsByResolutionIdUseCase useCaseInstanceSecond = UpdateResolutionsByResolutionIdUseCase.getInstance();
        UpdateResolutionsByResolutionIdUseCase useCaseInstanceThird = UpdateResolutionsByResolutionIdUseCase.getInstance();

        assertSame(useCaseInstanceSecond, useCaseInstanceThird);
    }

    @Test
    void test_Invoke_UpdateResolutionsByResolutionId_When_Success() {

        when(posResolutionRepository.findByResolutionId(anyLong())).thenReturn(PosResolutionEntityMock.build());

        updateResolutionsByResolutionIdUseCase.invoke(anyLong(), ResolutionDTOMock.buildResolutionUpdateDTO());
    }

    @Test
    void test_UpdateResolutionsByResolutionId_When_There_Is_An_Active_Resolution() {

        ResolutionUpdateDTO resolution = ResolutionDTOMock.buildResolutionUpdateDTO();
        resolution.setStatus(Boolean.TRUE);

        when(posResolutionRepository.findByResolutionId(anyLong())).thenReturn(PosResolutionEntityMock.build());
        when(posResolutionRepository.existsByPosIdAndStatus(anyLong(), anyBoolean())).thenReturn(Boolean.TRUE);
        when(posResolutionRepository.findByPosIdAndStatus(anyLong(), anyBoolean())).thenReturn(PosResolutionEntityMock.build());

        assertThrows(Exception.class, () -> updateResolutionsByResolutionIdUseCase.invoke(anyLong(), resolution));
    }

    @Test
    void test_UpdateResolutionsByResolutionId_When_PosId_Is_False_Resolution() {

        ResolutionUpdateDTO resolution = ResolutionDTOMock.buildResolutionUpdateDTO();
        resolution.setStatus(Boolean.TRUE);

        when(posResolutionRepository.findByResolutionId(anyLong())).thenReturn(PosResolutionEntityMock.build());
        when(posResolutionRepository.existsByPosIdAndStatus(anyLong(), anyBoolean())).thenReturn(Boolean.FALSE);
        when(posResolutionRepository.findByPosIdAndStatus(anyLong(), anyBoolean())).thenReturn(PosResolutionEntityMock.build());

        updateResolutionsByResolutionIdUseCase.invoke(anyLong(),resolution);
    }

    @Test
    void test_Invoke_When_Status_Is_True_And_Confirmed_True() {

        ResolutionUpdateDTO resolution = ResolutionDTOMock.buildResolutionUpdateDTO();
        resolution.setStatus(Boolean.TRUE);
        resolution.setConfirmed(Boolean.TRUE);

        when(posResolutionRepository.findByResolutionId(anyLong())).thenReturn(PosResolutionEntityMock.build());
        when(posResolutionRepository.existsByPosIdAndStatus(anyLong(), anyBoolean())).thenReturn(Boolean.TRUE);
        when(posResolutionRepository.findByPosIdAndStatus(anyLong(), anyBoolean())).thenReturn(PosResolutionEntityMock.build());

        updateResolutionsByResolutionIdUseCase.invoke(anyLong(),resolution);
    }

    @Test
    void test_UpdateResolutionsByResolutionId_When_Status_Is_True_And_Confirmed_True() {

        ResolutionUpdateDTO resolution = ResolutionDTOMock.buildResolutionUpdateDTO();
        resolution.setConfirmed(Boolean.TRUE);
        resolution.setStatus(Boolean.TRUE);

        when(posResolutionRepository.findByResolutionId(anyLong())).thenReturn(PosResolutionEntityMock.build());
        when(posResolutionRepository.existsByPosIdAndStatus(anyLong(), anyBoolean())).thenReturn(Boolean.TRUE);
        when(posResolutionRepository.findByPosIdAndStatus(anyLong(), anyBoolean())).thenReturn(new PosResolutionEntity());

        updateResolutionsByResolutionIdUseCase.invoke(anyLong(),resolution);
    }

    @Test
    void test_UpdateResolutionsByResolutionId_When_PosId_Does_Not_Exist() {

        ResolutionUpdateDTO resolution = ResolutionDTOMock.buildResolutionUpdateDTO();
        resolution.setStatus(Boolean.TRUE);

        when(posResolutionRepository.findByResolutionId(anyLong())).thenReturn(PosResolutionEntityMock.build());
        when(posResolutionRepository.existsByPosIdAndStatus(anyLong(), anyBoolean())).thenReturn(Boolean.FALSE);

        assertDoesNotThrow(() -> updateResolutionsByResolutionIdUseCase.invoke(anyLong(), resolution));
    }

    @Test
    void test_UpdateResolutionsByResolutionId_When_Confirmed_Is_True_Status_Is_True_AND_Pos_Not_Exist() {

        ResolutionUpdateDTO resolution = ResolutionDTOMock.buildResolutionUpdateDTO();
        resolution.setConfirmed(Boolean.TRUE);
        resolution.setStatus(Boolean.TRUE);

        when(posResolutionRepository.findByResolutionId(anyLong())).thenReturn(PosResolutionEntityMock.build());
        when(posResolutionRepository.existsByPosIdAndStatus(anyLong(), anyBoolean())).thenReturn(Boolean.FALSE);
        when(posResolutionRepository.findByPosIdAndStatus(anyLong(), anyBoolean())).thenReturn(new PosResolutionEntity());

        updateResolutionsByResolutionIdUseCase.invoke(anyLong(),resolution);
    }

    @Test
    void test_UpdateResolutionsByResolutionId_When_Confirmed_Is_False_Status_Is_False_AND_Pos_Exist() {

        ResolutionUpdateDTO resolution = ResolutionDTOMock.buildResolutionUpdateDTO();
        resolution.setConfirmed(Boolean.FALSE);
        resolution.setStatus(Boolean.FALSE);

        when(posResolutionRepository.findByResolutionId(anyLong())).thenReturn(PosResolutionEntityMock.build());
        when(posResolutionRepository.existsByPosIdAndStatus(anyLong(), anyBoolean())).thenReturn(Boolean.TRUE);
        when(posResolutionRepository.findByPosIdAndStatus(anyLong(), anyBoolean())).thenReturn(new PosResolutionEntity());

        updateResolutionsByResolutionIdUseCase.invoke(anyLong(),resolution);
    }

    @Test
    void test_UpdateResolutionsByResolutionId_When_Confirmed_Is_True_Status_Is_False_AND_Pos_Exist() {

        ResolutionUpdateDTO resolution = ResolutionDTOMock.buildResolutionUpdateDTO();
        resolution.setConfirmed(Boolean.TRUE);
        resolution.setStatus(Boolean.FALSE);

        when(posResolutionRepository.findByResolutionId(anyLong())).thenReturn(PosResolutionEntityMock.build());
        when(posResolutionRepository.existsByPosIdAndStatus(anyLong(), anyBoolean())).thenReturn(Boolean.TRUE);
        when(posResolutionRepository.findByPosIdAndStatus(anyLong(), anyBoolean())).thenReturn(new PosResolutionEntity());

        updateResolutionsByResolutionIdUseCase.invoke(anyLong(),resolution);
    }

}
