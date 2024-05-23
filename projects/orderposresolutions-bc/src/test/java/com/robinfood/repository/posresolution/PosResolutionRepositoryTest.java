package com.robinfood.repository.posresolution;

import com.robinfood.constants.GeneralConstants;
import com.robinfood.databases.DatabaseManager;
import com.robinfood.entities.db.mysql.PosResolutionEntity;
import com.robinfood.enums.OrderByEnum;
import com.robinfood.exceptions.BusinessRuleException;
import com.robinfood.mocks.entities.PosResolutionEntityMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class PosResolutionRepositoryTest {

    @Mock
    private DatabaseManager databaseManager;

    @InjectMocks
    private PosResolutionRepository posResolutionRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void test_GetInstance_Should_When_Success() {

        PosResolutionRepository repository = new PosResolutionRepository(databaseManager);
        PosResolutionRepository repositoryInstanceSecond = PosResolutionRepository.getInstance();
        PosResolutionRepository repositoryInstanceThird = PosResolutionRepository.getInstance();

        assertSame(repositoryInstanceSecond, repositoryInstanceThird);
        assertSame(databaseManager, repository.getDatabaseManager());
    }

    @Test
    void test_SaveAll_Should_When_List_Empty_Success() {

        final List<PosResolutionEntity> posResolutionEntities = new ArrayList<>();

        when(databaseManager.executeInsert(any(), any())).thenReturn(BigInteger.ONE);

        final List<BigInteger> ids = posResolutionRepository.saveAll(posResolutionEntities);

        assertEquals(posResolutionEntities.size(), ids.size());
        verify(databaseManager, times(posResolutionEntities.size())).executeInsert(any(), any());
    }

    @Test
    void test_SaveAll_Should_When_List_Not_Empty_Success() {

        final List<PosResolutionEntity> posResolutionEntities = List.of(PosResolutionEntityMock.build());

        when(databaseManager.executeInsert(any(), any())).thenReturn(BigInteger.ONE);

        final List<BigInteger> ids = posResolutionRepository.saveAll(posResolutionEntities);

        assertEquals(posResolutionEntities.size(), ids.size());
        verify(databaseManager, times(posResolutionEntities.size())).executeInsert(any(), any());
    }

    @Test
    void test_ExistsByPosIdAndStatus_Should_When_Is_False() {

        final Long posId = 1L;

        when(databaseManager.executeQuery(any(), any(), any())).thenReturn(new PosResolutionEntity());

        final boolean result = posResolutionRepository.existsByPosIdAndStatus(posId, true);

        assertFalse(result);
        verify(databaseManager).executeQuery(any(), any(), any());
    }

    @Test
    void test_ExistsByPosIdAndStatus_Should_When_Is_True() {

        final Long posId = 1L;

        when(databaseManager.executeQuery(any(), any(), any())).thenReturn(PosResolutionEntityMock.build());

        final boolean result = posResolutionRepository.existsByPosIdAndStatus(posId, true);

        assertTrue(result);
        verify(databaseManager).executeQuery(any(), any(), any());
    }

    @Test
    void test_FindByPosIdAndStatus_Should_When_Found() {

        final Long posId = 1L;

        when(databaseManager.executeQuery(any(), any(), any())).thenReturn(PosResolutionEntityMock.build());

        final PosResolutionEntity result = posResolutionRepository.findByPosIdAndStatus(posId, true);

        assertEquals(PosResolutionEntity.class, result.getClass());
        verify(databaseManager).executeQuery(any(), any(), any());
    }

    @Test
    void test_FindByPosIdAndStatus_Should_When_Not_Found() {

        final Long posId = 1L;

        when(databaseManager.executeQuery(any(), any(), any())).thenReturn(new PosResolutionEntity());

        final PosResolutionEntity result = posResolutionRepository.findByPosIdAndStatus(posId, true);

        assertEquals(PosResolutionEntity.class, result.getClass());
        verify(databaseManager).executeQuery(any(), any(), any());
    }

    @Test
    void test_UpdateCurrentAndDicStatusIdById_Should_When_Success() {

        when(databaseManager.executeInsert(any(), any())).thenReturn(BigInteger.ONE);

        final BigInteger result = posResolutionRepository.updateCurrentAndDicStatusIdById(PosResolutionEntityMock.build());

        assertEquals(BigInteger.ONE, result);
        verify(databaseManager).executeInsert(any(), any());
    }

    @Test
    void test_FindByResolutionId_When_Found() {

        final Long resolutionId = 1L;

        when(databaseManager.executeQuery(any(), any(), any())).thenReturn(PosResolutionEntityMock.build());

        final PosResolutionEntity result = posResolutionRepository.findByResolutionId(resolutionId);

        assertEquals(PosResolutionEntity.class, result.getClass());
        verify(databaseManager).executeQuery(any(), any(), any());
    }

    @Test
    void test_FindByResolutionId_When_Not_Found() {

        final Long resolutionId = 1L;

        when(databaseManager.executeQuery(any(), any(), any())).thenReturn(new PosResolutionEntity());

        final PosResolutionEntity result = posResolutionRepository.findByResolutionId(resolutionId);

        assertEquals(PosResolutionEntity.class, result.getClass());
        verify(databaseManager).executeQuery(any(), any(), any());
    }

    @Test
    void test_UpdateByResolutionId_When_Success() {

        when(databaseManager.executeInsert(any(), any())).thenReturn(BigInteger.ONE);

        final BigInteger result = posResolutionRepository.updateByResolutionId(PosResolutionEntityMock.build());

        assertEquals(BigInteger.ONE, result);
        verify(databaseManager).executeInsert(any(), any());
    }

    @Test
    void test_When_FindById_Should_Success() {

        when(databaseManager.executeQuery(any(), any(), any())).thenReturn(PosResolutionEntityMock.build());

        final PosResolutionEntity result =
                posResolutionRepository.findById(1L);

        assertEquals(PosResolutionEntityMock.build(), result);
        verify(databaseManager).executeQuery(any(), any(), any());
    }

    @Test
    void test_FindById_When_NotFound_Should_BusinessRuleException() {

        when(databaseManager.executeQuery(any(), any(), any())).thenReturn(null);

        assertThrows(BusinessRuleException.class, () -> posResolutionRepository.findById(1L));
        verify(databaseManager).executeQuery(any(), any(), any());
    }

    @Test
    void test_FindAllByPosIdAndStatus_When_DataOK_Should_EntityList() {

        when(databaseManager.executeQueryList(any(), any(), any())).thenReturn(List.of(PosResolutionEntityMock.build()));

        final List<PosResolutionEntity> result =
                posResolutionRepository.findAllByPosIdAndStatus(1L, GeneralConstants.DEFAULT_ONE_INTEGER);

        assertEquals(1, result.size());
        verify(databaseManager).executeQueryList(any(), any(), any());
    }

    @Test
    void test_CountAllResolutions_When_Not_Found() {

        final Long resolutionId = 1L;

        when(databaseManager.executeQueryCount(any(), any(), any())).thenReturn(0);

        final Integer count = posResolutionRepository.countSearchResolutions(null, Boolean.TRUE, "Resolution", null);

        assertEquals(Integer.class, count.getClass());
        verify(databaseManager).executeQueryCount(any(), any(), any());
    }

    @Test
    void test_CountAllResolutions_When_Found() {

        final Long resolutionId = 1L;

        when(databaseManager.executeQueryCount(any(), any(), any())).thenReturn(1);

        final Integer count = posResolutionRepository.countSearchResolutions(1L, null, "Resolution", null);

        assertEquals(Integer.class, count.getClass());
        verify(databaseManager).executeQueryCount(any(), any(), any());
    }

    @Test
    void test_FindAllResolution_When_DataOK_Should_EntityList() {

        when(databaseManager.executeQueryList(any(), any(), any())).thenReturn(List.of(PosResolutionEntityMock.build()));

        final List<PosResolutionEntity> result =
                posResolutionRepository.searchResolutions(Boolean.FALSE, OrderByEnum.ASC, 1, null,   10, null, true);

        assertEquals(1, result.size());
        verify(databaseManager).executeQueryList(any(), any(), any());
    }

    @Test
    void test_FindAllResolution_When_Parameters_IS_NULL_DataOK_Should_EntityList() {

        when(databaseManager.executeQueryList(any(), any(), any())).thenReturn(List.of(PosResolutionEntityMock.build()));

        final List<PosResolutionEntity> result =
                posResolutionRepository.searchResolutions(null, null, 1, null, 10,"", false);

        assertEquals(1, result.size());
        verify(databaseManager).executeQueryList(any(), any(), any());
    }

}
