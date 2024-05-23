package com.robinfood.configurations.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.robinfood.configurations.dto.v1.ActivateOrDeactivateDTOMock;
import com.robinfood.configurations.dto.v1.ResponseResolutionsWithPosDTO;
import com.robinfood.configurations.dto.v1.StoreResolutionDTO;
import com.robinfood.configurations.dto.v1.StoreResolutionDTOMock;
import com.robinfood.configurations.exceptions.BusinessRuleException;
import com.robinfood.configurations.models.Resolution;
import com.robinfood.configurations.models.ResolutionMock;
import com.robinfood.configurations.repositories.jpa.ResolutionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StoreResolutionServiceImplTest {

    @Mock
    private ResolutionRepository resolutionRepository;

    @InjectMocks
    private StoreResolutionServiceImpl storeResolutionService;

    @Test
    void test_Created_Should_Success() {

        StoreResolutionDTO storeResolutionDTO = StoreResolutionDTOMock.build();
        storeResolutionDTO.setEndDate(null);
        storeResolutionDTO.setStartDate(null);

        Resolution resolution = ResolutionMock.build();
        resolution.setEndDate(null);
        resolution.setStartDate(null);
        resolution.setStatus(0);

        when(resolutionRepository.saveAll(anyList())).thenReturn(List.of(resolution));

        final List<ResponseResolutionsWithPosDTO> response = storeResolutionService.create(List.of(storeResolutionDTO));

        assertNotNull(response);
    }

    @Test
    void test_Created_Should_When_Status_Is_True_And_Confirmed_False() {

        when(resolutionRepository.existsByPosIdAndStatus(1L, 1)).thenReturn(true);

        StoreResolutionDTO storeResolutionDTO = StoreResolutionDTOMock.build();
        storeResolutionDTO.setConfirmed(false);
        storeResolutionDTO.setStatus(true);

        assertThrows(BusinessRuleException.class, () -> storeResolutionService.create(List.of(storeResolutionDTO)));
    }

    @Test
    void test_Created_Should_When_Status_Is_True_And_Confirmed_True() {

        when(resolutionRepository.existsByPosIdAndStatus(1L, 1)).thenReturn(true);
        when(resolutionRepository.findByPosIdAndStatus(1L, 1)).thenReturn(Optional.of(ResolutionMock.build()));

        StoreResolutionDTO storeResolutionDTO = StoreResolutionDTOMock.build();
        storeResolutionDTO.setConfirmed(true);
        storeResolutionDTO.setStatus(true);

        final List<ResponseResolutionsWithPosDTO> response = storeResolutionService.create(List.of(storeResolutionDTO));

        assertNotNull(response);
    }

    @Test
    void test_Created_Should_When_ExistsByPosIdAndStatus_Is_False() {

        when(resolutionRepository.existsByPosIdAndStatus(1L, 1)).thenReturn(false);
        when(resolutionRepository.findByPosIdAndStatus(1L, 1)).thenReturn(Optional.of(ResolutionMock.build()));

        final List<ResponseResolutionsWithPosDTO> response =
                storeResolutionService.create(List.of(StoreResolutionDTOMock.build()));

        assertNotNull(response);
    }

    @Test
    void test_Created_Should_When_FindByPosIdAndStatus_Is_Empty() {

        when(resolutionRepository.existsByPosIdAndStatus(1L, 1)).thenReturn(false);
        when(resolutionRepository.findByPosIdAndStatus(1L, 1)).thenReturn(Optional.empty());

        final List<ResponseResolutionsWithPosDTO> response =
                storeResolutionService.create(List.of(StoreResolutionDTOMock.build()));

        assertNotNull(response);
    }

    @Test
    void test_Update_Should_Success() throws BusinessRuleException {

        when(resolutionRepository.findById(1L)).thenReturn(Optional.of(ResolutionMock.build()));

        when(resolutionRepository.findAllByPosIdAndStatus(any(), any()))
                .thenReturn(Optional.of(List.of(ResolutionMock.build())));

        storeResolutionService = new StoreResolutionServiceImpl(resolutionRepository, new ObjectMapper());

        final String dataResponseResolution =
                storeResolutionService.update(StoreResolutionDTOMock.build(), 1L);

        assertEquals("{}", dataResponseResolution);
    }

    @Test
    void test_Update_Should_When_PosId_NotFound() {

        when(resolutionRepository.findById(1L)).thenReturn(Optional.empty());

        storeResolutionService = new StoreResolutionServiceImpl(resolutionRepository, new ObjectMapper());

        assertThrows(BusinessRuleException.class, () ->
                storeResolutionService.update(StoreResolutionDTOMock.build(), 1L));
    }

    @Test
    void test_IsActivateOrDeactivateByResolutionId_When_Status_Is_True_Should_Return_Resolution() {

        final Long resolutionId = 1L;

        when(resolutionRepository.findById(resolutionId)).thenReturn(Optional.of(ResolutionMock.build()));

        final Boolean response = storeResolutionService.isActivateOrDeactivateByResolutionId(
                ActivateOrDeactivateDTOMock.build(),
                resolutionId
        );

        assertTrue(response);
    }

    @Test
    void test_IsActivateOrDeactivateByResolutionId_When_Resolution_Not_Found_Should_Return_BusinessException() {

        final Long resolutionId = 1L;

        assertThrows(BusinessRuleException.class, () ->
                storeResolutionService.isActivateOrDeactivateByResolutionId(
                        ActivateOrDeactivateDTOMock.build(),
                        resolutionId
                ));
    }
}
