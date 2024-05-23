package com.robinfood.usecases.saveresolutionsbystore;

import com.robinfood.dtos.v1.request.StoreResolutionDTO;
import com.robinfood.entities.db.mysql.PosResolutionEntity;
import com.robinfood.mocks.dtos.v1.request.StoreResolutionDTOMock;
import com.robinfood.mocks.entities.PosResolutionEntityMock;
import com.robinfood.repository.posresolution.IPosResolutionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigInteger;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.anyList;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.when;

class SaveResolutionsByStoreUseCaseTest {

    @Mock
    private IPosResolutionRepository posResolutionRepository;

    @InjectMocks
    private SaveResolutionsByStoreUseCase saveResolutionsByStoreUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void test_GetInstance_Should_When_Success() {

        SaveResolutionsByStoreUseCase useCaseInstanceSecond = SaveResolutionsByStoreUseCase.getInstance();
        SaveResolutionsByStoreUseCase useCaseInstanceThird = SaveResolutionsByStoreUseCase.getInstance();

        assertSame(useCaseInstanceSecond, useCaseInstanceThird);
    }

    @Test
    void test_Invoke_Should_When_Success() {

        final StoreResolutionDTO storeResolutionDTO = StoreResolutionDTOMock.build();
        final List<BigInteger> mockIds = List.of(BigInteger.ONE);

        when(posResolutionRepository.saveAll(anyList())).thenReturn(mockIds);
        when(posResolutionRepository.existsByPosIdAndStatus(anyLong(), anyBoolean())).thenReturn(Boolean.FALSE);

        saveResolutionsByStoreUseCase.invoke(storeResolutionDTO);
    }

    @Test
    void test_Invoke_With_Pos_Should_When_Success() {

        final StoreResolutionDTO storeResolutionDTO = StoreResolutionDTOMock.buildWithPosIdIsNull();
        final List<BigInteger> mockIds = List.of(BigInteger.ONE);

        when(posResolutionRepository.saveAll(anyList())).thenReturn(mockIds);
        when(posResolutionRepository.existsByPosIdAndStatus(anyLong(), anyBoolean())).thenReturn(Boolean.FALSE);

        saveResolutionsByStoreUseCase.invoke(storeResolutionDTO);
    }

    @Test
    void test_Invoke_Should_When_There_Is_An_Active_Resolution() {

        final int initialIndex = 0;

        final StoreResolutionDTO storeResolutionDTO = StoreResolutionDTOMock.build();
        storeResolutionDTO.getResolutions().get(initialIndex).setStatus(Boolean.TRUE);

        final List<BigInteger> mockIds = List.of(BigInteger.ONE);

        when(posResolutionRepository.saveAll(anyList())).thenReturn(mockIds);
        when(posResolutionRepository.existsByPosIdAndStatus(anyLong(), anyBoolean())).thenReturn(Boolean.TRUE);

        assertThrows(Exception.class, () -> saveResolutionsByStoreUseCase.invoke(storeResolutionDTO));
    }

    @Test
    void test_Invoke_Should_When_Status_Is_True_And_Confirmed_True() {

        final int initialIndex = 0;

        final StoreResolutionDTO storeResolutionDTO = StoreResolutionDTOMock.build();
        storeResolutionDTO.getResolutions().get(initialIndex).setConfirmed(Boolean.TRUE);
        storeResolutionDTO.getResolutions().get(initialIndex).setStatus(Boolean.TRUE);

        when(posResolutionRepository.existsByPosIdAndStatus(anyLong(), anyBoolean())).thenReturn(Boolean.TRUE);
        when(posResolutionRepository.findByPosIdAndStatus(anyLong(), anyBoolean())).thenReturn(PosResolutionEntityMock.build());

        saveResolutionsByStoreUseCase.invoke(storeResolutionDTO);
    }

    @Test
    void test_Invoke_Should_When_Status_Is_True_And_Confirmed_False() {

        final StoreResolutionDTO storeResolutionDTO = StoreResolutionDTOMock.build();
        storeResolutionDTO.getResolutions().get(0).setStatus(Boolean.TRUE);
        storeResolutionDTO.getResolutions().get(0).setConfirmed(Boolean.FALSE);
        when(posResolutionRepository.existsByPosIdAndStatus(anyLong(), anyBoolean())).thenReturn(Boolean.FALSE);
        when(posResolutionRepository.findByPosIdAndStatus(anyLong(), anyBoolean())).thenReturn(PosResolutionEntityMock.build());

        saveResolutionsByStoreUseCase.invoke(storeResolutionDTO);
    }

    @Test
    void test_Invoke_Should_When_Status_Is_False_And_Confirmed_True() {

        final StoreResolutionDTO storeResolutionDTO = StoreResolutionDTOMock.build();
        storeResolutionDTO.getResolutions().get(0).setStatus(Boolean.FALSE);
        storeResolutionDTO.getResolutions().get(0).setConfirmed(Boolean.TRUE);
        when(posResolutionRepository.existsByPosIdAndStatus(anyLong(), anyBoolean())).thenReturn(Boolean.FALSE);
        when(posResolutionRepository.findByPosIdAndStatus(anyLong(), anyBoolean())).thenReturn(PosResolutionEntityMock.build());

        saveResolutionsByStoreUseCase.invoke(storeResolutionDTO);
    }

    @Test
    void test_Invoke_Should_When_FindByPosIdAndStatus_Is_Empty() {

        final StoreResolutionDTO storeResolutionDTO = StoreResolutionDTOMock.build();

        when(posResolutionRepository.existsByPosIdAndStatus(anyLong(), anyBoolean())).thenReturn(Boolean.FALSE);
        when(posResolutionRepository.findByPosIdAndStatus(anyLong(), anyBoolean())).thenReturn(PosResolutionEntity.builder().build());

        saveResolutionsByStoreUseCase.invoke(storeResolutionDTO);
    }

}