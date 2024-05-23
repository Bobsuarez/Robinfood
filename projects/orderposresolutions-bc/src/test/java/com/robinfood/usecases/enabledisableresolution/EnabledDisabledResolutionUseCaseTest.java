package com.robinfood.usecases.enabledisableresolution;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.robinfood.dtos.v1.request.EnabledDisabledResolutionDTO;
import com.robinfood.mocks.dtos.v1.request.EnableDisableResolutionDTOMock;
import com.robinfood.mocks.entities.PosResolutionEntityMock;
import com.robinfood.repository.posresolution.IPosResolutionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static com.robinfood.constants.GeneralConstants.DEFAULT_ONE_INTEGER;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

class EnabledDisabledResolutionUseCaseTest {

    @Mock
    private IPosResolutionRepository posResolutionRepository;

    @Mock
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void test_GetInstance_When_DataOK_Should_Successfully() {

        EnabledDisabledResolutionUseCase useCaseInstanceSecond = EnabledDisabledResolutionUseCase.getInstance();
        EnabledDisabledResolutionUseCase useCaseInstanceThird = EnabledDisabledResolutionUseCase.getInstance();

        assertSame(useCaseInstanceSecond, useCaseInstanceThird);
    }

    @Test
    void test_Invoke_When_isBodyTrue_Should_Successfully_Before_UpdateRowsCurrentZero() {

        final EnabledDisabledResolutionDTO alternateResolutionDTO = EnableDisableResolutionDTOMock.build();
        final String resolutionIds = DEFAULT_ONE_INTEGER.toString();

        when(posResolutionRepository.findByResolutionId(any())).thenReturn(PosResolutionEntityMock.build());

        when(posResolutionRepository.findAllByPosIdAndStatus(anyLong(), any()))
                .thenReturn(List.of(PosResolutionEntityMock.build()));

        EnabledDisabledResolutionUseCase alternateResolutionUseCase =
                new EnabledDisabledResolutionUseCase(posResolutionRepository, new ObjectMapper());

        String responseUseCase = alternateResolutionUseCase.invoke(alternateResolutionDTO, resolutionIds);
        assertEquals("{}", responseUseCase);
    }

    @Test
    void test_Invoke_When_isBodyFalse_Should_Successfully_UpdateRow() {

        final EnabledDisabledResolutionDTO alternateResolutionDTO = EnableDisableResolutionDTOMock.build();
        alternateResolutionDTO.setStatus(Boolean.FALSE);
        final String resolutionIds = DEFAULT_ONE_INTEGER.toString();

        when(posResolutionRepository.findByResolutionId(any())).thenReturn(PosResolutionEntityMock.build());

        EnabledDisabledResolutionUseCase alternateResolutionUseCase =
                new EnabledDisabledResolutionUseCase(posResolutionRepository, new ObjectMapper());

        String responseUseCase = alternateResolutionUseCase.invoke(alternateResolutionDTO, resolutionIds);
        assertEquals("{}", responseUseCase);
    }
}